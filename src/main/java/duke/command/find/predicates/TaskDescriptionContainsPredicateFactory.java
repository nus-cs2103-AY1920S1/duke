package duke.command.find.predicates;

import duke.model.Task;

import java.util.function.Function;
import java.util.function.Predicate;

class TaskDescriptionContainsPredicateFactory implements FindTaskPredicateFactory {
    private Function<String, String> cleanCommand;

    public TaskDescriptionContainsPredicateFactory(Function<String, String> cleanCommand) {
        this.cleanCommand = cleanCommand;
    }

    private String getSearchTerm(String command) {
        return cleanCommand.apply(command).trim();
    }

    @Override
    public boolean shouldEnable(String command) {
        return !getSearchTerm(command).isEmpty();
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final String searchTerm = getSearchTerm(command).toLowerCase();
        return (task) -> {
            return task.getDescription().toLowerCase().contains(searchTerm);
        };
    }
}
