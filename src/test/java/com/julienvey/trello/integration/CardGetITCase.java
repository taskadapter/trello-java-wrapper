package com.julienvey.trello.integration;

import static org.fest.assertions.Assertions.assertThat;

import com.julienvey.trello.domain.Label;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.julienvey.trello.TrelloConfig;
import com.julienvey.trello.impl.http.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Attachment;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.CheckList;
import com.julienvey.trello.impl.TrelloImpl;

@RunWith(Parameterized.class)
public class CardGetITCase {

    public static final String CARD_ID = "518bab520967804c03002994";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static List<TrelloHttpClient> data() {
        return TrelloHttpClients.all();
    }

    public CardGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TrelloConfig.applicationKey(), "", httpClient);
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
    public void testGetCardChecklists() {
        List<CheckList> cardChecklists = trello.getCardChecklists(CARD_ID);

        assertThat(cardChecklists).isNotNull();
        assertThat(cardChecklists).hasSize(2);
        assertThat(cardChecklists.get(0).getId()).isEqualTo("519902831dc610b17800e3e6");
        assertThat(cardChecklists.get(1).getId()).isEqualTo("51990272b1740a191800e5af");
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
