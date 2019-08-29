public class DoneCommand extends Command {
    private int doneIdx;

    /**
     * Creates a DoneCommand with a given task index to be marked as done.
     * @param idx Index of task to be marked as done
     */
    DoneCommand(int idx) {
        this.doneIdx = idx;
    }

    String execute(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        if (doneIdx < 1 || doneIdx > tasks.size()) {
            throw new DukeInvalidTaskException(doneIdx);
        }
        tasks.get(doneIdx).markAsDone();
        storage.writeToFile(tasks);
        return "Nice! I've marked this task as done:\n  " + tasks.get(doneIdx);
    }

    boolean isExit() {
        return false;
    }
}
