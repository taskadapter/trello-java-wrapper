package com.julienvey.trello;

import java.io.File;
import java.util.List;

import com.julienvey.trello.domain.Action;
import com.julienvey.trello.domain.AddMemberToBoardResult;
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
import com.julienvey.trello.domain.MemberType;
import com.julienvey.trello.domain.MyPrefs;
import com.julienvey.trello.domain.Organization;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.domain.Webhook;

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

    /**
     * Adds member with {@code email} email address to board with id {@code boardId}. The member will receive
     * permissions according {@code type}.
     *
     * @param boardId  The id of the board.
     * @param email    The email address of a user to add as a member of the board.
     * @param type     Determines what type of member the user being added should be of the board. Valid values: admin,
     *                 normal, observer. When {@code type} is {@code null} the {@code normal} type will be used.
     * @param fullName The full name of the user to as a member of the board. Must have a length of at least 1 and
     */
    AddMemberToBoardResult addMemberToBoard(String boardId, String email, MemberType type, String fullName);

    /**
     * Adds member with {@code memberId} board with id {@code boardId}. The member will receive permissions according
     * {@code type}.
     *
     * @param boardId  The id of the board.
     * @param memberId The id of the member to add to the board.
     * @param type     Determines what type of member the user being added should be of the board. Valid values: admin,
     *                 normal, observer. When {@code type} is {@code null} the {@code normal} type will be used.
     */
    AddMemberToBoardResult addMemberToBoard(String boardId, String memberId, MemberType type);

    /**
     * Removes member with {@code memberId} board with id {@code boardId}.
     *
     * @param boardId  The id of the board.
     * @param memberId The id of the member to add to the board.
     *
     * @throws NotFoundException When user with {@code memberId} does not exists or not a member of this board.
     */
    Board removeMemberFromBoard(String boardId, String memberId);

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

    /**
     * Deletes a card.
     * <b>Warning</b>
     * Deleting a card cannot be undone. Its safer to mark the card as closed (archived).
     *
     * @param cardId The ID of the card.
     */
    void deleteCard(String cardId);

    /* Lists */

    TList getList(String listId, Argument... args);

    List<Card> getListCards(String listId, Argument... args);

    /* CheckLists */

    CheckList getCheckList(String checkListId, Argument... args);

    CheckList createCheckList(String cardId, CheckList checkList);

    void createCheckItem(String checkListId, CheckItem checkItem);

    List<CheckList> getCardChecklists(String cardId, Argument... args);

    /* Organizations */

    Organization getOrganization(String organizationId, Argument... args);

    List<Board> getOrganizationBoards(String organizationId, Argument... args);

    List<Member> getOrganizationMembers(String string, Argument... args);

    /* Labels */

    /**
     * Get information about a label by id.
     *
     * @param labelId The label id.
     *
     * @return The label.
     */
    Label getLabel(String labelId, Argument... args);

    /**
     * Create a new label on a board.
     * <p>
     * The color valid values are: yellow, purple, blue, red, green, orange, black, sky, pink, lime, null. Label name
     * and board id is required.
     * <p>
     * Usage example:
     * <pre>
     * <code>
     * trello.createLabel(new Label()
     *                 .setName("Visited")
     *                 .setColor("green")
     *                 .setIdBoard("idBoard")
     *         );
     *
     * // Or using fluent API
     *
     * Label label = new Label()
     *                 .setInternalTrello(trello)
     *                 .setName("Visited")
     *                 .setColor("green")
     *                 .setIdBoard("idBoard")
     *                 .create();
     * </code>
     * @param label The label itself.
     *
     * @return The newly created label.
     */
    Label createLabel(Label label);

    /**
     * Create a new label on a board.
     *
     * @param label The label itself.
     *
     * @return The newly created label.
     */
    Label updateLabel(Label label);

    /**
     * Delete a label by id.
     *
     * @param labelId The label id.
     */
    void deleteLabel(String labelId);

    /////////////////

    Card createCard(String listId, Card card);

    void addLabelsToCard(String idCard, String[] labels);

    /**
     * Adds label to the card.
     *
     * @param cardId  The card id.
     * @param labelId The existing label id.
     *
     * @return The labels ids present on the card.
     */
    List<String> addLabelToCard(String cardId, String labelId);

    void addCommentToCard(String idCard, String comment);

    /**
     * Update an existing comment. Note that only the original author of a comment can update it.
     *
     * @param idCard          The id of the card.
     * @param commentActionId The comment action id.
     * @param text            The new text for the comment.
     *
     * @return The updated action.
     */
    Action updateComment(String idCard, String commentActionId, String text);

    void addAttachmentToCard(String idCard, File file);

    void addUrlAttachmentToCard(String idCard, String url);

    /**
     * @param idCard       The id of the card.
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

    Webhook createWebhook(Webhook webhook);

    Webhook getWebhook(String webhookId);

    Webhook updateWebhook(Webhook webhook);

    Webhook deleteWebhook(String webhookId);

    Member me();

    List<Webhook> getWebhooks();
}
