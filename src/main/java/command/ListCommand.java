package command;

import task.TaskListController;
import util.DukeOutput;

import java.util.Optional;

public class ListCommand implements Command {
    private TaskListController taskListController;

    public ListCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.displayAllTasks();

        return Optional.of(new ListenCommand(taskListController));
    }
}
