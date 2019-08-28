package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.InputMismatchException;

public class DoneCommand extends InputCommand {
    public DoneCommand(String index) {
        super(index);
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            String index = getString();
            if (index.split(" ").length > 1) {
                throw new InputMismatchException();
            }
            int done = Integer.parseInt(index);
            if (taskList.getNumTasks() > done) {
                throw new IndexOutOfBoundsException();
            }

            ui.showDoneTask(taskList.doneTask(done - 1));
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index is not within the task list size.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid number.");
        } catch (InputMismatchException e) {
            throw new DukeException("☹ OOPS!!! Please enter a single number.");
        }
    }
}
