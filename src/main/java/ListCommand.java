import java.util.ArrayList;
import java.util.List;

class ListCommand implements Command {
    private final List<Task> tasks;

    ListCommand(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a message with the list of tasks.
     *
     * @param words Array of words from the input line.
     * @return Message to show the user.
     */
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
