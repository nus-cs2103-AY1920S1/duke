import java.io.IOException;
import java.util.List;

public class DoneCommand implements Command {
    private final List<Task> tasks;
    private Storage storage;

    DoneCommand(List<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    @Override
    public List<String> run(String[] words) throws IOException {
        Task task = tasks.get(Integer.parseInt(words[1]) - 1);
        task.markAsDone();
        storage.store(tasks);
        return List.of("Nice! I've marked this task as done:", "  " + task);
    }
}
