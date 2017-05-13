package com.julienvey.trello.integration;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.*;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.julienvey.trello.utils.ArgUtils.arg;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.MapAssert.entry;

@RunWith(Parameterized.class)
public class OrganizationGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String ORGANIZATION_ID = "testorganization99";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{{new ApacheHttpClient()}, {new AsyncTrelloHttpClient()}, {new RestTemplateHttpClient()}});
    }

    public OrganizationGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testGetBoardFetchCard() {
        List<Board> boards = trello.getOrganizationBoards(ORGANIZATION_ID);
        
        assertThat(boards).isNotNull();
        assertThat(boards).hasSize(1);       

    }   
    
}
