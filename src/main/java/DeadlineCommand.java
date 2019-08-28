import java.io.IOException;

public class DeadlineCommand extends Command {

    DeadlineCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit(){
        return false;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            //Check if description is empty (does not check when user input
            //multiple spaces as the description.
            String[] commandLine = commandDesc.substring(9).split(" /by ");
            Deadline newDeadline = new Deadline(commandLine[0], commandLine[1]);
            storage.appendToFile(System.getProperty("user.dir") + "/data/tasks.txt", newDeadline.stringForAppend());
            tasks.addDeadline(newDeadline);
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("deadline");
        }
    }
}
