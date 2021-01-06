package org.example.bowling.services.impl;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.example.bowling.model.Player;
import org.example.bowling.model.Round;
import org.example.bowling.services.ScoreBoardService;

import com.google.common.annotations.VisibleForTesting;


/**
 * {@link ScoreBoardService}'s implementation
 *
 * @author camilo
 */
public class ScoreBoardServiceImpl implements ScoreBoardService {

    @Override
    public void printScoreBoard(Map<Player, List<Round>> roundByPlayer) {
        System.out.println("Frame\t\t1\t\t2\t\t3\t\t4\t\t5\t\t6\t\t7\t\t8\t\t9\t\t10");
        roundByPlayer.forEach((player, rounds) -> {
            System.out.println(player.getName());
            System.out.println("Pinfalls\t" + this.getPinfallsMessageLine(rounds));
            System.out.println("Score\t\t" + this.getScoreMessageLine(rounds));
        });
    }

    /**
     * Get the message line for all the {@link Round#getPinsKnocked()}
     * @param rounds list of rounds
     * @return message line for all the {@link Round#getPinsKnocked()}
     */
    @VisibleForTesting
    String getPinfallsMessageLine(List<Round> rounds) {
        StringBuilder stringBuilder = new StringBuilder();
        rounds.forEach(round -> {
            if (round.isStrike() && round.getPinsKnocked().size() > 1) {
                List<Integer> pinsKnockedAsInteger = round.getPinsKnockedAsInteger();
                stringBuilder.append(StringUtils
                        .join(pinsKnockedAsInteger.stream().map(n -> n == 10 ? "X" : n).collect(Collectors.toList()),
                                "\t"));
            } else if (round.isStrike()) {
                stringBuilder.append("\tX\t");
            } else if (round.isSpare()) {
                stringBuilder.append(round.getPinsKnocked().get(0)).append("\t/\t");
            } else {
                stringBuilder.append(StringUtils.join(round.getPinsKnocked(), "\t")).append("\t");
            }
        });
        return stringBuilder.toString();
    }

    /**
     * Get the message line for all the {@link Round#getCumulativeScore()}
     * @param rounds list of rounds
     * @return message line for all the {@link Round#getCumulativeScore()}
     */
    @VisibleForTesting
    String getScoreMessageLine(List<Round> rounds) {
        StringBuilder stringBuilder = new StringBuilder();
        rounds.forEach(round -> {
            stringBuilder.append(StringUtils.join(round.getCumulativeScore(), "\t\t"));
        });
        return stringBuilder.toString();
    }
}
