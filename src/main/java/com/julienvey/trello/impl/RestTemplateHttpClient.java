package com.julienvey.trello.impl;

import com.julienvey.trello.TrelloHttpClient;
import org.springframework.web.client.RestTemplate;

public class RestTemplateHttpClient implements TrelloHttpClient {

    private RestTemplate restTemplate;

    public RestTemplateHttpClient() {
        // TODO catch ClassNotFound
        restTemplate = new RestTemplate();
    }

    @Override
    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
        return restTemplate.postForObject(url, object, objectClass, params);
    }

    @Override
    public void postForLocation(String url, Object object, String... params) {
        restTemplate.postForLocation(url, object, params);
    }

    @Override
    public <T> T get(String url, Class<T> objectClass, String... params) {
        return restTemplate.getForObject(url, objectClass, params);
    }
}
