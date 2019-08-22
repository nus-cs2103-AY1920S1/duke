package command;

import task.Task;
import task.TaskListController;
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

        StringBuilder message = new StringBuilder("added: ")
                .append(task.getDescription())
                .append("\n");

        DukeOutput.printMessage(message.toString());

        return Optional.of(new ListenCommand(taskListController));
    }
}
