package duke.module;

import duke.module.AutoResponse;
import java.util.Scanner;

/**
 * Handles user input and output. <code>Ui</code> reads commands from the user and outputs the results.
 */
public class Ui {

    /** Handles keyboard inputs from the user. */
    private Scanner sc;

    /**
     * Initializes the Scanner object to handle UI.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints given message with line separators surrounding the message.
     *
     * @param message String(s) to print out.
     */
    public void printToUser(String... message) {
        System.out.println(AutoResponse.DUKE_LINE);
        for (String line : message) {
            System.out.println(AutoResponse.DUKE_TAB4 + line);
        }
        System.out.println(AutoResponse.DUKE_LINE + "\n");
    }

    public void greet() {
        this.printToUser(AutoResponse.DUKE_LOGO, AutoResponse.DUKE_HELLO);
    }

    public void bye() {
        this.printToUser(AutoResponse.DUKE_BYE);
    }

    /**
     * Reads the command portion of the input.
     *
     * @return The command as a String.
     */
    public String readCommand() {
        return sc.next();
    }

    /**
     * Reads the rest of the input.
     *
     * @return The remaining line as String.
     */
    public String readDescription() {
        String description = sc.nextLine();
        try {
            return description.substring(1);
        } catch (StringIndexOutOfBoundsException e) {
            return description;
        }
    }

}
