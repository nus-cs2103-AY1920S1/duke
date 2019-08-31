package duke;

import java.util.Scanner;

public class Ui {

    private Scanner s;
    Ui() {
        this.s = new Scanner(System.in);
    }

    void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    //WHY IS THIS GIVING PROBLEMS?
    String readCommand() {
       return s.nextLine().trim();
    }

    void showLoadingError() {
        System.out.println("Error loading data from file");
    }

    void showError(String errorMessage) {
        System.out.println(errorMessage);
    }
}
