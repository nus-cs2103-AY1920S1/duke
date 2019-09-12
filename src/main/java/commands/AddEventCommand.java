package commands;

import components.Storage;
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
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        Task temp = new TaskBuilder().type(TaskType.EVENT).description(taskName).timeframe(date).build();
        String[] response = taskList.addTask(temp);
        storage.save(taskList.getArr());
        return response;
    }
}
