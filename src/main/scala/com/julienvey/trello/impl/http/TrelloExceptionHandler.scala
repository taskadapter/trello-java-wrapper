package com.julienvey.trello.impl.http

import com.julienvey.trello.{ListNotFoundException, TrelloBadRequestException}

/**
  * Decorates errors coming from Trello REST API to make them more user-friendly
  */
object TrelloExceptionHandler {
  def process(serverResponse: String): TrelloBadRequestException = {
    if (serverResponse.contains("invalid value for idList")) {
      new ListNotFoundException("A Trello list with this ID does not exist on the requested board")
    } else {
      new TrelloBadRequestException(serverResponse)
    }
  }
}