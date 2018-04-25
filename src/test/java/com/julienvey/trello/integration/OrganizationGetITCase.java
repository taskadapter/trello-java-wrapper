package com.julienvey.trello.integration;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.julienvey.trello.Trello;
import com.julienvey.trello.TrelloHttpClient;
import com.julienvey.trello.domain.Board;
import com.julienvey.trello.domain.Member;
import com.julienvey.trello.impl.TrelloImpl;
import com.julienvey.trello.impl.http.ApacheHttpClient;
import com.julienvey.trello.impl.http.AsyncTrelloHttpClient;
import com.julienvey.trello.impl.http.RestTemplateHttpClient;

@RunWith(Parameterized.class)
public class OrganizationGetITCase {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String ORGANIZATION_ID = "testorganization99";

    private Trello trello;

    private TrelloHttpClient httpClient;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { { new ApacheHttpClient() }, { new AsyncTrelloHttpClient() }, { new RestTemplateHttpClient() } });
    }

    public OrganizationGetITCase(TrelloHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Before
    public void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "", httpClient);
    }

    @Test
    public void testGetOrganizationFetchBoard() {
        List<Board> boards = trello.getOrganizationBoards(ORGANIZATION_ID);

        assertThat(boards).isNotNull();
        assertThat(boards).hasSize(1);

    }

    @Test
    public void testGetOrganizationFetchMember() {
        List<Member> members = trello.getOrganizationMembers(ORGANIZATION_ID);

        assertThat(members).isNotNull();
        assertThat(members).hasSize(1);

    }

}
