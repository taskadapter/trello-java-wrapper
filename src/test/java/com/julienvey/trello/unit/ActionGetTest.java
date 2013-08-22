package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class ActionGetTest {


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

        when(httpClient.get(anyString(), eq(Action.class), any(String[].class))).thenReturn(mockAction);

        //When
        Action action = trello.getAction("idAction");

        //Then
        assertThat(action).isNotNull();
        assertThat(action.getId()).isEqualTo("idAction");
        assertThat(action.getType()).isEqualTo("createCard");
    }

    @Test
    public void testGetActionBoard() {
        Board actionBoard = trello.getActionBoard("idAction");

        assertThat(actionBoard).isNotNull();
        assertThat(actionBoard.getId()).isEqualTo("518baad5b05dbf4703004852");
    }

    @Test
    public void testGetActionCard() {
        Card actionCard = trello.getActionCard("idAction");

        assertThat(actionCard).isNotNull();
        assertThat(actionCard.getId()).isEqualTo("51990c2143453ab27e0087d4");
    }

    @Test
    public void testGetActionEntities() {
        List<Entity> actionEntities = trello.getActionEntities("idAction");

        assertThat(actionEntities).isNotNull();
        assertThat(actionEntities).hasSize(5);
        assertThat(actionEntities.get(0).getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }

    @Test
    public void testGetActionList() {
        TList actionList = trello.getActionList("idAction");

        assertThat(actionList).isNotNull();
        assertThat(actionList.getId()).isEqualTo("518baad5b05dbf4703004853");
    }

    @Test
    public void testGetActionMember() {
        Member actionMember = trello.getActionMember("5199022ced074110280076ed");

        assertThat(actionMember).isNotNull();
        assertThat(actionMember.getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }

    @Test
    public void testGetActionMembeCreator() {
        Member actionMemberCreator = trello.getActionMemberCreator("5199022ced074110280076ed");

        assertThat(actionMemberCreator).isNotNull();
        assertThat(actionMemberCreator.getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }

    @Test
    public void testGetActionOrganization() {
//        Organization actionOrganization = trello.getActionOrganization("5199022ced074110280076ed");
//
//        assertThat(actionOrganization).isNotNull();
//        assertThat(actionOrganization.getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }


}
