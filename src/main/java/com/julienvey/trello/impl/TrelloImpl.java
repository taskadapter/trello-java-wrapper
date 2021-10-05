package com.julienvey.trello.impl;

import static com.julienvey.trello.impl.TrelloUrl.ADD_ATTACHMENT_TO_CARD;
import static com.julienvey.trello.impl.TrelloUrl.ADD_CHECKITEMS_TO_CHECKLIST;
import static com.julienvey.trello.impl.TrelloUrl.ADD_COMMENT_TO_CARD;
import static com.julienvey.trello.impl.TrelloUrl.ADD_EXISTING_LABEL_TO_CARD;
import static com.julienvey.trello.impl.TrelloUrl.ADD_LABEL_TO_CARD;
import static com.julienvey.trello.impl.TrelloUrl.ADD_MEMBER_TO_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.ADD_MEMBER_TO_BOARD_BY_ID;
import static com.julienvey.trello.impl.TrelloUrl.ADD_MEMBER_TO_CARD;
import static com.julienvey.trello.impl.TrelloUrl.CREATE_CARD;
import static com.julienvey.trello.impl.TrelloUrl.CREATE_CHECKLIST;
import static com.julienvey.trello.impl.TrelloUrl.CREATE_LABEL;
import static com.julienvey.trello.impl.TrelloUrl.CREATE_WEBHOOK;
import static com.julienvey.trello.impl.TrelloUrl.DELETE_ATTACHMENT;
import static com.julienvey.trello.impl.TrelloUrl.DELETE_CARD;
import static com.julienvey.trello.impl.TrelloUrl.DELETE_LABEL;
import static com.julienvey.trello.impl.TrelloUrl.DELETE_WEBHOOK;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_CARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_ENTITIES;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_LIST;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_MEMBER;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_MEMBER_CREATOR;
import static com.julienvey.trello.impl.TrelloUrl.GET_ACTION_ORGANIZATION;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_ACTIONS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_CARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_CARDS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_CHECKLISTS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_LABELS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_LISTS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_MEMBERS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_MEMBERSHIPS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_MEMBER_CARDS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_MYPREFS;
import static com.julienvey.trello.impl.TrelloUrl.GET_BOARD_ORGANIZATION;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_ACTIONS;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_ATTACHMENT;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_ATTACHMENTS;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_CHECKLIST;
import static com.julienvey.trello.impl.TrelloUrl.GET_CARD_MEMBERS;
import static com.julienvey.trello.impl.TrelloUrl.GET_CHECK_LIST;
import static com.julienvey.trello.impl.TrelloUrl.GET_LABEL;
import static com.julienvey.trello.impl.TrelloUrl.GET_LIST;
import static com.julienvey.trello.impl.TrelloUrl.GET_LIST_CARDS;
import static com.julienvey.trello.impl.TrelloUrl.GET_MEMBER;
import static com.julienvey.trello.impl.TrelloUrl.GET_MEMBER_ACTIONS;
import static com.julienvey.trello.impl.TrelloUrl.GET_MEMBER_BOARDS;
import static com.julienvey.trello.impl.TrelloUrl.GET_MEMBER_CARDS;
import static com.julienvey.trello.impl.TrelloUrl.GET_ORGANIZATION;
import static com.julienvey.trello.impl.TrelloUrl.GET_ORGANIZATION_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.GET_ORGANIZATION_MEMBER;
import static com.julienvey.trello.impl.TrelloUrl.GET_WEBHOOK;
import static com.julienvey.trello.impl.TrelloUrl.REMOVE_MEMBER_FROM_BOARD;
import static com.julienvey.trello.impl.TrelloUrl.REMOVE_MEMBER_FROM_CARD;
import static com.julienvey.trello.impl.TrelloUrl.UPDATE_CARD;
import static com.julienvey.trello.impl.TrelloUrl.UPDATE_CARD_COMMENT;
import static com.julienvey.trello.impl.TrelloUrl.UPDATE_LABEL;
import static com.julienvey.trello.impl.TrelloUrl.UPDATE_WEBHOOK;
import static com.julienvey.trello.impl.TrelloUrl.ME;
import static com.julienvey.trello.impl.TrelloUrl.TOKEN_WEBHOOKS;
import static com.julienvey.trello.impl.TrelloUrl.createUrl;
import static com.julienvey.trello.impl.TrelloUrl.createUrlWithNoArgs;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.julienvey.trello.ListNotFoundException;
import com.julienvey.trello.NotFoundException;
import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloBadRequestException;
import com.julienvey.trello.TrelloHttpClient;
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
import com.julienvey.trello.domain.TrelloEntity;
import com.julienvey.trello.domain.Webhook;
import com.julienvey.trello.impl.domaininternal.Comment;
import com.julienvey.trello.impl.http.JDKTrelloHttpClient;

public class TrelloImpl implements Trello {

    private TrelloHttpClient httpClient;
    private String applicationKey;
    private String accessToken;

    private static Logger logger = LoggerFactory.getLogger(TrelloImpl.class);

    /**
     * Deprecated - use another constructor which accepts an instance of `TrelloHttpClient` instead of creating
     * one that is tied to Spring Web Framework.
     *
     * @see #TrelloImpl(String, String, TrelloHttpClient)
     */
    @Deprecated
    public TrelloImpl(String applicationKey, String accessToken) {
        this(applicationKey, accessToken, new JDKTrelloHttpClient());
    }

    public TrelloImpl(String applicationKey, String accessToken, TrelloHttpClient httpClient) {
        this.applicationKey = applicationKey;
        this.accessToken = accessToken;
        this.httpClient = httpClient;
    }

    /* Boards */

    @Override
    public Board getBoard(String boardId, Argument... args) {
        Board board = get(createUrl(GET_BOARD).params(args).asString(), Board.class, boardId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public List<Action> getBoardActions(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_ACTIONS).params(args).asString(), Action[].class, boardId));
    }

    @Override
    public List<Card> getBoardCards(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_CARDS).params(args).asString(), Card[].class, boardId));
    }

    @Override
    public Card getBoardCard(String boardId, String cardId, Argument... args) {
        Card card = get(createUrl(GET_BOARD_CARD).params(args).asString(), Card.class, boardId, cardId);
        card.setInternalTrello(this);
        return card;
    }

    @Override
    public List<CheckList> getBoardChecklists(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_CHECKLISTS).params(args).asString(), CheckList[].class, boardId));
    }

    @Override
    public List<Label> getBoardLabels(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_LABELS).params(args).asString(), Label[].class, boardId));
    }

    @Override
    public List<TList> getBoardLists(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_LISTS).params(args).asString(), TList[].class, boardId));
    }

    @Override
    public List<Member> getBoardMembers(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_MEMBERS).params(args).asString(), Member[].class, boardId));
    }

    @Override
    public List<Card> getBoardMemberCards(String boardId, String memberId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_MEMBER_CARDS).params(args).asString(), Card[].class, boardId, memberId));
    }

    @Override
    public AddMemberToBoardResult addMemberToBoard(String boardId, String email, MemberType type, String fullName) {
        Objects.requireNonNull(boardId);
        Objects.requireNonNull(email);

        Map<String, String> body = new HashMap<>(3);
        body.put("fullName", fullName);
        body.put("email", email);
        body.put("type", Optional.ofNullable(type).orElse(MemberType.NORMAL).value());

        AddMemberToBoardResult result = put(createUrl(ADD_MEMBER_TO_BOARD).asString(), body,
                AddMemberToBoardResult.class, boardId);
        result.setInternalTrello(this);

        return result;
    }

    @Override
    public AddMemberToBoardResult addMemberToBoard(String boardId, String memberId, MemberType type) {
        Objects.requireNonNull(boardId);
        Objects.requireNonNull(memberId);

        var body = Collections.singletonMap("type", Optional.ofNullable(type).orElse(MemberType.NORMAL).value());

        var result = put(createUrl(ADD_MEMBER_TO_BOARD_BY_ID).asString(), body,
                AddMemberToBoardResult.class, boardId, memberId);
        result.setInternalTrello(this);

        return result;
    }

    @Override
    public Board removeMemberFromBoard(String boardId, String memberId) {
        Objects.requireNonNull(boardId);
        Objects.requireNonNull(memberId);

        try {
            Board board = delete(createUrl(REMOVE_MEMBER_FROM_BOARD).asString(), Board.class, boardId, memberId);
            board.setInternalTrello(this);
            return board;
        } catch (TrelloBadRequestException e) {
            // Trello API uses very strange way to report this kind of problems.
            // we should rethrow proper exception
            if ("membership not found".equalsIgnoreCase(e.getMessage())) {
                throw new NotFoundException(String.format("User with member id %s is not member of %s board.",
                        memberId, boardId));
            } else if (e.getMessage().contains("\"message\":\"Invalid id or name\"")) {
                throw new NotFoundException(String.format("User with memberId or username %s is not found.", memberId));
            }
            throw e;
        }
    }

    // FIXME Remove this method
    @Override
    @Deprecated
    public List<CardWithActions> getBoardMemberActivity(String boardId, String memberId,
            String actionFilter, Argument... args) {
        if (actionFilter == null)
            actionFilter = "all";
        Argument[] argsAndFilter = Arrays.copyOf(args, args.length + 1);
        argsAndFilter[args.length] = new Argument("actions", actionFilter);

        return asList(() -> get(createUrl(GET_BOARD_MEMBER_CARDS).params(argsAndFilter).asString(),
                CardWithActions[].class, boardId, memberId));
    }

    @Override
    public List<Member> getBoardMemberships(String boardId, Argument... args) {
        return asList(() -> get(createUrl(GET_BOARD_MEMBERSHIPS).params(args).asString(), Member[].class, boardId));
    }

    @Override
    public MyPrefs getBoardMyPrefs(String boardId) {
        MyPrefs myPrefs = get(createUrl(GET_BOARD_MYPREFS).asString(), MyPrefs.class, boardId);
        myPrefs.setInternalTrello(this);
        return myPrefs;
    }

    @Override
    public Organization getBoardOrganization(String boardId, Argument... args) {
        Organization organization = get(createUrl(GET_BOARD_ORGANIZATION).params(args).asString(), Organization.class, boardId);
        organization.setInternalTrello(this);
        return organization;
    }

    /* Action */

    @Override
    public Action getAction(String actionId, Argument... args) {
        Action action = get(createUrl(GET_ACTION).params(args).asString(), Action.class, actionId);
        action.setInternalTrello(this);
        return action;
    }

    @Override
    public Board getActionBoard(String actionId, Argument... args) {
        Board board = get(createUrl(GET_ACTION_BOARD).params(args).asString(), Board.class, actionId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public Card getActionCard(String actionId, Argument... args) {
        Card card = get(createUrl(GET_ACTION_CARD).params(args).asString(), Card.class, actionId);
        card.setInternalTrello(this);
        return card;
    }

    @Override
    public List<Entity> getActionEntities(String actionId) {
        return asList(() -> get(createUrl(GET_ACTION_ENTITIES).asString(), Entity[].class, actionId));
    }

    @Override
    public TList getActionList(String actionId, Argument... args) {
        TList tList = get(createUrl(GET_ACTION_LIST).params(args).asString(), TList.class, actionId);
        tList.setInternalTrello(this);
        return tList;
    }

    @Override
    public Member getActionMember(String actionId, Argument... args) {
        Member member = get(createUrl(GET_ACTION_MEMBER).params(args).asString(), Member.class, actionId);
        member.setInternalTrello(this);
        return member;
    }

    @Override
    public Member getActionMemberCreator(String actionId, Argument... args) {
        Member member = get(createUrl(GET_ACTION_MEMBER_CREATOR).params(args).asString(), Member.class, actionId);
        member.setInternalTrello(this);
        return member;
    }

    @Override
    public Organization getActionOrganization(String actionId, Argument... args) {
        Organization organization = get(createUrl(GET_ACTION_ORGANIZATION).params(args).asString(), Organization.class, actionId);
        organization.setInternalTrello(this);
        return organization;
    }

    @Override
    public Card getCard(String cardId, Argument... args) {
        Card card = get(createUrl(GET_CARD).params(args).asString(), Card.class, cardId);
        card.setInternalTrello(this);
        return card;
    }

    @Override
    public List<Action> getCardActions(String cardId, Argument... args) {
        return asList(() -> get(createUrl(GET_CARD_ACTIONS).params(args).asString(), Action[].class, cardId));
    }

    @Override
    public List<Attachment> getCardAttachments(String cardId, Argument... args) {
        return asList(() -> get(createUrl(GET_CARD_ATTACHMENTS).params(args).asString(), Attachment[].class, cardId));

    }

    @Override
    public List<Member> getCardMembers(String cardId, Argument... args) {
        return asList(() -> get(createUrl(GET_CARD_MEMBERS).params(args).asString(), Member[].class, cardId));
    }

    @Override
    public Attachment getCardAttachment(String cardId, String attachmentId, Argument... args) {
        Attachment attachment = get(createUrl(GET_CARD_ATTACHMENT).params(args).asString(), Attachment.class, cardId, attachmentId);
        attachment.setInternalTrello(this);
        return attachment;
    }

    @Override
    public Board getCardBoard(String cardId, Argument... args) {
        Board board = get(createUrl(GET_CARD_BOARD).params(args).asString(), Board.class, cardId);
        board.setInternalTrello(this);
        return board;
    }

    @Override
    public void deleteCard(String cardId) {
        Objects.requireNonNull(cardId);
        delete(createUrlWithNoArgs(DELETE_CARD), Map.class, cardId);
    }

    @Override
    public List<CheckList> getCardChecklists(String cardId, Argument... args) {
        return asList(() -> get(createUrl(GET_CARD_CHECKLIST).params(args).asString(), CheckList[].class, cardId));
    }

    /* Lists */

    @Override
    public TList getList(String listId, Argument... args) {
        TList tList = get(createUrl(GET_LIST).params(args).asString(), TList.class, listId);
        tList.setInternalTrello(this);
        return tList;
    }

    @Override
    public List<Card> getListCards(String listId, Argument... args) {
        return asList(() -> get(createUrl(GET_LIST_CARDS).params(args).asString(), Card[].class, listId));
    }

    /* Organizations */
    @Override
    public Organization getOrganization(String organizationId, Argument... args) {
    	Organization organization = get(createUrl(GET_ORGANIZATION).params(args).asString(), Organization.class, organizationId);
    	organization.setInternalTrello(this);
        return organization;
    }

    @Override
    public List<Board> getOrganizationBoards(String organizationId, Argument... args) {
        return asList(() -> get(createUrl(GET_ORGANIZATION_BOARD).params(args).asString(), Board[].class, organizationId));
    }

    @Override
    public List<Member> getOrganizationMembers(String organizationId, Argument... args) {
        return asList(() -> get(createUrl(GET_ORGANIZATION_MEMBER).params(args).asString(), Member[].class, organizationId));
    }

    @Override
    public Label getLabel(String labelId, Argument... args) {
        Label label = get(createUrl(GET_LABEL).params(args).asString(), Label.class, labelId);
        return label.setInternalTrello(this);
    }

    @Override
    public Label createLabel(Label label) {
        Label createdLabel = postForObject(createUrlWithNoArgs(CREATE_LABEL), label, Label.class);
        return createdLabel.setInternalTrello(this);
    }

    @Override
    public Label updateLabel(Label label) {
        Label updatedLabel = put(createUrlWithNoArgs(UPDATE_LABEL), label, Label.class, label.getId());
        return updatedLabel.setInternalTrello(this);
    }

    @Override
    public void deleteLabel(String labelId) {
        delete(createUrlWithNoArgs(DELETE_LABEL), Map.class, labelId);
    }

    /* CheckLists */

    @Override
    public CheckList getCheckList(String checkListId, Argument... args) {
        CheckList checkList = get(createUrl(GET_CHECK_LIST).params(args).asString(), CheckList.class, checkListId);
        checkList.setInternalTrello(this);
        return checkList;
    }

    @Override
    public CheckList createCheckList(String cardId, CheckList checkList) {
        checkList.setIdCard(cardId);
        CheckList createdCheckList = postForObject(createUrl(CREATE_CHECKLIST).asString(), checkList, CheckList.class);
        createdCheckList.setInternalTrello(this);
        return createdCheckList;
    }

    @Override
    public void createCheckItem(String checkListId, CheckItem checkItem) {
        postForLocation(createUrl(ADD_CHECKITEMS_TO_CHECKLIST).asString(), checkItem, checkListId);
    }

    /* Others */

    @Override
    public Card createCard(String listId, Card card) {
        card.setIdList(listId);
        try {
            Card createdCard = postForObject(createUrl(CREATE_CARD).asString(), card, Card.class);
            createdCard.setInternalTrello(this);
            return createdCard;
        } catch (TrelloBadRequestException e) {
            throw decodeException(card, e);
        }
    }

    @Override
    public Card updateCard(Card card) {
        try {
            Card put = put(createUrl(UPDATE_CARD).asString(), card, Card.class, card.getId());
            put.setInternalTrello(this);
            return put;
        } catch (TrelloBadRequestException e) {
            throw decodeException(card, e);
        }
    }

    private static TrelloBadRequestException decodeException(Card card, TrelloBadRequestException e) {
        if (e.getMessage().contains("invalid value for idList")) {
            return new ListNotFoundException(card.getIdList());
        }
        if (e instanceof NotFoundException) {
            return new NotFoundException("Card with id " + card.getId() + " is not found. It may have been deleted in Trello");
        }
        return e;
    }

    @Override
    // FIXME Remove this method
    @Deprecated
    public Member getBasicMemberInformation(String username) {
        Member member = get(createUrl(GET_MEMBER).params(new Argument("fields", "username,fullName")).asString(), Member.class, username);
        member.setInternalTrello(this);
        return member;
    }

    @Override
    public Member getMemberInformation(String username) {
        Member member = get(createUrl(GET_MEMBER).asString(), Member.class, username);
        member.setInternalTrello(this);
        return member;
    }

    @Override
    public List<Board> getMemberBoards(String userId, Argument... args) {
        return asList(() -> get(createUrl(GET_MEMBER_BOARDS).params(args).asString(), Board[].class, userId));
    }

    @Override
    public List<Card> getMemberCards(String userId, Argument... args) {
        return asList(() -> get(createUrl(GET_MEMBER_CARDS).params(args).asString(), Card[].class, userId));
    }

    @Override
    public List<Action> getMemberActions(String userId, Argument... args) {
        return asList(() -> get(createUrl(GET_MEMBER_ACTIONS).params(args).asString(), Action[].class, userId));
    }

    @Override
    public void addLabelsToCard(String idCard, String[] labels) {
        for (String labelName : labels) {
            Label label = new Label();
            label.setName(labelName);
            postForObject(createUrl(ADD_LABEL_TO_CARD).asString(), label, Label.class, idCard);
        }
    }

    @Override
    public List<String> addLabelToCard(String cardId, String labelId) {
        Objects.requireNonNull(cardId);
        Objects.requireNonNull(labelId);

        return Arrays.asList(postForObject(createUrlWithNoArgs(ADD_EXISTING_LABEL_TO_CARD),
                Collections.singletonMap("value", labelId), String[].class, cardId));
    }

    @Override
    public void addCommentToCard(String idCard, String comment) {
        postForObject(createUrl(ADD_COMMENT_TO_CARD).asString(), new Comment(comment), Comment.class, idCard);
    }

    @Override
    public Action updateComment(String idCard, String commentActionId, String text) {
        return put(createUrlWithNoArgs(UPDATE_CARD_COMMENT), new Comment(text), Action.class, idCard, commentActionId);
    }

    @Override
    public void addAttachmentToCard(String idCard, File file) {
        postFileForObject(createUrl(ADD_ATTACHMENT_TO_CARD).asString(), file, Attachment.class, idCard);
    }

    @Override
    public void addUrlAttachmentToCard(String idCard, String url) {
        postForObject(createUrl(ADD_ATTACHMENT_TO_CARD).asString(), new Attachment(url), Attachment.class, idCard);
    }

    @Override
    public void deleteAttachment(String idCard, String attachmentId) {
        delete(createUrlWithNoArgs(DELETE_ATTACHMENT), Map.class, idCard, attachmentId);
    }

    @Override
    public List<Member> addMemberToCard(String idCard, String userId) {
        return asList(() -> postForObject(createUrl(ADD_MEMBER_TO_CARD).asString(), Collections.singletonMap("value", userId),
                Member[].class, idCard));
    }

    @Override
    public List<Member> removeMemberFromCard(String idCard, String userId) {
        return asList(() -> delete(createUrl(REMOVE_MEMBER_FROM_CARD).asString(), Member[].class, idCard, userId));
    }

    @Override
    public Webhook createWebhook(Webhook webhook) {
        Webhook createdWebhook = postForObject(createUrlWithNoArgs(CREATE_WEBHOOK), webhook, Webhook.class);
        return createdWebhook.setInternalTrello(this);
    }

    @Override
    public Webhook getWebhook(String webhookId) {
        return get(createUrl(GET_WEBHOOK).asString(), Webhook.class, webhookId);
    }

    @Override
    public Webhook updateWebhook(Webhook webhook) {
        Webhook createdWebhook = put(createUrlWithNoArgs(UPDATE_WEBHOOK), webhook, Webhook.class, webhook.getId());
        return createdWebhook.setInternalTrello(this);
    }

    @Override
    public Webhook deleteWebhook(String webhookId) {
        return delete(createUrl(DELETE_WEBHOOK).asString(), Webhook.class, webhookId);
    }

    @Override
    public Member me() {
        return get(createUrlWithNoArgs(ME), Member.class);
    }

    @Override
    public List<Webhook> getWebhooks() {
        return asList(() -> get(createUrl(TOKEN_WEBHOOKS).asString(), Webhook[].class, accessToken));
    }

    /* internal methods */

    private <T> T postFileForObject(String url, File file, Class<T> objectClass, String... params) {
        logger.debug("PostFileForObject request on Trello API at url {} for class {} with params {}", url,
                objectClass.getCanonicalName(), params);

        return httpClient.postFileForObject(url, file, objectClass, enrichParams(params));
    }

    private <T> T postForObject(String url, Object object, Class<T> objectClass, String... params) {
        logger.debug("PostForObject request on Trello API at url {} for class {} with params {}", url, objectClass.getCanonicalName(), params);
        return httpClient.postForObject(url, object, objectClass, enrichParams(params));
    }

    private void postForLocation(String url, Object object, String... params) {
        logger.debug("PostForLocation request on Trello API at url {} for class {} with params {}", url, object.getClass().getCanonicalName(), params);
        httpClient.postForLocation(url, object, enrichParams(params));
    }

    private <T> T get(String url, Class<T> objectClass, String... params) {
        logger.debug("Get request on Trello API at url {} for class {} with params {}", url, objectClass.getCanonicalName(), params);
        return httpClient.get(url, objectClass, enrichParams(params));
    }

    private <T> T put(String url, Object object, Class<T> objectClass, String... params) {
        logger.debug("Put request on Trello API at url {} for class {} with params {}", url, object.getClass().getCanonicalName(), params);
        return httpClient.putForObject(url, object, objectClass, enrichParams(params));
    }

    private <T> T delete(String url, Class<T> responseType, String... params) {
        logger.debug("Delete request on Trello API at url {} for class {} with params {}", url, responseType.getClass().getCanonicalName(), params);
        return httpClient.delete(url, responseType, enrichParams(params));
    }

    private String[] enrichParams(String[] params) {
        var paramList = new ArrayList<>(Arrays.asList(params));
        paramList.add(applicationKey);
        paramList.add(accessToken);
        return paramList.toArray(new String[paramList.size()]);
    }

    private <T extends TrelloEntity> List<T> asList(Supplier<T[]> responseSupplier) {
        return Arrays.stream(responseSupplier.get())
                .peek(t -> t.setInternalTrello(this))
                .collect(Collectors.toList());
    }
}
