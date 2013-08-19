Java Wrapper for Trello API

## Overview

This project is a Java Wrapper for the [Trello API](https://trello.com/docs/). It provides a fluent interface for requesting the API

## Usage

### Maven dependency

trello-java-wrapper is available on maven central. The current release is 0.2

```xml
<dependency>
    <groupId>com.julienvey.trello</groupId>
    <artifactId>trello-java-wrapper</artifactId>
    <version>0.2</version>
</dependency>
```

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

trello-java-wrapper is still a work in progress and has not yet reached version 1.0

If you are missing some fonctionnalities, you can easily contribute and propose a pull request. Each pull request should respect current code conventions and also provide tests for the newly implemented features.

[![Build Status](https://travis-ci.org/julienvey/trello-java-wrapper.png?branch=master)](https://travis-ci.org/julienvey/trello-java-wrapper)

