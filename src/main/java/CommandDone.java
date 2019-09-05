/**
 * Encapsulates a command from user input String "done".
 */
public class CommandDone extends Command {

    public CommandDone(String cmd) {
        super(cmd);
        super.type = "Done: ";
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        try {
            int index = Integer.parseInt(command);
            if (index > sh.getNumOfTask()) {
                if (sh.isEmpty()) {
                    throw new TaskNotFoundException("> < Oops! The list is empty.");
                } else {
                    throw new TaskNotFoundException("> < Oops! The list contains only "
                            + sh.getNumOfTask()
                            + (sh.getNumOfTask() == 1 ? " task." : " tasks."));
                }
            }
            if (index < 1) {
                throw new TaskNotFoundException("> < Oops! Do we have non-positive tasks?");
            }
            sh.markAsDone(index);
            stor.save(sh.getList());
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException(
                    "> < Oops! I cannot recognise that task index.");
        }
    }

    @Override
    public String toString() {
        return "Done: " + command;
    }
}
