package com.julienvey.trello.impl.http;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.zip.GZIPInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.NotAuthorizedException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.TrelloBadRequestException;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.utils.IOUtils;

/**
 * Simple {@code TrelloHttpClient} based on JDK's standard {@code URLConnection}. The default implementation of http
 * client.
 *
 * @author Edgar Asatryan
 */
public class JDKTrelloHttpClient implements TrelloHttpClient {
    private static final Logger log = LoggerFactory.getLogger(TrelloImpl.class);
    private static final String APPLICATION_JSON = "application/json;charset=utf-8";
    private static final String LF = "\r\n";
    private static final String GZIP = "gzip";
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
        // Trello API does support compression
        conn.setRequestProperty("Accept-Encoding", GZIP);
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

    @Override
    public <T> T postFileForObject(String url, File file, Class<T> responseType, String... params) {
        try (InputStream in = new BufferedInputStream(Files.newInputStream(file.toPath()))) {
            HttpURLConnection conn = openConnection(url, params, "POST");
            String boundary = "----" + UUID.randomUUID();
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            try (OutputStream requestStream = conn.getOutputStream();
                 Writer writer = new OutputStreamWriter(requestStream)) {
                writer.append(LF).append("--").append(boundary).append(LF)
                        .append("Content-Disposition: form-data; name=\"file\"; filename=\"" + file.getName() + "\"").append(LF)
                        .append("Content-Type: application/octet-stream").append(LF)
                        .append("Content-Transfer-Encoding: binary").append(LF)
                        .append(LF);

                writer.flush();

                IOUtils.copyTo(in, requestStream);

                writer.append(LF).append("--").append(boundary).append("--").append(LF)
                        .flush();
            }

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

        try (InputStream responseStream = responseStream(conn)) {
            return objectMapper.readValue(responseStream, responseType);
        } catch (JsonProcessingException e) {
            throw new TrelloHttpException("Cannot parse Trello response. Expected to get a json string, but got: " + IOUtils.toString(responseStream(conn)));
        }
    }

    private InputStream responseStream(HttpURLConnection conn) throws IOException {
        // checking encoding to provide appropriate input stream
        boolean isCompressed = GZIP.equalsIgnoreCase(conn.getHeaderField("Content-Encoding"));

        if (isCompressed) {
            log.debug("Using GZIPInputStream for {}", conn.getURL());
            return new GZIPInputStream(conn.getInputStream());
        }

        return conn.getInputStream();
    }

    private void writeRequest(Object object, HttpURLConnection conn) throws IOException {
        try (OutputStream requestStream = conn.getOutputStream()) {
            objectMapper.writeValue(requestStream, object);
        }
    }
}
