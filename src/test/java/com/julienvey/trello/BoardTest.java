package com.julienvey.trello;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static com.julienvey.trello.utils.ArgUtils.arg;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

public class BoardTest {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String BOARD_ID = "518baad5b05dbf4703004852";

    private static Trello trello;

    @BeforeClass
    public static void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "");
    }

    @Test
    public void testGetBoardSimple() {
        Board board = trello.getBoard(BOARD_ID);

        assertThat(board).isNotNull();
        assertThat(board.getId()).isEqualTo(BOARD_ID);
        assertThat(board.getDesc()).isEqualTo("Description for testing");
        assertThat(board.getIdOrganization()).isEqualTo("518baaaa815af84031004375");
        assertThat(board.getLabelNames()).includes(entry("green", "Label 1 Green"),
                entry("yellow", "Label 2 Yellow"),
                entry("orange", "Label 3 Orange"),
                entry("red", "Label 4 Red"),
                entry("purple", "Label 5 Purple"),
                entry("blue", "Label 6 Blue"));
        assertThat(board.getName()).isEqualTo("Trello Test Board");
        assertThat(board.getUrl()).isEqualTo("https://trello.com/board/trello-test-board/518baad5b05dbf4703004852");
        assertThat(board.isClosed()).isFalse();
        assertThat(board.isPinned()).isFalse();
        assertThat(board.getPrefs()).isNotNull().isNotEmpty();
    }

    @Test
    public void testGetBoardFetchLists() {
        Board board = trello.getBoard(BOARD_ID);
        List<TList> lists = board.fetchLists();

        assertThat(lists).hasSize(4);
    }

    @Test
    public void testGetBoardFetchListsCardsAll() {
        Board board = trello.getBoard(BOARD_ID);
        List<TList> lists = board.fetchLists(arg("cards", "all"));

        assertThat(lists).hasSize(4);

        TList list = lists.get(0);
        assertThat(list.getCards()).hasSize(3);
    }

    @Test
    public void testGetBoardFetchListsCardsOpen() {
        Board board = trello.getBoard(BOARD_ID);
        List<TList> lists = board.fetchLists(arg("cards", "open"));

        assertThat(lists).hasSize(4);

        TList list = lists.get(0);
        assertThat(list.getCards()).hasSize(2);
    }

    @Test
    public void testGetBoardFetchMembers() {
        Board board = trello.getBoard(BOARD_ID);
        List<Member> members = board.fetchMembers();

        assertThat(members).hasSize(1);
    }

    @Test
    public void testGetBoardActions(){
        List<Action> boardActions = trello.getBoardActions(BOARD_ID);

        assertThat(boardActions).isNotNull();
        assertThat(boardActions).hasSize(26);
        assertThat(boardActions.get(0).getId()).isEqualTo("51990c2143453ab27e0087d5");
    }
}
