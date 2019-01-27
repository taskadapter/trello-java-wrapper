package com.julienvey.trello.impl.domaininternal;

import java.util.Objects;

public class StringValueHolder {
    private final String value;

    public StringValueHolder(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StringValueHolder)) return false;
        StringValueHolder that = (StringValueHolder) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
