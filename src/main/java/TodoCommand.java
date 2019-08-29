public class TodoCommand extends Command {
    private String description;

    TodoCommand(String description) {
        this.description = description;
    }

    String execute(TaskList tasks, Storage storage) throws DukeIoException {
        Task t = new TodoTask(description);
        tasks.add(t);
        String result = "Got it. I've added this task:\n";
        result += "  " + t + "\n";
        result += "Now you have " + tasks.size() + " tasks in the list.";
        storage.writeToFile(tasks);
        return result;
    }

    boolean isExit() {
        return false;
    }
}
