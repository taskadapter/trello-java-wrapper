package com.julienvey.trello;

public interface TrelloHttpClient {

    public <T> T get(String url, Class<T> objectClass, String... params);

    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params);

    public void postForLocation(String url, Object object, String... params);
}
