package com.julienvey.trello.unit;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;

public class ListGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetList() {
        // Given
        TList mockList = new TList();
        mockList.setId("idList");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockList);

        // When
        TList list = trello.getList("idList");

        // Then
        assertThat(list).isNotNull();
        assertThat(list.getId()).isEqualTo("idList");

        verify(httpClient).get(eq("https://api.trello.com/1/lists/{listId}?key={applicationKey}&token={userToken}"),
                eq(TList.class), eq("idList"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetListCards() {
        // Given
        Card card1 = new Card();
        card1.setId("idCard1");
        Card card2 = new Card();
        card2.setId("idCard2");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Card[] { card1, card2 });

        // When
        List<Card> listCards = trello.getListCards("idList");

        // Then
        assertThat(listCards).isNotNull();
        assertThat(listCards).hasSize(2);

        verify(httpClient).get(eq("https://api.trello.com/1/lists/{listId}/cards?key={applicationKey}&token={userToken}"),
                eq(Card[].class), eq("idList"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
