package com.julienvey.trello;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class ActionGetTest {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String ACTION_ID = "51990c2143453ab27e0087d5";

    private static Trello trello;

    @BeforeClass
    public static void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "");
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


}
