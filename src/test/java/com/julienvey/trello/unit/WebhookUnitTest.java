package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Webhook;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class WebhookUnitTest {

    private final String CALLBACK_URL = "http://localhost:8080/callbackUrl";
    private final String BOARD_ID = "boardId";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testCreateWebhook() {
        //Given
        Webhook webhook = new Webhook();
        webhook.setCallbackURL(CALLBACK_URL);
        webhook.setIdModel(BOARD_ID);

        when(httpClient.postForObject(anyString(), any(Class.class), anyObject(),(String[]) anyVararg())).thenReturn(webhook);

        //When
        Webhook foundWebhook = trello.createWebhook(webhook);

        //Then
        assertThat(foundWebhook).isNotNull();
        assertThat(foundWebhook.getCallbackURL()).isEqualTo(CALLBACK_URL);
        assertThat(foundWebhook.getIdModel()).isEqualTo(BOARD_ID);

        verify(httpClient).postForObject(eq("https://api.trello.com/1/webhooks/?key={applicationKey}&token={userToken}"),
                eq(webhook), eq(Webhook.class), (String[]) anyVararg());
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetWebhookById() {
        //Given
        Webhook webhook = new Webhook();
        webhook.setCallbackURL(CALLBACK_URL);
        webhook.setIdModel(BOARD_ID);

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(webhook);

        //When
        Webhook foundWebhook = trello.getWebhook("webhookId");

        //Then
        assertThat(foundWebhook).isNotNull();
        assertThat(foundWebhook.getCallbackURL()).isEqualTo(CALLBACK_URL);
        assertThat(foundWebhook.getIdModel()).isEqualTo(BOARD_ID);

        verify(httpClient).get(eq("https://api.trello.com/1/webhooks/{webhookId}?key={applicationKey}&token={userToken}"),
                eq(Webhook.class), eq("webhookId"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testUpdateWebhook() {
        //Given
        Webhook webhook = new Webhook();
        webhook.setCallbackURL(CALLBACK_URL);
        webhook.setIdModel(BOARD_ID);
        webhook.setDescription("updated description");

        when(httpClient.putForObject(anyString(), any(), any(Class.class), (String[]) anyVararg())).thenReturn(webhook);

        //When
        Webhook foundWebhook = trello.updateWebhook(webhook);

        //Then
        assertThat(foundWebhook).isNotNull();
        assertThat(foundWebhook.getCallbackURL()).isEqualTo(CALLBACK_URL);
        assertThat(foundWebhook.getIdModel()).isEqualTo(BOARD_ID);

        verify(httpClient).putForObject(eq("https://api.trello.com/1/webhooks/{webhookId}?key={applicationKey}&token={userToken}"),
                eq(webhook), eq(Webhook.class), (String[]) anyVararg());
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testDeleteWebhookById() {
        //Given
        Webhook webhookToDelete = new Webhook();
        webhookToDelete.setCallbackURL(CALLBACK_URL);
        webhookToDelete.setIdModel(BOARD_ID);

        when(httpClient.delete(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(webhookToDelete);

        //When
        Webhook deletedWebhook = trello.deleteWebhook("webhookId");

        //Then
        assertThat(deletedWebhook).isNotNull();
        assertThat(deletedWebhook.getCallbackURL()).isEqualTo(CALLBACK_URL);
        assertThat(deletedWebhook.getIdModel()).isEqualTo(BOARD_ID);

        verify(httpClient).delete(eq("https://api.trello.com/1/webhooks/{webhookId}?key={applicationKey}&token={userToken}"),
                eq(Webhook.class), eq("webhookId"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
