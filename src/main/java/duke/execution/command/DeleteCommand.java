package duke.execution.command;

import java.io.IOException;

import duke.execution.CompleteList;
import duke.execution.Storage;
import duke.execution.Ui;

import duke.models.Planner;

public class DeleteCommand extends Command {

    /**
     * Constructor for Delete command.
     *
     * @param action Delete command word.
     * @param variable Number of task to be deleted.
     */
    public DeleteCommand(String action, String variable) {
        super(action, variable);
    }

    /**
     * Executes the delete command and prints out statements to
     * tell the user that the delete tasks has been deleted to
     * the list of tasks.
     *
     * @param errands Not needed in this case.
     * @param ui Prints out statements to indicate to user what
     *           has happened.
     * @param storage Stores the tasks inside another file so that
     *                the task will be available the next time the
     *                bot starts up.
     * @return Returns String to print out to the user.
     * @throws IOException If the named file exists but is a directory rather
     *                     than a regular file, does not exist but cannot be
     *                     created, or cannot be opened for any other reason.
     */
    @Override
    public String execute(CompleteList errands, Ui ui, Storage storage) throws IOException {
        assert errands != null;
        assert ui != null;
        assert storage != null;
        if (variable.equals("all")) {
            storage.writeToFile(Storage.file, "");
            CompleteList.listOfPlans.clear();
            Ui.printIndent();
            return "Everything in your list has been removed!\n"
                    + "Add more tasks to get started again!!!";
        } else {
            int taskNumber = Integer.parseInt(variable);
            CompleteList.listOfPlans.remove(taskNumber - 1);
            storage.writeToFile(Storage.file, "");
            for (Planner errand : CompleteList.listOfPlans) {
                storage.addToFile(Storage.file, errand.toString());
            }
            String deleteOutput = ui.printRemove() + "\n" + Ui.printDelete(taskNumber) + "\n";
            deleteOutput += Ui.printNumOfTasks();
            return deleteOutput;
        }
    }
}
