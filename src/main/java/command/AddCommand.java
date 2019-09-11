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
            String removedTodo = textInput.replaceFirst("todo ", "");
            String[] splitRemovedTodo = removedTodo.split(" /p ");
            String description = splitRemovedTodo[0];
            int priority = Integer.parseInt(splitRemovedTodo[1]);
            return TaskList.addTask(new Todo(description, priority));
        } else if (textInput.startsWith("deadline")) {
            if (isInvalidCommand(textInput, "deadline")) {
                throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
            }
            String removeTaskWord = textInput.replaceFirst("deadline ", "");
            String[] taskSplit = removeTaskWord.split(" /by ");
            String[] findPriority = taskSplit[1].split(" /p ");
            int priority = Integer.parseInt(findPriority[1]);
            return TaskList.addTask(new Deadline(taskSplit[0], findPriority[0], priority));
        } else if (textInput.startsWith("event")) {
            if (isInvalidCommand(textInput, "event")) {
                throw new DukeException("OOPS!!! The description of a event cannot be empty.");
            }

            String removeTaskWord = textInput.replaceFirst("event ", "");
            String[] taskSplit = removeTaskWord.split(" /at ");
            String[] findPriority = taskSplit[1].split(" /p ");
            int priority = Integer.parseInt(findPriority[1]);
            return TaskList.addTask(new Event(taskSplit[0], findPriority[0], priority));
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}