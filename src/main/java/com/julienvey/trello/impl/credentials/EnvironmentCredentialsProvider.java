package com.julienvey.trello.impl.credentials;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Reads application applicationKey and accessToken from environment variables. Environment variable names are: {@code
 * TRELLO_APPLICATION_KEY} and {@code TRELLO_ACCESS_TOKEN}. Both variables should be strictly present to successfully
 * invoke constructor.
 *
 * @author Edgar Asatryan
 */
public class EnvironmentCredentialsProvider extends LazyTrelloCredentialsProviderSupport {
    private static final Logger log = LoggerFactory.getLogger(EnvironmentCredentialsProvider.class);

    private static final String APPLICATION_KEY_ENV = "TRELLO_APPLICATION_KEY";
    private static final String ACCESS_TOKEN_ENV = "TRELLO_ACCESS_TOKEN";

    public EnvironmentCredentialsProvider() {
        this(false);
    }

    /**
     * @param preload Whether eagerly load credentials or not.
     */
    public EnvironmentCredentialsProvider(boolean preload) {
        if (preload)
            load();
    }

    private static void checkEnv() {
        Map<String, String> env = System.getenv();

        boolean keyMissing = !env.containsKey(APPLICATION_KEY_ENV);
        boolean tokenMissing = !env.containsKey(ACCESS_TOKEN_ENV);

        // both are present
        if (!(keyMissing || tokenMissing)) {
            log.info("Both {} and {} are present.", APPLICATION_KEY_ENV, ACCESS_TOKEN_ENV);
            return;
        }

        StringBuilder errorMessage = new StringBuilder("The ");

        if (keyMissing) {
            errorMessage.append(APPLICATION_KEY_ENV);
        }

        if (tokenMissing) {
            if (keyMissing) errorMessage.append(" and ");
            errorMessage.append(ACCESS_TOKEN_ENV);
        }

        errorMessage.append(" environment variable(s) must be set.");

        throw new IllegalStateException(errorMessage.toString());
    }

    @Override
    public void load() {
        // already loaded
        if (isLoaded())
            return;

        checkEnv();

        // if we reached this point everything is ok
        applicationKey = System.getenv(APPLICATION_KEY_ENV);
        accessToken = System.getenv(ACCESS_TOKEN_ENV);
    }
}
