package duke.command.factory;

import duke.command.*;
import duke.command.command.*;
import duke.task.TasksController;
import error.ui.UiException;
import ui.UiController;

import java.util.Optional;

/***
 * <p>
 * Command to listen and parse user input.
 * </p>
 */
public class CommandFactory {
    private TasksController tasksController;
    private AddCommandFactory addCommandFactory;
    private UiController ui;

    /***
     * <p>
     * ListenCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list on which commands are executed.
     */
    public CommandFactory(TasksController tasksController, UiController ui) {
        this.tasksController = tasksController;
        this.addCommandFactory = new AddCommandFactory(tasksController, ui);
        this.ui = ui;
    }

    /***
     * <p>
     * Reads and parse user input.
     * </p>
     * @return corresponding commands.
     */
    public Optional<? extends Command> parse(String userInput) throws UiException {
        String command = CommandUtils.getCommand(userInput);
        String arguments = CommandUtils.getArguments(userInput);

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
            return addCommandFactory.parse(userInput);
        }
    }




}
