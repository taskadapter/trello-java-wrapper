package com.julienvey.trello.impl.credentials;

import java.util.Objects;

import com.julienvey.trello.TrelloCredentialsProvider;

/**
 * Allows to wrap {@code TrelloCredentialsProvider} in each other.
 *
 * @author Edgar Asatryan
 */
public class DelegatingCredentialsProvider implements TrelloCredentialsProvider {
    private final TrelloCredentialsProvider delegate;

    public DelegatingCredentialsProvider(TrelloCredentialsProvider delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public void load() {
        delegate.load();
    }

    @Override
    public String applicationKey() {
        return delegate.applicationKey();
    }

    @Override
    public String accessToken() {
        return delegate.accessToken();
    }
}
