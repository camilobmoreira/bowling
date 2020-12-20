package org.example.bowling.utils;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Class to help dealing with files
 *
 * @author camilo
 */
public class FileUtils {

    /**
     * Opens a file and reads its content.
     * @param filePath path or name of the file to be opened
     * @return a {@link List} of {@link String} with every line read from provided file. Empty {@link List} if file
     * doesn't exists
     * @throws IOException thrown if file can't be read
     */
    public static List<String> openFileAndReadLines(String filePath) throws IOException {
        List<String> lines = new LinkedList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return lines;
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line);
        }
        reader.close();
        return lines;
    }

}
