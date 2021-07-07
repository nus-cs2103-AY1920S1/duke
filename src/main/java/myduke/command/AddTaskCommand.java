package myduke.command;

import java.util.Scanner;

import myduke.storage.StorageManager;
import myduke.task.DoAfter;
import myduke.ui.Ui;
import myduke.exception.DukeException;
import myduke.exception.DukeIllegalArgumentException;
import myduke.task.Deadline;
import myduke.task.Event;
import myduke.task.Task;
import myduke.task.TaskList;
import myduke.task.ToDo;
import myduke.type.TaskType;

/**
 * Adds a task to a list of tasks.
 */
public class AddTaskCommand extends Command {
    private final TaskType type;
    private final String arguments;

    public AddTaskCommand(TaskType type, String arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, StorageManager storage) throws DukeException {
        Task newTask = createTask();
        taskList.addTask(newTask);
        ui.printResponse("Got it. I've added this task:", taskList.size(), newTask);
    }

    /**
     * Creates a task element.
     *
     * @return a task element.
     *
     * @throws DukeException if type of task or their arguments are invalid.
     */
    private Task createTask() throws DukeException {
        Scanner in = new Scanner(" " + arguments.trim());
        switch (type) {
        case TASK_TODO:
            return ToDo.parse(in);

        case TASK_DEADLINE:
            return Deadline.parse(in);

        case TASK_EVENT:
            return Event.parse(in);

        case TASK_DO_AFTER:
            return DoAfter.parse(in);

        default:
            throw new DukeIllegalArgumentException("TaskType is not implemented");
        }
    }
}
