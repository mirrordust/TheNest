package com.mirrordust.telcodata.storage;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

@Service
public class FileSystemStorageService implements StorageService {
    private final Path rootLocation;
    private final Path apkLocation;
    private final Set<String> ALLOWED_EXTENSIONS = new HashSet<String>() {{
        add("txt");
    }};

    @Autowired
    public FileSystemStorageService(StorageProperties properties) {
        this.rootLocation = Paths.get(properties.getLocation());
        this.apkLocation = Paths.get(properties.getAppLocation());
    }

    @Override
    public void store(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (!ALLOWED_EXTENSIONS.contains(FilenameUtils.getExtension(filename))) {
                throw new StorageException("Not allowed file type");
            }
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file " + filename);
            }
            if (filename.contains("..")) {
                // This is a security check
                throw new StorageException(
                        "Cannot store file with relative path outside current directory "
                                + filename);
            }
            Files.copy(file.getInputStream(), this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new StorageException("Failed to store file " + filename, e);
        }
    }

    @Override
    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read stored files", e);
        }
    }

    @Override
    public Stream<Path> loadApk() {
        try {
            return Files.walk(this.apkLocation, 1)
                    .filter(path -> !path.equals(this.apkLocation))
                    .map(this.apkLocation::relativize);
        } catch (IOException e) {
            throw new StorageException("Failed to read apk file", e);
        }
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Path loadApk(String apkname) {
        return apkLocation.resolve(apkname);
    }

    @Override
    public Resource loadAsResource(String filename, boolean isDataSet) {
        try {
            Path file;
            if (isDataSet) {
                file = load(filename);
            } else {
                file = loadApk(filename);
            }
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new StorageFileNotFoundException(
                        "Could not read file: " + filename);

            }
        } catch (MalformedURLException e) {
            throw new StorageFileNotFoundException("Could not read file: " + filename, e);
        }
    }

//    @Override
//    public void deleteAll() {
//        FileSystemUtils.deleteRecursively(rootLocation.toFile());
//    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
            Files.createDirectories(apkLocation);
        } catch (IOException e) {
            throw new StorageException("Could not initialize storage", e);
        }
    }
}
