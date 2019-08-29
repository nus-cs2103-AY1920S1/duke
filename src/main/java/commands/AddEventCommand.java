package commands;

import components.Storage;
import components.Ui;
import tasks.Task;
import tasks.TaskBuilder;
import components.TaskList;
import tasks.TaskType;

import java.util.Date;

public class AddEventCommand implements Command {
    private String taskName;
    private Date date;

    public AddEventCommand(String taskName, Date date) {
        this.taskName = taskName;
        this.date = date;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        Task temp = new TaskBuilder().type(TaskType.EVENT).description(taskName).timeframe(date).build();
        taskList.addTask(temp);
    }
}
