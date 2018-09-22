package com.julienvey.trello.impl.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.NotAuthorizedException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import com.julienvey.trello.TrelloBadRequestException;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.InputStream;

public class ApacheHttpClient implements TrelloHttpClient {

    private DefaultHttpClient httpClient;
    private ObjectMapper mapper;

    public ApacheHttpClient() {
        this(new DefaultHttpClient());
    }

    public ApacheHttpClient(DefaultHttpClient httpClient) {
        this.httpClient = httpClient;
        this.mapper = new ObjectMapper();
    }

    @Override
    public <T> T get(String url, Class<T> objectClass, String... params) {
        HttpGet httpGet = new HttpGet(UrlExpander.expandUrl(url, params));
        return getEntityAndReleaseConnection(objectClass, httpGet);
    }

    @Override
    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
        HttpPost httpPost = new HttpPost(UrlExpander.expandUrl(url, params));

        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(object), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            return getEntityAndReleaseConnection(objectClass, httpPost);
        } catch (JsonProcessingException e) {
            // TODO : custom exception
            throw new RuntimeException(e);
        }
    }

    public <T> T postFileForObject(String url, File file, Class<T> objectClass, String... params) {
        HttpPost httpPost = new HttpPost(UrlExpander.expandUrl(url, params));

        MultipartEntity entity = new MultipartEntity();
        entity.addPart("file", new FileBody(file));
        try {
            entity.addPart("filename", new StringBody(file.getName()));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }

        httpPost.setEntity(entity);

        return getEntityAndReleaseConnection(objectClass, httpPost);
    }

    @Override
    public <T> T putForObject(String url, T object, Class<T> objectClass, String... params) {
        HttpPut put = new HttpPut(UrlExpander.expandUrl(url, params));
        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(object), ContentType.APPLICATION_JSON);
            put.setEntity(entity);

            return getEntityAndReleaseConnection(objectClass, put);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object object, String... params) {
        HttpPost httpPost = new HttpPost(UrlExpander.expandUrl(url, params));

        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(object), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            HttpResponse httpResponse = this.httpClient.execute(httpPost);

            Header location = httpResponse.getFirstHeader("Location");
            if (location != null) {
                return URI.create(location.getValue());
            } else {
                // TODO : error
                throw new NullPointerException();
            }
        } catch (JsonProcessingException e) {
            // TODO : custom exception
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        } finally {
            httpPost.releaseConnection();
        }
    }

    private <T> T getEntityAndReleaseConnection(Class<T> objectClass, HttpRequestBase httpRequest) {
        try {
            HttpResponse httpResponse = this.httpClient.execute(httpRequest);

            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpEntity != null) {
                String body = toString(httpEntity.getContent());
                if (httpResponse.getStatusLine().getStatusCode() == 400) {
                    throw new TrelloBadRequestException(body);
                }
                if (httpResponse.getStatusLine().getStatusCode() == 401) {
                    throw new NotAuthorizedException();
                }
                if (httpResponse.getStatusLine().getStatusCode() == 404) {
                    throw new NotFoundException("Resource not found: " + httpRequest.getURI());
                }
                try {
                    return this.mapper.readValue(body, objectClass);
                } catch (JsonProcessingException e) {
                    throw new TrelloHttpException("Cannot parse Trello response. Expected to get a json string, but got: " + body);
                }
            } else {
                throw new TrelloHttpException("http entity returned by Trello is null");
            }
        } catch (TrelloHttpException e) {
            throw e;
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        } finally {
            httpRequest.releaseConnection();
        }
    }

    private static String toString(InputStream stream) {
        return new java.util.Scanner(stream).useDelimiter("\\A").next();
    }
}
