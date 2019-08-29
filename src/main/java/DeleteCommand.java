public class DeleteCommand extends Command {
    private int deleteIdx;

    /**
     * Creates a DeleteCommand with a given task index to delete.
     * @param idx Index of task to be deleted
     */
    DeleteCommand(int idx) {
        this.deleteIdx = idx;
    }

    String execute(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        if (deleteIdx < 1 || deleteIdx > tasks.size()) {
            throw new DukeInvalidTaskException(deleteIdx);
        }
        String result = "Noted. I've removed this task:\n";
        result += "  " + tasks.remove(deleteIdx) + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    boolean isExit() {
        return false;
    }
}
