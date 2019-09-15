package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public class DeleteCommand extends Command{

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            ui.deleteMessage(val-1, tasks);
            tasks.remove(val - 1);
            storage.updateFile(tasks);
        } catch (Exception e) {
            System.out.println("Error, you have entered an invalid number");
        }
    }

    public String executeAsString(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            String result = ui.deleteMessageFX(val-1, tasks);
            tasks.remove(val - 1);
            storage.updateFile(tasks);
            return result;
        } catch (Exception e) {
            return "Error, you have entered an invalid number";
            //return e.getMessage() + "\n" + tasks.size();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
