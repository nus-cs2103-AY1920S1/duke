package jermi.command;

import static jermi.misc.Constant.COMMAND_ADD_ACTIVITY_DATE_TIME_DELIMITER;
import static jermi.misc.Constant.COMMAND_ADD_ACTIVITY_DATE_TIME_SPLIT_LIMIT;
import static jermi.misc.Constant.COMMAND_ADD_ACTIVITY_INDEX;
import static jermi.misc.Constant.COMMAND_ADD_MESSAGE;
import static jermi.misc.Constant.COMMAND_ADD_DATE_TIME_INDEX;
import static jermi.misc.Constant.COMMAND_NUMBER_OF_TASKS;
import static jermi.misc.Constant.COMMAND_PLURAL_NOUN;
import static jermi.misc.Constant.COMMAND_ADD_PREPOSITION_DATE_TIME_DELIMITER;
import static jermi.misc.Constant.COMMAND_ADD_PREPOSITION_DATE_TIME_INDEX;
import static jermi.misc.Constant.COMMAND_ADD_PREPOSITION_DATE_TIME_SPLIT_LIMIT;
import static jermi.misc.Constant.COMMAND_SINGULAR_NOUN;
import static jermi.misc.Constant.COMMAND_TASK_FORMAT;

import java.util.Objects;

import jermi.component.Formatter;
import jermi.component.Storage;
import jermi.component.TaskList;
import jermi.exception.JermiException;
import jermi.task.Deadline;
import jermi.task.Event;
import jermi.task.Task;
import jermi.task.ToDo;
import jermi.misc.TaskType;

/**
 * A representation of the command for adding a task to the list.
 */
public class AddCommand extends Command {
    /** Task type of the task to be added. */
    private TaskType taskType;
    /** Description of the task to be added. */
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
            //Fallthrough
        case EVENT:
            String[] activityAndDateTime = this.description.split(COMMAND_ADD_ACTIVITY_DATE_TIME_DELIMITER,
                    COMMAND_ADD_ACTIVITY_DATE_TIME_SPLIT_LIMIT);
            String activity = activityAndDateTime[COMMAND_ADD_ACTIVITY_INDEX].trim();
            String dateTime = activityAndDateTime[COMMAND_ADD_PREPOSITION_DATE_TIME_INDEX]
                    .split(COMMAND_ADD_PREPOSITION_DATE_TIME_DELIMITER,
                            COMMAND_ADD_PREPOSITION_DATE_TIME_SPLIT_LIMIT)[COMMAND_ADD_DATE_TIME_INDEX];
            switch (this.taskType) {
            case DEADLINE:
                task = new Deadline(activity, dateTime);
                break;
            case EVENT:
                task = new Event(activity, dateTime);
                break;
            default:
                assert task != null : "task cannot be null";
            }
            break;
        default:
            assert task != null : "task cannot be null";
        }
        return task;
    }

    /**
     * Executes the command.
     *
     * @param taskList Task list.
     * @param formatter Formatter.
     * @param storage Storage.
     * @return Output response.
     * @throws JermiException JermiException.
     */
    @Override
    public String execute(TaskList taskList, Formatter formatter, Storage storage) throws JermiException {
        Task task = this.createTask();
        taskList.add(task);
        int numOfTasks = taskList.getSize();
        storage.taskListToFile();
        assert numOfTasks >= 0 : "numOfTasks should be >= 0";
        return formatter.echo(COMMAND_ADD_MESSAGE,
                String.format(COMMAND_TASK_FORMAT, task), String.format(COMMAND_NUMBER_OF_TASKS, numOfTasks,
                        numOfTasks == 1 ? COMMAND_SINGULAR_NOUN : COMMAND_PLURAL_NOUN));
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

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (!(obj instanceof AddCommand)) {
            return false;
        } else {
            AddCommand other = (AddCommand) obj;
            return this.taskType == other.taskType && this.description.equals(other.description);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.taskType, this.description);
    }
}
