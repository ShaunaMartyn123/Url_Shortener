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

    @Value("${url.shortener.base-url}") // Inject base URL from application.properties
    private String baseUrl;

    public List<Url> getAllUrls() {
        return urlRepository.findAll();
    }

    public String createShortenedUrl(String originalUrl) {
        String filteredUrl = filterUrl(originalUrl);
        String shortenedUrl = generateUniqueShortUrl(filteredUrl);

        Url url = new Url();
        url.setOriginalUrl(originalUrl);
        url.setShortenedUrl(shortenedUrl);
        urlRepository.save(url);

        return shortenedUrl;
    }

    private String filterUrl(String url) {
        StringBuilder res = new StringBuilder();
        for (char c : url.toCharArray()) {
            if (Character.isLetter(c)) {
                res.append(c);
            }
        }
        return res.toString();
    }

    private String generateUniqueShortUrl(String filteredUrl) {
        List<Url> allUrls = getAllUrls();
        Set<String> uniqueUrls = new HashSet<>();
        allUrls.forEach(u -> uniqueUrls.add(u.getShortenedUrl()));

        StringBuilder sb = new StringBuilder();
        Random random = new Random();

        while (true) {
            for (int i = 0; i < 6; i++) {
                sb.append(filteredUrl.charAt(random.nextInt(filteredUrl.length())));
            }
            String shortUrl = sb.toString();
            if (!uniqueUrls.contains(shortUrl)) {
                return baseUrl + shortUrl;
            } else {
                sb.setLength(0);
            }
        }
    }
//////////////////////////////////////////////////////////////////////////////////
    public boolean deleteUrlById(Long id) {
        Optional<Url> url = urlRepository.findById(id);
        if (url.isPresent()) {
            urlRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }
}
