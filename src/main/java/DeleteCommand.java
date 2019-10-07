

public class DeleteCommand extends UndoableCommand {
    private int deleteIdx;
    private Task deletedTask;

    /**
     * Creates a DeleteCommand with a given task index to delete.
     * @param idx Index of task to be deleted
     */
    DeleteCommand(int idx) {
        this.deleteIdx = idx;
    }

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        if (deleteIdx < 1 || deleteIdx > tasks.size()) {
            throw new DukeInvalidTaskException(deleteIdx);
        }
        deletedTask = tasks.remove(deleteIdx);
        String result = "Noted. I've removed this task:\n";
        result += "  " + deletedTask + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    @Override
    String undo(TaskList tasks, Storage storage) throws DukeIoException {
        tasks.add(deletedTask, deleteIdx);
        String result = "Noted. I've added back this task:\n";
        result += "  " + deletedTask + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    @Override
    boolean isExit() {
        return false;
    }
}
