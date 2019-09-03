import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class DeleteCommand extends Command {
    private int listPointer;

    public DeleteCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            listPointer = Integer.parseInt(commandDetails);
            Task deletedTask = tasks.getList().get(listPointer - 1);
            tasks.deleteTask(listPointer - 1);
            ui.printResponse("Noted. I've removed this task: \n"
                    + INDENT + "   " + deletedTask + "\n" + INDENT +
                    "Now you have " + tasks.getList().size() + " tasks in the list.");
            storage.updateTodoFile(tasks.getListString());
        } catch (Exception err) {
            throw new DukeException(err.getMessage());
        }
    }
}
