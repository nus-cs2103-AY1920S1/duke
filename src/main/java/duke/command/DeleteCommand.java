package duke.command;

import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {

    /**
     * Creates new deadline task using a TaskList, Ui and Storage, it will then be added into the taskArrayList that
     * was loaded into the TaskList as param.
     * @param tasks the TaskList to be used
     * @param ui the Ui to be used
     * @param storage the Storage to be used
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (ui.getRemainingWords().isEmpty()) {
            throw new DukeException("☹OOPS!!! Wrong format");
        }
        int position = Integer.parseInt(ui.getRemainingWords().trim());
        System.out.println("Noted. I've removed this task.");
        System.out.println(tasks.getTaskArrayList().get(position-1));
        tasks.delete(position-1);
        storage.writeData();
        System.out.println("Now you have " + tasks.getTaskArrayList().size() + " tasks in the list");
    }

    /**
     * Returns true or false regarding whether this method will end the while loop in the duke method run()
     * @return false or true
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
