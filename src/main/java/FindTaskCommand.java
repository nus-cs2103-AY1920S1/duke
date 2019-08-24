import java.util.List;
import java.util.stream.Collectors;

public class FindTaskCommand implements Command {
    private final String searchTerm;

    public FindTaskCommand(String searchTerm) {
        this.searchTerm = searchTerm.toLowerCase();
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        List<Task> taskResults = tasks.stream().filter(task -> {
            return task.getDescription().toLowerCase().contains(searchTerm);
        }).collect(Collectors.toUnmodifiableList());

        ui.displayTasks("Here are the matching tasks in your list:", taskResults);
    }
}
