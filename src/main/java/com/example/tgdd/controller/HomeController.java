package com.example.tgdd.controller;

import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<Resource> index() {
        try {
            Path path = Paths.get("index.html");
            if (!Files.exists(path)) {
                return ResponseEntity.status(404).body(null);
            }
            Resource resource = new FileUrlResource(path.toAbsolutePath().toString());
            return ResponseEntity.ok()
                    .contentType(MediaType.TEXT_HTML)
                    .body(resource);
        } catch (Exception e) {
            return ResponseEntity.status(500).build();
        }
    }

    @GetMapping("/api/health")
    @ResponseBody
    public String health() {
        return "OK - Server is running. Workdir: " + System.getProperty("user.dir");
    }
}
