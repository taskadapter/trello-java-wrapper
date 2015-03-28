package com.julienvey.trello;

import java.net.URI;

public interface TrelloHttpClient {
    // TODO add IO exception

    public <T> T get(String url, Class<T> objectClass, String... params);

    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params);

    public URI postForLocation(String url, Object object, String... params);

    public <T> T putForObject(String url, T object, Class<T> objectClass, String... params);
}
