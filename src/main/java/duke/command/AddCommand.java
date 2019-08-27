package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskEnum;

public class AddCommand extends Command {

    private String description = "";
    private String date = "";
    private TaskEnum taskType;

    /**
     * Creates a new AddCommand object.
     *
     * @param description Description of task.
     * @param taskType Type of task.
     */
    public AddCommand(String description, TaskEnum taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Creates a new AddCommand object.
     *
     * @param description Description of task.
     * @param date Date of task.
     * @param taskType Type of task.
     */
    public AddCommand(String description, String date, TaskEnum taskType) {
        this.description = description;
        this.date = date;
        this.taskType = taskType;
    }

    /**
     * Executes the current command.
     *
     * @param ui Ui object.
     * @param storage Storage object.
     * @param taskList TaskList object.
     */
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task task = null;
        switch (taskType) {
        case TODO:
            task = taskList.add(description, TaskEnum.TODO);
            break;
        case DEADLINE:
            task = taskList.add(description, date, TaskEnum.DEADLINE);
            break;
        case EVENT:
            task = taskList.add(description, date, TaskEnum.EVENT);
            break;
        default:
            ;
            break;
        }

        if (task != null) {
            ui.printOutput("  " + task,
                "Got it. I've added this task: ",
                taskList.getTaskList().size());
            storage.save(taskList.getTaskList());
        }
    }
}
