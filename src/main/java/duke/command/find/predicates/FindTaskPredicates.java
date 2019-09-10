package duke.command.find.predicates;

import duke.model.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class FindTaskPredicates {
    private final List<FindTaskPredicateFactory> predicateFactories = new ArrayList<>(List.of(
            new TaskDoneStatePredicateFactory(),
            new TaskDescriptionContainsPredicateFactory(this::cleanCommand),
            new TaskBeforeDatePredicateFactory(),
            new TaskOnDatePredicateFactory(),
            new TaskAfterDatePredicateFactory(),
            new TaskTypePredicateFactory()
    ));

    public FindTaskPredicates() {
    }

    /**
     * Adds a possible search criteria that will be activated based on the rules set by the FindTaskPredicateFactory.
     *
     * @param predicate A FindTaskPredicateFactory that parses an incoming command for the keywords its interested in.
     */
    public void register(FindTaskPredicateFactory predicate) {
        predicateFactories.add(predicate);
    }

    /**
     * Returns a Predicate that filters Tasks for whether they meet all the Predicates enabled by the command argument.
     *
     * @param command The command that contains keywords to invoke search Predicates.
     * @return A Predicate to filter Tasks based on the command argument.
     */
    public Predicate<Task> createPredicate(String command) {
        Predicate<Task> combinedPredicate = null;

        for (FindTaskPredicateFactory predicateFactory : predicateFactories) {
            if (!predicateFactory.shouldEnable(command)) {
                continue;
            }

            Predicate<Task> predicate = predicateFactory.createPredicate(command);
            if (combinedPredicate == null) {
                combinedPredicate = predicate;
            } else {
                combinedPredicate = combinedPredicate.and(predicate);
            }
        }

        return combinedPredicate;
    }

    /**
     * Removes all the supported subcommands and their arguments from the command parameter.
     * Useful for subcommand-less queries like TaskDescriptionContainsPredicateFactory where it was designed to be
     * invoked without a "/contains" subcommand.
     *
     * @param command The command that contains subcommand keywords to remove.
     * @return A command with the supported subcommand keywords removed.
     */
    private String cleanCommand(String command) {
        for (FindTaskPredicateFactory predicateFactory : predicateFactories) {
            if (!(predicateFactory instanceof FindTaskCleanableCommandPredicateFactory)) {
                continue;
            }

            command = ((FindTaskCleanableCommandPredicateFactory) predicateFactory).cleanCommand(command);
        }

        return command;
    }
}
