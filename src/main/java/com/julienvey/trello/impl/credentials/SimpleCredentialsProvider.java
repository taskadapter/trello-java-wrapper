package com.julienvey.trello.impl.credentials;

import java.util.Objects;

import com.julienvey.trello.TrelloCredentialsProvider;

/**
 * Possibly simplest implementation that ever could exist. Simply holds provided credentials. There is no load process
 * at all.
 *
 * @author Edgar Asatryan
 */
public class SimpleCredentialsProvider implements TrelloCredentialsProvider {
    private final String applicationKey;
    private final String accessToken;

    public SimpleCredentialsProvider(String applicationKey, String accessToken) {
        this.applicationKey = Objects.requireNonNull(applicationKey, "applicationKey is null.");
        this.accessToken = Objects.requireNonNull(accessToken, "accessToken is null.");
    }

    @Override
    public void load() {
        // empty
    }

    @Override
    public String applicationKey() {
        return applicationKey;
    }

    @Override
    public String accessToken() {
        return accessToken;
    }
}
