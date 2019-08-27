package seedu.duke.core;

public class Ui {
    private static String LOGO =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static String GREETINGMSG =
            "____________________________________________________________\n" +
            "Hello! I'm Duke\n" +
            "What can I do for you?\n" +
            "____________________________________________________________";

    public void readCommand() {

    }

    public void showWelcome() {
        System.out.println(GREETINGMSG);
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public void printByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showLoadingError() {

    }
}
