package duke.command;

public class ByeCommand extends Command {

    public void execute() {
        this.isExit = true;
        this.ui.greetGoodbye();
    }
}
