package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;
import java.util.NoSuchElementException;

public class AddTodoCommand extends AddTaskCommand {

    public AddTodoCommand(String restOfCommand) {
        super(restOfCommand);
    }

    @Override
    public String getDescription() {
        return this.restOfCommand.strip();
    }

    @Override
    public String getDeadline() {
        return "no deadline";
    }

    @Override
    public Task createTask() {
        return null;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task newTask = tasks.newTask(TaskList.TaskType.TODO, getDescription(), getDeadline());
            tasks.add(newTask);
        } catch (NoSuchElementException e) {
            // user input after task type is blank
            ui.showError("Oops! You did not enter a description!");
        }
    }
}
