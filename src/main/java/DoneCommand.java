public class DoneCommand extends Command {
    String cmdDetails;

    public DoneCommand(String firstPart, String everythingElse) {
        super(firstPart, everythingElse);
        cmdDetails = everythingElse;
    }

    @Override
    public String execute(TaskList list, Ui ui, SaveToFile store) throws DukeException {
        if (cmdDetails.length() < 1) {
            throw new DukeException("Please specify the task that is completed.");
        } else if (list.size() < 1) {
            throw new DukeException("There is nothing in the list, please add some tasks.");
        } else if (list.size() <  Integer.parseInt(cmdDetails)) {
            throw new DukeException("There is no task " + cmdDetails + ", please choose another task.");
        } else {
            int taskIndex =  Integer.parseInt(cmdDetails);
            list.getTask(taskIndex - 1).doTask();
            store.updateFile(list);
            return "Task done";
        }
    }
}
