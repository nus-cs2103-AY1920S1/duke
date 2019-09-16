package duke.command;

import duke.command.find.predicates.FindTaskPredicates;
import duke.model.Task;
import duke.storage.Storage;
import duke.ui.Ui;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FindTaskCommand implements Command {
    private static final FindTaskPredicates findTaskPredicates = new FindTaskPredicates();
    private final String command;

    public FindTaskCommand(String command) {
        this.command = command;
    }

    @Override
    public void execute(List<Task> tasks, Ui ui, Storage storage) {
        Predicate<Task> searchFunction = findTaskPredicates.createPredicate(command);

        List<Task> taskResults;
        if (searchFunction == null) {
            taskResults = tasks;
        } else {
            taskResults = tasks.stream().filter(searchFunction).collect(Collectors.toUnmodifiableList());
        }

        ui.displayTasks("Here are the matching tasks in your list:", taskResults);
    }
}
