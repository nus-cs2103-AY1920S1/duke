
public class SnoozeCommand extends Command {
    String cmdDetails;

    public SnoozeCommand(String firstPart, String everythingElse) {
        super(firstPart, everythingElse);
        this.cmdDetails = everythingElse;
    }

    /**
     * Executes the snooze command.
     * @param list is the task list where all tasks are stored
     * @param ui the ui handling the input and output of duke
     * @param store the storage object that helps load information from duke into the file and vice versa.
     * @return tells user that the task has been snoozed.
     * @throws DukeException
     */
    public String execute(TaskList list, Ui ui, SaveToFile store) throws DukeException {
        try {
            int indexOfTask = Integer.parseInt(cmdDetails) - 1;
            Tasks t = list.getTask(indexOfTask);
            String type = t.getType();
            switch (type) {

            case "D":
                Deadline d = (Deadline) t;
                d.snooze();
                return "Task snoozed, here are the updated details: \n" + d;

            case "E":
                Event e = (Event) t;
                e.snooze();
                return "Task snoozed, here are the updated details: \n" + e;

            case "T":
                throw new DukeException("Todo can't be snoozed.");
            }
            return "Task snoozed, new task details: \n" + t;
        } catch(NumberFormatException e) {
            throw new DukeException("Enter a task number!");
        }
    }

}
