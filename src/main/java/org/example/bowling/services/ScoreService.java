package org.example.bowling.services;


import java.util.List;
import java.util.Map;

import org.example.bowling.exception.InvalidPinsKnockedException;
import org.example.bowling.exception.MoreThanTenThrowsException;
import org.example.bowling.model.Player;
import org.example.bowling.model.Round;


/**
 * Service to deal with {@link Player}'s {@link Round}
 *
 * @author camilo
 */
public interface ScoreService {

    /**
     * Given a {@link List} of {@link String} to be parsed, it will read and transform them into a {@link Map}
     * of {@link List} of {@link Round} by {@link Player}
     *
     * @param lines lines to be parsed
     * @param separator value separator
     * @return {@link Map} of {@link List} of {@link Round} by {@link Player}
     */
    Map<Player, List<Round>> parseRoundsByPlayer(List<String> lines, String separator)
            throws InvalidPinsKnockedException, MoreThanTenThrowsException;

    /**
     * Given a {@link List} of {@link Round}, it will update each elemnt {@link Round#getRoundScore()} and
     * {@link Round#getCumulativeScore()}
     * @param rounds rounds to be updated
     */
    void updateRoundsSetScore(List<Round> rounds);
}
