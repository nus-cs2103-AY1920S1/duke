import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Scanner scanner = new Scanner(System.in);

    private static final List<String> inputList = new ArrayList<>();

    /**
     * Adds the given input to the list of previous user inputs if the input
     * is not "list". Otherwise, prints the full list of user inputs.
     * @param input     User text to be processed.
     */
    private static void process(String input) {
        if (input.equals("list")) {
            DukeFormatter.prettyPrint(inputList);
        } else {
            inputList.add(input);
            DukeFormatter.prettyPrint("added: " + input);
        }
    }

    /**
     * Takes in user input and processes it accordingly without input validation.
     * @param args  Standard args
     */
    // TODO: add input validation
    public static void main(String[] args) {
        DukeFormatter.prettyPrint(
                "Hello! I'm Duke\nWhat can I do for you?");
        while (scanner.hasNext()) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) { break; }
            process(userInput);
        }
        DukeFormatter.prettyPrint("Bye. Hope to see you again soon!");
    }
}
