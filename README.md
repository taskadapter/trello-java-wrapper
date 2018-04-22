Java Wrapper for Trello API

## Overview

This project is a Java Wrapper for the [Trello API](https://trello.com/docs/). It provides a fluent interface for requesting the API

## Usage

### Maven dependency

trello-java-wrapper is available on maven central. The current release is 0.3.2

```xml
<dependency>
    <groupId>com.julienvey.trello</groupId>
    <artifactId>trello-java-wrapper</artifactId>
    <version>0.3.2</version>
</dependency>
```

The wrapper can make use of one of the following HTTP clients: `Spring Web` (default), `Apache Http Components HttpClient`, `Ning async-http-client`. Choose one if you dont' already use one of those. If you use or choose anything but Spring Web, you'll need to instantiate the corresponding `TrelloHttpClient` implementation and pass it to the `TrelloImpl` constructor (see Init section below). The corresponding Maven coordinates are:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
</dependency>
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
</dependency>
<dependency>
    <groupId>com.ning</groupId>
    <artifactId>async-http-client</artifactId>
</dependency>
```
Failure to do so will most probably cause a `NoClassDefFoundError`.

### Init

To be able to use the wrapper, you simply need to instantiate the interface `Trello` and provide it with two parameters

* trelloKey : The key of your application
* trelloAccessToken : the oauth token of the authenticated user on Trello

```java
Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken);
```

### Usage

Then, simply use the object you just created to access the API

```java
Board board = trelloApi.getBoard(trelloBoardForAddingCardsId);
```

The wrapper provides a fluent interface. On each "Trello" Object, you have access to methods to fetch other object

```java
Board board = trelloApi.getBoard(trelloBoardForAddingCardsId);
List<TList> lists = board.fetchLists();
```

which can also be written like this

```java
List<TList> lists = trelloApi.getBoard(trelloBoardForAddingCardsId).fetchLists();
```

## Contribute

trello-java-wrapper is still a work in progress and has not yet reached version 1.0. It is licensed under the Apache v2 License.

If you are missing some fonctionnalities, you can easily contribute and propose a pull request. Each pull request should respect current code conventions and also provide tests for the newly implemented features.

![Build Status](https://img.shields.io/travis/bywan/trello-java-wrapper/dev.svg?style=flat-square)

## Implementation Status

| Method                    | Impl | Unit Test  | Int Test |
| ------------------------- | :--: | :--------: | :------: |
| GET /1/actions/[idAction] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/[field]
| GET /1/actions/[idAction]/board  | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/board/[field]
| GET /1/actions/[idAction]/card | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/card/[field]
| GET /1/actions/[idAction]/entities | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/list | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/list/[field]
| GET /1/actions/[idAction]/member | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/member/[field]
| GET /1/actions/[idAction]/memberCreator | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/memberCreator/[field]
| GET /1/actions/[idAction]/organization | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/actions/[idAction]/organization/[field]
| PUT /1/actions/[idAction]
| PUT /1/actions/[idAction]/text
| DELETE /1/actions/[idAction]
| GET /1/boards/[board_id] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/[field]
| GET /1/boards/[board_id]/actions | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/cards | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/cards/[filter]
| GET /1/boards/[board_id]/cards/[idCard]  | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/checklists | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/lists | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/lists/[filter]
| GET /1/boards/[board_id]/members | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/members/[filter]
| GET /1/boards/[board_id]/members/[idMember]/cards | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/membersInvited | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/membersInvited/[field]
| GET /1/boards/[board_id]/memberships
| GET /1/boards/[board_id]/memberships/[idMembership]
| GET /1/boards/[board_id]/myPrefs | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/organization | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/boards/[board_id]/organization/[field]
| PUT /1/boards/[board_id]
| PUT /1/boards/[board_id]/closed
| PUT /1/boards/[board_id]/desc
| PUT /1/boards/[board_id]/idOrganization
| PUT /1/boards/[board_id]/labelNames/blue
| PUT /1/boards/[board_id]/labelNames/green
| PUT /1/boards/[board_id]/labelNames/orange
| PUT /1/boards/[board_id]/labelNames/purple
| PUT /1/boards/[board_id]/labelNames/red
| PUT /1/boards/[board_id]/labelNames/yellow
| PUT /1/boards/[board_id]/members
| PUT /1/boards/[board_id]/members/[idMember]
| PUT /1/boards/[board_id]/memberships/[idMembership]
| PUT /1/boards/[board_id]/myPrefs/emailPosition
| PUT /1/boards/[board_id]/myPrefs/idEmailList
| PUT /1/boards/[board_id]/myPrefs/showListGuide
| PUT /1/boards/[board_id]/myPrefs/showSidebar
| PUT /1/boards/[board_id]/myPrefs/showSidebarActivity
| PUT /1/boards/[board_id]/myPrefs/showSidebarBoardActions
| PUT /1/boards/[board_id]/myPrefs/showSidebarMembers
| PUT /1/boards/[board_id]/name
| PUT /1/boards/[board_id]/prefs/calendarFeedEnabled
| PUT /1/boards/[board_id]/prefs/cardAging
| PUT /1/boards/[board_id]/prefs/cardCovers
| PUT /1/boards/[board_id]/prefs/comments
| PUT /1/boards/[board_id]/prefs/invitations
| PUT /1/boards/[board_id]/prefs/permissionLevel
| PUT /1/boards/[board_id]/prefs/selfJoin
| PUT /1/boards/[board_id]/prefs/voting
| PUT /1/boards/[board_id]/subscribed
| POST /1/boards
| POST /1/boards/[board_id]/calendarKey/generate
| POST /1/boards/[board_id]/checklists
| POST /1/boards/[board_id]/emailKey/generate
| POST /1/boards/[board_id]/lists
| POST /1/boards/[board_id]/markAsViewed
| POST /1/boards/[board_id]/powerUps
| DELETE /1/boards/[board_id]/members/[idMember]
| DELETE /1/boards/[board_id]/powerUps/[powerUp]
| GET /1/cards/[card id or shortlink] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/cards/[card id or shortlink]/[field]
| GET /1/cards/[card id or shortlink]/actions | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/cards/[card id or shortlink]/attachments | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/cards/[card id or shortlink]/attachments/[idAttachment] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/cards/[card id or shortlink]/board | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/cards/[card id or shortlink]/board/[field]
| GET /1/cards/[card id or shortlink]/checkItemStates
| GET /1/cards/[card id or shortlink]/checklists
| GET /1/cards/[card id or shortlink]/list
| GET /1/cards/[card id or shortlink]/list/[field]
| GET /1/cards/[card id or shortlink]/members
| GET /1/cards/[card id or shortlink]/membersVoted
| PUT /1/cards/[card id or shortlink] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| PUT /1/cards/[card id or shortlink]/actions/[idAction]/comments
| PUT /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]/name
| PUT /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]/pos
| PUT /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]/state
| PUT /1/cards/[card id or shortlink]/checklist/[idChecklistCurrent]/checkItem/[idCheckItem]
| PUT /1/cards/[card id or shortlink]/closed
| PUT /1/cards/[card id or shortlink]/desc
| PUT /1/cards/[card id or shortlink]/due
| PUT /1/cards/[card id or shortlink]/idAttachmentCover
| PUT /1/cards/[card id or shortlink]/idBoard
| PUT /1/cards/[card id or shortlink]/idList
| PUT /1/cards/[card id or shortlink]/idMembers
| PUT /1/cards/[card id or shortlink]/labels
| PUT /1/cards/[card id or shortlink]/name
| PUT /1/cards/[card id or shortlink]/pos
| PUT /1/cards/[card id or shortlink]/subscribed
| PUT /1/cards/[card id or shortlink]/warnWhenUpcoming
| POST /1/cards | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| POST /1/cards/[card id or shortlink]/actions/comments | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| POST /1/cards/[card id or shortlink]/attachments
| POST /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem
| POST /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]/convertToCard
| POST /1/cards/[card id or shortlink]/checklists
| POST /1/cards/[card id or shortlink]/idMembers
| POST /1/cards/[card id or shortlink]/labels  | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| POST /1/cards/[card id or shortlink]/markAssociatedNotificationsRead
| POST /1/cards/[card id or shortlink]/membersVoted
| DELETE /1/cards/[card id or shortlink]
| DELETE /1/cards/[card id or shortlink]/actions/[idAction]/comments
| DELETE /1/cards/[card id or shortlink]/attachments/[idAttachment]
| DELETE /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]
| DELETE /1/cards/[card id or shortlink]/checklists/[idChecklist]
| DELETE /1/cards/[card id or shortlink]/idMembers/[idMember]
| DELETE /1/cards/[card id or shortlink]/labels/[color]
| DELETE /1/cards/[card id or shortlink]/membersVoted/[idMember]
| GET /1/checklists/[idChecklist] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/checklists/[idChecklist]/[field]
| GET /1/checklists/[idChecklist]/board
| GET /1/checklists/[idChecklist]/board/[field]
| GET /1/checklists/[idChecklist]/cards
| GET /1/checklists/[idChecklist]/cards/[filter]
| GET /1/checklists/[idChecklist]/checkItems
| GET /1/checklists/[idChecklist]/checkItems/[idCheckItem]
| PUT /1/checklists/[idChecklist]
| PUT /1/checklists/[idChecklist]/idCard
| PUT /1/checklists/[idChecklist]/name
| PUT /1/checklists/[idChecklist]/pos
| POST /1/checklists
| POST /1/checklists/[idChecklist]/checkItems
| DELETE /1/checklists/[idChecklist]
| DELETE /1/checklists/[idChecklist]/checkItems/[idCheckItem]
| GET /1/lists/[idList] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png) | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/lists/[idList]/[field]
| GET /1/lists/[idList]/actions
| GET /1/lists/[idList]/board
| GET /1/lists/[idList]/board/[field]
| GET /1/lists/[idList]/cards
| GET /1/lists/[idList]/cards/[filter]
| PUT /1/lists/[idList]
| PUT /1/lists/[idList]/closed
| PUT /1/lists/[idList]/idBoard
| PUT /1/lists/[idList]/name
| PUT /1/lists/[idList]/pos
| PUT /1/lists/[idList]/subscribed
| POST /1/lists
| POST /1/lists/[idList]/cards
| GET /1/members/[idMember or username] | ![](http://icdn.pro/images/fr/v/e/verifier-vert-ok-icone-8505-48.png)
| GET /1/members/[idMember or username]/[field]
| GET /1/members/[idMember or username]/actions
| GET /1/members/[idMember or username]/boards
| GET /1/members/[idMember or username]/boards/[filter]
| GET /1/members/[idMember or username]/boardsInvited
| GET /1/members/[idMember or username]/boardsInvited/[field]
| GET /1/members/[idMember or username]/cards
| GET /1/members/[idMember or username]/cards/[filter]
| GET /1/members/[idMember or username]/notifications
| GET /1/members/[idMember or username]/notifications/[filter]
| GET /1/members/[idMember or username]/organizations
| GET /1/members/[idMember or username]/organizations/[filter]
| GET /1/members/[idMember or username]/organizationsInvited
| GET /1/members/[idMember or username]/organizationsInvited/[field]
| GET /1/members/[idMember or username]/sessions
| GET /1/members/[idMember or username]/tokens
| PUT /1/members/[idMember or username]
| PUT /1/members/[idMember or username]/avatarSource
| PUT /1/members/[idMember or username]/bio
| PUT /1/members/[idMember or username]/fullName
| PUT /1/members/[idMember or username]/initials
| PUT /1/members/[idMember or username]/prefs/colorBlind
| PUT /1/members/[idMember or username]/prefs/minutesBetweenSummaries
| PUT /1/members/[idMember or username]/username
| POST /1/members/[idMember or username]/avatar
| POST /1/members/[idMember or username]/idBoardsPinned
| POST /1/members/[idMember or username]/oneTimeMessagesDismissed
| DELETE /1/members/[idMember or username]/idBoardsPinned/[idBoard]
| GET /1/notifications/[idNotification]
| GET /1/notifications/[idNotification]/[field]
| GET /1/notifications/[idNotification]/board
| GET /1/notifications/[idNotification]/board/[field]
| GET /1/notifications/[idNotification]/card
| GET /1/notifications/[idNotification]/card/[field]
| GET /1/notifications/[idNotification]/entities
| GET /1/notifications/[idNotification]/list
| GET /1/notifications/[idNotification]/list/[field]
| GET /1/notifications/[idNotification]/member
| GET /1/notifications/[idNotification]/member/[field]
| GET /1/notifications/[idNotification]/memberCreator
| GET /1/notifications/[idNotification]/memberCreator/[field]
| GET /1/notifications/[idNotification]/organization
| GET /1/notifications/[idNotification]/organization/[field]
| PUT /1/notifications/[idNotification]
| PUT /1/notifications/[idNotification]/unread
| POST /1/notifications/all/read
| GET /1/organizations/[idOrg or name]
| GET /1/organizations/[idOrg or name]/[field]
| GET /1/organizations/[idOrg or name]/actions
| GET /1/organizations/[idOrg or name]/boards
| GET /1/organizations/[idOrg or name]/boards/[filter]
| GET /1/organizations/[idOrg or name]/members
| GET /1/organizations/[idOrg or name]/members/[filter]
| GET /1/organizations/[idOrg or name]/members/[idMember]/cards
| GET /1/organizations/[idOrg or name]/membersInvited
| GET /1/organizations/[idOrg or name]/membersInvited/[field]
| GET /1/organizations/[idOrg or name]/memberships
| GET /1/organizations/[idOrg or name]/memberships/[idMembership]
| PUT /1/organizations/[idOrg or name]
| PUT /1/organizations/[idOrg or name]/desc
| PUT /1/organizations/[idOrg or name]/displayName
| PUT /1/organizations/[idOrg or name]/members
| PUT /1/organizations/[idOrg or name]/members/[idMember]
| PUT /1/organizations/[idOrg or name]/members/[idMember]/deactivated
| PUT /1/organizations/[idOrg or name]/memberships/[idMembership]
| PUT /1/organizations/[idOrg or name]/name
| PUT /1/organizations/[idOrg or name]/prefs/associatedDomain
| PUT /1/organizations/[idOrg or name]/prefs/boardVisibilityRestrict/org
| PUT /1/organizations/[idOrg or name]/prefs/boardVisibilityRestrict/private
| PUT /1/organizations/[idOrg or name]/prefs/boardVisibilityRestrict/public
| PUT /1/organizations/[idOrg or name]/prefs/externalMembersDisabled
| PUT /1/organizations/[idOrg or name]/prefs/orgInviteRestrict
| PUT /1/organizations/[idOrg or name]/prefs/permissionLevel
| PUT /1/organizations/[idOrg or name]/website
| POST /1/organizations
| POST /1/organizations/[idOrg or name]/logo
| DELETE /1/organizations/[idOrg or name]
| DELETE /1/organizations/[idOrg or name]/logo
| DELETE /1/organizations/[idOrg or name]/members/[idMember]
| DELETE /1/organizations/[idOrg or name]/members/[idMember]/all
| DELETE /1/organizations/[idOrg or name]/prefs/associatedDomain
| DELETE /1/organizations/[idOrg or name]/prefs/orgInviteRestrict
| GET /1/search
| GET /1/search/members
| GET /1/tokens/[token]
| GET /1/tokens/[token]/[field]
| GET /1/tokens/[token]/member
| GET /1/tokens/[token]/member/[field]
| GET /1/tokens/[token]/webhooks
| GET /1/tokens/[token]/webhooks/[idWebhook]
| POST /1/tokens/[token]/webhooks
| DELETE /1/tokens/[token]
| DELETE /1/tokens/[token]/webhooks/[idWebhook]
| GET /1/types/[id]
| GET /1/webhooks/[idWebhook]
| GET /1/webhooks/[idWebhook]/[field]
| PUT /1/webhooks/[idWebhook]
| PUT /1/webhooks/[idWebhook]/active
| PUT /1/webhooks/[idWebhook]/callbackURL
| PUT /1/webhooks/[idWebhook]/description
| PUT /1/webhooks/[idWebhook]/idModel
| POST /1/webhooks
| DELETE /1/webhooks/[idWebhook]
