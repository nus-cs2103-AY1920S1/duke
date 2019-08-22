package command;

import task.TaskListController;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;

public class DoneCommand implements Command {
    private TaskListController taskListController;
    private int completedTaskIndex;

    public DoneCommand(TaskListController taskListController, String argument) {
        this.taskListController = taskListController;
        completedTaskIndex = Integer.valueOf(argument) - 1;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.setTaskToDone(completedTaskIndex);

        return Optional.of(new ListenCommand(taskListController));
    }
}
