import java.io.IOException;
import java.util.Scanner;

public class Ui {

    public Ui() {
    }

    public void hello() {
        String openingMessage = "\tHello! I'm Duke\n\tWhat can I do for you?\n";
        System.out.println(openingMessage);
    }

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
