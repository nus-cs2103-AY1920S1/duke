package duke.util;

/**
 * Ui class, to handle programme output in Duke application.
 */
public class Ui {
    private static String[] WELCOME_MESSAGE = {
        "Hello! I'm",
        " ____        _        ",
        "|  _ \\ _   _| | _____ ",
        "| | | | | | | |/ / _ \\",
        "| |_| | |_| |   <  __/",
        "|____/ \\__,_|_|\\_\\___|",
        "What can I do for you?"
    };

    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.print(WELCOME_MESSAGE);
    }

    /**
     * Prints the given string in output destination.
     *
     * @param strArr String to be printed in output destination.
     */
    public void print(String...strArr) {
        for (String s : strArr) {
            System.out.println("> " + s);
        }
    }
}
