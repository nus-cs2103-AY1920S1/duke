package commands;

import parser.Parser;
import storage.Storage;
import ui.Ui;
import tasks.TaskList;

public class AddCommand extends Command {

    private String input;
    private String action;
    private String description;

    public AddCommand(String input, String action, String description) {
        this.input = input;
        this.action = action;
        this.description = description;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            return (ui.addTask() + "\n" + (tasks.addTask(this.input, this.action, this.description)));
        } catch (Exception err) {
            return (err.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
