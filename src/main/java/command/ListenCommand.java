package command;

import task.TaskListController;
import util.DukeInput;
import util.DukeOutput;

import java.util.Optional;

public class ListenCommand implements Command {
    private TaskListController taskListController;

    public ListenCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        String userInput = DukeInput.readUserInput();

        switch (userInput) {
            case "bye":
                return Optional.of(new ByeCommand());
            case "list":
                return Optional.of(new ListCommand(taskListController));
            default:
                return Optional.of(new AddCommand(userInput, taskListController));
        }
    }
}
