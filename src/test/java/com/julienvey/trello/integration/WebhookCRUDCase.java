package com.julienvey.trello.integration;

import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Parameterized.class)
public class WebhookCRUDCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static List<TrelloHttpClient> data() {
        return TrelloHttpClients.all();
    }

    public WebhookCRUDCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testCreateGetAndDeleteWebhook() {
        final String callbackUrl = "<provided callbackURL must be a valid URL during the creation of the webhook and return 200 on HEAD request during hook creation time>";
        final String boardId = "518baad5b05dbf4703004852";
        final Webhook newWebhook = new Webhook()
                .setCallbackURL(callbackUrl)
                .setIdModel(boardId);

        final Webhook createdWebhook = trello.createWebhook(newWebhook);
        System.out.println(createdWebhook.getId());

        assertThat(createdWebhook).isNotNull();
        assertThat(createdWebhook.getId()).isNotNull();
        assertThat(createdWebhook.getId()).isNotEmpty();
        assertThat(createdWebhook.getIdModel()).isEqualTo(boardId);
        assertThat(createdWebhook.getCallbackURL()).isEqualTo(callbackUrl);
        assertThat(createdWebhook.getDescription()).isEmpty();

        String updatedDescription = "Updated description";
        createdWebhook.setDescription(updatedDescription);
        Webhook updatedWebhook = trello.updateWebhook(createdWebhook);

        final Webhook foundWebhook = trello.getWebhook(createdWebhook.getId());
        assertThat(foundWebhook).isNotNull();
        assertThat(foundWebhook.getId()).isNotNull();
        assertThat(foundWebhook.getId()).isEqualTo(updatedWebhook.getId());
        assertThat(foundWebhook.getIdModel()).isEqualTo(updatedWebhook.getIdModel());
        assertThat(foundWebhook.getCallbackURL()).isEqualTo(updatedWebhook.getCallbackURL());
        assertThat(foundWebhook.getDescription()).isEqualTo(updatedDescription);

        trello.deleteWebhook(foundWebhook.getId());

        boolean isCatchVisited = false;
        try {
            trello.getWebhook(foundWebhook.getId());
        } catch (NotFoundException notFoundException) {
            isCatchVisited = true;
            assertThat(notFoundException.getMessage().contains("Resource not found: "));
        }

        assertThat(isCatchVisited).isTrue();
    }

}
