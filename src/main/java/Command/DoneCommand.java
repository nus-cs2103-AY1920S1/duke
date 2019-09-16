package Command;

import Utilities.Storage;
import Utilities.TaskList;
import Utilities.Ui;

public class DoneCommand extends Command {
    public DoneCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            assert val <= (tasks.size()) : "Enter a smaller number";
            tasks.taskDone(val-1);
            ui.doneMessage(val-1, tasks);
            storage.updateFile(tasks);
        } catch (AssertionError f){
            System.out.println(f.getMessage());
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    public String executeAsString(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            tasks.taskDone(val-1);
            storage.updateFile(tasks);
            return ui.doneMessageFX(val-1, tasks);
        } catch (Exception e) {
            return "Error, you have entered an invalid number";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
