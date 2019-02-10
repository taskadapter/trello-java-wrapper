package com.julienvey.trello.impl.credentials;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;

import com.julienvey.trello.TrelloCredentialsProvider;

public class ClasspathCredentialProviderTest {
    @Rule
    public final ClearSystemProperties clearSystemPropertiesRule = new ClearSystemProperties("trello.configPath");

    private static void classpathCredentialsProvider() {
        new ClasspathCredentialsProvider(true);
    }

    @Test
    public void shouldReadFromTheClasspath() {
        TrelloCredentialsProvider credentialProvider = new ClasspathCredentialsProvider();

        assertThat(credentialProvider.applicationKey()).isEqualTo("applicationKeyFromClasspathFile");
        assertThat(credentialProvider.accessToken()).isEqualTo("accessTokenFromClasspathFile");
    }

    @Test
    public void shouldReadFromProvidedConfig() {
        System.setProperty("trello.configPath", "alt_trello.properties");

        TrelloCredentialsProvider credentialProvider = new ClasspathCredentialsProvider();

        assertThat(credentialProvider.applicationKey()).isEqualTo("applicationKeyFromAlternativeClasspathFile");
        assertThat(credentialProvider.accessToken()).isEqualTo("accessTokenFromFromAlternativeClasspathFile");
    }

    @Test
    public void shouldReadFromNestedProvidedConfig() {
        System.setProperty("trello.configPath", "config/trello.properties");

        TrelloCredentialsProvider credentialProvider = new ClasspathCredentialsProvider();

        assertThat(credentialProvider.applicationKey()).isEqualTo("applicationKeyFromNestedClasspathFile");
        assertThat(credentialProvider.accessToken()).isEqualTo("accessTokenFromNestedClasspathFile");
    }

    @Test
    public void cantCreateInstanceWhenBothAreMissing() {
        System.setProperty("trello.configPath", "config/empty.properties");

        assertThatIllegalStateException()
                .isThrownBy(ClasspathCredentialProviderTest::classpathCredentialsProvider)
                .withMessage("The property file empty.properties does not contain required key(s):" +
                        " trello.applicationKey, trello.accessToken");
    }

    @Test
    public void cantCreateInstanceWhenApplicationKeyIsMissing() {
        System.setProperty("trello.configPath", "config/application_key_missing.properties");

        assertThatIllegalStateException()
                .isThrownBy(ClasspathCredentialProviderTest::classpathCredentialsProvider)
                .withMessage("The property file application_key_missing.properties does not contain required key(s):" +
                        " trello.applicationKey");
    }

    @Test
    public void cantCreateInstanceWhenAccessTokenIsMissing() {
        System.setProperty("trello.configPath", "config/access_token_missing.properties");

        assertThatIllegalStateException()
                .isThrownBy(ClasspathCredentialProviderTest::classpathCredentialsProvider)
                .withMessage("The property file access_token_missing.properties does not contain required key(s):" +
                        " trello.accessToken");
    }
}