package com.julienvey.trello.impl.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.NotAuthorizedException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.TrelloBadRequestException;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import com.julienvey.trello.utils.IOUtils;

/**
 * Simple {@code TrelloHttpClient} based on JDK's standard {@code URLConnection}. The default implementation of http
 * client.
 *
 * @author Edgar Asatryan
 */
public class JDKTrelloHttpClient implements TrelloHttpClient {
    private static final String APPLICATION_JSON = "application/json;charset=utf-8";
    private final ObjectMapper objectMapper;

    public JDKTrelloHttpClient(ObjectMapper objectMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    public JDKTrelloHttpClient() {
        this(new ObjectMapper());
    }

    @Override
    public <T> T get(String url, Class<T> responseType, String... params) {
        try {
            HttpURLConnection conn = openConnection(url, params, "GET");

            return readResponse(responseType, conn);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, Object body, Class<T> responseType, String... params) {
        try {
            HttpURLConnection conn = openConnection(url, params, "POST");
            writeRequest(body, conn);

            return readResponse(responseType, conn);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object body, String... params) {
        try {
            HttpURLConnection conn = openConnection(url, params, "POST");
            writeRequest(body, conn);

            String location = Optional.ofNullable(conn.getHeaderField("Location"))
                    .orElseThrow(() -> new NullPointerException("Location header is not present!"));

            return URI.create(location);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    private HttpURLConnection openConnection(String url, String[] params, String httpMethod) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(UrlExpander.expandUrl(url, params)).openConnection();
        conn.setRequestProperty("Accept", APPLICATION_JSON);
        conn.setRequestProperty("Content-Type", APPLICATION_JSON);
        conn.setRequestMethod(httpMethod);

        if (httpMethod.equals("POST") || httpMethod.equals("PUT")) {
            conn.setDoOutput(true);
        }

        return conn;
    }

    @Override
    public <T> T putForObject(String url, T body, Class<T> responseType, String... params) {
        try {
            HttpURLConnection conn = openConnection(url, params, "PUT");
            writeRequest(body, conn);

            return readResponse(responseType, conn);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T delete(String url, Class<T> responseType, String... params) {
        try {
            HttpURLConnection conn = openConnection(url, params, "DELETE");

            return readResponse(responseType, conn);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    private void checkStatusCode(HttpURLConnection conn) throws IOException {
        switch (conn.getResponseCode()) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new TrelloBadRequestException(IOUtils.toString(conn.getErrorStream()));
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new NotAuthorizedException();
            case HttpURLConnection.HTTP_NOT_FOUND:
                throw new NotFoundException("Resource not found: " + conn.getURL());
        }
    }

    private <T> T readResponse(Class<T> responseType, HttpURLConnection conn) throws IOException {
        checkStatusCode(conn);

        try (InputStream responseStream = conn.getInputStream()) {
            return objectMapper.readValue(responseStream, responseType);
        } catch (JsonProcessingException e) {
            throw new TrelloHttpException("Cannot parse Trello response. Expected to get a json string, but got: " + IOUtils.toString(conn.getErrorStream()));
        }
    }

    private void writeRequest(Object object, HttpURLConnection conn) throws IOException {
        try (OutputStream requestStream = conn.getOutputStream()) {
            objectMapper.writeValue(requestStream, object);
        }
    }
}
