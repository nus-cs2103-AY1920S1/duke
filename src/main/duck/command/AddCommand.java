package duck.command;

import duck.task.Deadline;
import duck.task.Event;
import duck.task.Task;
import duck.task.Todo;
import duck.util.Storage;
import duck.util.TaskList;
import duck.util.Ui;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * This is a <code>Command</code> to add tasks into the task list.
 * Three available types of adding commands for different tasks are (1)todo with the task description; (2)deadline with
 * the description and a due time; (3)event with the description and a start time and an ending time. After the
 * <code>execute</code>, corresponding tasks will be added to task list.
 */
public class AddCommand extends Command {

    private String taskType;
    private String description;
    private LocalDateTime dateTime;
    private LocalTime time;

    /**
     * This is a class constructor specifying task type and task details.
     *
     * @param taskType       a string representing the task type
     * @param description    a string contains the task details
     */
    public AddCommand(String taskType, String description) {
        this.taskType = taskType;
        this.description = description;
    }

    /**
     * This is a class constructor specifying task type, task details, and the datetime.
     *
     * @param taskType       a string representing the task type
     * @param description    a string contains the task details
     * @param dateTime       a <code>LocalDateTime</code> object
     */
    public AddCommand(String taskType, String description, LocalDateTime dateTime) {
        this(taskType, description);
        this.dateTime = dateTime;
    }

    /**
     * This is a class constructor specifying task type, task details, the datetime, and an ending time.
     *
     * @param taskType       a string representing the task type
     * @param description    a string contains the task details
     * @param dateTime       a <code>LocalDateTime</code> object
     * @param time           a <code>LocalTime</code> object
     */
    public AddCommand(String taskType, String description, LocalDateTime dateTime, LocalTime time) {
        this(taskType, description, dateTime);
        this.time = time;
    }

    /**
     * Adds a certain <code>Task</code> object to the task list and shows users the successful execution of adding.
     * Task types can be <code>Todo</code>, <code>Deadline</code>, <code>Event</code>, which depends on
     * <code>taskType</code> specified in the constructor.
     *
     * @param taskList        the task list that provides information about users' current tasks and to be modified
     * @param ui              the <code>Ui</code> object to handle input and output
     * @param storage         the <code>Storage</code> object to load and record data
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Task newTask;

        switch (taskType) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            newTask = new Deadline(description, dateTime);
            break;
        default:
            newTask = new Event(description, dateTime, time);
        }

        taskList.add(newTask);
        ui.showTaskAdded(taskList.getTotalTask(), newTask);
    }

    /**
     * Compares two <code>AddCommand</code> objects according to their description, dateTime, and time, if there is any.
     *
     * @param obj  the object to be compared
     * @return     <code>true</code> if two objects are both <code>AddCommand<></code> and have the same description,
     *             dateTime, and time if specified in the constructor;
     *             <code>false</code> otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand another = (AddCommand) obj;
            if (taskType.equals(another.taskType)) {
                return description.equals(another.description)
                        && (dateTime == null || dateTime.equals(another.dateTime))
                        && (time == null || time.equals(another.time));
            } else {
                return false;
            }

        } else {
            return false;
        }
    }
}
