package duke.module;

import java.util.Scanner;

/**
 * Handles user input and output. <code>Ui</code> reads commands from the user and outputs the results.
 */
public class Ui {

    /** {@value DUKE_LOGO} */
    private static final String DUKE_LOGO = " ____        _        \n"
                                      + "    |  _ \\ _   _| | _____ \n"
                                      + "    | | | | | | | |/ / _ \\\n"
                                      + "    | |_| | |_| |   <  __/\n"
                                      + "    |____/ \\__,_|_|\\_\\___|\n";
    /** {@value DUKE_HELLO} */
    private static final String DUKE_HELLO = "Hello! I'm Duke!\n    What can I do for you?";
    /** {@value DUKE_BYE} */
    private static final String DUKE_BYE = "Bye. Hope to see you again soon!";
    /** {@value DUKE_LINE}*/
    private static final String DUKE_LINE = "    _________________________________________________________________";
    /** {@value DUKE_TAB4} */
    private static final String DUKE_TAB4 = "    ";

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
        System.out.println(DUKE_LINE);
        for (String line : message) {
            System.out.println(DUKE_TAB4 + line);
        }
        System.out.println(DUKE_LINE + "\n");
    }

    public void greet() {
        this.printToUser(DUKE_LOGO, DUKE_HELLO);
    }

    public void bye() {
        this.printToUser(DUKE_BYE);
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
