package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.InputMismatchException;

public class DeleteCommand extends Command {
    private String index;

    public DeleteCommand(String index) {
        this.index = index.trim();
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        try {
            if (index.split(" ").length > 1) {
                throw new InputMismatchException();
            }

            int del = Integer.parseInt(index);
            if (taskList.getNumTasks() > del) {
                throw new IndexOutOfBoundsException();
            }

            ui.showDeletedTask(taskList.deleteTask(del - 1), taskList.getNumTasks());
        } catch (InputMismatchException e) {
            throw new DukeException("☹ OOPS!!! Please enter a single number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of the task to delete is not within the task list size.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid number.");
        }
    }
}
