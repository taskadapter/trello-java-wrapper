package com.julienvey.trello;

import java.net.URI;

public interface TrelloHttpClient {
    <T> T get(String url, Class<T> objectClass, String... params);

    <T> T postForObject(String url, Object object, Class<T> objectClass, String... params);

    URI postForLocation(String url, Object object, String... params);

    <T> T putForObject(String url, Object object, Class<T> objectClass, String... params);

    <T> T delete(String url, Class<T> responseType, String... params);
}
