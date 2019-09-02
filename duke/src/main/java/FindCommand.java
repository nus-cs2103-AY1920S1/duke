import java.util.ArrayList;

class FindCommand extends Command {
    public FindCommand(String command, String remainingCommand) {
        super(command, remainingCommand);
    }

    public void execute(TaskList tasks, Ui ui) {
        tasks.listMatch(remainingCommand, ui);
    }
}