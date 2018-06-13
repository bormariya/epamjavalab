package utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public interface TestUtils {

    static String fromSystemOut(Runnable runnable) throws IOException {
        PrintStream realOut = System.out;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             PrintStream printStream = new PrintStream(out)) {
            System.setOut(printStream);
            runnable.run();
            return new String(out.toByteArray());
        } finally {
            System.setOut(realOut);
        }
    }
}