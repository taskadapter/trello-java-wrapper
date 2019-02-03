package com.julienvey.trello.impl.http;

import static com.julienvey.trello.impl.http.UrlExpander.expandUrl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.GzipSource;
import okio.Okio;

/**
 * The {@code TrelloHttpClient} backed by {@link OkHttpClient}.
 *
 * @author Edgar Asatryan
 */
public class OkHttpTrelloHttpClient implements TrelloHttpClient {
    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final MediaType APPLICATION_JSON = MediaType.parse("application/json; charset=utf-8");
    private static final MediaType APPLICATION_OCTET_STREAM = MediaType.parse("application/octet-stream");
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

    private static Builder requestBuilder(String url, String[] params) {
        return new Builder()
                .header("Accept", APPLICATION_JSON.toString())
                .url(expandUrl(url, params));
    }

    @Override
    public <T> T get(String url, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(requestBuilder(url, params)
                .get()
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postForObject(String url, Object body, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(requestBuilder(url, params)
                .post(requestBody(body))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object body, String... params) {
        try (Response response = httpClient.newCall(requestBuilder(url, params)
                .post(requestBody(body))
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
    public <T> T putForObject(String url, Object body, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(requestBuilder(url, params)
                .put(requestBody(body))
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T delete(String url, Class<T> responseType, String... params) {
        try (Response response = httpClient.newCall(requestBuilder(url, params)
                .delete()
                .build())
                .execute()) {

            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    @Override
    public <T> T postFileForObject(String url, File file, Class<T> responseType, String... params) {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), RequestBody.create(APPLICATION_OCTET_STREAM, file))
                .build();

        try (Response response = httpClient.newCall(new Builder()
                .url(expandUrl(url, params))
                .post(requestBody)
                .build())
                .execute()) {
            return readResponse(responseType, response);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        }
    }

    private <T> T readResponse(Class<T> responseType, Response response) throws IOException {
        checkStatusCode(response);
        ResponseBody body = Optional.ofNullable(response.body())
                .orElseThrow(() -> new IllegalStateException("Cannot read response body because it is null."));

        try {
            return objectMapper.readValue(body.byteStream(), responseType);
        } catch (JsonProcessingException e) {
            throw new TrelloHttpException("Cannot parse Trello response.", e);
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
