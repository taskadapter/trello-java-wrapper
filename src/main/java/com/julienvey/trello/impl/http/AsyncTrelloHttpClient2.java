package com.julienvey.trello.impl.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.julienvey.trello.exception.TrelloHttpException;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.Response;

/**
 * TrelloHttpClient based on the org.asynchttpclient HTTP client, the successor to the ning async http client.
 */
public class AsyncTrelloHttpClient2 extends AbstractHttpClient {

    private final AsyncHttpClient asyncHttpClient;
    private final ObjectReader reader;
    private final ObjectWriter writer;

    public AsyncTrelloHttpClient2() {
        this(new DefaultAsyncHttpClient(), new ObjectMapper());
    }

    public AsyncTrelloHttpClient2(AsyncHttpClient asyncHttpClient, ObjectMapper mapper) {
        this(asyncHttpClient, mapper.reader(), mapper.writer());
    }

    public AsyncTrelloHttpClient2(AsyncHttpClient asyncHttpClient, ObjectReader reader, ObjectWriter writer) {
        this.asyncHttpClient = asyncHttpClient;
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public <T> T get(String url, final Class<T> objectClass, String... params) {
        Future<T> f;
        try {
            f = asyncHttpClient.prepareGet(expandUrl(url, params)).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return reader.forType(objectClass).readValue(response.getResponseBody());
                        }

                        @Override
                        public void onThrowable(Throwable t) {
                            throw new TrelloHttpException(t);
                        }
                    });
            return f.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, T object, final Class<T> objectClass, String... params) {
        Future<T> f;
        try {
            byte[] body = this.writer.writeValueAsBytes(object);
            f = asyncHttpClient.preparePost(expandUrl(url, params)).setBody(body).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return reader.forType(objectClass).readValue(response.getResponseBody());
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
        Future<URI> f;
        try {
            byte[] body = this.writer.writeValueAsBytes(object);
            f = asyncHttpClient.preparePost(expandUrl(url, params)).setBody(body).execute(
                    new AsyncCompletionHandler<URI>() {

                        @Override
                        public URI onCompleted(Response response) {
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
        Future<T> f;
        try {
            byte[] body = this.writer.writeValueAsBytes(object);
            f = asyncHttpClient.preparePut(expandUrl(url, params)).setBody(body).execute(
                    new AsyncCompletionHandler<T>() {

                        @Override
                        public T onCompleted(Response response) throws Exception {
                            return reader.forType(objectClass).readValue(response.getResponseBody());
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
