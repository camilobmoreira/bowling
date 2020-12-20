package org.example.bowling.services.impl;


import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

import org.example.bowling.model.Player;
import org.example.bowling.model.Round;
import org.example.bowling.services.ScoreService;


/**
 * {@link ScoreService}'s implementation
 *
 * @author camilo
 */
public class ScoreServiceImpl implements ScoreService {

    @Override
    public Map<Player, List<Round>> parseRoundsByPlayer(List<String> lines, String separator) {
        Map<Player, List<Round>> roundByPlayer = new LinkedHashMap<>();
        Player lastPlayer = null;
        Round lastRound = null;
        for (String line : lines) {
            boolean newRound = false;
            String[] split = line.split(separator);
            Player currentPlayer = new Player(split[0]);
            roundByPlayer.putIfAbsent(currentPlayer, new ArrayList<>());

            // if the current player is not equals to the last player, we need to create a new round instead of updating
            // the same one
            if (!currentPlayer.equals(lastPlayer)) {
                lastRound = new Round();
                lastPlayer = currentPlayer;
                newRound = true;
            }

            lastRound.addPinsKnocked(split[1]);

            if (newRound) {
                roundByPlayer.get(currentPlayer).add(lastRound);
            }
        }
        return roundByPlayer;
    }

}
