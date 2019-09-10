package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.util.InputMismatchException;

public class DeleteCommand extends InputCommand {
    public DeleteCommand(String index) {
        super(index);
    }


    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        int del;

        try {
            String index = getString();
            if (index.split(" ").length > 1) {
                System.out.println(index + index.split("").length);
                throw new InputMismatchException();
            }
            del = Integer.parseInt(index);
            if (taskList.getNumTasks() < del) {
                throw new IndexOutOfBoundsException();
            }
        } catch (InputMismatchException e) {
            throw new DukeException("☹ OOPS!!! Please enter a single number.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The index of the task to delete is not within the task list size.");
        } catch (NumberFormatException e) {
            throw new DukeException("☹ OOPS!!! Please enter a valid number.");
        }
        
        assert del - 1 <= taskList.getNumTasks();
        return ui.getDeletedTask(taskList.deleteTask(del - 1), taskList.getNumTasks());
    }
}
