package com.julienvey.trello.impl.credentials;

import com.julienvey.trello.TrelloCredentialsProvider;

/**
 * The convenient base class for implementation that lazily reads credentials into strings.
 *
 * @author Edgar Asatryan
 */
abstract class LazyTrelloCredentialsProviderSupport implements TrelloCredentialsProvider {
    String applicationKey;
    String accessToken;

    boolean isLoaded() {
        return applicationKey != null && accessToken != null;
    }

    void tryLoad() {
        if (!isLoaded()) {
            load();
        }
    }

    void append(String application, String token, StringBuilder builder) {

        if (applicationKey == null)
            builder.append(application);

        if (accessToken == null) {
            if (application == null) builder.append(", ");
            builder.append(token);
        }
    }

    @Override
    public String applicationKey() {
        tryLoad();
        return applicationKey;
    }

    @Override
    public String accessToken() {
        tryLoad();
        return accessToken;
    }
}
