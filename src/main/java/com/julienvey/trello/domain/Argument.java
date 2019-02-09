package com.julienvey.trello.domain;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Argument {

    private String argName;
    private String argValue;

    public Argument(String argName, String argValue) {
        this.argName = argName;
        this.argValue = argValue;
    }

    public String getArgName() {
        return argName;
    }

    public void setArgName(String argName) {
        this.argName = argName;
    }

    public String getArgValue() {
        return argValue;
    }

    public void setArgValue(String argValue) {
        this.argValue = argValue;
    }

    @Override
    public String toString() {
        return "Argument{" + "argName='" + argName + '\'' +
                ", argValue='" + argValue + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Argument)) return false;
        Argument argument = (Argument) o;
        return Objects.equals(argName, argument.argName) &&
                Objects.equals(argValue, argument.argValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(argName, argValue);
    }
}
