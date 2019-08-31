package command;

import main.Storage;
import main.TaskList;
import main.Ui;
import task.Task;

import java.io.IOException;

public class DeleteCommand extends Command {

    String[] temp;

    public DeleteCommand(String[] temp) {
        this.temp = temp;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws NullPointerException, IOException {
        try {
            Task toRemove = task.getList().remove(Integer.parseInt(temp[1]) - 1);
            ui.printRemove(task.getList(), toRemove);
            storage.arrayToFile(task.getList());
        } catch (NullPointerException e) {
            ui.printError("Please input a valid task number to delete.");
        } catch (IndexOutOfBoundsException e) {
            ui.printError("Please input a valid task number to delete.");
        }
    }
}
