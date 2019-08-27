package duke.command;

public class IncorrectCommand extends Command {

    @Override
    public void execute() {
        ui.showError("I'm sorry, but I don't know what that means :-(");
    }
}
