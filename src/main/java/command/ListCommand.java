package command;

import task.Ui;

public class ListCommand extends Command {
    @Override
    public String execute() {
        return Ui.printList();
    }
}