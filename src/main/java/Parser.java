import java.util.Scanner;

public class Parser {

    public static Command parse(String fullCommand) throws DukeException {
        Scanner sc = new Scanner(fullCommand);
        String command = sc.next();
        if (command.equals("bye")) {
            return new ExitCommand(fullCommand);
        } else if (command.equals("list")) {
            return new ListCommand(fullCommand);
        } else if (command.startsWith("delete")) {
            return new DeleteCommand(fullCommand);
        } else if (command.startsWith("done")) {
            return new DoneCommand(fullCommand);
        } else if (command.startsWith("todo") || command.startsWith("deadline")
                || command.startsWith("event")) {
            return new AddCommand(fullCommand);
        } else {
            throw new DukeException("     \u2639 OOPS!!! I'm sorry, " +
                    "but I don't know what that means :-(");
        }
    }

}
