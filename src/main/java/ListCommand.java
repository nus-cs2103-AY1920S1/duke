public class ListCommand extends Command {
    public ListCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printResponse("Here are the tasks in your list:\n" + tasks.getListString());
    }
}
