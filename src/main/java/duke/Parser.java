package duke;

import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.DoneCommand;
import command.EventCommand;
import command.ExitCommand;
import command.FindCommand;
import command.ListCommand;
import command.ToDoCommand;
import exception.DukeException;
import exception.DukeInvalidTaskDescriptionException;
import exception.DukeInvalidTaskTimeException;
import task.Task;

public class Parser {

    /**
     * Returns a Command that is converted from user's input.
     * @param input a string that contains the user input.
     * @return a Command for the main logic in Duke to execute.
     * @throws DukeException DukeException that may arise from invalid inputs.
     */
    public static Command parse(String input) throws DukeException {
        String[] token = input.split(" ");
        String[] temp;
        String taskDesc;
        Task t;
        switch (token[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            if (token.length <= 1) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
            try {
                int index = Integer.parseInt(token[1]);
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
        case "delete" :
            if (token.length <= 1) {
                throw new DukeException("Give me a goddamn numbered task to delete.");
            }
            try {
                int index = Integer.parseInt(token[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Give me a goddamn numbered task to delete.");
            }
        case "todo":
            if (token.length <= 1) {
                throw new DukeInvalidTaskDescriptionException("ToDo");
            }
            return new ToDoCommand(token[1]);
        case "deadline":
            temp = input.split("/by");
            taskDesc = temp[0].substring(8).trim();
            if (taskDesc.equals("")) {
                throw new DukeInvalidTaskDescriptionException("Deadline");
            } else if (temp.length < 2) {
                throw new DukeInvalidTaskTimeException("deadline");
            }
            return new DeadlineCommand(taskDesc, temp[1].trim());
        case "event":
            temp = input.split("/at");
            taskDesc = temp[0].substring(5).trim();
            if (taskDesc.equals("")) {
                throw new DukeInvalidTaskDescriptionException("Event");
            } else if (temp.length < 2) {
                throw new DukeInvalidTaskTimeException("event");
            }
            return new EventCommand(taskDesc, temp[1].trim());
        case "find":
            if (token.length <= 1) {
                throw new DukeException("Give me a goddamn keyword to find.");
            }
            return new FindCommand(input.substring(4).trim());
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}