package com.julienvey.trello.integration;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;

@RunWith(Parameterized.class)
public class ActionGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String ACTION_ID = "51990c2143453ab27e0087d5";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{new ApacheHttpClient()}, {new AsyncTrelloHttpClient()}, {new RestTemplateHttpClient()}});
    }

    public ActionGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testGetActionById() {
        Action board = trello.getAction(ACTION_ID);

        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo(ACTION_ID);
        assertThat(board.getType()).isEqualTo("createCard");
    }

    @Test
    public void testGetActionBoard() {
        Board actionBoard = trello.getActionBoard(ACTION_ID);

        assertThat(actionBoard).isNotNull();
        assertThat(actionBoard.getId()).isEqualTo("518baad5b05dbf4703004852");
    }

    @Test
    public void testGetActionCard() {
        Card actionCard = trello.getActionCard(ACTION_ID);

        assertThat(actionCard).isNotNull();
        assertThat(actionCard.getId()).isEqualTo("51990c2143453ab27e0087d4");
    }

    @Test
    public void testGetActionEntities() {
        List<Entity> actionEntities = trello.getActionEntities(ACTION_ID);

        assertThat(actionEntities).isNotNull();
        assertThat(actionEntities).hasSize(5);
        assertThat(actionEntities.get(0).getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }

    @Test
    public void testGetActionList() {
        TList actionList = trello.getActionList(ACTION_ID);

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
