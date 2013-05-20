package com.julienvey.trello;

import com.julienvey.trello.impl.TrelloImpl;
import org.junit.BeforeClass;

public class ActionTest {

    private static final String TEST_APPLICATION_KEY = "db555c528ce160c33305d2ea51ae1197";
    public static final String ACTION_ID = "51990c2143453ab27e0087d5";

    private static Trello trello;

    @BeforeClass
    public static void setUp() {
        trello = new TrelloImpl(TEST_APPLICATION_KEY, "");
    }


}
