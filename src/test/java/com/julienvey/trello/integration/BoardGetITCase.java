package com.julienvey.trello.integration;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.julienvey.trello.utils.ArgUtils.arg;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

@RunWith(Parameterized.class)
public class BoardGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String BOARD_ID = "518baad5b05dbf4703004852";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{new ApacheHttpClient()}, {new AsyncTrelloHttpClient()}, {new RestTemplateHttpClient()}});
    }

    public BoardGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
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
        assertThat(board.getUrl()).isEqualTo("https://trello.com/b/laQO5ODb/trello-test-board");
        assertThat(board.isClosed()).isFalse();
        assertThat(board.isPinned()).isFalse();
        assertThat(board.getPrefs()).isNotNull();
    }

    @Test
    public void testGetBoardFetchLists() {
        Board board = trello.getBoard(BOARD_ID);
        List<TList> lists = board.fetchLists();

        assertThat(lists).hasSize(4);
    }

    @Test
    public void testGetBoardWithLists() {
        Board board = trello.getBoard(BOARD_ID, arg("lists", "all"));
        List<TList> lists = board.getLists();

        assertThat(lists).isNotNull();
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
    public void testGetBoardFetchCard() {
        Board board = trello.getBoard(BOARD_ID);
        Card card = board.fetchCard("518bab520967804c03002994");

        assertThat(card).isNotNull();
        assertThat(card.getId()).isEqualTo("518bab520967804c03002994");
        assertThat(card.getName()).isEqualTo("Test 1");

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
    public void testGetBoardFetchActions() {
        Board board = trello.getBoard(BOARD_ID);
        List<Action> actions = board.fetchActions();

        assertThat(actions).hasSize(26);
    }

    @Test
    public void testGetBoardFetchCards() {
        Board board = trello.getBoard(BOARD_ID);
        List<Card> cards = board.fetchCards();

        assertThat(cards).hasSize(2);
        assertThat(cards.get(0).getId()).isEqualTo("518bab520967804c03002994");
    }

    @Test
    public void testGetBoardFetchCheckLists() {
        Board board = trello.getBoard(BOARD_ID);
        List<CheckList> checkLists = board.fetchCheckLists();

        assertThat(checkLists).hasSize(2);
        assertThat(checkLists.get(0).getId()).isEqualTo("51990272b1740a191800e5af");
    }

    @Test
    public void testGetBoardFetchMemberCards() {
        Board board = trello.getBoard(BOARD_ID);
        List<Card> cards = board.fetchMemberCards("5187a69eabd0b7305100beaa");

        assertThat(cards).hasSize(1);
        assertThat(cards.get(0).getId()).isEqualTo("518bab520967804c03002994");
    }

    @Test
    public void testGetBoardFetchMembersInvited() {
        Board board = trello.getBoard(BOARD_ID);
        List<Member> members = board.fetchMembersInvited();

        assertThat(members).hasSize(0);
    }

    @Test
    public void testGetBoardFetchMyPrefs() {
        Board board = trello.getBoard(BOARD_ID);
        MyPrefs myPrefs = board.fetchMyPrefs();

        assertThat(myPrefs).isNotNull();
    }

    @Test
    public void testGetBoardFetchOrganization() {
        Board board = trello.getBoard(BOARD_ID);
        Organization organization = board.fetchOrganization();

        assertThat(organization).isNotNull();
        assertThat(organization.getId()).isEqualTo("518baaaa815af84031004375");
    }

    @Test
    public void testGetBoardActions(){
        List<Action> boardActions = trello.getBoardActions(BOARD_ID);

        assertThat(boardActions).isNotNull();
        assertThat(boardActions).hasSize(26);
        assertThat(boardActions.get(0).getId()).isEqualTo("51990c2143453ab27e0087d5");
    }

    @Test
    public void testGetBoardCards(){
        List<Card> boardCards = trello.getBoardCards(BOARD_ID);

        assertThat(boardCards).isNotNull();
        assertThat(boardCards).hasSize(2);
        assertThat(boardCards.get(0).getId()).isEqualTo("518bab520967804c03002994");
    }

    @Test
    public void testGetBoardCard(){
        Card boardCard = trello.getBoardCard(BOARD_ID, "518bab520967804c03002994");

        assertThat(boardCard).isNotNull();
    }

    @Test
    public void testGetBoardChecklists(){
        List<CheckList> boardChecklists = trello.getBoardChecklists(BOARD_ID);

        assertThat(boardChecklists).isNotNull();
        assertThat(boardChecklists).hasSize(2);
    }

    @Test
    public void testGetBoardLists(){
        List<TList> boardLists = trello.getBoardLists(BOARD_ID);

        assertThat(boardLists).isNotNull();
        assertThat(boardLists).hasSize(4);
        assertThat(boardLists.get(0).getId()).isEqualTo("518baad5b05dbf4703004853");
    }

    @Test
    public void testGetBoardMembers(){
        List<Member> boardMembers = trello.getBoardMembers(BOARD_ID, arg("fields", "all"));

        assertThat(boardMembers).isNotNull();
        assertThat(boardMembers).hasSize(1);
        assertThat(boardMembers.get(0).getId()).isEqualTo("5187a69eabd0b7305100beaa");
    }

    @Test
    public void testGetBoardMemberCards(){
        List<Card> boardMemberCards = trello.getBoardMemberCards(BOARD_ID, "5187a69eabd0b7305100beaa");

        assertThat(boardMemberCards).isNotNull();
        assertThat(boardMemberCards).hasSize(1);
    }

    @Test
    public void testGetBoardMembersInvited(){
        List<Member> boardMembersInvited = trello.getBoardMembersInvited(BOARD_ID);

        assertThat(boardMembersInvited).isNotNull();
        assertThat(boardMembersInvited).hasSize(0);
    }

    @Test
    public void testGetBoardMyPrefs(){
        MyPrefs boardMyMyPrefs = trello.getBoardMyPrefs(BOARD_ID);

        assertThat(boardMyMyPrefs).isNotNull();
    }

    @Test
    public void testGetBoardOrganization(){
        Organization boardOrganization = trello.getBoardOrganization(BOARD_ID);

        assertThat(boardOrganization).isNotNull();
        assertThat(boardOrganization.getId()).isEqualTo("518baaaa815af84031004375");
    }

	@Test
	public void testGetBoardMemberActivityComments() {
		List<CardWithActions> commentActivity = trello.getBoardMemberActivity(BOARD_ID,
				"5187a69eabd0b7305100beaa", "commentCard");

		assertThat(commentActivity).isNotNull();
		assertThat(commentActivity).hasSize(1);
		assertThat(commentActivity.get(0).getActions()).isNotNull();
		assertThat(commentActivity.get(0).getActions()).hasSize(1);
		assertThat(commentActivity.get(0).getActions().get(0)).isNotNull();
		assertThat(commentActivity.get(0).getActions().get(0).getData()).isNotNull();
		assertThat(commentActivity.get(0).getActions().get(0).getData().getText()).isNotNull();
		assertThat(commentActivity.get(0).getActions().get(0).getData().getText())
				.isEqualToIgnoringCase("a comment");
	}
}
