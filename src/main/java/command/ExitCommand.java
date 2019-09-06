package command;

import task.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public String execute() {
        return Ui.endOfInteractions();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}