package command;

import task.TaskListController;

import java.util.Optional;

public class FindCommand implements Command {
    private TaskListController taskListController;
    private String searchParameter;

    FindCommand(TaskListController taskListController, String arguments) {
        this.taskListController = taskListController;
        searchParameter = arguments;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.findTasks(searchParameter);
        return Optional.of(new ListenCommand(taskListController));
    }
}
