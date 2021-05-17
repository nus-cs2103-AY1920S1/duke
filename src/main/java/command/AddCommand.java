package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.TaskList;
import task.TaskType;

import java.util.Calendar;


/**
 * Represents a command that adds a task to the task list.
 */
public class AddCommand extends Command {
    private TaskType taskType;
    private String message;
    private Calendar calendar;

    /**
     * Creates a Command to add a new task with a date.
     *
     * @param taskType type of the task.
     * @param message message of the task.
     * @param calendar date of the task represented in a Calendar object.
     */
    public AddCommand(TaskType taskType, String message, Calendar calendar) {
        this.taskType = taskType;
        this.message = message;
        this.calendar = calendar;
    }

    /**
     * Creates a Command to add a new task without a date.
     *
     * @param taskType type of the task.
     * @param message message of the task.
     */
    public AddCommand(TaskType taskType, String message) {
        this.taskType = taskType;
        this.message = message;
    }

    /**
     * Executes this task.
     *
     * @param tasks All the tasks that the user currently has.
     * @param ui The Ui object associated with Duke.
     * @param storage The Storage object associated with Duke
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task;

        switch (taskType) {
        case TODO:
            Task newTaskTodo = new Task(message, TaskType.TODO);
            tasks.add(newTaskTodo);
            task = newTaskTodo;
            break;
        case DEADLINE:
            Task newTaskDeadline = new Task(message, TaskType.DEADLINE);
            newTaskDeadline.setTime(calendar);
            tasks.add(newTaskDeadline);
            task = newTaskDeadline;
            break;
        case EVENT:
            Task newTaskEvent = new Task(message, TaskType.EVENT);
            newTaskEvent.setTime(calendar);
            tasks.add(newTaskEvent);
            task = newTaskEvent;
            break;
        default:
            task = new Task("placeholder", TaskType.TODO);
        }

        ui.showLine();
        ui.showAddTaskMessage();
        ui.printAddedTask(task);
        ui.println("     Now you have " + tasks.size() + " tasks in the list.");
        ui.showLine();
    }
}
