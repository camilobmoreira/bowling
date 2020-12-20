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

    @Override
    public void updateRoundsSetScore(List<Round> rounds) {
        this.updateRoundScore(rounds);
        this.updateCumulativeScore(rounds);
    }

    /**
     * Update each {@link Round#getRoundScore()}
     * @param rounds rounds to be updated
     */
    private void updateRoundScore(List<Round> rounds) {
        AtomicReference<Round> lastRoundAtomicReference = new AtomicReference<>();
        AtomicReference<Round> lastButOneRoundAtomicReference = new AtomicReference<>();
        Collections.reverse(rounds);
        rounds.forEach(currentRound -> {
            Integer score = currentRound.getPinsKnockedAsInteger().stream().reduce(0, Integer::sum);

            currentRound.setRoundScore(score);
            Round lastRound = lastRoundAtomicReference.get();
            Round lastButOneRound = lastButOneRoundAtomicReference.get();
            if (lastRound == null) {
                lastRoundAtomicReference.set(currentRound);
                return;
            }

            if (currentRound.isSpare()) {
                currentRound.setRoundScore(score + lastRound.getPinsKnockedAsInteger().get(0));
            } else if (currentRound.isStrike() && lastRound.getPinsKnocked().size() == 3) {
                currentRound.setRoundScore(
                        score + lastRound.getPinsKnockedAsInteger().subList(0, 2).stream().reduce(0, Integer::sum));
            } else if (currentRound.isStrike() && lastRound.getPinsKnocked().size() == 2) {
                currentRound
                        .setRoundScore(score + lastRound.getPinsKnockedAsInteger().stream().reduce(0, Integer::sum));
            } else if (currentRound.isStrike() && lastRound.getPinsKnocked().size() == 1 && lastButOneRound != null) {
                currentRound.setRoundScore(
                        score + lastRound.getPinsKnockedAsInteger().get(0) + lastButOneRound.getPinsKnockedAsInteger()
                                .get(0));
            }

            lastButOneRoundAtomicReference.set(lastRound);
            lastRoundAtomicReference.set(currentRound);
        });
        Collections.reverse(rounds);
    }

    /**
     * Update each {@link Round#getCumulativeScore()}
     * @param rounds rounds to be updated
     */
    private void updateCumulativeScore(List<Round> rounds) {
        AtomicReference<Round> lastRoundAtomicReference = new AtomicReference<>();
        rounds.forEach(currentRound -> {
            Round lastRound = lastRoundAtomicReference.get();
            currentRound.setCumulativeScore(currentRound.getRoundScore());
            if (lastRound != null) {
                currentRound.setCumulativeScore(currentRound.getCumulativeScore() + lastRound.getCumulativeScore());
            }
            lastRoundAtomicReference.set(currentRound);
        });
    }
}
