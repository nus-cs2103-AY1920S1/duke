package duke.ui;

import java.util.Scanner;

public class Ui {

    String divider;
    String logo;
    String commandType;
    Scanner scanner;
    String remainingWords;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        showLine();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        showLine();
    }

    public void showLine() {
        divider = "_____________________________________________________";
        System.out.println(divider);
    }

    public String readCommand() {
        commandType = scanner.next();
        remainingWords = scanner.nextLine();
        return commandType;
    }

    public String getRemainingWords() {
        return remainingWords;
    }

    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    public void showLoadingError() {
        System.out.println("error");
    }
}
