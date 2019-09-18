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
        } else if(list.size() < 1) {
            throw new DukeException("There is nothing in the list, please add some tasks.");
        } else if(list.size() < Integer.parseInt(cmdDetails)) {
            throw new DukeException("There is no task " + cmdDetails + ", please choose another task");
        } else {
            list.deleteTask(Integer.parseInt(cmdDetails) - 1);
            store.updateFile(list);
            return "Task deleted!";
        }
    }
}
