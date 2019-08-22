package command;

import task.Task;
import task.TaskListController;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;

public class AddCommand implements Command {
    private Task task;
    private TaskListController taskListController;

    public AddCommand(String taskDescription, TaskListController taskListController) {
        this.task = new Task(taskDescription);
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        taskListController.addTask(task);

        DukeMessage message = new DukeMessage("added: ")
                .append(task.getDescription());

        DukeOutput.printMessage(message);

        return Optional.of(new ListenCommand(taskListController));
    }
}
