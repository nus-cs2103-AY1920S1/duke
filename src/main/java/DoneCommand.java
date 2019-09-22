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
        } else {
            try {
                int taskIndex =  Integer.parseInt(cmdDetails);
                list.getTask(taskIndex - 1).doTask();
                store.updateFile(list);
                return "Task done";
            } catch(IndexOutOfBoundsException e) {
                throw new DukeException("Task " + cmdDetails + " is not available, please choose another task.");
            }

        }
    }
}
