package com.julienvey.trello.impl.credentials;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.julienvey.trello.TrelloCredentialsProvider;

/**
 * Combines various {@link TrelloCredentialsProvider} and tries to survive the maximum of them.
 * <p>
 * All method invocations will be delegating to each of them until one of them eventually succeeded. Subsequent
 * invocations will be delegated to succeeded one. If during invocation successful {@link TrelloCredentialsProvider}
 * will throw an exception it'll be forgotten and all process starts again until one of them succeeds again. In the case
 * that they all {@link TrelloCredentialsProvider} fail, an {@code IllegalStateException} will be thrown.
 *
 * @author Edgar Asatryan.
 */
public class CompositeCredentialsProvider implements TrelloCredentialsProvider {
    private static final Logger log = LoggerFactory.getLogger(CompositeCredentialsProvider.class);

    private final List<TrelloCredentialsProvider> providers;
    // Cache the succeeded provider to increase performance.
    private TrelloCredentialsProvider lucky;

    public CompositeCredentialsProvider(TrelloCredentialsProvider... providers) {
        this(Arrays.asList(providers));
    }

    public CompositeCredentialsProvider(List<TrelloCredentialsProvider> providers) {
        this.providers = Objects.requireNonNull(providers);

        if (providers.isEmpty()) {
            throw new IllegalArgumentException("providers should must not be empty.");
        }
    }

    @Override
    public void load() {
        iterateUntil(provider -> {
            provider.load();
            return null;
        });
    }

    private <R> R iterateUntil(Function<TrelloCredentialsProvider, R> fun) {
        if (lucky != null) {
            // Woohoo! We got an already cached.
            try {
                return fun.apply(lucky);
            } catch (Exception e) {
                // something wrong with succeeded provider
                log.info("Already succeeded provider {} throws an exception with message.", lucky.getClass().getName(), e.getMessage());
                lucky = null;
            }
        }

        for (TrelloCredentialsProvider provider : providers) {
            try {
                R result = fun.apply(provider);
                // provider successfully returns a value
                // should remember that guy for the future
                lucky = provider;

                return result;
            } catch (Exception e) {
                // iteration continues
                log.info("The provider {} throws an exception with message.", provider.getClass().getName(), e.getMessage());
            }
        }

        // Doh! All providers are failed
        throw new IllegalStateException("None of " + providers.size() + " providers succeed to perform operation.");
    }

    @Override
    public String applicationKey() {
        return iterateUntil(TrelloCredentialsProvider::applicationKey);
    }

    @Override
    public String accessToken() {
        return iterateUntil(TrelloCredentialsProvider::accessToken);
    }
}
