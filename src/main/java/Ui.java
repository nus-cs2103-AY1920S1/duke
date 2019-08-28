import java.io.IOException;
import java.util.Scanner;

/**
 * Represents the UI and deals with interactions with the user.
 */
public class Ui {

    /**
     * An empty constructor that creates the Ui object.
     */
    public Ui() {}

    /**
     * Prints the hello message at the start of the program.
     */
    public void hello() {
        String openingMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        System.out.println(openingMessage);
    }

    /**
     * Takes in user input and passes it on to a Parser object to deal with the input.
     */
    public void takeInUserInput() {
        Scanner scanner = new Scanner(System.in);
        Parser parser = new Parser();
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            command = command.trim();

            try {
                parser.executeCommand(command);
            } catch (InvalidCommandException | MissingInputException | MissingDescriptionException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Prints the exit message when user exits the program. Overwrites the tasks in the stored tasks file.
     */
    public void exit() {
        System.out.println("\tBye. Hope to see you again soon!");
        Storage storage = new Storage();
        try {
            storage.overwriteTasks();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.exit(0);
    }

}
