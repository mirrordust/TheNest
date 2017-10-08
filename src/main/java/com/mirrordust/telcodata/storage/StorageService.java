package com.mirrordust.telcodata.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

public interface StorageService {
    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Stream<Path> loadApk();

    Path load(String filename);

    Path loadApk(String apkname);

    Resource loadAsResource(String filename, boolean isDataSet);
//    void deleteAll();
}
