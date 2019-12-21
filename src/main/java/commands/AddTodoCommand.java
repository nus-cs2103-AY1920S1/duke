package commands;

import components.Storage;
import tasks.Task;
import tasks.TaskBuilder;
import components.TaskList;
import tasks.TaskType;

public class AddTodoCommand implements Command {
    private String description;

    public AddTodoCommand(String description) {
        this.description = description;
    }

    @Override
    public String[] execute(Storage storage, TaskList taskList) throws DukeException {
        Task temp = new TaskBuilder().type(TaskType.TODO).description(description).build();
        String[] response = taskList.addTask(temp);
        storage.save(taskList.getArr());
        return response;
    }
}
