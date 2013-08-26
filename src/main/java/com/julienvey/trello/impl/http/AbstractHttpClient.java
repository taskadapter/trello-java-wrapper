package com.julienvey.trello.impl.http;

import com.julienvey.trello.TrelloHttpClient;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class AbstractHttpClient implements TrelloHttpClient {

    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    protected String expandUrl(String url, String... params) {
        if (url == null) {
            return null;
        }
        if (url.indexOf('{') == -1) {
            return url;
        }
        Matcher matcher = NAMES_PATTERN.matcher(url);
        StringBuffer sb = new StringBuffer();

        int variable = 0;
        while (matcher.find()) {
            String variableValue = params[variable];
            String replacement = Matcher.quoteReplacement(variableValue);
            matcher.appendReplacement(sb, replacement);
            variable++;
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
