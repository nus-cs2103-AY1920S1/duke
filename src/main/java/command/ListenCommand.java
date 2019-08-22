package command;

import task.TaskListController;
import util.DukeInput;

import java.util.Optional;

public class ListenCommand implements Command {
    private TaskListController taskListController;

    public ListenCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    @Override
    public Optional<Command> execute() {
        String userInput = DukeInput.readUserInput();
        String command = getCommand(userInput);
        String arguments = getArguments(userInput);

        switch (command) {
            case "bye":
                return Optional.of(new ByeCommand());
            case "list":
                return Optional.of(new ListCommand(taskListController));
            case "done":
                return Optional.of(new DoneCommand(taskListController, arguments));
            case "delete":
                return Optional.of(new DeleteCommand(taskListController, arguments));
            default:
                return Optional.of(new AddCommand(command, arguments, taskListController));
        }
    }

    private String getCommand(String userInput) {
        return userInput.split(" ", 2)[0];
    }

    private String getArguments(String userInput) {
        String[] splitInput = userInput.split(" ", 2);
        if (splitInput.length > 1) {
            return splitInput[1];
        } else {
            return "";
        }
    }
}
