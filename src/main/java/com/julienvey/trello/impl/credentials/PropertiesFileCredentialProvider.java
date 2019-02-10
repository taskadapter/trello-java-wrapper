package com.julienvey.trello.impl.credentials;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import com.julienvey.trello.TrelloCredentialsProvider;

/**
 * Reads application applicationKey and accessToken from the properties file. The user can create property file named
 * {@code .trello.properties} in home directory it'll would be picked up by invoking {@link
 * PropertiesFileCredentialProvider#ofDefault()}. This location can be configured via {@code
 * -Dtrello.configPath=/path/to/file}.
 * <p>
 * Here is a typical example who file should be look like:
 * <pre>
 * <code>
 * trello.applicationKey=yourApplicationKey
 * trello.accessToken=yourAccessToken
 * </code>
 *
 * @author Edgar Asatryan
 */
public class PropertiesFileCredentialProvider extends LazyTrelloCredentialsProviderSupport implements TrelloCredentialsProvider {
    private static final String APPLICATION_KEY_PROPERTY = "trello.applicationKey";
    private static final String ACCESS_TOKEN_PROPERTY = "trello.accessToken";
    private static final Path DEFAULT_LOCATION = Paths.get(System.getProperty("user.home"), ".trello.properties");

    private final File file;

    public PropertiesFileCredentialProvider(File file) {
        this(file, false);
    }

    /**
     * @param file
     * @param preload Whether eagerly load credentials or not.
     */
    public PropertiesFileCredentialProvider(File file, boolean preload) {
        this.file = Objects.requireNonNull(file);
        if (preload)
            load();
    }

    public static PropertiesFileCredentialProvider ofDefault() {
        return of(Optional.ofNullable(System.getProperty("trello.configPath"))
                .map(Paths::get)
                .orElse(DEFAULT_LOCATION));
    }

    public static PropertiesFileCredentialProvider of(Path path) {
        return new PropertiesFileCredentialProvider(path.toFile());
    }

    public static PropertiesFileCredentialProvider of(URL url) {
        return of(url, false);
    }

    public static PropertiesFileCredentialProvider of(URL url, boolean preload) {
        Objects.requireNonNull(url);
        try {
            return new PropertiesFileCredentialProvider(new File(url.toURI()), preload);
        } catch (URISyntaxException e) {
            throw new IllegalStateException("Cannot create file from URL: " + url, e);
        }
    }

    private static Properties loadProperties(File file) {
        try (InputStream is = Files.newInputStream(file.toPath(), StandardOpenOption.READ)) {
            Properties prop = new Properties();
            prop.load(is);

            boolean missingKey = !prop.containsKey(APPLICATION_KEY_PROPERTY);
            boolean missingToken = !prop.containsKey(ACCESS_TOKEN_PROPERTY);

            if (!(missingKey || missingToken)) {
                return prop;
            }

            StringBuilder errorMessage = new StringBuilder("The property file ").append(file.getName())
                    .append(" does not contain required key(s): ");

            if (missingKey)
                errorMessage.append(APPLICATION_KEY_PROPERTY);

            if (missingToken) {
                if (missingKey) errorMessage.append(", ");
                errorMessage.append(ACCESS_TOKEN_PROPERTY);
            }

            throw new IllegalStateException(errorMessage.toString());
        } catch (IOException e) {
            throw new UncheckedIOException("The I/O error occurred when reading: " + file.getPath(), e);
        }
    }

    @Override
    public void load() {
        if (isLoaded()) {
            return;
        }

        Properties properties = loadProperties(file);
        applicationKey = properties.getProperty(APPLICATION_KEY_PROPERTY);
        accessToken = properties.getProperty(ACCESS_TOKEN_PROPERTY);
    }
}
