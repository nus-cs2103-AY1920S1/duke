package duke.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     * reads the user input.
     *
     * @return return string
     */
    public String readCommand() {
        if (sc.hasNextLine()) {
            return sc.nextLine();
        } else {
            return "";
        }
    }

    /**
     * prints out greeting information.
     */
    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        showLine();
        print("Hello! I'm Duke\nWhat can I do for you?");
        showLine();
    }

    /**
     * shows errors encountered during execution.
     *
     * @param message error message
     */
    public void showError(String message) {
        print(message);
    }

    public void showLoadingError() {
        print("This is your first time using Duke, no history loaded.");
    }

    public void showLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * prints out information in certain format.
     *
     * @param str message to be printed
     */
    public void print(String str) {
        String[] lines = str.split("\n");
        StringBuilder sb = new StringBuilder();
        for (String line : lines) {
            sb.append("\n     " + line);
        }
        System.out.println(sb.toString());
    }

}
