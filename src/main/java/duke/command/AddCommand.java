package duke.command;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;

public class AddCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, String command) {
        String message = "";
        try {
            String addTextDescription;
            String addTextPeriod;
            if (command.contains("todo")) {
                addTextDescription = command.substring(command.lastIndexOf("todo ") + 5);
                if (addTextDescription.length() == 0) {
                    throw new DukeException("Error [AddCommand] 0x0000006:" +
                            System.lineSeparator() + "The description of a todo cannot be empty.");
                } else {
                    message += tasks.add(new Todo(addTextDescription));
                }
            } else if (command.contains("deadline")) {
                if (command.lastIndexOf("/by") == -1) {
                    throw new DukeException("Error [AddCommand] 0x0000007:" +
                            System.lineSeparator() + "You are missing information.");
                }
                addTextDescription = command.substring(9, command.lastIndexOf("/by")).trim();
                addTextPeriod = command.substring(command.lastIndexOf("/by ") + 3).trim();
                if (addTextDescription.length() == 0 || addTextPeriod.length() == 0) {
                    throw new DukeException("Error [AddCommand] 0x0000008:" +
                            System.lineSeparator() + "You are missing information.");
                } else {
                    message += tasks.add(new Deadline(addTextDescription, addTextPeriod));
                }
            } else if (command.contains("event")) {
                if (command.lastIndexOf("/at") == -1) {
                    throw new DukeException("Error [AddCommand] 0x0000009:" +
                            System.lineSeparator() + "You are missing information.");
                }
                addTextDescription = command.substring(6, command.lastIndexOf("/at")).trim();
                addTextPeriod = command.substring(command.lastIndexOf("/at ") + 3).trim();
                if (addTextDescription.length() == 0 || addTextPeriod.length() == 0) {
                    throw new DukeException("Error [AddCommand] 0x0000010:" +
                            System.lineSeparator() + "You are missing information.");
                } else {
                    message += tasks.add(new Event(addTextDescription, addTextPeriod));
                }
            } else {
                throw new DukeException("Error [AddCommand] 0x0000011:" +
                        System.lineSeparator() + "I'm sorry, but I don't know what that means.");
            }
        } catch (DukeException ex) {
            return ex.getMessage();
        }
        return message;
    }

}
