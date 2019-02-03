package com.julienvey.trello.integration;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.julienvey.trello.impl.http.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;

@RunWith(Parameterized.class)
public class ListGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String LIST_ID = "518baad5b05dbf4703004853";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static List<TrelloHttpClient> data() {
        return TrelloHttpClients.all();
    }

    public ListGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testGetList() {
        TList list = trello.getList(LIST_ID);

        assertThat(list).isNotNull();
        assertThat(list.getId()).isEqualTo(LIST_ID);
    }

    @Test
    public void testGetListCards() {
        List<Card> listCards = trello.getListCards(LIST_ID);

        assertThat(listCards).isNotNull();
        assertThat(listCards).hasSize(2);
        assertThat(listCards.get(0).getId()).isEqualTo("518bab520967804c03002994");
    }
}
