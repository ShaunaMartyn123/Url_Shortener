package com.example.url_shortener.controller;

import com.example.url_shortener.entity.Url;
import com.example.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin - fixes error
public class UrlController {

    @Autowired
    private UrlService urlService;

    @GetMapping("/urls")
    public List<Url> getAllUrls() {
        return urlService.getAllUrls();
    }

    @PostMapping("/shorten")
    public String createShortenedUrl(@RequestParam String originalUrl) {
        return urlService.createShortenedUrl(originalUrl);
    }
////////////////////////////////////////////////////////////////////////////////////////
    @DeleteMapping("/urls/{id}")
    public ResponseEntity<Void> deleteUrl(@PathVariable Long id) {
        boolean deleted = urlService.deleteUrlById(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
