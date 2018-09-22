package com.julienvey.trello.impl.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class UrlExpander {
    private static final Pattern NAMES_PATTERN = Pattern.compile("\\{([^/]+?)\\}");

    public static String expandUrl(String url, String... params) {
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
