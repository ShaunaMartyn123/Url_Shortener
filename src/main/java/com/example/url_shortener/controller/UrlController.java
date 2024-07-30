package com.example.url_shortener.controller;

/*import com.example.url_shortener.entity.Url;
import com.example.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
//@CrossOrigin(origins = "http://localhost:3000") // Allow requests from this origin - fixes error
@CrossOrigin(origins = "https://shaunamartyn123.github.io/Url_Shortener/") // Allows requests to the GitHub pages url
public class UrlController {

    @Autowired
    private UrlService urlService; //Inject an instance of UrlService

    // Handles HTTP GET requests for "/api/urls" and returns all URLs.
    @GetMapping("/urls")
    public List<Url> getAllUrls() { // Calls the service method to fetch all URLs from the database.
        return urlService.getAllUrls();
    }
////////////////////////////////////////////////////////////this is the working version for localhost
    // Handles HTTP POST requests for "/api/shorten" to create a shortened URL.
    @PostMapping("/shorten")
    public String createShortenedUrl(@RequestParam String originalUrl) {
        return urlService.createShortenedUrl(originalUrl);
    }
////////////////////////////////////////////////////////////////////////////////////////
    // Handles HTTP DELETE requests for "/api/urls/{id}" to delete a URL by its ID.
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
*/


import com.example.url_shortener.entity.Url;
import com.example.url_shortener.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "https://shaunamartyn123.github.io/Url_Shortener/") // Allow requests from GitHub Pages
public class UrlController {

    @Autowired
    private UrlService urlService; // Inject an instance of UrlService

    // Handles HTTP GET requests for "/api/urls" and returns all URLs.
    @GetMapping("/urls")
    public List<Url> getAllUrls() { // Calls the service method to fetch all URLs from the database.
        return urlService.getAllUrls();
    }

    // Handles HTTP POST requests for "/api/shorten" to create a shortened URL.
    @PostMapping("/shorten")
    public ResponseEntity<Map<String, String>> createShortenedUrl(@RequestParam String originalUrl) {
        try {
            String shortenedUrl = urlService.createShortenedUrl(originalUrl);
            Map<String, String> response = new HashMap<>();
            response.put("shortenedUrl", shortenedUrl);
            return ResponseEntity.ok(response); // Response entity returns a JSON response
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("error", "Failed to shorten the URL");
            return ResponseEntity.status(500).body(response);
        }
    }

    // Handles HTTP DELETE requests for "/api/urls/{id}" to delete a URL by its ID. - Unchanged
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

