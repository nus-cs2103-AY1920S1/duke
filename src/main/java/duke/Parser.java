package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EmptyCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
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
            try {
                String command = "";
                String remaining = "";
                String item = "";
                String date = "";

                // Check description is not empty
                if (input.indexOf(" ") == -1 || (input.indexOf(" ") + 1) == -1) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    command = input.substring(0, input.indexOf(" "));
                    remaining = input.substring(input.indexOf(" ") + 1);

                    // Check description is not empty
                    if (remaining.replace(" ", "").length() <= 0) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                }

                switch (command) {
                case "todo":
                    return new AddCommand(remaining, TaskEnum.TODO);
                case "deadline":
                    //Check input is valid
                    if (remaining.indexOf("/by") == -1 || (input.indexOf("/by") + 4) == -1) {
                        throw new DukeException("Please ensure that /by and the date is included");
                    } else {
                        item = remaining.substring(0, remaining.indexOf("/by") - 1);
                        date = remaining
                            .substring(remaining.indexOf("/by") + 4, remaining.length());
                    }

                    return new AddCommand(item, date, TaskEnum.DEADLINE);
                case "event":
                    //Check input is valid
                    if (remaining.indexOf("/at") == -1 || (input.indexOf("/at") + 4) == -1) {
                        throw new DukeException("Please ensure that /at and the date is included");
                    } else {
                        item = remaining.substring(0, remaining.indexOf("/at") - 1);
                        date = remaining
                            .substring(remaining.indexOf("/at") + 4, remaining.length());
                    }

                    return new AddCommand(item, date, TaskEnum.EVENT);
                default:
                    ;
                    break;
                }

            } catch (DukeException de) {
                ;
            } catch (Exception e) {
                new DukeException("Something went wrong. Please try again.");
            }
        } else if (input.contains("find")) { // Find an item
            try {
                String command = "";
                String remaining = "";
                if (input.indexOf(" ") == -1 || (input.indexOf(" ") + 1) == -1) {
                    throw new DukeException("The description of a find cannot be empty.");
                } else {
                    command = input.substring(0, input.indexOf(" "));
                    remaining = input.substring(input.indexOf(" ") + 1);

                    // Check description is not empty
                    if (remaining.replace(" ", "").length() <= 0) {
                        throw new DukeException("The description of a find cannot be empty.");
                    }
                }

                return new FindCommand(remaining);
            } catch (DukeException de) {
                ;
            } catch (Exception e) {
                new DukeException("Something went wrong. Please try again.");
            }
        } else if (input.contains("done")) { // Mark item as complete
            int taskNo = Integer.parseInt(
                input.replace("done", "")
                    .replace(" ", "")); // Removing 'done' and empty spaces

            return new DoneCommand(taskNo);
        } else if (input.contains("delete")) { // Delete item
            int taskNo = Integer.parseInt(
                input.replace("delete", "")
                    .replace(" ", "")); // Removing 'delete' and empty spaces

            return new DeleteCommand(taskNo);
        } else if (input.equals("list")) { // List items
            return new ListCommand();
        } else if (input.equals("bye")) { // Exit application
            return new ExitCommand();
        } else { // Invalid command
            new DukeException("I'm sorry, but I don't know what that means :-(");
        }

        return new EmptyCommand();
    }
}
