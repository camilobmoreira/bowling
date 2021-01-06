package org.example.bowling.services;


import java.io.IOException;
import java.util.List;

import org.example.bowling.services.impl.FileServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;


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
    public void openFileAndReadLines$nonExistentFile() {
        try {
            this.fileService.openFileAndReadLines("Whatever.txt");
            Assertions.fail();
        } catch (IOException e) {
            Assertions.assertEquals("File Whatever.txt does not exist.", e.getMessage());
        }
    }
}
