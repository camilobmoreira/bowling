package org.example.bowling.services;


import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.example.bowling.model.Player;
import org.example.bowling.model.Round;
import org.example.bowling.services.impl.ScoreServiceImpl;
import org.example.bowling.utils.ArgumentsUtils;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


/**
 * Test class for {@link ScoreService}
 */
@RunWith(JUnit4.class)
public class ScoreServiceTest {

    private final ScoreService scoreService = new ScoreServiceImpl();

    @Test
    public void parseRoundsByPlayerTest$AllZeros() {
        List<String> lines = Arrays.asList(
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0",
                "Joe\t0"
        );
        Map<Player, List<Round>> roundByPlayer = this.scoreService.parseRoundsByPlayer(lines, ArgumentsUtils.DEFAULT_SEPARATOR);
        roundByPlayer.values().forEach(this.scoreService::updateRoundsSetScore);
        List<Round> joesRounds = roundByPlayer.get(new Player("Joe"));

        Assertions.assertAll(
                () -> Assertions.assertEquals(0, joesRounds.stream().map(Round::getPinsKnockedAsInteger).flatMap(Collection::stream).reduce(0, Integer::sum)),
                () -> Assertions.assertEquals(0, joesRounds.stream().map(Round::getRoundScore).reduce(0, Integer::sum)),
                () -> Assertions.assertEquals(0, joesRounds.stream().map(Round::getCumulativeScore).reduce(0, Integer::sum))
        );
    }

    @Test
    public void parseRoundsByPlayerTest$AllTens() {
        List<String> lines = Arrays.asList(
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10",
                "Joe\t10"
        );
        Map<Player, List<Round>> roundByPlayer = this.scoreService.parseRoundsByPlayer(lines, ArgumentsUtils.DEFAULT_SEPARATOR);
        roundByPlayer.values().forEach(this.scoreService::updateRoundsSetScore);
        List<Round> joesRounds = roundByPlayer.get(new Player("Joe"));

        Assertions.assertAll(
                () -> Assertions.assertEquals(120, joesRounds.stream().map(Round::getPinsKnockedAsInteger).flatMap(Collection::stream).reduce(0, Integer::sum)),
                () -> Assertions.assertEquals(joesRounds.stream().map(Round::getRoundScore).reduce(0, Integer::sum), joesRounds.get(9).getCumulativeScore())
        );
    }

    @Test
    public void parseRoundsByPlayerTest$ExampleDotTxt() {
        List<String> lines = Arrays.asList(
                "Jeff\t10",
                "John\t3",
                "John\t7",
                "Jeff\t7",
                "Jeff\t3",
                "John\t6",
                "John\t3",
                "Jeff\t9",
                "Jeff\t0",
                "John\t10",
                "Jeff\t10",
                "John\t8",
                "John\t1",
                "Jeff\t0",
                "Jeff\t8",
                "John\t10",
                "Jeff\t8",
                "Jeff\t2",
                "John\t10",
                "Jeff\tF",
                "Jeff\t6",
                "John\t9",
                "John\t0",
                "Jeff\t10",
                "John\t7",
                "John\t3",
                "Jeff\t10",
                "John\t4",
                "John\t4",
                "Jeff\t10",
                "Jeff\t8",
                "Jeff\t1",
                "John\t10",
                "John\t9",
                "John\t0"
        );
        Map<Player, List<Round>> roundByPlayer = this.scoreService.parseRoundsByPlayer(lines, ArgumentsUtils.DEFAULT_SEPARATOR);
        roundByPlayer.values().forEach(this.scoreService::updateRoundsSetScore);

        List<Integer> jeffsPinsKnockedExpected = Arrays.asList(10, 10, 9, 10, 8, 10, 6, 10, 10, 19);
        List<Integer> jeffsCumulativeScoreExpected = Arrays.asList(20, 39, 48, 66, 74, 84, 90, 120, 148, 167);

        List<Integer> johnsPinsKnockedExpected = Arrays.asList(10, 9, 10, 9, 10, 10, 9, 10, 8, 19);
        List<Integer> johnsCumulativeScoreExpected = Arrays.asList(16, 25, 44, 53, 82, 101, 110, 124, 132, 151);

        List<Round> jeffsRounds = roundByPlayer.get(new Player("Jeff"));
        List<Round> johnsRounds = roundByPlayer.get(new Player("John"));

        Assertions.assertAll(
                () -> {
                    List<List<Integer>> pinsKnockedList = jeffsRounds.stream().map(Round::getPinsKnockedAsInteger).collect(Collectors.toList());
                    this.assertPinsKnocked(jeffsPinsKnockedExpected, pinsKnockedList);
                },
                () -> Assertions.assertEquals(jeffsCumulativeScoreExpected, jeffsRounds.stream().map(Round::getCumulativeScore).collect(Collectors.toList())),
                () -> Assertions.assertEquals(jeffsRounds.stream().map(Round::getRoundScore).reduce(0, Integer::sum), jeffsRounds.get(9).getCumulativeScore()),

                () -> {
                    List<List<Integer>> pinsKnockedList = johnsRounds.stream().map(Round::getPinsKnockedAsInteger).collect(Collectors.toList());
                    this.assertPinsKnocked(johnsPinsKnockedExpected, pinsKnockedList);
                },
                () -> Assertions.assertEquals(johnsCumulativeScoreExpected, johnsRounds.stream().map(Round::getCumulativeScore).collect(Collectors.toList())),
                () -> Assertions.assertEquals(johnsRounds.stream().map(Round::getRoundScore).reduce(0, Integer::sum), johnsRounds.get(9).getCumulativeScore())
        );
    }

    private void assertPinsKnocked(List<Integer> pinsKnockedExpected, List<List<Integer>> pinsKnockedList) {
        Assertions.assertEquals(pinsKnockedExpected.size(), pinsKnockedList.size());
        for (int i = 0; i < pinsKnockedExpected.size(); i++) {
            Assertions
                    .assertEquals(pinsKnockedExpected.get(i), pinsKnockedList.get(i).stream().reduce(0, Integer::sum));
        }
    }

}
