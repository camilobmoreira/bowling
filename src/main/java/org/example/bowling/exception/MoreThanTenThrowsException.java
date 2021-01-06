package org.example.bowling.exception;


/**
 * Exception for more than ten throws in a game
 *
 * @author camilo
 */
public class MoreThanTenThrowsException extends Exception {

    public MoreThanTenThrowsException(String message) {
        super(message);
    }
}
