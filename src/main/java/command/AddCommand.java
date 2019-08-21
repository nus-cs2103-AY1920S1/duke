package command;

import task.TaskListController;
import util.DukeOutput;

import java.util.Optional;

public class AddCommand implements Command {
    private String task;
    private TaskListController taskListController;

    public AddCommand(String task, TaskListController taskListController) {
        this.task = task;
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.addTask(task);

        String message = "added: " + task;
        DukeOutput.printMessage(message);

        return Optional.of(new ListenCommand(taskListController));
    }
}
