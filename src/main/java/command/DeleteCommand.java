package command;

import task.TaskListController;

import java.util.Optional;

public class DeleteCommand implements Command {
    private TaskListController taskListController;
    private int deletedTaskIndex;

    public DeleteCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        deletedTaskIndex = Integer.valueOf(argument) - 1;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.deleteTask(deletedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }

}
