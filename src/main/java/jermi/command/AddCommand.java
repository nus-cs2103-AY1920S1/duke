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

public class AddCommand extends Command {
    private TaskType taskType;
    private String description;

    public AddCommand(TaskType taskType, String description) {
        super();
        this.taskType = taskType;
        this.description = description;
    }

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

    @Override
    public boolean shouldExit() {
        return false;
    }
}
