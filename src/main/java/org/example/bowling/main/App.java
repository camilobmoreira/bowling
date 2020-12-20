package org.example.bowling.main;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.example.bowling.model.Player;
import org.example.bowling.model.Round;
import org.example.bowling.services.ScoreService;
import org.example.bowling.utils.ArgumentsUtils;
import org.example.bowling.services.impl.ScoreServiceImpl;
import org.example.bowling.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);


    public static void main(String[] args) {
        ScoreService scoreService = new ScoreServiceImpl();

        ArgumentsUtils.validateArgumentsPrintHelp(args);
        Map<String, String> arguments = ArgumentsUtils.convertArgumentsToMap(args);

        try {
            List<String> lines = FileUtils.openFileAndReadLines(arguments.get(ArgumentsUtils.FILE_NAME));
            Map<Player, List<Round>> roundByPlayer = scoreService.parseRoundsByPlayer(lines, arguments.get(
                    ArgumentsUtils.VALUE_SEPARATOR));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
