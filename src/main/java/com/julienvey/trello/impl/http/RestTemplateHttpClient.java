package com.julienvey.trello.impl.http;

import java.net.URI;
import java.util.function.Supplier;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.julienvey.trello.NotAuthorizedException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.TrelloBadRequestException;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;

public class RestTemplateHttpClient implements TrelloHttpClient {
    private static final HttpEntity<?> EMPTY_BODY = null;

    private RestTemplate restTemplate;

    public RestTemplateHttpClient() {
        restTemplate = new RestTemplate();
    }

    private static RuntimeException translateException(HttpStatusCodeException e) {
        String response = e.getResponseBodyAsString();

        switch (e.getStatusCode()) {
            case BAD_REQUEST:
                return new TrelloBadRequestException(response, e);
            case NOT_FOUND:
                return new NotFoundException(response, e);
            case UNAUTHORIZED:
                return new NotAuthorizedException(response, e);
            default:
                return new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, Object body, Class<T> responseType, String... params) {
        return execute(() -> restTemplate.postForObject(url, body, responseType, (Object[]) params));
    }

    @Override
    public URI postForLocation(String url, Object body, String... params) {
        return execute(() -> restTemplate.postForLocation(url, body, (Object[]) params));
    }

    @Override
    public <T> T get(String url, Class<T> responseType, String... params) {
        return execute(() -> restTemplate.getForObject(url, responseType, (Object[]) params));
    }

    @Override
    public <T> T putForObject(String url, Object body, Class<T> objectClass, String... params) {
        return execute(() -> restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(body), objectClass, (Object[]) params)
                .getBody());
    }

    @Override
    public <T> T delete(String url, Class<T> responseType, String... params) {
        return execute(() -> restTemplate.exchange(url, HttpMethod.DELETE, EMPTY_BODY, responseType, (Object[]) params)
                .getBody());
    }

    private <T> T execute(Supplier<T> httpResult) {
        try {
            return httpResult.get();
        } catch (HttpStatusCodeException e) {
            throw translateException(e);
        }
    }
}
