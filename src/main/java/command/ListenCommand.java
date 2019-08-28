package command;

import error.UnknownCommandException;
import task.TaskListController;
import util.DukeInput;
import util.DukeMessage;
import util.DukeOutput;

import java.util.Optional;

/***
 * Command to listen and parse user input.
 */
public class ListenCommand implements Command {
    private TaskListController taskListController;

    /***
     * ListenCommand constructor
     * @param taskListController controller for task list on which commands are executed.
     */
    public ListenCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    /***
     * Reads and parse user input.
     * @return corresponding commands.
     */
    @Override
    public Optional<Command> execute() {
        String userInput = DukeInput.readUserInput();
        String command = getCommand(userInput);
        String arguments = getArguments(userInput);

        switch (command) {
            case "bye":
                return Optional.of(new ByeCommand(taskListController));
            case "list":
                return Optional.of(new ListCommand(taskListController));
            case "done":
                return Optional.of(new DoneCommand(taskListController, arguments));
            case "delete":
                return Optional.of(new DeleteCommand(taskListController, arguments));
            default:
                try {
                    return Optional.of(new AddCommand(command, arguments, taskListController));
                } catch (UnknownCommandException e) {
                    DukeOutput.printMessage(new DukeMessage(e.getMessage()));
                    return Optional.of(new ListenCommand(taskListController));
                }
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
