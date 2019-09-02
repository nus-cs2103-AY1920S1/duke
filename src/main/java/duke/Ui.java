package duke;

import java.util.List;
import java.util.Scanner;

class Ui {
    private Scanner input = new Scanner(System.in);

    boolean hasNextLine() {
        return input.hasNextLine();
    }

    String readCommand() {
        return input.nextLine();
    }

    void printMessage(List<String> messages) {
        System.out.println("    ____________________________________________________________");
        for (String message : messages) {
            System.out.println("     " + message);
        }
        System.out.println("    ____________________________________________________________\n");
    }

    void showError(String message) {
        printMessage(List.of(message));
    }

    void showWelcome() {
        printMessage(List.of("Hello! I'm Duke", "What can I do for you?"));
    }
}
