import java.util.Scanner;

/**
 * Encapsulates attributes and behaviour of Duke, a personal assistant chatbot.
 *
 * Duke can send its unique logo when queried.
 *
 * @author atharvjoshi
 * @contributors j-lum, damithc
 * @version CS2103 AY19/20 Sem 1 iP Week 2
 */
public class Duke {
    // a unique duke logo
    private final String logo;

    // greet message
    private String greetMessage;

    /**
     * COMMENT
     */
    public Duke() {
        logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        greetMessage = "Hello! I'm Duke\n"
                + "What can I do for you?\n";
    }

    /**
     * COMMENT
     * @return
     */
    public String getLogo() {
        return logo;
    }

    /**
     * COMMENT
     */
    public void greet() {
        System.out.print(greetMessage);
    }
}
