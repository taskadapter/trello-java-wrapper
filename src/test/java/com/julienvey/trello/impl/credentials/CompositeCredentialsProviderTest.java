package com.julienvey.trello.impl.credentials;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ClearSystemProperties;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

import com.julienvey.trello.TrelloCredentialsProvider;

public class CompositeCredentialsProviderTest {
    private static final String KEY = "key";
    private static final String TOKEN = "token";
    @Rule
    public final ClearSystemProperties clearSystemPropertiesRule = new ClearSystemProperties("trello.configPath");
    @Rule
    public final EnvironmentVariables environmentVariables = new EnvironmentVariables();

    private static TrelloCredentialsProvider throwingMockProvider() {
        TrelloCredentialsProvider failingProvider = mock(TrelloCredentialsProvider.class);
        when(failingProvider.applicationKey()).thenThrow(IllegalStateException.class);
        when(failingProvider.accessToken()).thenThrow(IllegalStateException.class);
        doThrow(IOException.class).when(failingProvider).load();

        return failingProvider;
    }

    private static TrelloCredentialsProvider workingProvider() {
        return spy(new SimpleCredentialsProvider(KEY, TOKEN));
    }

    @Test
    public void shouldThrowWhenListIsEmpty() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new CompositeCredentialsProvider(Collections.emptyList()))
                .withMessage("providers should must not be empty.");
    }

    @Test
    public void notThrowingProviderShouldReturn() {
        // The have an file in the classpath matches so we should avoid that
        System.setProperty("trello.configPath", "config/empty.properties");

        CompositeCredentialsProvider provider = new CompositeCredentialsProvider(Arrays.asList(
                new EnvironmentCredentialsProvider(),
                new ClasspathCredentialsProvider(),
                PropertiesFileCredentialProvider.ofDefault(),
                new SimpleCredentialsProvider(KEY, TOKEN)
        ));

        assertThat(provider.applicationKey()).isEqualTo(KEY);
        assertThat(provider.accessToken()).isEqualTo(TOKEN);
    }

    @Test
    public void succeededProviderShouldBeCached() {
        TrelloCredentialsProvider throwingProvider = throwingMockProvider();
        TrelloCredentialsProvider workingProvider = workingProvider();

        CompositeCredentialsProvider provider = new CompositeCredentialsProvider(throwingProvider, workingProvider);

        // just invocations
        provider.applicationKey();
        provider.applicationKey();
        provider.applicationKey();

        verify(throwingProvider).applicationKey();
        verifyNoMoreInteractions(throwingProvider);

        verify(workingProvider, times(3)).applicationKey();
        verifyNoMoreInteractions(workingProvider);
    }

    @Test
    public void succeededProviderShouldFastReturn() {
        TrelloCredentialsProvider throwingProvider1 = throwingMockProvider();
        TrelloCredentialsProvider throwingProvider2 = throwingMockProvider();
        TrelloCredentialsProvider throwingProvider3 = throwingMockProvider();
        TrelloCredentialsProvider workingProvider = workingProvider();

        CompositeCredentialsProvider provider = new CompositeCredentialsProvider(Arrays.asList(
                workingProvider,
                throwingProvider1,
                throwingProvider2,
                throwingProvider3
        ));

        // just invocations
        provider.applicationKey();
        provider.applicationKey();
        provider.applicationKey();

        verify(workingProvider, times(3)).applicationKey();
        verifyNoMoreInteractions(workingProvider);

        verifyZeroInteractions(throwingProvider1, throwingProvider2, throwingProvider3);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void succeededProviderShouldBeForgottenIfThrows() {
        TrelloCredentialsProvider firstTimeFailing = mock(TrelloCredentialsProvider.class, "firstTimeFailing");
        TrelloCredentialsProvider secondTimeFailing = mock(TrelloCredentialsProvider.class, "secondTimeFailing");

        when(firstTimeFailing.applicationKey())
                .thenThrow(IOException.class)
                .thenReturn("secondKey")
                .thenThrow(IOException.class);

        when(secondTimeFailing.applicationKey())
                .thenReturn("firstKey")
                .thenThrow(IOException.class)
                .thenReturn("firstKey");

        CompositeCredentialsProvider provider = new CompositeCredentialsProvider(firstTimeFailing, secondTimeFailing);

        assertThat(provider.applicationKey()).isEqualTo("firstKey");
        assertThat(provider.applicationKey()).isEqualTo("secondKey");
        assertThat(provider.applicationKey()).isEqualTo("firstKey");
        assertThat(provider.applicationKey()).isEqualTo("firstKey");

        verify(firstTimeFailing, times(4)).applicationKey();
        verify(secondTimeFailing, times(4)).applicationKey();

        verifyNoMoreInteractions(firstTimeFailing, secondTimeFailing);
    }

    @Test
    public void shouldThrowExceptionWhenAllFail() {
        assertThatIllegalStateException()
                .isThrownBy(() -> {
                    CompositeCredentialsProvider provider = new CompositeCredentialsProvider(
                            throwingMockProvider(), throwingMockProvider(), throwingMockProvider(),
                            throwingMockProvider(), throwingMockProvider(), throwingMockProvider()
                    );

                    provider.accessToken();
                })
                .withMessage("None of 6 providers succeed to perform operation.");
    }
}