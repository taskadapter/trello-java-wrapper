package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Attachment;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import org.fest.assertions.Assertions;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class ListGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetList(){
        //Given
        TList mockList = new TList();
        mockList.setId("idList");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockList);

        //When
        TList list = trello.getList("idList");

        //Then
        assertThat(list).isNotNull();
        assertThat(list.getId()).isEqualTo("idList");

        verify(httpClient).get(eq("https://api.trello.com/1/lists/{listId}?key={applicationKey}&token={userToken}"),
                eq(TList.class), eq("idList"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
