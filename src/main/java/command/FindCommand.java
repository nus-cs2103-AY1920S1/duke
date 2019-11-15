package command;

import task.Task;
import util.Storage;
import util.TodoList;

import java.util.List;
import java.util.stream.Collectors;

public class FindCommand extends command.Command {
    private String description;
    private final static String HEADING = "Here are the matching tasks in your list: \n";

    public FindCommand(String description) {
        this.description = description;
    }

    @Override
    public String run(TodoList tasks, Storage storage) {
        List<Task> matches = tasks.find(this.description);
        return HEADING + matches.stream().map(task -> "\t" + task.toString())
                                .collect(Collectors.joining("\n"));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
