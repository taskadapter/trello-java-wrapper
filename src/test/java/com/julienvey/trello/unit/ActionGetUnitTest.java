package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ActionGetUnitTest {

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Before
    public void setUp() {
        httpClient = mock(TrelloHttpClient.class);
        trello = new TrelloImpl("", "", httpClient);
    }

    @Test
    public void testGetActionById() {
        //Given
        Action mockAction = new Action();
        mockAction.setId("idAction");
        mockAction.setType("createCard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockAction);

        //When
        Action action = trello.getAction("idAction");

        //Then
        assertThat(action).isNotNull();
        assertThat(action.getId()).isEqualTo("idAction");
        assertThat(action.getType()).isEqualTo("createCard");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}?key={applicationKey}&token={userToken}"),
                eq(Action.class), eq("idAction"), eq(""), eq(""));
        verifyNoMoreInteractions(httpClient);
    }

    @Test
    public void testGetActionBoard() {
        //Given
        Board mockBoard = new Board();
        mockBoard.setId("idBoard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockBoard);

        //When
        Board actionBoard = trello.getActionBoard("idBoard");

        //Then
        assertThat(actionBoard).isNotNull();
        assertThat(actionBoard.getId()).isEqualTo("idBoard");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/board?key={applicationKey}&token={userToken}"),
                eq(Board.class), eq("idBoard"), eq(""), eq(""));
    }

    @Test
    public void testGetActionCard() {
        //Given
        Card mockCard = new Card();
        mockCard.setId("idCard");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockCard);

        //When
        Card actionCard = trello.getActionCard("idCard");

        //Then
        assertThat(actionCard).isNotNull();
        assertThat(actionCard.getId()).isEqualTo("idCard");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/card?key={applicationKey}&token={userToken}"),
                eq(Card.class), eq("idCard"), eq(""), eq(""));
    }

    @Test
    public void testGetActionEntities() {
        //Given
        Entity entity1 = new Entity();
        entity1.setId("idEntity1");
        Entity entity2 = new Entity();
        entity1.setId("idEntity2");
        Entity entity3 = new Entity();
        entity1.setId("idEntity3");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(new Entity[]{entity1, entity2, entity3});

        //When
        List<Entity> actionEntities = trello.getActionEntities("idAction");

        //Then
        assertThat(actionEntities).isNotNull();
        assertThat(actionEntities).hasSize(3);
        assertThat(actionEntities.get(0).getId()).isEqualTo("idEntity3");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/entities?key={applicationKey}&token={userToken}"),
                eq(Entity[].class), eq("idAction"), eq(""), eq(""));
    }

    @Test
    public void testGetActionList() {
        //Given
        TList mockList = new TList();
        mockList.setId("idList");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockList);

        //When
        TList actionList = trello.getActionList("idList");

        //Then
        assertThat(actionList).isNotNull();
        assertThat(actionList.getId()).isEqualTo("idList");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/list?key={applicationKey}&token={userToken}"),
                eq(TList.class), eq("idList"), eq(""), eq(""));
    }

    @Test
    public void testGetActionMember() {
        //Given
        Member mockMember = new Member();
        mockMember.setId("idMember");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockMember);

        //When
        Member actionMember = trello.getActionMember("idMember");

        //Then
        assertThat(actionMember).isNotNull();
        assertThat(actionMember.getId()).isEqualTo("idMember");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/member?key={applicationKey}&token={userToken}"),
                eq(Member.class), eq("idMember"), eq(""), eq(""));
    }

    @Test
    public void testGetActionMembeCreator() {
        //Given
        Member mockMember = new Member();
        mockMember.setId("idMember");

        when(httpClient.get(anyString(), any(Class.class), (String[]) anyVararg())).thenReturn(mockMember);

        //When
        Member actionMember = trello.getActionMemberCreator("idMember");

        //Then
        assertThat(actionMember).isNotNull();
        assertThat(actionMember.getId()).isEqualTo("idMember");

        verify(httpClient).get(eq("https://api.trello.com/1/actions/{actionId}/memberCreator?key={applicationKey}&token={userToken}"),
                eq(Member.class), eq("idMember"), eq(""), eq(""));
    }

    @Test
    public void testGetActionOrganization() {
//        Organization actionOrganization = trello.getActionOrganization("5199022ced074110280076ed");
//
//        assertThat(actionOrganization).isNotNull();
//        assertThat(actionOrganization.getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }


}
