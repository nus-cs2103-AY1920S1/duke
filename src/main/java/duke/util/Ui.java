package duke.util;

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

    public Ui() {
        this.print(WELCOME_MESSAGE);
    }

    public void print(String...strArr) {
        for (String s : strArr) {
            System.out.println("> " + s);
        }
    }
}
