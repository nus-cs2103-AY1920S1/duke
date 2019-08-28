//package mypackage;

/**
 * Represents duke exceptions.
 */
public class DukeException extends Exception{
    public String description;
    public DukeException (String description) {
        this.description = description;
    }
}
