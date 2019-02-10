package com.julienvey.trello.impl.credentials;

/**
 * Reads application applicationKey and accessToken from the classpath resource file. The resource file must in a
 * ordinary {@code .properties} format. The default location of the file is {@code trello.properties}. The file location
 * can be configured via {@code -Dtrello.configPath} program argument.
 *
 * @author Edgar Asatryan
 */
public class ClasspathCredentialsProvider extends DelegatingCredentialsProvider {
    private static final String DEFAULT_TRELLO_PROPERTIES_NAME = "trello.properties";
    private static final String CONFIG_PATH = "trello.configPath";

    public ClasspathCredentialsProvider() {
        this(false);
    }

    /**
     * @param preload Whether eagerly load credentials or not.
     */
    public ClasspathCredentialsProvider(boolean preload) {
        super(PropertiesFileCredentialProvider.of(
                ClasspathCredentialsProvider.class.getClassLoader()
                        .getResource(System.getProperty(CONFIG_PATH, DEFAULT_TRELLO_PROPERTIES_NAME)),
                preload
                )
        );
    }
}
