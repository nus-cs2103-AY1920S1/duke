public class FindCommand extends Command {
    public FindCommand(String command, String commandDetails, String indent) {
        super(command, commandDetails, indent);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the matching tasks in your list:\n"
                + tasks.findMatchingTasksString(commandDetails));
    }
}
