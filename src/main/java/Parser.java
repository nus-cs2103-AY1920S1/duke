import java.util.Scanner;

public class Parser {
    public Command parse(String userInput) {
        String[] separatedInputs = userInput.trim().split("\\s+");
        switch (separatedInputs[0].toLowerCase()) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            //fallthrough
        case "delete":
            Scanner s = new Scanner(separatedInputs[1]);
            if (separatedInputs.length > 2 || !s.hasNextInt()) {
                return new IncorrectCommand();
            }
            if (separatedInputs[0].toLowerCase().equals("done")) {
                return new DoneCommand(s.nextInt() - 1);
            } else {
                return new DeleteCommand(s.nextInt() - 1);
            }
        case "todo":
            String description = userInput.trim().substring(separatedInputs[0].length()).trim();
            if (description.equals("")) {
                return new IncorrectCommand();
            }
            return new ToDoCommand(description);
        case "deadline":
            if (userInput.indexOf("/by") == -1) {
                return new IncorrectCommand();
            } else {
                String[] arguments = userInput.trim().substring(separatedInputs[0].length()).trim().
                        split("/by");
                return new DeadlineCommand(arguments);
            }
        case "event":
            if (userInput.indexOf("/at") == -1) {
                return new IncorrectCommand();
            } else {
                String[] arguments = userInput.trim().substring(separatedInputs[0].length()).trim().
                        split("/at");
                return new EventCommand(arguments);
            }
        default:
            return new IncorrectCommand();
        }
    }
}
