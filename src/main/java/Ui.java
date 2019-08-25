import java.util.Scanner;

class Ui {
    /**
     * interacting with the user. Scanning input from user.
     */
    private Scanner sc;

    /**
     * constructor.
     */
    Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * prints welcome message to user.
     */
    void showWelcome() {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n");
    }

    /**
     * scans the whole line of command.
     * @return the line of command.
     */
    String readCommand() {
        return sc.nextLine();
    }

    /**
     * prints a line.
     */
    void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * print the error
     * @param message is the error message.
     */
    void showError(String message) {
        System.out.println("    " + message);
    }
}
