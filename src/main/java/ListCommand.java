import java.util.ArrayList;
import java.util.List;

class ListCommand implements Command {
    private final TaskList tasks;

    ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<String> run(String[] words) {
        List<String> messages = new ArrayList<>();
        messages.add("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            messages.add(i + 1 + "." + tasks.get(i));
        }
        return messages;
    }
}
