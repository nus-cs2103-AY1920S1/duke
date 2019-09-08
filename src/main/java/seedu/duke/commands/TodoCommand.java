package seedu.duke.commands;

import seedu.duke.TaskList;
import seedu.duke.exceptions.DukeException;
import seedu.duke.exceptions.EmptyTodoDescException;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(String arg, TaskList taskList) {
        super(arg, taskList);
    }

    @Override
    public String execute() throws DukeException {
        return addTodo(arg);
    }

    /**
     * Adds a Todo to the list of tasks.
     * @param desc The description of the todo
     * @return The response.
     * @throws DukeException If {@code desc} is empty.
     */
    private String addTodo(String desc) throws DukeException {
        assert desc != null;
        if (desc.isEmpty()) {
            throw new EmptyTodoDescException();
        }
        Task task = new Todo(desc);
        taskList.add(task);
        return taskList.getAddedMsg(task);
    }
}
