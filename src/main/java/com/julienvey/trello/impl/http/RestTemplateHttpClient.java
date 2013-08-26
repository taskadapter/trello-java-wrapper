package com.julienvey.trello.impl.http;

import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestTemplateHttpClient implements TrelloHttpClient {

    private RestTemplate restTemplate;

    public RestTemplateHttpClient() {
        restTemplate = new RestTemplate();
    }

    @Override
    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
        try {
            return restTemplate.postForObject(url, object, objectClass, params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }

    }

    @Override
    public URI postForLocation(String url, Object object, String... params) {
        try {
            return restTemplate.postForLocation(url, object, params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T get(String url, Class<T> objectClass, String... params) {
        try {
            return restTemplate.getForObject(url, objectClass, params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }
}
