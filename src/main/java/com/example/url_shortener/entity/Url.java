package com.example.url_shortener.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Url_shortener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String originalUrl;
    private String shortenedUrl;

    // Allows creating an instance with specified original and shortened URLs
    public Url_shortener (String originalUrl, String shortenedUrl) {
        this.originalUrl = originalUrl;
        this.shortenedUrl = shortenedUrl;
    }

    public Url_shortener() {
        // Empty constructor 
    }

    // Getters and Setters - Methods to Retrieve the original values of the fields and Set up or Update the new values
    public Long getId() {
        return id;
    }

    // Setter for the ID
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for the original ID
    public String getOriginalUrl() {
        return originalUrl;
    }

    // Setter for the original URL
    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    // Getter for the original URL
    public String getShortenedUrl() {
        return shortenedUrl;
    }

    // Setter for the shortened URL
    public void setShortenedUrl(String shortenedUrl) {
        this.shortenedUrl = shortenedUrl;
    }
}