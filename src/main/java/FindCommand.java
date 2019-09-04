import java.util.ArrayList;

public class FindCommand extends Command {
    public FindCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the matching tasks in your list:\n"
                + tasks.findMatchingTasksString(commandDetails));
    }
}
