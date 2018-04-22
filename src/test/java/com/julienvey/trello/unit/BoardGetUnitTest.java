package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.*;

import static com.julienvey.trello.utils.ArgUtils.arg;
import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.anyVararg;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

public class BoardGetUnitTest {


    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = Mockito.mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetBoardSimple() {
        //Given
        Board mockBoard = new Board();
        mockBoard.setId("idBoard");
        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockBoard);

        //When
        Board board = trello.getBoard("idBoard");

        //Then
        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo("idBoard");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}?key={applicationKey}&token={userToken}"),
                eq(Board.class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardWithLists() {
        //Given
        Board mockBoard = new Board();
        mockBoard.setId("idBoard");
        TList list1 = new TList();
        list1.setId("list1");
        TList list2 = new TList();
        list2.setId("list2");
        mockBoard.setLists(Arrays.asList(list1, list2));
        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockBoard);

        //When
        Board board = trello.getBoard("idBoard", arg("lists", "all"));

        //Then
        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo("idBoard");
        assertThat(board.getLists()).isNotNull();
        assertThat(board.getLists().size()).isEqualTo(2);

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}?key={applicationKey}&token={userToken}&lists=all"),
            eq(Board.class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardActions() {
        //Given
        Action action1 = new Action();
        action1.setId("idAction1");
        Action action2 = new Action();
        action1.setId("idAction2");
        Action action3 = new Action();
        action1.setId("idAction3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Action[]{action1, action2, action3});

        //When
        List<Action> boardActions = trello.getBoardActions("idBoard");

        //Then
        assertThat(boardActions).isNotNull();
        assertThat(boardActions).hasSize(3);
        assertThat(boardActions.get(0).getId()).isEqualTo("idAction3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/actions?key={applicationKey}&token={userToken}"),
                eq(Action[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardCards() {
        //Given
        Card action1 = new Card();
        action1.setId("idCard1");
        Card action2 = new Card();
        action1.setId("idCard2");
        Card action3 = new Card();
        action1.setId("idCard3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Card[]{action1, action2, action3});

        //When
        List<Card> boardCards = trello.getBoardCards("idBoard");

        //Then
        assertThat(boardCards).isNotNull();
        assertThat(boardCards).hasSize(3);
        assertThat(boardCards.get(0).getId()).isEqualTo("idCard3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/cards?key={applicationKey}&token={userToken}"),
                eq(Card[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardCard() {
        //Given
        Card mockCard = new Card();
        mockCard.setId("idCard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockCard);

        //When
        Card boardCard = trello.getBoardCard("idBoard", "idCard");

        //Then
        assertThat(boardCard).isNotNull();
        assertThat(boardCard.getId()).isEqualTo("idCard");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/cards/{cardId}?key={applicationKey}&token={userToken}"),
                eq(Card.class), eq("idBoard"), eq("idCard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);

    }

    @Test
    public void testGetBoardChecklists() {
        //Given
        CheckList list1 = new CheckList();
        list1.setId("idList1");
        CheckList list2 = new CheckList();
        list1.setId("idList2");
        CheckList list3 = new CheckList();
        list1.setId("idList3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new CheckList[]{list1, list2, list3});

        //When
        List<CheckList> boardChecklists = trello.getBoardChecklists("idBoard");

        //Then
        assertThat(boardChecklists).isNotNull();
        assertThat(boardChecklists).hasSize(3);
        assertThat(boardChecklists.get(0).getId()).isEqualTo("idList3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/checklists?key={applicationKey}&token={userToken}"),
                eq(CheckList[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardLists() {
        //Given
        TList action1 = new TList();
        action1.setId("idList1");
        TList action2 = new TList();
        action1.setId("idList2");
        TList action3 = new TList();
        action1.setId("idList3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new TList[]{action1, action2, action3});

        //When
        List<TList> boardLists = trello.getBoardLists("idBoard");

        //Then
        assertThat(boardLists).isNotNull();
        assertThat(boardLists).hasSize(3);
        assertThat(boardLists.get(0).getId()).isEqualTo("idList3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/lists?key={applicationKey}&token={userToken}"),
                eq(TList[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardMembers() {
        //Given
        Member member1 = new Member();
        member1.setId("idMember1");
        Member member2 = new Member();
        member1.setId("idMember2");
        Member member3 = new Member();
        member1.setId("idMember3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Member[]{member1, member2, member3});

        //When
        List<Member> boardMembers = trello.getBoardMembers("idBoard");

        //Then
        assertThat(boardMembers).isNotNull();
        assertThat(boardMembers).hasSize(3);
        assertThat(boardMembers.get(0).getId()).isEqualTo("idMember3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/members?key={applicationKey}&token={userToken}"),
                eq(Member[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardMemberCards() {
        //Given
        Card action1 = new Card();
        action1.setId("idCard1");
        Card action2 = new Card();
        action1.setId("idCard2");
        Card action3 = new Card();
        action1.setId("idCard3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Card[]{action1, action2, action3});

        //When
        List<Card> boardMemberCards = trello.getBoardMemberCards("idBoard", "idMember");

        //Then
        assertThat(boardMemberCards).isNotNull();
        assertThat(boardMemberCards).hasSize(3);
        assertThat(boardMemberCards.get(0).getId()).isEqualTo("idCard3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/members/{memberId}/cards?key={applicationKey}&token={userToken}"),
                eq(Card[].class), eq("idBoard"), eq("idMember"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardMembersInvited() {
        //Given
        Member member1 = new Member();
        member1.setId("idMember1");
        Member member2 = new Member();
        member1.setId("idMember2");
        Member member3 = new Member();
        member1.setId("idMember3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Member[]{member1, member2, member3});

        //When
        List<Member> boardMembersInvited = trello.getBoardMembersInvited("idBoard");

        //Then
        assertThat(boardMembersInvited).isNotNull();
        assertThat(boardMembersInvited).hasSize(3);
        assertThat(boardMembersInvited.get(0).getId()).isEqualTo("idMember3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/membersInvited?key={applicationKey}&token={userToken}"),
                eq(Member[].class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardMyPrefs() {
        //Given
        MyPrefs myPrefs = new MyPrefs();
        myPrefs.setEmailKey("Key");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(myPrefs);

        //When
        MyPrefs boardMyMyPrefs = trello.getBoardMyPrefs("idBoard");

        //Then
        assertThat(boardMyMyPrefs).isNotNull();
        assertThat(boardMyMyPrefs.getEmailKey()).isEqualTo("Key");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/myPrefs?key={applicationKey}&token={userToken}"),
                eq(MyPrefs.class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardOrganization() {
        //Given
        Organization organization = new Organization();
        organization.setId("idOrg");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(organization);

        //When
        Organization boardOrganization = trello.getBoardOrganization("idBoard");

        //Then
        assertThat(boardOrganization).isNotNull();
        assertThat(boardOrganization.getId()).isEqualTo("idOrg");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/organization?key={applicationKey}&token={userToken}"),
                eq(Organization.class), eq("idBoard"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetBoardMemberActivityComments() {
        //Given
        CardWithActions action1 = new CardWithActions();
        action1.setId("idCard1");
        CardWithActions action2 = new CardWithActions();
        action1.setId("idCard2");
        CardWithActions action3 = new CardWithActions();
        action1.setId("idCard3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new CardWithActions[]{action1, action2, action3});

        //When
        List<CardWithActions> commentActivity = trello.getBoardMemberActivity("idBoard",
                "memberId", "commentCard");

        //Then
        assertThat(commentActivity).isNotNull();
        assertThat(commentActivity).hasSize(3);
        assertThat(commentActivity.get(0).getId()).isEqualTo("idCard3");

        verify(httpClient).get(eq("https://api.trello.com/1/boards/{boardId}/members/{memberId}/cards?key={applicationKey}&token={userToken}&actions=commentCard"),
                eq(CardWithActions[].class), eq("idBoard"), eq("memberId"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }
}
