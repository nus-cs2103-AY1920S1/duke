package main.java;

public class InvalidCommandException extends DukeException {
    InvalidCommandException() {
        super("Invalid Command");
    }
}
