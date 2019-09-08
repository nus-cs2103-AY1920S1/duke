package duke.command;

import error.UnknownCommandException;
import duke.task.TasksController;
import util.OutputBuilder;
import util.DukeOutput;

import java.util.Optional;

/***
 * <p>
 * Command to listen and parse user input.
 * </p>
 */
public class CommandFactory {
    private TasksController tasksController;

    /***
     * <p>
     * ListenCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list on which commands are executed.
     */
    public CommandFactory(TasksController tasksController) {
        this.tasksController = tasksController;
    }

    /***
     * <p>
     * Reads and parse user input.
     * </p>
     * @return corresponding commands.
     */
    public Optional<Command> parse(String userInput) {
        String command = getCommand(userInput);
        String arguments = getArguments(userInput);

        switch (command) {
        case "bye":
            return Optional.of(new ByeCommand(tasksController));
        case "list":
            return Optional.of(new ListCommand(tasksController));
        case "done":
            return Optional.of(new DoneCommand(tasksController, arguments));
        case "delete":
            return Optional.of(new DeleteCommand(tasksController, arguments));
        case "find":
            return Optional.of(new FindCommand(tasksController, arguments));
        default:
            try {
                return Optional.of(new AddCommand(command, arguments, tasksController));
            } catch (UnknownCommandException e) {
                DukeOutput.printMessage(new OutputBuilder(e.getMessage()));
                return Optional.empty();
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
