package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.CheckList;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CheckListGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetCheckList() {
        //Given
        CheckList mockCheckList = new CheckList();
        mockCheckList.setId("idCheckList");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockCheckList);

        //When
        CheckList checkList = trello.getCheckList("idCheckList");

        //Then
        assertThat(checkList).isNotNull();
        assertThat(checkList.getId()).isEqualTo("idCheckList");

        verify(httpClient).get(eq("https://api.trello.com/1/checklists/{checkListId}?key={applicationKey}&token={userToken}"),
                eq(CheckList.class), eq("idCheckList"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
