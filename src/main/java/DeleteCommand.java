public class DeleteCommand extends Command {

    String cmdDetails;

    public DeleteCommand(String firstPart, String everythingElse) {
        super(firstPart, everythingElse);
        cmdDetails = everythingElse;
    }

    @Override
    public String execute(TaskList list, Ui ui, SaveToFile store) throws DukeException {
        if (cmdDetails.length() < 1) {
            throw new DukeException("Please specify the task to be deleted.");
        } else {
            list.deleteTask(Integer.parseInt(cmdDetails) - 1);
            store.updateFile(list);
            return "Task deleted!";
        }
    }
}
