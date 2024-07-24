package com.example.url_shortener.service;

import com.example.url_shortener.entity.Url;
import com.example.url_shortener.repository.UrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UrlService {

    @Autowired
    private UrlRepository urlRepository;

    @Value("${url.shortener.base-url}") // Get base URL from application.properties
    private String baseUrl;

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    // Create a shortened URL for the original URL
    public String createShortenedUrl(String originalUrl) {
        String filteredUrl = filterUrl(originalUrl);// Filters the original URL to keep only letters
        String shortenedUrl = generateUniqueShortUrl(filteredUrl);// Generates a unique short URL based on the filtered URL

        // Creates a new Url entity and sets its original and shortened URLs
        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);
        urlRepository.save(url); // Saves the Url entity to the repository

        return shortenedUrl;
    }

    // Filters the URL to include only letter characters
    private String filterUrl(String url) {
        StringBuilder res = new StringBuilder();
        for (char c : url.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(c);
            }
        }
        return res.toString();
    }

    // Generates a unique short URL
    private String generateUniqueShortUrl(String filteredUrl) {
        List<Url> allUrls = getAllUrls(); // Receives all URLs from the repository
        Set<String> uniqueUrls = new HashSet<>();
        allUrls.forEach(u -> uniqueUrls.add(u.getShortenedUrl()));

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        // Keeps generating short URls until a unique one is found
        while (true) {
            for (int i = 0; i < 6; i++) {
                sb.append(filteredUrl.charAt(random.nextInt(filteredUrl.length())));
            }
            String shortUrl = sb.toString();
            // Check if the generated URL is unique
            if (!uniqueUrls.contains(shortUrl)) {
                return baseUrl + shortUrl;
            } else {
                sb.setLength(0); // Resets the StringBuilder if the URL is not unique
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////
    //Deletes a URL by its ID
    public boolean deleteUrlById(Long id) {
        Optional<Url> url = urlRepository.findById(id); // Find the URL by its ID
        // if URL is found it deletes it and returns true
        if (url.isPresent()) {
            urlRepository.deleteById(id);
            return true;
        } else {
            // If the URl is not found it returns false
            return false;
        }
    }
}
