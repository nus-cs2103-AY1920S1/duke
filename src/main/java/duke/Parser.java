package duke;

import command.*;
import exception.DukeException;
import exception.DukeInvalidTaskDescriptionException;
import exception.DukeInvalidTaskTimeException;
import task.Task;

import java.text.spi.NumberFormatProvider;

public class Parser {

    public static Command parse(String command) throws DukeException {
        String[] token = command.split(" ");
        String[] temp;
        String taskDesc;
        Task t;
        switch (token[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            if (token.length<=1) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
            try {
                int index = Integer.parseInt(token[1]);
                return new DoneCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Give me a goddamn numbered task to do.");
            }
        case "delete" :
            if (token.length<=1) {
               throw new DukeException("Give me a goddamn numbered task to delete.");
            }
            try {
                int index = Integer.parseInt(token[1]);
                return new DeleteCommand(index);
            } catch (NumberFormatException e) {
                throw new DukeException("Give me a goddamn numbered task to delete.");
            }
        case "todo":
            if (token.length<=1) {
                throw new DukeInvalidTaskDescriptionException("ToDo");
            }
            return new ToDoCommand(token[1]);
        case "deadline":
            temp = command.split("/by");
            taskDesc = temp[0].substring(8).trim();
            if(taskDesc.equals("")) {
                throw new DukeInvalidTaskDescriptionException("Deadline");
            } else if(temp.length < 2) {
                throw new DukeInvalidTaskTimeException("deadline");
            }
            return new DeadlineCommand(taskDesc, temp[1].trim());
        case "event":
            temp = command.split("/at");
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
            return new FindCommand(command.substring(4).trim());
        default:
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}


