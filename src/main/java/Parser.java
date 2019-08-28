import java.util.ArrayList;
import java.util.Arrays;

// Deals with making sense of the user command
public class Parser {

    // Constructor
    public Parser() { }

    public Command parse(String userInput, TaskList taskList) throws DukeException {
        ArrayList<String> userInputArr = splitStrIntoArr(userInput, " ");
        Command currCommand;
        String taskDescription;
        // Identify command by first word
        switch (userInputArr.get(0)) {
        case "bye":
            currCommand = new ExitCommand();
            break;
        case "list":
            currCommand = new ListCommand();
            break;
        case "todo":
            // Empty description
            if (userInputArr.size() == 1) {
                throw new EmptyDescriptionException("todo");
            } else {
                // Splice back the description
                taskDescription = String.join(" ",
                        userInputArr.subList(1, userInputArr.size()));
                currCommand = new AddCommand("todo",
                        taskDescription, false);
            }
            break;
        case "find":
            // Empty description
            if (userInputArr.size() == 1) {
                throw new EmptyDescriptionException("find");
            } else {
                // Splice back the description
                String findDescription = String.join(" ",
                        userInputArr.subList(1, userInputArr.size()));
                currCommand = new FindCommand(findDescription);
            }
            break;
        case "deadline":
            currCommand = parseSubCommand("deadline", "/by", userInputArr);
            break;
        case "event":
            currCommand = parseSubCommand("event", "/at", userInputArr);
            break;
        case "done":
            // Empty/no list index of task provided
            if (userInputArr.size() == 1 || userInputArr.size() > 2) {
                throw new DukeException("Please put the list index of the " +
                        "completed task after \"done\" and nothing else.");
            } else {
                // Check if integer is provided
                try {
                    int doneIdx = Integer.parseInt(userInputArr.get(1));
                    // Check if integer is within range of number of tasks
                    if (doneIdx > taskList.getNumTasks() || doneIdx < 1) {
                        throw new DukeException("Integer is not within range of tasks.");
                    }
                    currCommand = new DoneCommand(doneIdx);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid integer after \"Done\".");
                }
            }
            break;
        case "delete":
            // Empty/no list index of task provided
            if (userInputArr.size() == 1 || userInputArr.size() > 2) {
                throw new DukeException("Please put the list index of the " +
                        "completed task after \"delete\" and nothing else.");
            } else {
                // Check if integer is provided
                try {
                    int deleteIdx = Integer.parseInt(userInputArr.get(1));
                    // Check if integer is within range of number of tasks
                    if (deleteIdx > taskList.getNumTasks() || deleteIdx < 1) {
                        throw new DukeException("Integer is not within range of tasks.");
                    }
                    currCommand = new DeleteCommand(deleteIdx);
                } catch (NumberFormatException e) {
                    throw new DukeException("Please enter a valid integer after \"delete\".");
                }
            }
            break;
        default:
            throw new UnknownCommandException();
        }
    return currCommand;
    }

    // Handles user input of commands with subcommands
    private Command parseSubCommand(String command, String subCommand, ArrayList<String> userInputArr) throws DukeException{
        // Empty Description
        // E.g. (only "deadline" or "deadline /by") or (only "event" or "deadline /at")
        if (userInputArr.size() == 1 || userInputArr.get(1).equalsIgnoreCase(subCommand)) {
            throw new EmptyDescriptionException(command);
        } else {
            int firstByIdx = userInputArr.indexOf(subCommand);
            int lastByIdx = userInputArr.lastIndexOf(subCommand);
            // No "/by" or multiple "/by"s provided
            if (firstByIdx == -1 || firstByIdx != lastByIdx) {
                throw new IncorrectInfoInputException(subCommand);
                // No description of "/by" or "/at"
            } else if (firstByIdx == userInputArr.size()-1) {
                throw new EmptyDescriptionException(subCommand);
            } else {
                // Splice words after first-word command and before "/by" or "/at"
                String taskDescription = String.join(" ",
                        userInputArr.subList(1, firstByIdx));
                // Description for '/by', '/at'
                String subCommandDescription = String.join(" ",
                        userInputArr.subList(firstByIdx+1, userInputArr.size()));
                Command newCommand = new SubCommand(command, taskDescription, subCommand, subCommandDescription);
                return newCommand;
            }
        }
    }

    // Splits a string by given regex pattern to AL
    // E.g. "A B C" to [A, B, C] with pattern " "
    public ArrayList<String> splitStrIntoArr(String input, String pattern) {
        ArrayList<String> result = new ArrayList<String>(
                // Split string by pattern
                Arrays.asList(input.split(pattern))
        );
        return result;
    }
}
