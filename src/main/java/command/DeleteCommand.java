package command;

import utilities.Storage;
import utilities.TaskList;
import utilities.Ui;

public class DeleteCommand extends Command {

    public DeleteCommand(String command) {
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            assert val <= (tasks.size()) : "Enter a smaller number";
            ui.deleteMessage(val - 1, tasks);
            tasks.remove(val - 1);
            storage.updateFile(tasks);
        } catch (AssertionError f) {
            System.out.println(f.getMessage());
        } catch (Exception e) {
            System.out.println("file not found");
        }
    }

    /**
     * Executes deletion.
     *
     * @param tasks is TaskList of tasks
     *
     * @param ui returns strings
     *
     * @param storage is for output
     *
     * @return the final string command that has to be printed
     */
    public String executeAsString(TaskList tasks, Ui ui, Storage storage) {
        String[]splitWords = command.split(" ");

        try {
            int val = Integer.parseInt(splitWords[1]);
            assert val <= (tasks.size()) : "Enter a smaller number";
            String result = ui.deleteMessageFX(val - 1, tasks);
            tasks.remove(val - 1);
            storage.updateFile(tasks);
            return result;
        } catch (AssertionError f) {
            return f.getMessage();
        } catch (Exception e) {
            return "File not found";
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
