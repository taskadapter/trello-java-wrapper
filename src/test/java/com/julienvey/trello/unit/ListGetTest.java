package com.julienvey.trello.unit;

import com.julienvey.trello.Trello;
import com.julienvey.trello.domain.TList;
import com.julienvey.trello.impl.TrelloImpl;
import org.fest.assertions.Assertions;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ListGetTest {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String LIST_ID = "518baad5b05dbf4703004853";

    private static Trello trello;

    @BeforeClass
    public static void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "");
    }

    @Test
    public void testGetList(){
        TList list = trello.getList(LIST_ID);

        assertThat(list).isNotNull();
        assertThat(list.getId()).isEqualTo(LIST_ID);
    }
}
