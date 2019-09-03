import java.util.Scanner;

public class Duke{
    /**
     * Main driver class for Duke.
     *
     */
    public static void main(String[] args) {
        dukePrint("Hello! I'm Duke", "What can I do for you?");
        Scanner consoleScanner = new Scanner(System.in);
        String userInput = "";
        while (!userInput.equals("exit")) {
            userInput = consoleScanner.next();
            if (!userInput.equals("exit")) {
                echo(userInput);
            }
        }

        dukePrint("Bye. Hope to see you again soon!");
    }

    /**
     * Echos string.
     * @param echoedString targeted String to be echoed
     */
    private static void echo(String echoedString) {
        dukePrint(echoedString);
    }

    /**
     * Prints what Duke says in correct format.
     * @param texts any number of String arguments
     *              each prints on a new line.
     */

    private static void dukePrint(String... texts) {
        System.out.println("    _____________________________");
        for (String text : texts) {
            System.out.println("    " + text);
        }
        System.out.println("    _____________________________");

    }


}
