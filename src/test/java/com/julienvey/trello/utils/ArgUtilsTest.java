package com.julienvey.trello.utils;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Arrays;

import org.junit.Test;

import com.julienvey.trello.domain.Argument;

public class ArgUtilsTest {
    @Test
    public void emptyVarArgMethodCallShouldReturnEmptyArray() {
        Argument[] args = ArgUtils.args();

        assertThat(args).hasSize(0);
    }

    @Test
    public void emptyVarArgMethodCallShouldReturnSameArray() {
        Argument[] args1 = ArgUtils.args();
        Argument[] args2 = ArgUtils.args();

        assertThat(args1).isSameAs(args2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oddNumberOfArgumentsShouldThrowException1() {
        ArgUtils.args("arg1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void oddNumberOfArgumentsShouldThrowException5() {
        ArgUtils.args(
                "name1", "value1",
                "name2", "value2",
                "name3" /* missing value */
        );
    }

    @Test
    public void varArgShouldReturnProperArray() {
        Argument[] args = ArgUtils.args(
                "name1", "value1",
                "name2", "value2",
                "name3", "value3",
                "name4", "value4"
        );

        assertThat(Arrays.asList(args))
                .containsExactly(
                        new Argument("name1", "value1"),
                        new Argument("name2", "value2"),
                        new Argument("name3", "value3"),
                        new Argument("name4", "value4")
                );
    }
}