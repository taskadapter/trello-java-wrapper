package com.julienvey.trello;

import java.io.File;
import java.util.List;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.Argument;
import com.julienvey.trello.domain.Attachment;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Card;
import com.julienvey.trello.domain.CardWithActions;
import com.julienvey.trello.domain.CheckItem;
import com.julienvey.trello.domain.CheckList;
import com.julienvey.trello.domain.Entity;
import com.julienvey.trello.domain.Label;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.domain.MyPrefs;
import com.julienvey.trello.domain.Organization;
import com.julienvey.trello.domain.TList;

public interface Trello {

    /* Board */
    Board getBoard(String boardId, Argument... args);

    List<Action> getBoardActions(String boardId, Argument... args);

    List<Card> getBoardCards(String boardId, Argument... args);

    Card getBoardCard(String boardId, String cardId, Argument... args);

    List<CheckList> getBoardChecklists(String boardId, Argument... args);

    List<TList> getBoardLists(String boardId, Argument... args);

    List<Label> getBoardLabels(String boardId, Argument... args);

    List<Member> getBoardMembers(String boardId, Argument... args);

    List<Card> getBoardMemberCards(String boardId, String memberId, Argument... args);

    //FIXME Remove this method
    @Deprecated
	List<CardWithActions> getBoardMemberActivity(String boardId, String memberId,
			String actionsFilter, Argument... args);

    List<Member> getBoardMemberships(String boardId, Argument... args);

    MyPrefs getBoardMyPrefs(String boardId);

    Organization getBoardOrganization(String boardId, Argument... args);

    /* Actions */

    Action getAction(String actionId, Argument... args);

    Board getActionBoard(String actionId, Argument... args);

    Card getActionCard(String actionId, Argument... args);

    List<Entity> getActionEntities(String actionId);

    TList getActionList(String actionId, Argument... args);

    Member getActionMember(String actionId, Argument... args);

    Member getActionMemberCreator(String actionId, Argument... args);

    Organization getActionOrganization(String actionId, Argument... args);

    /* Cards */

    Card getCard(String cardId, Argument... args);

    List<Action> getCardActions(String cardId, Argument... args);

    List<Attachment> getCardAttachments(String cardId, Argument... args);

    List<Member> getCardMembers(String cardId, Argument... args);

    Attachment getCardAttachment(String cardId, String attachmentId, Argument... args);

    Board getCardBoard(String cardId, Argument... args);

    /* Lists */

    TList getList(String listId, Argument... args);

    List<Card> getListCards(String listId, Argument... args);

    /* CheckLists */

    CheckList getCheckList(String checkListId, Argument... args);

    CheckList createCheckList(String cardId, CheckList checkList);

    void createCheckItem(String checkListId, CheckItem checkItem);

    List<CheckList> getCardChecklists(String cardId, Argument... args);

    /* Organizations */

    List<Board> getOrganizationBoards(String organizationId, Argument... args);

    List<Member> getOrganizationMembers(String string, Argument... args);

    /////////////////

    Card createCard(String listId, Card card);

    void addLabelsToCard(String idCard, String[] labels);

    void addCommentToCard(String idCard, String comment);

    void addAttachmentToCard(String idCard, File file);

    void addUrlAttachmentToCard(String idCard, String url);

    /**
     *
     * @param idCard The id of the card.
     * @param attachmentId The id of the attachment to delete.
     */
    void deleteAttachment(String idCard, String attachmentId);

    List<Member> addMemberToCard(String idCard, String userId);

    List<Member> removeMemberFromCard(String idCard, String userId);

    Card updateCard(Card card);

    // FIXME Remove this method
    @Deprecated
    Member getBasicMemberInformation(String username);

    Member getMemberInformation(String username);

    List<Board> getMemberBoards(String userId, Argument... args);

    List<Card> getMemberCards(String userId, Argument... args);

    List<Action> getMemberActions(String userId, Argument... args);
}
