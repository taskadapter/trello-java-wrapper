package com.julienvey.trello.impl.http;

import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class RestTemplateHttpClient implements TrelloHttpClient {
    private static final HttpEntity<?> EMPTY_BODY = null;

    private RestTemplate restTemplate;

    public RestTemplateHttpClient() {
        restTemplate = new RestTemplate();
    }

    @Override
    public <T> T postForObject(String url, Object object, Class<T> objectClass, String... params) {
        try {
            return restTemplate.postForObject(url, object, objectClass, (Object[]) params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object object, String... params) {
        try {
            return restTemplate.postForLocation(url, object, (Object[]) params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T get(String url, Class<T> objectClass, String... params) {
        try {
            return restTemplate.getForObject(url, objectClass, (Object[]) params);
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T putForObject(String url, T object, Class<T> objectClass, String... params) {
        try {
            return restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(object), objectClass, (Object[]) params)
                    .getBody();
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T delete(String url, Class<T> responseType, String... params) {
        try {
            return restTemplate.exchange(url, HttpMethod.DELETE, EMPTY_BODY, responseType, (Object[]) params)
                    .getBody();
        } catch (RestClientException e) {
            throw new TrelloHttpException(e);
        }
    }
}
