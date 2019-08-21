/*
 * DukeException.java
 * Level-6
 * CS2103T
 * @author Gabriel Ong
 *
 * Represents the exception that can store an error message to be printed for the user
 * in the operation of Duke.java.
 *
 */

public class DukeException extends Exception {
    public DukeException(String message) {
        super("☹ OOPS!!! " + message);
    }
}
