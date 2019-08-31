package command;

import main.Storage;
import main.TaskList;
import main.Ui;

import java.io.IOException;
import java.lang.reflect.Array;

public class DoneCommand extends Command {
    String[] temp;

    public DoneCommand(String[] temp){
        this.temp = temp;
    }

    public void execute(TaskList task, Ui ui, Storage storage) throws IOException {
        try {
            int index = Integer.parseInt(temp[1]) - 1;
            ui.printDone(task.getList(), index);
            storage.arrayToFile(task.getList());
        } catch (NullPointerException e) {
            ui.printError("Please input a valid task number.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError("Please input a valid task number.");
        }

    }
}
