/**
 * Simulates an add command of the Duke System.
 * @author Fabian Chia Hup Peng
 */

public class AddCommand extends Command {

    private String taskType;
    private String taskDescription;
    private String taskDateTime;

    /**
     * Creates an AddCommand instance with the appropriate attributes.
     * @param taskType The type of task to be recorded.
     * @param taskDescription The description of task to be recorded.
     */
    public AddCommand(String taskType, String taskDescription) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
    }

    /**
     * Creates an AddCommand instance with the appropriate attributes.
     * @param taskType The type of task to be recorded.
     * @param taskDescription The description of task to be recorded.
     * @param taskDateTime The date & time of the task to be recorded.
     */
    public AddCommand(String taskType, String taskDescription, String taskDateTime) {
        this.taskType = taskType;
        this.taskDescription = taskDescription;
        this.taskDateTime = taskDateTime;
    }

    /**
     * Executes the add command; creates and adds the task to the task list,
     * and prints the added message.
     * @param taskList The task list for the task to be added to.
     * @param ui The ui which prints the added message.
     * @param storage The storage which deals with the hard drive.
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if(isToDo(taskType)) {
            ToDo toDo = new ToDo(taskDescription);
            taskList.addTask(toDo);
            ui.printAddedMessage(toDo, taskList);
        } else if(isDeadline(taskType)) {
            Deadline deadline = new Deadline(taskDescription, taskDateTime);
            taskList.addTask(deadline);
            ui.printAddedMessage(deadline, taskList);
        } else {
            Event event = new Event(taskDescription, taskDateTime);
            taskList.addTask(event);
            ui.printAddedMessage(event, taskList);
        }
    }

    private static boolean isToDo(String str) {
        return str.equals("todo");
    }

    private static boolean isDeadline(String str) {
        return str.equals("deadline");
    }

}
