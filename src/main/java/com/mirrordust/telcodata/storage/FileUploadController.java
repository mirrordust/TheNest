package com.mirrordust.telcodata.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

@Controller
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/")
    public String listUploadedFiles(Model model) throws IOException {

        model.addAttribute("files", storageService.loadAll().map(
                path -> new FileDesc(path.getFileName().toString(),
                        MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveFile", path.getFileName().toString()).build().toString(),
                        MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "viewFile", path.getFileName().toString()).build().toString()))
                .collect(Collectors.toList()));

        return "uploadForm";
    }

    @GetMapping("/downloads")
    public String showApp(Model model) throws IOException {
        model.addAttribute("apks", storageService.loadApk().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "serveApk", path.getFileName().toString()).build().toString())
                .collect(Collectors.toList()));

        return "app";
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = storageService.loadAsResource(filename, true);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/files/view/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> viewFile(@PathVariable String filename) {
        Resource file = storageService.loadAsResource(filename, true);
        return ResponseEntity.ok().header(
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/apk/{apkname:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveApk(@PathVariable String apkname) {
        Resource apk = storageService.loadAsResource(apkname, false);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + apk.getFilename() + "\"").body(apk);
    }

    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        storageService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");

        return "redirect:/";

    }

    @PostMapping("/uploads")
    @ResponseBody
    public UploadResponse fileUploadAPI(@RequestParam("file") MultipartFile file) {
        try {
            storageService.store(file);
            return new UploadResponse(
                    true, "200", "successfully uploaded " + file.getOriginalFilename());
        } catch (Exception e) {
            return new UploadResponse(false, "null", "failed");
        }
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }
}
