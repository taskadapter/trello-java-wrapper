package com.julienvey.trello

import com.julienvey.trello.impl.TrelloImpl
import com.julienvey.trello.impl.http.ApacheHttpClient

object TrelloTestFactory {
  val httpClient = new ApacheHttpClient
  val trello = new TrelloImpl(TrelloConfig.applicationKey, TrelloConfig.token, httpClient)
}
