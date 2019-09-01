import java.io.IOException;
import java.util.List;

class DeleteCommand implements Command {
    private final List<Task> tasks;
    private Storage storage;

    DeleteCommand(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Removes a task, and returns a message about the removed task and number of remaining tasks.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     * @throws IOException If the tasks cannot be saved.
     */
    @Override
    public List<String> run(String[] words) throws IOException {
        Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
        storage.store(tasks);
        return List.of("Noted. I've removed this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
