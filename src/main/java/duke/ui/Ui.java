package duke.ui;

import java.util.Scanner;

public class Ui {

    static String divider;
    Scanner scanner;
    String remainingWords;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Shows welcome message
     */
     public String showWelcomeMessage() {
         String output = "";
         output += "Hello from \n";
         output += " ____        _        \n"
                 + "|  _ \\ _   _| | _____ \n"
                 + "| | | | | | | |/ / _ \\\n"
                 + "| |_| | |_| |   <  __/\n"
                 + "|____/ \\__,_|_|\\_\\___|\n \n ";
         output += "What can I do for you?";
         return output;
     }

    /**
     * Prints out the divider that is to be used for each command or output
     */
    public static void showLine() {
        divider = "_____________________________________________________";
        System.out.println(divider);
    }

    public void storeRemaining(String string) {
        remainingWords = string;
    }

    /**
     * Returns the remainingWords scanned in readCommand()
     * @return remainingWords
     */
    public String getRemainingWords() {
        return remainingWords;
    }

    /**
     * Prints out the errorMessage directly
     * @param errorMessage String to be printed
     */
    public void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Prints out the loadingMessage
     */
    public void showLoadingError() {
        System.out.println("Loading Error");
    }
}
