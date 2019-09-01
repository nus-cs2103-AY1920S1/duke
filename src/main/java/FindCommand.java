import java.io.IOException;

public class FindCommand extends Command {

    FindCommand(String commandDesc) {
        super(commandDesc);
    }

    boolean isExit() {
        return false;
    }

    @Override
    String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException {
        try {
            /*Check if description is empty (does not check when user input
              multiple spaces as the description.
            */
            if (!commandDesc.substring(5).equals((""))) {
                String output = tasks.findTasks(commandDesc.substring(5));
                return output;
            } else {
                throw new EmptyDescDukeException("find");
            }
        } catch (IndexOutOfBoundsException err) {
            throw new EmptyDescDukeException("find");
        }
    }
}
