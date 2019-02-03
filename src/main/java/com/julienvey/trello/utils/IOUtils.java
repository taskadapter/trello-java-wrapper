package com.julienvey.trello.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Scanner;

public class IOUtils {
    public static String toString(InputStream in) {
        Objects.requireNonNull(in);
        try (Scanner scanner = new Scanner(in, StandardCharsets.UTF_8.name())) {
            return scanner.useDelimiter("\\A").next();
        }
    }

    /**
     * Copies all remaining bytes from {@code in} to {@code out}. The {@code out} will be flushed.
     * <p>
     * Note that method does not make any buffering, so caller must ensure that streams are buffered if needed.
     *
     * @param in  The input source.
     * @param out The output destination.
     *
     * @throws IOException When any I/O error occurred.
     */
    public static void copyTo(InputStream in, OutputStream out) throws IOException {
        Objects.requireNonNull(in);
        Objects.requireNonNull(out);

        int r;
        while ((r = in.read()) != -1) {
            out.write(r);
        }

        out.flush();
    }
}
