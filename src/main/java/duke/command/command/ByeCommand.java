package duke.command.command;

import duke.command.Command;
import duke.task.TasksController;

/***
 * <p>
 * Command to exit the application.
 * </p>
 */
public class ByeCommand implements Command {
    private TasksController tasksController;

    /***
     * <p>
     * ByeCommand constructor.
     * </p>
     * @param tasksController controller for duke.task list to which new tasks will be saved to.
     */
    public ByeCommand(TasksController tasksController) {
        this.tasksController = tasksController;
    }

    /***
     * <p>
     * Attempts to write new tasks to storage. If unsuccessful, verifies with user if program should close.
     * </p>
     */
    @Override
    public void execute() {
//        try {
//            DukeStorage.getInstance().writeTaskData(tasksController.getTasks());
//        } catch (ConfigurationException e) {
//            OutputBuilder fileWriteErrorMessage =
//                    new OutputBuilder("☹ OOPS!!! Unable to save duke.task data. Close duke.Duke anyway? (Y/N)");
//            DukeOutput.printMessage(fileWriteErrorMessage);
//
//            boolean forceClosedSelectionMade = false;
//
//            while (!forceClosedSelectionMade) {
//                String forceCloseSelection = DukeInput.readUserInput();
//
//                switch (forceCloseSelection) {
//                case "Y":
//                    sayGoodbye();
//                    forceClosedSelectionMade = true;
//                case "N":
//                    forceClosedSelectionMade = true;
//                default:
//                    OutputBuilder invalidSelectionMessage = new OutputBuilder("☹ OOPS!!! Please select Y/N");
//                    DukeOutput.printMessage(invalidSelectionMessage);
//                }
//            }
//        }
//
//        sayGoodbye();
//        DukeInput.close();}
    }
}
