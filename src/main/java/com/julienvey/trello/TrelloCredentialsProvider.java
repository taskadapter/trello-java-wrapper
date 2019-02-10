package com.julienvey.trello;

/**
 * The Trello API application key and access token provider. Does help to externalize security sensitive information.
 * <p>
 * There is various ways to provide Trello API credentials.
 *
 * @author Edgar Asatryan
 * @see com.julienvey.trello.impl.credentials.ClasspathCredentialsProvider
 * @see com.julienvey.trello.impl.credentials.CommandLineArgumentCredentialsProvider
 * @see com.julienvey.trello.impl.credentials.CompositeCredentialsProvider
 * @see com.julienvey.trello.impl.credentials.DelegatingCredentialsProvider
 * @see com.julienvey.trello.impl.credentials.EnvironmentCredentialsProvider
 * @see com.julienvey.trello.impl.credentials.PropertiesFileCredentialProvider
 * @see com.julienvey.trello.impl.credentials.SimpleCredentialsProvider
 */
public interface TrelloCredentialsProvider {
    /**
     * Actually reads credentials from underlying resources. Subsequent invocations may reload credentials or do
     * nothing.
     * <p>
     * After successful method invocation {@link TrelloCredentialsProvider#accessToken()} and {@link
     * TrelloCredentialsProvider#applicationKey()} should return non null values or throw an {@link
     * IllegalStateException} exception.
     */
    void load();

    /**
     * Gets the Trello application key.
     *
     * @return The Trello application key aka API Key.
     */
    String applicationKey();

    /**
     * Gets the Trello OAuth access token.
     *
     * @return The Trello OAuth access token.
     */
    String accessToken();
}
