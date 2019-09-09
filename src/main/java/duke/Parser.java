package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.UpcomingCommand;
import duke.task.TaskEnum;

public class Parser {

    /**
     * Returns a command object that represents the command entered.
     *
     * @param input Command to be parsed.
     * @return Command object.
     */
    public static Command parse(String input) {
        // ADD TODO, DEADLINE, EVENT TASKS
        if (input.contains("todo") || input.contains("deadline") || input.contains("event")) {
            return processTaskCommand(input);
        } else if (input.contains("find")) { // Find an item
            return processFindCommand(input);
        } else if (input.contains("done")) { // Mark item as complete
            return processDoneCommand(input);
        } else if (input.contains("delete")) { // Delete item
            return processDeleteCommand(input);
        } else if (input.equals("list")) { // List items
            return new ListCommand();
        } else if (input.equals("upcoming")) { // List items
            return new UpcomingCommand();
        } else if (input.equals("bye")) { // Exit application
            return new ExitCommand();
        } else { // Invalid command
            new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return new EmptyCommand();
    }

    // Processing TODO, DEADLINE, EVENT Tasks
    private static Command processTaskCommand(String input) {
        try {
            String command = "";
            String remaining = "";

            // Check description is not empty
            if (input.indexOf(" ") == -1 || (input.indexOf(" ") + 1) == -1) {
                throw new DukeException("The description of a task cannot be empty.");
            }

            command = input.substring(0, input.indexOf(" "));
            remaining = input.substring(input.indexOf(" ") + 1);

            assert command.length() > 0 : "Command cannot be empty";
            assert remaining.length() > 0 : "Remaining cannot be empty";

            // Check description is not an empty space
            if (remaining.replace(" ", "").length() <= 0) {
                throw new DukeException("The description of a task cannot be empty.");
            }

            switch (command) {
            case "todo":
                return new AddCommand(remaining, TaskEnum.TODO);
            case "deadline":
                return addDateTask(input, remaining, "/by", TaskEnum.DEADLINE);
            case "event":
                return addDateTask(input, remaining, "/at", TaskEnum.EVENT);
            default:
                ;
                break;
            }
        } catch (DukeException de) {
            // Exception is thrown in the try block
        } catch (Exception e) {
            new DukeException("Something went wrong. Please try again.");
        }
        return new EmptyCommand();
    }

    private static Command processFindCommand(String input) {
        try {
            String command = "";
            String remaining = "";
            if (input.indexOf(" ") == -1 || (input.indexOf(" ") + 1) == -1) {
                throw new DukeException("The description of a find cannot be empty.");
            }

            command = input.substring(0, input.indexOf(" "));
            remaining = input.substring(input.indexOf(" ") + 1);

            assert command.length() > 0 : "Command cannot be empty";
            assert remaining.length() > 0 : "Remaining cannot be empty";

            // Check description is not empty
            if (remaining.replace(" ", "").length() <= 0) {
                throw new DukeException("The description of a find cannot be empty.");
            }

            return new FindCommand(remaining);
        } catch (DukeException de) {
            // Exception is thrown in the try block
        } catch (Exception e) {
            new DukeException("Something went wrong. Please try again.");
        }

        return new EmptyCommand();
    }

    private static Command processDoneCommand(String input) {
        int taskNo = Integer.parseInt(
            input.replace("done", "")
                .replace(" ", "")); // Removing 'done' and empty spaces

        assert taskNo != 0 : "Task number cannot be empty";

        return new DoneCommand(taskNo);
    }

    private static Command processDeleteCommand(String input) {
        int taskNo = Integer.parseInt(
            input.replace("delete", "")
                .replace(" ", "")); // Removing 'delete' and empty spaces

        assert taskNo != 0 : "Task number cannot be empty";

        return new DeleteCommand(taskNo);
    }

    private static Command addDateTask(String input, String remaining, String specifier,
        TaskEnum taskType) {
        try {
            //Check input is valid
            if (remaining.indexOf(specifier) == -1 || (input.indexOf(specifier) + 4) == -1) {
                throw new DukeException(
                    "Please ensure that " + specifier + " and the date is included");
            }

            var item = remaining.substring(0, remaining.indexOf(specifier) - 1);
            var date = remaining
                .substring(remaining.indexOf(specifier) + 4, remaining.length());

            assert item.length() > 0 : "Item cannot be empty";
            assert date.length() > 0 : "Date cannot be empty";

            return new AddCommand(item, date, taskType);
        } catch (DukeException de) {
            // Exception is thrown in the try block
        }
        return new EmptyCommand();
    }
}
