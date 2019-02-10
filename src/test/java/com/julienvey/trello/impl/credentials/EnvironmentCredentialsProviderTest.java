package com.julienvey.trello.impl.credentials;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class EnvironmentCredentialsProviderTest {
    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    @Test
    public void cantCreateInstanceWhenBothAreMissing() {
        assertThatIllegalStateException()
                .isThrownBy(this::environmentCredentialsProvider)
                .withMessage("The TRELLO_APPLICATION_KEY and TRELLO_ACCESS_TOKEN environment variable(s) must be set.");
    }

    @Test
    public void cantCreateInstanceWhenApplicationKeyIsMissing() {
        environmentVariables.set("TRELLO_ACCESS_TOKEN", "accessToken");

        assertThatIllegalStateException()
                .isThrownBy(this::environmentCredentialsProvider)
                .withMessage("The TRELLO_APPLICATION_KEY environment variable(s) must be set.");
    }

    private EnvironmentCredentialsProvider environmentCredentialsProvider() {
        return new EnvironmentCredentialsProvider(true);
    }

    @Test
    public void cantCreateInstanceWhenTokenIsMissing() {
        environmentVariables.set("TRELLO_APPLICATION_KEY", "applicationKey");

        assertThatIllegalStateException()
                .isThrownBy(this::environmentCredentialsProvider)
                .withMessage("The TRELLO_ACCESS_TOKEN environment variable(s) must be set.");
    }

    @Test
    public void shouldCreateInstanceWithBothArePresent() {
        environmentVariables.set("TRELLO_APPLICATION_KEY", "applicationKey");
        environmentVariables.set("TRELLO_ACCESS_TOKEN", "accessToken");

        new EnvironmentCredentialsProvider();
    }
}