package org.example.bowling.model;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


/**
 * Represents each round played
 *
 * @author camilo
 */
public class Round {

    private List<String> pinsKnocked = new LinkedList<>();
    private Integer roundScore = 0;
    private Integer cumulativeScore = 0;

    public List<String> getPinsKnocked() {
        return this.pinsKnocked;
    }

    public List<Integer> getPinsKnockedAsInteger() {
        return this.pinsKnocked.stream().map(p -> p.equals("F") ? 0 : Integer.parseInt(p)).collect(Collectors.toList());
    }

    public void setPinsKnocked(List<String> pinsKnocked) {
        this.pinsKnocked = pinsKnocked;
    }

    public void addPinsKnocked(String pinsKnocked) {
        this.pinsKnocked.add(pinsKnocked);
    }

    public Integer getRoundScore() {
        return this.roundScore;
    }

    public void setRoundScore(Integer roundScore) {
        this.roundScore = roundScore;
    }

    public Integer getCumulativeScore() {
        return this.cumulativeScore;
    }

    public void setCumulativeScore(Integer cumulativeScore) {
        this.cumulativeScore = cumulativeScore;
    }

    public boolean isStrike() {
        return this.getPinsKnockedAsInteger().get(0) == 10;
    }

    public boolean isSpare() {
        List<Integer> pins = this.getPinsKnockedAsInteger();
        return !this.isStrike() && pins.get(0) + pins.get(1) == 10;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Round)) return false;
        Round round = (Round) o;
        return this.pinsKnocked.equals(round.pinsKnocked) && this.roundScore.equals(round.roundScore) &&
                this.cumulativeScore.equals(round.cumulativeScore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.pinsKnocked, this.roundScore, this.cumulativeScore);
    }

    @Override
    public String toString() {
        return "Round{" + "pinsKnocked=" + this.pinsKnocked + ", roundScore=" + this.roundScore + ", cumulativeScore="
                + this.cumulativeScore + '}';
    }
}
