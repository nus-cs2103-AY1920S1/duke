package duke.util;

import java.io.IOException;

public class Command {
    private static final int BYE = 0;
    private static final int LIST = 1;
    private static final int DONE = 2;
    private static final int DELETE = 3;
    private static final int FIND = 4;
    private static final int TASK = 5;

    private String[] inputParts;
    private int command;

    public Command(String[] inputParts, int command) {
        this.command = command;
        this.inputParts = inputParts;
    }

    public boolean execute(Storage storage, Ui ui, TaskList tasks) throws IOException, DukeException {
        if (command == 0) {
            storage.saveHistory(tasks.getTaskList());
            ui.byeMessage();
            return true;
        } else if (command == 1){
            ui.drawLine();
            tasks.displayList();
            ui.drawLineNewLine();
        } else if (command == 2){
            tasks.markItemComplete(Integer.parseInt(inputParts[1]), ui);
        } else if (command == 3){
            tasks.deleteItem(Integer.parseInt(inputParts[1]), ui);
        } else if (command == 4){
            tasks.findItem(inputParts[1], ui);
        } else if (command == 5){
            tasks.registerNewTask(inputParts, ui);
        }
        return false;
    }
}
