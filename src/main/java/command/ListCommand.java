package command;

import task.TaskListController;

import java.util.Optional;

public class ListCommand implements Command {
    private TaskListController taskListController;

    public ListCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.displayTasks();

        return Optional.of(new ListenCommand(taskListController));
    }
}
