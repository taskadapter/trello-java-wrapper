package com.julienvey.trello

import com.julienvey.trello.impl.TrelloImpl
import com.julienvey.trello.impl.http.{ApacheHttpClient, OkHttpTrelloHttpClient}

object TrelloTestFactory {
  val httpClient = new OkHttpTrelloHttpClient()
  val trello = new TrelloImpl(TrelloConfig.applicationKey, TrelloConfig.token, httpClient)
}
