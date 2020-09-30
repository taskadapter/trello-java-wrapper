Java Wrapper for Trello API

## Overview

This project is a Java Wrapper for the [Trello REST API](https://trello.com/docs/). It provides a fluent interface for requesting the API.

## Usage

The library can be used with Java 11 or newer. 

Available in [Maven Central](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.taskadapter%22%20AND%20a%3A%22trello-java-wrapper%22).
Gradle dependency declaration:

```
dependencies {
    compile 'com.taskadapter:trello-java-wrapper:<current-version>'
}
```

The wrapper can make use of one of the following HTTP clients: 

- JDKTrelloHttpClient
    - pure JDK http client, no other dependencies
- [OKHttp](https://github.com/square/okhttp)
    - `compile 'com.squareup.okhttp3:okhttp:VERSION'`
- `Spring Web`
    - `compile 'org.springframework:spring-web:VERSION'`
- [Apache Http Components HttpClient](https://hc.apache.org/)
    - `compile 'org.apache.httpcomponents:httpclient:VERSION'`
- [Async Http Client](https://github.com/AsyncHttpClient/async-http-client/)
    - `compile 'org.asynchttpclient:async-http-client:VERSION'`
- [Ning async-http-client](https://github.com/ning/async-http-client)
    - The predecessor to the modern Async Http Client above
    - `compile 'com.ning:async-http-client:VERSION'`
 
Choose one if you don't already use one of those. 
You will need to instantiate the corresponding `TrelloHttpClient` implementation and pass it to the `TrelloImpl` 
constructor (see Init section below). 

```
Optional dependency in case you want to attach files to cards:

compile 'org.apache.httpcomponents:httpmime'

```
Failure to do so will most probably cause a `NoClassDefFoundError`.

### Init

First, create an instance of `TrelloImpl`. This examples uses an http client based on Apache Http library.
 
```java
Trello trelloApi = new TrelloImpl(trelloKey, trelloAccessToken, new ApacheHttpClient());
```

* trelloKey : The key of your application
* trelloAccessToken : the oauth token of the authenticated user on Trello
* http client : one of supported http client implementations. See `com.julienvey.trello.impl.http` package.

### Usage

Sample call:
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

Each pull request should respect current code conventions and provide tests for the newly implemented features.
The new test code is implemented using Scala. The prod code is only Java - to avoid adding extra Scala dependency to the lib without providing much value (at the moment).

## Implementation Status

| Method                    | Impl | Unit Test  | Int Test |
| ------------------------- | :--: | :--------: | :------: |
| GET /1/actions/[idAction] | Yes | Yes | Yes
| GET /1/actions/[idAction]/[field]
| GET /1/actions/[idAction]/board  | Yes | Yes | Yes
| GET /1/actions/[idAction]/board/[field]
| GET /1/actions/[idAction]/card | Yes | Yes | Yes
| GET /1/actions/[idAction]/card/[field]
| GET /1/actions/[idAction]/entities | Yes | Yes | Yes
| GET /1/actions/[idAction]/list | Yes | Yes | Yes
| GET /1/actions/[idAction]/list/[field]
| GET /1/actions/[idAction]/member | Yes | Yes | Yes
| GET /1/actions/[idAction]/member/[field]
| GET /1/actions/[idAction]/memberCreator | Yes | Yes | Yes
| GET /1/actions/[idAction]/memberCreator/[field]
| GET /1/actions/[idAction]/organization | Yes | Yes | Yes
| GET /1/actions/[idAction]/organization/[field]
| PUT /1/actions/[idAction]
| PUT /1/actions/[idAction]/text
| DELETE /1/actions/[idAction]
| GET /1/boards/[board_id] | Yes | Yes | Yes
| GET /1/boards/[board_id]/[field]
| GET /1/boards/[board_id]/actions | Yes | Yes | Yes
| GET /1/boards/[board_id]/cards | Yes | Yes | Yes
| GET /1/boards/[board_id]/cards/[filter]
| GET /1/boards/[board_id]/cards/[idCard]  | Yes | Yes | Yes
| GET /1/boards/[board_id]/checklists | Yes | Yes | Yes
| GET /1/boards/[board_id]/labels | Yes | Yes | Yes
| GET /1/boards/[board_id]/lists | Yes | Yes | Yes
| GET /1/boards/[board_id]/lists/[filter]
| GET /1/boards/[board_id]/members | Yes | Yes
| GET /1/boards/[board_id]/members/[filter]
| GET /1/boards/[board_id]/members/[idMember]/cards | Yes | Yes | Yes
| GET /1/boards/[board_id]/memberships
| GET /1/boards/[board_id]/memberships/[idMembership]
| GET /1/boards/[board_id]/myPrefs | Yes | Yes | Yes
| GET /1/boards/[board_id]/organization | Yes | Yes | Yes
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
| PUT /1/boards/[board_id]/members | Yes | No | Yes
| PUT /1/boards/[board_id]/members/[idMember] | Yes | No | Yes
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
| DELETE /1/boards/[board_id]/members/[idMember] | Yes | No | Yes
| DELETE /1/boards/[board_id]/powerUps/[powerUp]
| GET /1/cards/[card id or shortlink] | Yes | Yes | Yes
| GET /1/cards/[card id or shortlink]/[field]
| GET /1/cards/[card id or shortlink]/actions | Yes | Yes | Yes
| GET /1/cards/[card id or shortlink]/attachments | Yes | Yes | Yes
| GET /1/cards/[card id or shortlink]/attachments/[idAttachment] | Yes | Yes | Yes
| GET /1/cards/[card id or shortlink]/board | Yes
| GET /1/cards/[card id or shortlink]/board/[field]
| GET /1/cards/[card id or shortlink]/checkItemStates
| GET /1/cards/[card id or shortlink]/checklists | Yes | Yes | Yes
| GET /1/cards/[card id or shortlink]/list
| GET /1/cards/[card id or shortlink]/list/[field]
| GET /1/cards/[card id or shortlink]/members
| GET /1/cards/[card id or shortlink]/membersVoted
| PUT /1/cards/[card id or shortlink] | Yes
| PUT /1/cards/[card id or shortlink]/actions/[idAction]/comments | Yes | No | Yes
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
| POST /1/cards | Yes
| POST /1/cards/[card id or shortlink]/actions/comments | Yes | No | Yes
| POST /1/cards/[card id or shortlink]/attachments | Yes <br> (Except AsyncTrelloHttpClient) | No | Yes
| POST /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem
| POST /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]/convertToCard
| POST /1/cards/[card id or shortlink]/checklists
| POST /1/cards/[card id or shortlink]/idMembers | Yes | No | Yes
| POST /1/cards/[card id or shortlink]/labels | Yes | No | Yes
| POST /1/cards/[card id or shortlink]/idLabels | Yes | No | Yes
| POST /1/cards/[card id or shortlink]/markAssociatedNotificationsRead
| POST /1/cards/[card id or shortlink]/membersVoted
| DELETE /1/cards/[card id or shortlink] | Yes | No | Yes
| DELETE /1/cards/[card id or shortlink]/actions/[idAction]/comments
| DELETE /1/cards/[card id or shortlink]/attachments/[idAttachment] | Yes | No | Yes
| DELETE /1/cards/[card id or shortlink]/checklist/[idChecklist]/checkItem/[idCheckItem]
| DELETE /1/cards/[card id or shortlink]/checklists/[idChecklist]
| DELETE /1/cards/[card id or shortlink]/idMembers/[idMember] | Yes | No | Yes
| DELETE /1/cards/[card id or shortlink]/labels/[color]
| DELETE /1/cards/[card id or shortlink]/membersVoted/[idMember]
| GET /1/checklists/[idChecklist] | Yes | Yes | Yes
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
| GET /1/labels/[idLabel] | Yes | No | Yes
| PUT /1/labels/[idLabel] | Yes | No | Yes
| PUT /1/labels/[idLabel]/color
| PUT /1/labels/[idLabel]/name
| POST /1/labels/[idLabel] | Yes | No | Yes
| DELETE /1/labels/[idLabel] | Yes | No | Yes
| GET /1/lists/[idList] | Yes | Yes | Yes
| GET /1/lists/[idList]/[field]
| GET /1/lists/[idList]/actions
| GET /1/lists/[idList]/board
| GET /1/lists/[idList]/board/[field]
| GET /1/lists/[idList]/cards | Yes | Yes | Yes
| GET /1/lists/[idList]/cards/[filter]
| PUT /1/lists/[idList]
| PUT /1/lists/[idList]/closed
| PUT /1/lists/[idList]/idBoard
| PUT /1/lists/[idList]/name
| PUT /1/lists/[idList]/pos
| PUT /1/lists/[idList]/subscribed
| POST /1/lists
| POST /1/lists/[idList]/cards
| GET /1/members/[idMember or username] | Yes
| GET /1/members/[idMember or username]/[field]
| GET /1/members/[idMember or username]/actions | Yes | Yes | Yes
| GET /1/members/[idMember or username]/boards | Yes | Yes | Yes
| GET /1/members/[idMember or username]/boards/[filter]
| GET /1/members/[idMember or username]/boardsInvited
| GET /1/members/[idMember or username]/boardsInvited/[field]
| GET /1/members/[idMember or username]/cards | Yes | No | Yes
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
| GET /1/organizations/[idOrg or name] | Yes | Yes| Yes
| GET /1/organizations/[idOrg or name]/[field]
| GET /1/organizations/[idOrg or name]/actions
| GET /1/organizations/[idOrg or name]/boards | Yes | Yes | Yes
| GET /1/organizations/[idOrg or name]/boards/[filter]
| GET /1/organizations/[idOrg or name]/members | Yes | Yes | Yes
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
