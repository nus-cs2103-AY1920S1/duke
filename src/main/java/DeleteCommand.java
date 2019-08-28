import java.io.IOException;
import java.util.List;

class DeleteCommand implements Command {
    private final List<Task> tasks;
    private Storage storage;

    DeleteCommand(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public List<String> run(String[] words) throws IOException {
        Task task = tasks.remove(Integer.parseInt(words[1]) - 1);
        storage.store(tasks);
        return List.of("Noted. I've removed this task:", "  " + task,
                "Now you have " + tasks.size() + " tasks in the list.");
    }
}
