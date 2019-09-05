public class TodoCommand extends UndoableCommand {
    private String description;

    TodoCommand(String description) {
        this.description = description;
    }

    @Override
    String execute(TaskList tasks, Storage storage) throws DukeIoException {
        Task t = new TodoTask(description);
        tasks.add(t);
        String result = "Got it. I've added this task:\n";
        result += "  " + t + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    @Override
    String undo(TaskList tasks, Storage storage) throws DukeInvalidTaskException, DukeIoException {
        return (new DeleteCommand(tasks.size())).execute(tasks, storage);
    }

    @Override
    boolean isExit() {
        return false;
    }
}
