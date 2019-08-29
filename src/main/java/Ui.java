import java.util.Calendar;
import java.util.Scanner;

/**
 * Deals with user interaction for Duke
 */
public class Ui {
    public static final String LINE = "    ____________________________________________________________\n";

    /**
     * Greets the user with default text.
     */
    public void greet() {
        String greeting = LINE
                + "     Hello! I'm Duke\n"
                + "     What can I do for you?\n"
                + LINE;

        System.out.println(greeting);
    }

    /**
     * Gets user input through the console.
     *
     * @param parser Parser object that Duke has initialised.
     */
    public void getUserInput(Parser parser) {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        parser.parse(userInput);
    }

}
