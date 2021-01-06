package org.example.bowling.main;


import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.example.bowling.model.Player;
import org.example.bowling.model.Round;
import org.example.bowling.services.ScoreBoardService;
import org.example.bowling.services.ScoreService;
import org.example.bowling.services.impl.FileServiceImpl;
import org.example.bowling.services.impl.ScoreBoardServiceImpl;
import org.example.bowling.services.impl.ScoreServiceImpl;
import org.example.bowling.utils.ArgumentsUtils;
import org.example.bowling.services.FileService;


public class App {

    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        ScoreService scoreService = new ScoreServiceImpl();
        ScoreBoardService scoreBoardService = new ScoreBoardServiceImpl();

        ArgumentsUtils.validateArgumentsPrintHelp(args);
        if (args.length == 0) {
            return;
        }
        Map<String, String> arguments = ArgumentsUtils.convertArgumentsToMap(args);

        try {
            List<String> lines = fileService.openFileAndReadLines(arguments.get(ArgumentsUtils.FILE_NAME));
            Map<Player, List<Round>> roundByPlayer = scoreService.parseRoundsByPlayer(lines, arguments.get(
                    ArgumentsUtils.VALUE_SEPARATOR));
            roundByPlayer.values().forEach(scoreService::updateRoundsSetScore);
            scoreBoardService.printScoreBoard(roundByPlayer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
