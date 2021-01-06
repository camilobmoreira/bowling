package org.example.bowling.services;


import java.io.IOException;
import java.util.List;

import org.example.bowling.services.impl.FileServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


/**
 * Test class for {@link FileService}
 *
 * @author camilo
 */
public class FileServiceTest {

    private final FileService fileService = new FileServiceImpl();

    @Test
    public void openFileAndReadLines$existentFile() throws IOException {
        List<String> lines = this.fileService.openFileAndReadLines("Example.txt");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(lines),
                () -> Assertions.assertTrue(lines.size() > 0)
        );
    }

    @Test
    public void openFileAndReadLines$nonExistentFile() throws IOException {
        List<String> lines = this.fileService.openFileAndReadLines("Whatever.txt");
        Assertions.assertAll(
                () -> Assertions.assertNotNull(lines),
                () -> Assertions.assertEquals(0, lines.size())
        );
    }
}
