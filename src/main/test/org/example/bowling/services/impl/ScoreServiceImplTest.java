package org.example.bowling.services.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.example.bowling.model.Round;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Test class for {@link ScoreServiceImpl}
 *
 * @author camilo
 */
@RunWith(JUnit4.class)
public class ScoreServiceImplTest {

    private final ScoreServiceImpl scoreService = new ScoreServiceImpl();

    @Test
    public void validateRoundsTest$simpleTestEverythingIsValid() {
        Round round = new Round();
        round.setPinsKnocked(Arrays.asList("5", "5"));
        this.scoreService.validateRounds(round, "playerName", 1);
    }

    @Test
    public void validateRoundsTest$simpleTestInvalidKnockedPins11() {
        Round round = new Round();
        round.setPinsKnocked(Arrays.asList("6", "5"));
        Assertions.assertThrows(RuntimeException.class, () -> this.scoreService.validateRounds(round, "playerName", 1));
    }

    @Test
    public void validateRoundsTest$simpleTestInvalidKnockedPinsMinus1() {
        Round round = new Round();
        round.setPinsKnocked(Collections.singletonList("-1"));
        Assertions.assertThrows(RuntimeException.class, () -> this.scoreService.validateRounds(round, "playerName", 1));
    }

    @Test
    public void validateRoundsTest$simpleTestMoreThan10PinsKnockedStrikeOnLastFrame() {
        Round round = new Round();
        round.setPinsKnocked(Arrays.asList("10", "1", "1"));
        this.scoreService.validateRounds(round, "playerName", 10);
    }

    @Test
    public void validateRoundsTest$simpleTestMoreThan10PinsKnockedStrikeNotLastFrame() {
        Round round = new Round();
        round.setPinsKnocked(Arrays.asList("10", "1", "1"));
        Assertions.assertThrows(RuntimeException.class, () -> this.scoreService.validateRounds(round, "playerName", 1));
    }

    @Test
    public void validateRoundsTest$simpleTestMoreThan10Frames() {
        Round round = new Round();
        round.setPinsKnocked(Collections.singletonList("1"));
        Assertions
                .assertThrows(RuntimeException.class, () -> this.scoreService.validateRounds(round, "playerName", 11));
    }

    @Test
    public void updateCumulativeScore$simpleTest() {
        Round round1 = new Round();
        round1.setRoundScore(10);
        Round round2 = new Round();
        round2.setRoundScore(10);
        Round round3 = new Round();
        round3.setRoundScore(10);
        List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2, round3));
        this.scoreService.updateCumulativeScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(10, round1.getCumulativeScore()),
                () -> Assertions.assertEquals(20, round2.getCumulativeScore()),
                () -> Assertions.assertEquals(30, round3.getCumulativeScore())
        );
    }

    @Test
    public void updateRoundScore$simplesTest() {
        Round round1 = new Round();
        round1.setPinsKnocked(Arrays.asList("4", "3"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("2", "6"));
        List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(7, round1.getRoundScore()),
                () -> Assertions.assertEquals(8, round2.getRoundScore())
        );
    }

    @Test
    public void updateRoundScore$simplesTestSpare() {
        Round round1 = new Round();
        round1.setPinsKnocked(Arrays.asList("7", "3"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("4", "5"));
        List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(14, round1.getRoundScore()),
                () -> Assertions.assertEquals(9, round2.getRoundScore())
        );
    }

    @Test
    public void updateRoundScore$simplesTestStrike() {
        Round round1 = new Round();
        round1.setPinsKnocked(Collections.singletonList("10"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("4", "5"));
        List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(19, round1.getRoundScore()),
                () -> Assertions.assertEquals(9, round2.getRoundScore())
        );
    }

    @Test
    public void updateRoundScore$simplesTestAllZeros() {
        Round round1 = new Round();
        round1.setPinsKnocked(Arrays.asList("0", "0"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("0", "0"));
        List<Round> rounds = new ArrayList<>(Arrays.asList(round1, round2));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, round1.getRoundScore()),
                () -> Assertions.assertEquals(0, round2.getRoundScore())
        );
    }

    @Test
    public void updateRoundScore$simplesTestAllZerosButStrikeOnLastFrame() {
        Round round1 = new Round();
        round1.setPinsKnocked(Arrays.asList("0", "0"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("0", "0"));
        Round round3 = new Round();
        round3.setPinsKnocked(Arrays.asList("0", "0"));
        Round round4 = new Round();
        round4.setPinsKnocked(Arrays.asList("0", "0"));
        Round round5 = new Round();
        round5.setPinsKnocked(Arrays.asList("0", "0"));
        Round round6 = new Round();
        round6.setPinsKnocked(Arrays.asList("0", "0"));
        Round round7 = new Round();
        round7.setPinsKnocked(Arrays.asList("0", "0"));
        Round round8 = new Round();
        round8.setPinsKnocked(Arrays.asList("0", "0"));
        Round round9 = new Round();
        round9.setPinsKnocked(Arrays.asList("0", "0"));
        Round round10 = new Round();
        round10.setPinsKnocked(Arrays.asList("10", "10", "10"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, rounds.subList(0, 9).stream().map(Round::getRoundScore)
                        .reduce(0, Integer::sum)),
                () -> Assertions.assertEquals(30, round10.getRoundScore()));
    }

    @Test
    public void updateRoundScore$simplesTestAllZerosButStrikeOnTwoLastFrame() {
        Round round1 = new Round();
        round1.setPinsKnocked(Arrays.asList("0", "0"));
        Round round2 = new Round();
        round2.setPinsKnocked(Arrays.asList("0", "0"));
        Round round3 = new Round();
        round3.setPinsKnocked(Arrays.asList("0", "0"));
        Round round4 = new Round();
        round4.setPinsKnocked(Arrays.asList("0", "0"));
        Round round5 = new Round();
        round5.setPinsKnocked(Arrays.asList("0", "0"));
        Round round6 = new Round();
        round6.setPinsKnocked(Arrays.asList("0", "0"));
        Round round7 = new Round();
        round7.setPinsKnocked(Arrays.asList("0", "0"));
        Round round8 = new Round();
        round8.setPinsKnocked(Arrays.asList("0", "0"));
        Round round9 = new Round();
        round9.setPinsKnocked(Collections.singletonList("10"));
        Round round10 = new Round();
        round10.setPinsKnocked(Arrays.asList("10", "10", "10"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        this.scoreService.updateRoundScore(rounds);
        Assertions.assertAll(
                () -> Assertions.assertEquals(0, rounds.subList(0, 8).stream().map(Round::getRoundScore)
                        .reduce(0, Integer::sum)),
                () -> Assertions.assertEquals(30, round9.getRoundScore()),
                () -> Assertions.assertEquals(30, round10.getRoundScore()));
    }

}
