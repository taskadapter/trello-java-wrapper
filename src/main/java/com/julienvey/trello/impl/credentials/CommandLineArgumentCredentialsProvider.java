package com.julienvey.trello.impl.credentials;

/**
 * Retrieves application key and access token from command line arguments. The credentials can be passed like: {@code
 * java -jar app.jar -Dtrello.applicationKey=myApplicationKey -Dtrello.accessToken=myAccessToken}.
 *
 * @author Edgar Asatryan
 */
public class CommandLineArgumentCredentialsProvider extends LazyTrelloCredentialsProviderSupport {
    private static final String APPLICATION_KEY_ARG = "trello.applicationKey";
    private static final String ACCESS_TOKEN_ARG = "trello.accessToken";

    @Override
    public void load() {
        if (isLoaded())
            return;

        applicationKey = System.getProperty(APPLICATION_KEY_ARG);
        accessToken = System.getProperty(ACCESS_TOKEN_ARG);

        if (applicationKey == null || accessToken == null) {
            StringBuilder errorMessage = new StringBuilder("Following required argument(s) missing: ");

            append(APPLICATION_KEY_ARG, ACCESS_TOKEN_ARG, errorMessage);

            throw new IllegalStateException(errorMessage.toString());
        }
    }
}
