package command;

import error.ConfigurationException;
import error.UnknownCommandException;
import task.TaskListController;
import util.DukeInput;
import util.DukeMessage;
import util.DukeOutput;
import util.DukeStorage;

import java.util.Optional;

public class ByeCommand implements Command {
    private TaskListController taskListController;

    ByeCommand(TaskListController taskListController) {
        this.taskListController = taskListController;
    }

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
