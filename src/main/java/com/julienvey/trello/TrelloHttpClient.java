package com.julienvey.trello;

import java.io.File;
import java.net.URI;

import com.julienvey.trello.exception.TrelloHttpException;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient2;
import com.julienvey.trello.impl.http.JDKTrelloHttpClient;
import com.julienvey.trello.impl.http.OkHttpTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;

/**
 * The third-party HTTP clients wrapper. Allows to not hardy depend on particular HTTP clients.
 * <p>
 * Provides standard set of methods to perform {@code GET}, {@code POST}, {@code PUT}, {@code DELETE} method.
 * <h2>Exceptions</h2>
 * <p>
 * The implementation should not transparently rethrow any exception that comes with third-party HTTP client library.
 * Implementors can use wrapping exceptions like {@link NotFoundException}, {@link NotAuthorizedException} etc. If there
 * is no specific exception for particular case {@link TrelloHttpException} should be rethrown. It is very recommended
 * to embed native exception as cause, to provide more details about exception occurred.
 * <p>
 * The Trello API does support {@code gzip} response compression when user send the {@code Accept-Encoding: gzip}, so
 * implementor should at least consider (although many modern HTTP clients handles {@code gzip}
 * compression/decompression transparently) to send appropriate header and subsequently decompress bodies.
 * <p>
 * The most basic implementation is {@link JDKTrelloHttpClient}, it is default implementation for this interface and
 * does not require any additional dependencies.
 *
 * @see JDKTrelloHttpClient
 * @see ApacheHttpClient
 * @see RestTemplateHttpClient
 * @see OkHttpTrelloHttpClient
 * @see AsyncTrelloHttpClient
 * @see AsyncTrelloHttpClient2
 */
public interface TrelloHttpClient {
    /**
     * Performs HTTP {@code GET} request and converts response.
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url          The URL to make request to.
     * @param responseType The response object class object.
     * @param params       The URL query part or path parameters that should be expanded.
     * @param <T>          The response object type.
     *
     * @return The converted response.
     */
    <T> T get(String url, Class<T> responseType, String... params);

    /**
     * Performs HTTP {@code POST} request and converts response.
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url          The URL to make request to.
     * @param body         The object to be converted to its {@code JSON} representation and {@code POST}ed. Use {@code
     *                     null} to send request with empty body.
     * @param responseType The response object class object.
     * @param params       The URL query part or path parameters that should be expanded.
     * @param <T>          The response object type.
     *
     * @return The converted response.
     */
    <T> T postForObject(String url, Object body, Class<T> responseType, String... params);

    /**
     * Performs HTTP {@code POST} request and returns the value of the {@code Location} header.
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url    The URL to make request to.
     * @param body   The object to be converted to its {@code JSON} representation and {@code POST}ed. Use {@code null}
     *               to send request with empty body.
     * @param params The URL query part or path parameters that should be expanded.
     *
     * @return The {@code POST}ed resource location.
     */
    URI postForLocation(String url, Object body, String... params);

    /**
     * Performs HTTP {@code PUT} request and converts response.
     *
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url          The URL to make request to.
     * @param body         The object to be converted to its {@code JSON} representation and {@code POST}ed. Use {@code
     *                     null} send request with empty body.
     * @param responseType The response object class object.
     * @param params       The URL query part or path parameters that should be expanded.
     * @param <T>          The response object type.
     *
     * @return The converted response.
     */
    <T> T putForObject(String url, Object body, Class<T> responseType, String... params);

    /**
     * Performs HTTP {@code DELETE} request and converts response.
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url          The URL to make request to.
     * @param responseType The response object class object.
     * @param params       The URL query part or path parameters that should be expanded.
     * @param <T>          The response object type.
     *
     * @return The converted response.
     */
    <T> T delete(String url, Class<T> responseType, String... params);

    /**
     * Performs HTTP {@code POST} request and converts response. The {@code file} should be transmitted using {@code
     * multipart/form-data}.
     * <p>
     * This is optional method.
     * <p>
     * The template variables in {@code url} are expanded using the given {@code params}, if any.
     *
     * @param url          The URL to make request to.
     * @param file         The file to upload.
     * @param responseType The response object class object.
     * @param params       The URL query part or path parameters that should be expanded.
     * @param <T>          The response object type.
     *
     * @return The converted response.
     */
    default <T> T postFileForObject(String url, File file, Class<T> responseType, String... params) {
        throw new UnsupportedOperationException("Uploading files is not supported by default!");
    }
}
