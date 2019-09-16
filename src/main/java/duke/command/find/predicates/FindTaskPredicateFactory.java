package duke.command.find.predicates;

import duke.model.Task;

import java.util.function.Predicate;

public interface FindTaskPredicateFactory {
    /**
     * Tests if the command argument requires this particular predicate to be activated.
     *
     * @param command The command that may or may not contain keywords that invoke this particular search Predicate.
     * @return Whether the command contains the keywords to use this particular search Predicate.
     */
    boolean shouldEnable(String command);

    /**
     * Returns a Predicate that filters Tasks for whether they meet this search Predicate's criteria.
     *
     * @param command The command that contains keywords used for this particular search Predicate.
     * @return A Predicate to filter Tasks by this search Predicate's criteria.
     */
    Predicate<Task> createPredicate(String command);
}

