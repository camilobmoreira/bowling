package org.example.bowling.services;


import java.io.IOException;
import java.util.List;


/**
 * Class to help dealing with files
 *
 * @author camilo
 */
public interface FileService {

    /**
     * Opens a file and reads its content.
     * @param filePath path or name of the file to be opened
     * @return a {@link List} of {@link String} with every line read from provided file. Empty {@link List} if file
     * doesn't exists
     * @throws IOException thrown if file can't be read
     */
    List<String> openFileAndReadLines(String filePath) throws IOException;

}
