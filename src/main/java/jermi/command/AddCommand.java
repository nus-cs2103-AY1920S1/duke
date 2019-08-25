package jermi.command;

import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.component.Ui;
import jermi.exception.JermiException;
import jermi.task.Deadline;
import jermi.task.Event;
import jermi.task.Task;
import jermi.task.ToDo;
import jermi.type.TaskType;

/**
 * A representation of the command for adding task to the list.
 */
public class AddCommand extends Command {
    /** Task type of the task to be added */
    private TaskType taskType;
    /** Description of the task to be added */
    private String description;

    /**
     * Public constructor for class.
     *
     * @param taskType Task type of the task to be added.
     * @param description Description of the task to be added.
     */
    public AddCommand(TaskType taskType, String description) {
        super();
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * Creates the task according to the task type and the description of the task.
     *
     * @return Created task.
     */
    private Task createTask() {
        Task task = null;
        switch (this.taskType) {
        case TO_DO:
            task = new ToDo(this.description);
            break;
        case DEADLINE:
        case EVENT:
            String[] activityAndDateTime = this.description.split("/", 2);
            String activity = activityAndDateTime[0].trim();
            String dateTime = activityAndDateTime[1].split(" ", 2)[1];
            switch (this.taskType) {
            case DEADLINE:
                task = new Deadline(activity, dateTime);
                break;
            case EVENT:
                task = new Event(activity, dateTime);
                break;
            }
            break;
        }
        return task;
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param ui UI.
     * @param storage Storage.
     * @throws JermiException JermiException.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws JermiException {
        Task task = this.createTask();
        taskList.add(task);
        int numOfTasks = taskList.getSize();
        ui.echo("Got it. I've added this task:"
                , "  " + task
                , String.format("Now you have %d task%s in the list.", numOfTasks, numOfTasks == 1 ? "" : "s"));
        storage.taskListToFile();
    }

    /**
     * Indicates if the program should exit.
     *
     * @return {@code false}.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
