import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class DoneCommand extends Command {
    private int listPointer;

    public DoneCommand(String command, String commandDetails, String INDENT) {
        super(command, commandDetails, INDENT);
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws FileNotFoundException, UnsupportedEncodingException {
        listPointer = Integer.parseInt(commandDetails);
        tasks.markTaskAsDone(listPointer - 1);
        ui.printResponse("Nice! I've marked this task as done: \n"
                + INDENT + "   " + tasks.getList().get(listPointer - 1));
        storage.updateTodoFile(tasks.getListString());
    }
}
