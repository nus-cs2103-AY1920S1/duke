public class CommandDone extends Command {

    public CommandDone(String cmd) {
        super(cmd);
    }

    @Override
    public void execute(Sheet sh, Ui ui, Storage stor) throws DukeException {
        try {
            int index = Integer.parseInt(command);
            if (index > sh.getNumOfTask()) {
                if (sh.isEmpty()) {
                    throw new TaskNotFoundException("☹ OOPS!!! The list is empty.");
                } else {
                    throw new TaskNotFoundException("☹ OOPS!!! The list contains only "
                            + sh.getNumOfTask()
                            + (sh.getNumOfTask() == 1 ? " task." : " tasks."));
                }
            }
            if (index < 1) {
                throw new TaskNotFoundException("☹ OOPS!!! Do we have non-positive tasks?");
            }
            sh.markAsDone(index);
            stor.save(sh.getList());
        } catch (NumberFormatException e) {
            throw new IllegalTaskIndexException(
                    "☹ OOPS!!! I cannot recognise that task index. :-(");
        }
    }
}
