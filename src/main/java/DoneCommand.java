public class DoneCommand extends UndoableCommand {
    private int doneIdx;

    /**
     * Creates a DoneCommand with a given task index to be marked as done.
     * @param idx Index of task to be marked as done
     */
    DoneCommand(int idx) {
        this.doneIdx = idx;
    }

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        if (doneIdx < 1 || doneIdx > tasks.size()) {
            throw new DukeInvalidTaskException(doneIdx);
        }
        tasks.get(doneIdx).markAsDone();
        storage.writeToFile(tasks);
        return "Nice! I've marked this task as done:\n  " + tasks.get(doneIdx);
    }

    @Override
    String undo(TaskList tasks, Storage storage) throws DukeIoException {
        tasks.get(doneIdx).markAsUndone();
        storage.writeToFile(tasks);
        return "Uh oh! I've marked this task as undone:\n  " + tasks.get(doneIdx);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
