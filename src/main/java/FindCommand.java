import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FindCommand extends Command {
    private String description;
    private final String heading = "Here are the matching tasks in your list: \n";

    FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String run(TodoList tasks, Storage storage) {
        List<Task> matches = tasks.find(this.description);
        return heading + matches.stream().map(task -> "\t" + task.toString())
                                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
