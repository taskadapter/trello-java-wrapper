package com.julienvey.trello.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class AsyncTrelloHttpClient implements TrelloHttpClient {

    private AsyncHttpClient asyncHttpClient;
    private ObjectMapper mapper;

    public AsyncTrelloHttpClient() {
        this(new AsyncHttpClient());
    }

    public AsyncTrelloHttpClient(AsyncHttpClient asyncHttpClient) {
        this.asyncHttpClient = asyncHttpClient;
        this.mapper = new ObjectMapper();
    }

    @Override
    public <T> T get(String url, final Class<T> objectClass, String... params) {
        try {
            Future<T> f = asyncHttpClient.prepareGet(UrlExpander.expandUrl(url, params)).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return mapper.readValue(response.getResponseBody(), objectClass);
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, Object object, final Class<T> objectClass, String... params) {
        try {
            byte[] body = this.mapper.writeValueAsBytes(object);
            Future<T> f = asyncHttpClient.preparePost(UrlExpander.expandUrl(url, params))
                    .setHeader("Content-Type", "application/json")
                    .setBody(body).execute(new AsyncCompletionHandler<T>() {
                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return mapper.readValue(response.getResponseBody(), objectClass);
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object object, String... params) {
        try {
            byte[] body = this.mapper.writeValueAsBytes(object);
            Future<URI> f = asyncHttpClient.preparePost(UrlExpander.expandUrl(url, params)).setBody(body).execute(
                    new AsyncCompletionHandler<URI>() {

                        @Override
                        public URI onCompleted(Response response) throws Exception {
                            String location = response.getHeader("Location");
                            if (location != null) {
                                return URI.create(location);
                            } else {
                                throw new TrelloHttpException("Location header not set");
                            }
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T putForObject(String url, T object, final Class<T> objectClass, String... params) {
        try {
            byte[] body = this.mapper.writeValueAsBytes(object);
            Future<T> f = asyncHttpClient.preparePut(UrlExpander.expandUrl(url, params)).setBody(body).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return mapper.readValue(response.getResponseBody(), objectClass);
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T delete(String url, final Class<T> responseType, String... params) {
        try {
            Future<T> f = asyncHttpClient.prepareDelete(UrlExpander.expandUrl(url, params)).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return mapper.readValue(response.getResponseBody(), responseType);
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (IOException | InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }
}
