package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CardGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetCard() {
        //Given
        Card mockCard = new Card();
        mockCard.setId("idCard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockCard);

        //When
        Card card = trello.getCard("idCard");

        //Then
        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo("idCard");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}?key={applicationKey}&token={userToken}"),
                eq(Card.class), eq("idCard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetCardByShortUrl() {
        //Given
        Card mockCard = new Card();
        mockCard.setId("idCard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockCard);

        //When
        Card card = trello.getCard("shortUrl");

        //Then
        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo("idCard");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}?key={applicationKey}&token={userToken}"),
                eq(Card.class), eq("shortUrl"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetCardActions() {
        //Given
        Action action1 = new Action();
        action1.setId("idAction1");
        Action action2 = new Action();
        action1.setId("idAction2");
        Action action3 = new Action();
        action1.setId("idAction3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Action[]{action1, action2, action3});

        //When
        List<Action> cardActions = trello.getCardActions("idCard");

        //Then
        assertThat(cardActions).isNotNull();
        assertThat(cardActions).hasSize(3);
        assertThat(cardActions.get(0).getId()).isEqualTo("idAction3");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}/actions?key={applicationKey}&token={userToken}"),
                eq(Action[].class), eq("idCard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetCardAttachments() {
        //Given
        Attachment action1 = new Attachment();
        action1.setId("idAttach1");
        Attachment action2 = new Attachment();
        action1.setId("idAttach2");
        Attachment action3 = new Attachment();
        action1.setId("idAttach3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Attachment[]{action1, action2, action3});

        //When
        List<Attachment> cardAttachments = trello.getCardAttachments("idCard");

        //Then
        assertThat(cardAttachments).isNotNull();
        assertThat(cardAttachments).hasSize(3);
        assertThat(cardAttachments.get(0).getId()).isEqualTo("idAttach3");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}/attachments?key={applicationKey}&token={userToken}"),
                eq(Attachment[].class), eq("idCard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetCardAttachment() {
        //Given
        Attachment mockAttachement = new Attachment();
        mockAttachement.setId("idAttach");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockAttachement);

        //When
        Attachment cardAttachment = trello.getCardAttachment("idCard", "idAttach");

        assertThat(cardAttachment).isNotNull();
        assertThat(cardAttachment.getId()).isEqualTo("idAttach");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}/attachments/{attachmentId}?key={applicationKey}&token={userToken}"),
                eq(Attachment.class), eq("idCard"), eq("idAttach"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetCardBoard() {
        //Given
        Board mockBoard = new Board();
        mockBoard.setId("idBoard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockBoard);

        //When
        Board cardBoard = trello.getCardBoard("idCard");

        assertThat(cardBoard).isNotNull();
        assertThat(cardBoard.getId()).isEqualTo("idBoard");

        verify(httpClient).get(eq("https://api.trello.com/1/cards/{cardId}/board?key={applicationKey}&token={userToken}"),
                eq(Board.class), eq("idCard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

}
