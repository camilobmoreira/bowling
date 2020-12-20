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

import com.google.common.annotations.VisibleForTesting;


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
            // the same one. Of if it had it's two chances already, unless it's the last one and it's a strike
            if (!currentPlayer.equals(lastPlayer) || (lastRound.getPinsKnocked().size() > 2 && !lastRound.isStrike()
                    && roundByPlayer.get(currentPlayer).size() != 10)) {
                lastRound = new Round();
                lastPlayer = currentPlayer;
                newRound = true;
            }

            lastRound.addPinsKnocked(split[1]);

            if (newRound) {
                roundByPlayer.get(currentPlayer).add(lastRound);
            }
            this.validateRounds(lastRound, currentPlayer.getName(), roundByPlayer.get(currentPlayer).size());
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
    @VisibleForTesting
    void updateRoundScore(List<Round> rounds) {
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
    @VisibleForTesting
    void updateCumulativeScore(List<Round> rounds) {
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

    /**
     * Validate each {@link Round} to be sure it doesn't have any invalid input, as negative number of pins knocked or
     * more than 10, unless it's the last round and it's a strike. Or if there are more than 10 throws per player.
     * @param round round to be validated
     * @param playerName player name
     * @param frame number of frame
     */
    @VisibleForTesting
    void validateRounds(Round round, String playerName, int frame) {
        List<Integer> pinsKnocked = round.getPinsKnockedAsInteger();
        Integer totalPins = pinsKnocked.stream().reduce(0, Integer::sum);
        if (totalPins < 0 || (totalPins > 10 && (frame != 10 || !round.isStrike() || pinsKnocked.size() != 3))) {
            throw new RuntimeException(String.format(
                    "The input file contains a invalid number of %s pins knocked in one round for player %s", totalPins,
                    playerName));
        } else if (frame > 10) {
            throw new RuntimeException(String.format(
                    "The input file contains more than 10 throws for player %s", playerName));
        }
    }
}
