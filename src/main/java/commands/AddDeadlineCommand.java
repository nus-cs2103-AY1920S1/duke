package commands;

import components.Storage;
import tasks.Task;
import tasks.TaskBuilder;
import components.TaskList;
import tasks.TaskType;

import java.util.Date;

public class AddDeadlineCommand implements Command {
    private String taskName;
    private Date deadline;

    public AddDeadlineCommand(String taskName, Date deadline) {
        this.taskName = taskName;
        this.deadline = deadline;
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        Task temp = new TaskBuilder().type(TaskType.DEADLINE).description(taskName).deadline(deadline).build();
        String[] response = taskList.addTask(temp);
        storage.save(taskList.getArr());
        return response;
    }
}
