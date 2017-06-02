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
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;

public class OrganizationGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetOrganizationBoards() {
        // Given
        Board board1 = new Board();
        board1.setId("idBoard1");
        Board board2 = new Board();
        board2.setId("idBoard2");
        Board board3 = new Board();
        board3.setId("idBoard3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Board[] { board1, board2, board3 });

        // When
        List<Board> OrgnizationBoards = trello.getOrganizationBoards("idOrganization");

        // Then
        assertThat(OrgnizationBoards).isNotNull();
        assertThat(OrgnizationBoards).hasSize(3);
        assertThat(OrgnizationBoards.get(0).getId()).isEqualTo("idBoard1");

        verify(httpClient).get(eq("https://api.trello.com/1/organizations/{organizationId}/boards?key={applicationKey}&token={userToken}"),
                eq(Board[].class), eq("idOrganization"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetOrganizationMembers() {
        // Given
        Member member1 = new Member();
        member1.setId("idMember1");
        Member member2 = new Member();
        member2.setId("idMember2");
        Member member3 = new Member();
        member3.setId("idMember3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Member[] { member1, member2, member3 });

        // When
        List<Member> OrgnizationMembers = trello.getOrganizationMembers("idOrganization");

        // Then
        assertThat(OrgnizationMembers).isNotNull();
        assertThat(OrgnizationMembers).hasSize(3);
        assertThat(OrgnizationMembers.get(0).getId()).isEqualTo("idMember1");

        verify(httpClient).get(eq("https://api.trello.com/1/organizations/{organizationId}/members?key={applicationKey}&token={userToken}"),
                eq(Member[].class), eq("idOrganization"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
