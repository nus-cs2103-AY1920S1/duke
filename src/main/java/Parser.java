import java.util.Scanner;
import java.util.NoSuchElementException;

/**
 * Represents a parser used to make sense of user input.
 */
public class Parser {

    /**
     * Parses the full string of command entered by the user into different parts that is understandable for duke.
     * @param fullCmd user input
     * @return a command comprising the command and the command details parsed from the user input.
     */

    public static Command parse(String fullCmd) {
        Scanner sc = new Scanner(fullCmd);
        String cmd = sc.next().trim();
        String remaining;
        try {
            remaining = sc.nextLine().trim();

        } catch (NoSuchElementException e) {
            remaining = "";
        }
        sc.close();
        return new Command(cmd, remaining);
    }


}