package command;

import task.DukeException;
import task.Todo;
import task.Event;
import task.Deadline;
import task.TaskList;

public class AddCommand extends Command {
    private final String textInput;

    public AddCommand(String textInput) {
        this.textInput = textInput;
    }

    @Override
    public String execute() {
        if (textInput.startsWith("todo")) {
            if (isInvalidCommand(textInput, "todo")) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            }
            String description = textInput.replaceFirst("todo ", "");
            return TaskList.addTask(new Todo(description));
        } else if (textInput.startsWith("deadline")) {
            if (isInvalidCommand(textInput, "deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String removeTaskWord = textInput.replaceFirst("deadline ", "");
            String[] taskSplit = removeTaskWord.split(" /by ");
            return TaskList.addTask(new Deadline(taskSplit[0], taskSplit[1]));
        } else if (textInput.startsWith("event")) {
            if (isInvalidCommand(textInput, "event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            String removeTaskWord = textInput.replaceFirst("event ", "");
            String[] taskSplit = removeTaskWord.split(" /at ");
            return TaskList.addTask(new Event(taskSplit[0], taskSplit[1]));
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}