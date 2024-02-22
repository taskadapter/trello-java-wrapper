package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.CheckItem;
import com.julienvey.trello.domain.CheckList;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.domaininternal.CheckItemState;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class CheckListUnitTest {

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

    @Test
    public void testUpdateCheckItemInCheckList() {
        //Given
        CheckItem mockCheckItem = new CheckItem();
        mockCheckItem.setId("idCheckItem");
        mockCheckItem.setState(CheckItemState.COMPLETE);
        when(httpClient.putForObject(anyString(), any(Class.class), any(), (String[]) anyVararg())).thenReturn(mockCheckItem);

        //When
        CheckItem checkItem = trello.completeCheckItem("idCheckList", "idCheckItem");

        //Then
        assertThat(checkItem).isNotNull();
        assertThat(checkItem.getId()).isEqualTo("idCheckItem");
        assertThat(checkItem.getState()).isEqualTo(CheckItemState.COMPLETE);

        verify(httpClient).putForObject(eq("https://api.trello.com/1/cards/{cardId}/checkitem/{checkItemId}?key={applicationKey}&token={userToken}"),
                eq(Map.of("state", CheckItemState.COMPLETE.getState())), eq(CheckItem.class), eq("idCheckList"), eq("idCheckItem"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
