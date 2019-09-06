import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws InputMismatchException {
        Scanner commandReader  = new Scanner(fullCommand);
        String command = commandReader.next();

        if (command == "bye") {
            return new ExitCommand();
        } else if (command == "list") {
            return new ListCommand();
        } else if (command == "done") {
            int taskId = commandReader.nextInt(); // extract the task ID entered by user
            return new DoneCommand(taskId);
        } else if (command == "delete") {
            int taskId = commandReader.nextInt();
            return new DeleteCommand(taskId);
        } else if (command == "todo") {
            String restOfCommand = commandReader.nextLine();
            return new AddTodoCommand(restOfCommand);
        } else if (command == "event") {
            String restOfCommand = commandReader.nextLine();
            return new AddEventCommand(restOfCommand);
        } else if (command == "deadline") {
            String restofCommand = commandReader.nextLine();
            return new AddDeadlineCommand(restofCommand);
        } else {
            throw new InputMismatchException();
        }
    }
}
