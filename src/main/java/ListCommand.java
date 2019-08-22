import java.util.ArrayList;
import java.util.List;

class ListCommand implements Command {
    private final List<Task> tasks;

    ListCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public List<String> run(String[] words) {
        List<String> messages = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            messages.add(i + 1 + "." + tasks.get(i));
        }
        return messages;
    }
}
