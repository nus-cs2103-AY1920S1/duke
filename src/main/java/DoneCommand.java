import java.util.List;

public class DoneCommand implements Command {
    private final List<Task> tasks;

    DoneCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<String> run(String[] words) {
        Task task = tasks.get(Integer.parseInt(words[1]) - 1);
        task.markAsDone();
        return List.of("Nice! I've marked this task as done:", "  " + task);
    }
}
