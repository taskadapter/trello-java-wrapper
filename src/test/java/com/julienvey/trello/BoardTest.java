package com.julienvey.trello;

import com.julienvey.trello.domain.Board;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class BoardTest {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";

    private static Trello trello;

    @BeforeClass
    public static void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "");
    }

    @Test
    public void testGetBoard() {
        Board board = trello.getBoard("518baad5b05dbf4703004852");

        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo("518baad5b05dbf4703004852");
        assertThat(board.getDesc()).isEqualTo("Description for testing");
        assertThat(board.getIdOrganization()).isEqualTo("518baaaa815af84031004375");
        assertThat(board.getLabelNames()).includes(entry("green", "Label 1 Green"),
                entry("yellow", "Label 2 Yellow"),
                entry("orange", "Label 3 Orange"),
                entry("red", "Label 4 Red"),
                entry("purple", "Label 5 Purple"),
                entry("blue", "Label 6 Blue"));
        assertThat(board.getLists()).hasSize(4);
    }
}
