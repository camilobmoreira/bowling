package org.example.bowling.services.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.example.bowling.model.Round;
import org.example.bowling.services.ScoreService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 *  Test class for {@link ScoreBoardServiceImpl}
 *
 * @author camilo
 */
@RunWith(JUnit4.class)
public class ScoreBoardServiceImplTest {

    ScoreBoardServiceImpl scoreBoardService = new ScoreBoardServiceImpl();
    ScoreService scoreService = new ScoreServiceImpl();

    @Test
    public void getPinfallsMessageLine$simpleTestAllZeros() {
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
        round10.setPinsKnocked(Arrays.asList("0", "0"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        String pinfallsMessageLine = this.scoreBoardService.getPinfallsMessageLine(rounds);
        Assertions.assertEquals("0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t0\t", pinfallsMessageLine);
    }

    @Test
    public void getPinfallsMessageLine$simpleTestAllTens() {
        Round round1 = new Round();
        round1.addPinsKnocked("10");
        Round round2 = new Round();
        round2.addPinsKnocked("10");
        Round round3 = new Round();
        round3.addPinsKnocked("10");
        Round round4 = new Round();
        round4.addPinsKnocked("10");
        Round round5 = new Round();
        round5.addPinsKnocked("10");
        Round round6 = new Round();
        round6.addPinsKnocked("10");
        Round round7 = new Round();
        round7.addPinsKnocked("10");
        Round round8 = new Round();
        round8.addPinsKnocked("10");
        Round round9 = new Round();
        round9.addPinsKnocked("10");
        Round round10 = new Round();
        round10.setPinsKnocked(Arrays.asList("10", "10", "10"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        String pinfallsMessageLine = this.scoreBoardService.getPinfallsMessageLine(rounds);
        Assertions.assertEquals("\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\t\tX\tX\tX\tX", pinfallsMessageLine);
    }

    @Test
    public void getScoreMessageLine$simpleTestAllZeros() {
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
        round10.setPinsKnocked(Arrays.asList("0", "0"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        this.scoreService.updateRoundsSetScore(rounds);
        String pinfallsMessageLine = this.scoreBoardService.getScoreMessageLine(rounds);
        Assertions.assertEquals("0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t0\t\t", pinfallsMessageLine);
    }

    @Test
    public void getScoreMessageLine$simpleTestAllTens() {
        Round round1 = new Round();
        round1.addPinsKnocked("10");
        Round round2 = new Round();
        round2.addPinsKnocked("10");
        Round round3 = new Round();
        round3.addPinsKnocked("10");
        Round round4 = new Round();
        round4.addPinsKnocked("10");
        Round round5 = new Round();
        round5.addPinsKnocked("10");
        Round round6 = new Round();
        round6.addPinsKnocked("10");
        Round round7 = new Round();
        round7.addPinsKnocked("10");
        Round round8 = new Round();
        round8.addPinsKnocked("10");
        Round round9 = new Round();
        round9.addPinsKnocked("10");
        Round round10 = new Round();
        round10.setPinsKnocked(Arrays.asList("10", "10", "10"));
        List<Round> rounds = new ArrayList<>(
                Arrays.asList(round1, round2, round3, round4, round5, round6, round7, round8, round9, round10));
        this.scoreService.updateRoundsSetScore(rounds);
        String pinfallsMessageLine = this.scoreBoardService.getScoreMessageLine(rounds);
        Assertions.assertEquals("30\t\t60\t\t90\t\t120\t\t150\t\t180\t\t210\t\t240\t\t270\t\t300\t\t", pinfallsMessageLine);
    }

}
