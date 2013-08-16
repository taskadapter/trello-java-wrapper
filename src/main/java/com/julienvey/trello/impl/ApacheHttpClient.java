package com.julienvey.trello.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.exception.TrelloHttpException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ApacheHttpClient implements TrelloHttpClient {

    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    private DefaultHttpClient httpClient;
    private ObjectMapper mapper;

    public ApacheHttpClient() {
        this.httpClient = new DefaultHttpClient();
        this.mapper = new ObjectMapper();
    }

    @Override
    public <T> T get(String url, Class<T> objectClass, String... params) {
        HttpGet httpGet = new HttpGet(expandUrl(url, params));
        return getEntityAndReleaseConnection(objectClass, httpGet);
    }

    @Override
    public <T> T postForObject(String url, T object, Class<T> objectClass, String... params) {
        HttpPost httpPost = new HttpPost(expandUrl(url, params));

        try {
            HttpEntity entity = new ByteArrayEntity(this.mapper.writeValueAsBytes(object), ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);

            return getEntityAndReleaseConnection(objectClass, httpPost);
        } catch (JsonProcessingException e) {
            // TODO : custom exception
            throw new RuntimeException(e);
        }
    }

    @Override
    public URI postForLocation(String url, Object object, String... params) {
        HttpPost httpPost = new HttpPost(expandUrl(url, params));

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
                return this.mapper.readValue(httpEntity.getContent(), objectClass);
            } else {
                // TODO : error
                throw new NullPointerException();
            }
        } catch (IOException e) {
            throw new TrelloHttpException(e);
        } finally {
            httpRequest.releaseConnection();
        }
    }

    private String expandUrl(String url, String... params) {
        if (url == null) {
            return null;
        }
        if (url.indexOf('{') == -1) {
            return url;
        }
        Matcher matcher = NAMES_PATTERN.matcher(url);
        StringBuffer sb = new StringBuffer();

        int variable = 0;
        while (matcher.find()) {
            String variableValue = params[variable];
            String replacement = Matcher.quoteReplacement(variableValue);
            matcher.appendReplacement(sb, replacement);
            variable++;
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
