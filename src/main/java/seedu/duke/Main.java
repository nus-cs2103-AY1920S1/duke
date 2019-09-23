package seedu.duke;

/**
 * This class contains a main method that simply calls the DukeGui main method, in order to workaround how
 * Java's runtime checks if the main class extends {@link javafx.application.Application} and if so, expects
 * the JavaFX runtime available as modules.
 */
public class Main {
    public static void main(String[] args) {
        DukeGui.main(args);
    }
}
