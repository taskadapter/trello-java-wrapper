package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

/**
 * @author Ruslan Ustits
 */
public class MemberGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetMemberBoards() {
        // Given
        Board board1 = new Board();
        board1.setId("idBoard1");
        Board board2 = new Board();
        board2.setId("idBoard2");
        Board board3 = new Board();
        board3.setId("idBoard3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Board[] { board1, board2, board3 });

        // When
        List<Board> memberBoards = trello.getMemberBoards("idMember");

        // Then
        assertThat(memberBoards).isNotNull();
        assertThat(memberBoards).hasSize(3);
        assertThat(memberBoards.get(0).getId()).isEqualTo("idBoard1");

        verify(httpClient).get(eq("https://api.trello.com/1/members/{userId}/boards?key={applicationKey}&token={userToken}"),
                eq(Board[].class), eq("idMember"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetMemberActions() {
        // Given
        Action action1 = new Action();
        action1.setId("1");
        Action action2 = new Action();
        action2.setId("2");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Action[] { action1, action2 });

        // When
        List<Action> memberActions = trello.getMemberActions("idMember");

        // Then
        assertThat(memberActions)
                .isNotNull()
                .hasSize(2)
                .containsExactly(action1, action2);

        verify(httpClient).get(eq("https://api.trello.com/1/members/{userId}/actions?key={applicationKey}&token={userToken}"),
                eq(Action[].class), eq("idMember"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
