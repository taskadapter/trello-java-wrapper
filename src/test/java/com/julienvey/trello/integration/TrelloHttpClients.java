package com.julienvey.trello.integration;

import com.julienvey.trello.impl.http.OkHttpTrelloHttpClient;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.impl.http.*;

import java.util.Arrays;
import java.util.List;

public final class TrelloHttpClients {
    private static final TrelloHttpClient[] clients = {
            new ApacheHttpClient(),
            new AsyncTrelloHttpClient(),
            new RestTemplateHttpClient(),
            new AsyncTrelloHttpClient2(),
            new JDKTrelloHttpClient(),
            new OkHttpTrelloHttpClient()
    };

    public static List<TrelloHttpClient> all() {
        return Arrays.asList(clients);
    }
}
