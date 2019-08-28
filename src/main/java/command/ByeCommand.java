package command;

import error.ConfigurationException;
import error.UnknownCommandException;
import task.TaskListController;
import util.DukeInput;
import util.DukeMessage;
import util.DukeOutput;
import util.DukeStorage;

import java.util.Optional;

/***
 * <p>
 * Command to exit the application.
 * </p>
 */
public class ByeCommand implements Command {
    private TaskListController taskListController;

    /***
     * <p>
     * ByeCommand constructor.
     * </p>
     * @param taskListController controller for task list to which new tasks will be saved to.
     */
    ByeCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

    /***
     * <p>
     * Attempts to write new tasks to storage. If unsuccessful, verifies with user if program should close.
     * </p>
     */
    @Override
    public Optional<Command> execute() {
        try {
            DukeStorage.getInstance().writeTaskData(taskListController.getTasks());
        } catch (ConfigurationException e) {
            DukeMessage fileWriteErrorMessage =
                    new DukeMessage("☹ OOPS!!! Unable to save task data. Close Duke anyway? (Y/N)");
            DukeOutput.printMessage(fileWriteErrorMessage);

            boolean forceClosedSelectionMade = false;

            while (!forceClosedSelectionMade) {
                String forceCloseSelection = DukeInput.readUserInput();

                switch (forceCloseSelection) {
                case "Y":
                    sayGoodbye();
                    forceClosedSelectionMade = true;
                    return Optional.empty();
                case "N":
                    forceClosedSelectionMade = true;
                    return Optional.of(new ListenCommand(taskListController));
                default:
                    DukeMessage invalidSelectionMessage = new DukeMessage("☹ OOPS!!! Please select Y/N");
                    DukeOutput.printMessage(invalidSelectionMessage);
                }
            }
        }

        sayGoodbye();
        DukeInput.close();
        return Optional.empty();
    }

    private void sayGoodbye() {
        DukeMessage goodbyeMessage = new DukeMessage("Bye. Hope to see you again soon!");
        DukeOutput.printMessage(goodbyeMessage);
    }
}
