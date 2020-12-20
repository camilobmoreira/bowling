package org.example.bowling.utils;


import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;


/**
 * Test class for {@link FileUtils}
 *
 * @author camilo
 */
public class FileUtilsTest {

    @Test
    public void openFileAndReadLines$existentFile() throws IOException {
        List<String> lines = FileUtils.openFileAndReadLines("Example.txt");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(lines),
                () -> Assertions.assertTrue(lines.size() > 0)
        );
    }

    @Test
    public void openFileAndReadLines$nonExistentFile() throws IOException {
        List<String> lines = FileUtils.openFileAndReadLines("Whatever.txt");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(lines),
                () -> Assertions.assertEquals(0, lines.size())
        );
    }
}
