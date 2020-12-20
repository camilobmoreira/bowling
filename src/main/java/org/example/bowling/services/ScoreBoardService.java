package org.example.bowling.services;


import java.util.List;
import java.util.Map;

import org.example.bowling.model.Player;
import org.example.bowling.model.Round;


/**
 * Service responsible for dealing with the score board
 *
 * @author camilo
 */
public interface ScoreBoardService {

     /**
      * Prints each {@link Player}'s {@link Round#getPinsKnocked()} and {@link Round#getCumulativeScore()}
      * @param roundByPlayer {@link Map} of {@link Round} by {@link Player} containing the values to be printed
      */
     void printScoreBoard(Map<Player, List<Round>> roundByPlayer);

}
