package com.urlshortener.urlShortener.service;

import com.urlshortener.urlShortener.model.Url;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@ConfigurationProperties(value = "url-shortener.service", ignoreUnknownFields = false)
public class UrlShortenerService {

    private String API_HOST; // comes from application.properties
    private final String SHORTEN_PATH = "/api/v1/shorten";

    private final RestTemplate restTemplate;

    public UrlShortenerService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public Url shortenUrl(Url url){
        String requestAddress = API_HOST + SHORTEN_PATH;
        return restTemplate.postForObject(requestAddress, url, Url.class);
    }

    public void setAPI_HOST(String API_HOST) {
        this.API_HOST = API_HOST;
    }

}
