package com.julienvey.trello.impl.http;

import static com.julienvey.trello.impl.http.UrlExpander.expandUrl;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.NotAuthorizedException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.TrelloBadRequestException;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * The {@code TrelloHttpClient} backed by {@link OkHttpClient}.
 *
 * @author Edgar Asatryan
 */
public class OkHttpTrelloHttpClient implements TrelloHttpClient {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final MediaType APPLICATION_JSON = MediaType.parse("application/json");
    private final OkHttpClient httpClient;
    private final ObjectMapper objectMapper;

    /**
     * Constructs new instance of {@code OkHttpTrelloHttpClient}.
     *
     * @param httpClient   The HTTP client to perform network calls.
     * @param objectMapper The request/response encoder/decoder.
     *
     * @throws NullPointerException When any argument is {@code null}.
     */
    public OkHttpTrelloHttpClient(OkHttpClient httpClient, ObjectMapper objectMapper) {
        this.httpClient = Objects.requireNonNull(httpClient);
        this.objectMapper = Objects.requireNonNull(objectMapper);
    }

    /**
     * Constructs new instance of {@code OkHttpTrelloHttpClient} with default values.
     */
    public OkHttpTrelloHttpClient() {
        this(new OkHttpClient(), new ObjectMapper());
    }

    @Override
    public <T> T get(String url, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(new Builder()
                .get()
                .url(expandUrl(url, params))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, Object body, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(new Builder()
                .post(requestBody(body))
                .url(expandUrl(url, params))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object body, String... params) {
        try (Response response = httpClient.newCall(new Builder()
                .post(requestBody(body))
                .url(expandUrl(url, params))
                .build())
                .execute()) {

            checkStatusCode(response);

            String location = Optional.ofNullable(response.header("Location"))
                    .orElseThrow(() -> new NullPointerException("Location header is not present!"));

            return URI.create(location);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T putForObject(String url, T body, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(new Builder()
                .put(requestBody(body))
                .url(expandUrl(url, params))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T delete(String url, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(new Builder()
                .delete()
                .url(expandUrl(url, params))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    private <T> T readResponse(Class<T> responseType, Response response) throws IOException {
        checkStatusCode(response);
        ResponseBody body = Optional.ofNullable(response.body()).orElseThrow(() -> new IllegalStateException("Cannot read response body because it is null."));

        try {
            return objectMapper.readValue(body.byteStream(), responseType);
        } catch (JsonProcessingException e) {
            throw new TrelloHttpException("Cannot parse Trello response. Expected to get a json string, but got: " + body.string());
        }
    }

    private void checkStatusCode(Response response) throws IOException {
        if (response.isSuccessful()) {
            return;
        }

        switch (response.code()) {
            case HttpURLConnection.HTTP_BAD_REQUEST:
                throw new TrelloBadRequestException(response.body() != null ? response.body().string() : "Bad Request");
            case HttpURLConnection.HTTP_UNAUTHORIZED:
                throw new NotAuthorizedException();
            case HttpURLConnection.HTTP_NOT_FOUND:
                throw new NotFoundException("Resource not found: " + response.request().url().uri());
        }
    }

    private RequestBody requestBody(Object object) throws JsonProcessingException {
        byte[] body = object == null ? EMPTY_BYTE_ARRAY : objectMapper.writeValueAsBytes(object);

        return RequestBody.create(APPLICATION_JSON, body);
    }
}
