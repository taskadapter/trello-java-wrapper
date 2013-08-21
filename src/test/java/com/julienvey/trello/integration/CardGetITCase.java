package com.julienvey.trello.integration;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Attachment;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Parameterized.class)
public class CardGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String CARD_ID = "518bab520967804c03002994";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{new ApacheHttpClient()}, {new AsyncTrelloHttpClient()}, {new RestTemplateHttpClient()}});
    }

    public CardGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testGetCard() {
        Card card = trello.getCard(CARD_ID);

        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo(CARD_ID);
        assertThat(card.getIdBoard()).isEqualTo("518baad5b05dbf4703004852");
    }

    @Test
    public void testGetCardByShortUrl() {
        Card card = trello.getCard("C1fXQHre");

        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo(CARD_ID);
        assertThat(card.getIdBoard()).isEqualTo("518baad5b05dbf4703004852");
    }

    @Test
    public void testGetCardActions() {
        List<Action> cardActions = trello.getCardActions(CARD_ID);

        assertThat(cardActions).isNotNull();
        assertThat(cardActions).hasSize(1);
        assertThat(cardActions.get(0).getId()).isEqualTo("5199029a7c4f3ca30a00136a");
    }

    @Test
    public void testGetCardAttachments() {
        List<Attachment> cardAttachments = trello.getCardAttachments(CARD_ID);

        assertThat(cardAttachments).isNotNull();
        assertThat(cardAttachments).hasSize(1);
        assertThat(cardAttachments.get(0).getId()).isEqualTo("519902b653ac28d57e00ec3b");
    }

    @Test
    public void testGetCardAttachment() {
        Attachment cardAttachment = trello.getCardAttachment(CARD_ID, "519902b653ac28d57e00ec3b");

        assertThat(cardAttachment).isNotNull();
        assertThat(cardAttachment.getId()).isEqualTo("519902b653ac28d57e00ec3b");
    }

    @Test
    public void testGetCardBoard() {
        Board cardBoard = trello.getCardBoard(CARD_ID);

        assertThat(cardBoard).isNotNull();
        assertThat(cardBoard.getId()).isEqualTo("518baad5b05dbf4703004852");
    }

}
