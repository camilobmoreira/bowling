package org.example.bowling.exception;

/**
 * Exception for an invalid number of pins knocked in one round
 *
 * @author camilo
 */
public class InvalidPinsKnockedException extends Exception {

    public InvalidPinsKnockedException(String message) {
        super(message);
    }
}
