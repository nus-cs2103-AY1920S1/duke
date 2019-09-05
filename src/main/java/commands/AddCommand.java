package commands;

import duke.Parser;
import duke.Storage;
import duke.Ui;
import tasks.TaskList;

public class AddCommand extends Command {

    private String input;
    private String action;
    private String description;
    private Parser parser;

    public AddCommand(String input, String action, String description) {
        this.input = input;
        this.action = action;
        this.description = description;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.addTask();
            tasks.addTask(this.input, this.action, this.description);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
