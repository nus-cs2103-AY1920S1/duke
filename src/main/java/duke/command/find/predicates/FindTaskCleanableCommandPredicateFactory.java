package duke.command.find.predicates;

public interface FindTaskCleanableCommandPredicateFactory extends FindTaskPredicateFactory {
    /**
     * Returns a new String without the keywords that are used by this particular search Predicate.
     *
     * @param command The command that may or may not contain keywords that invoke this particular search Predicate.
     * @return A new String without the keywords that are used by this particular search Predicate.
     */
    String cleanCommand(String command);
}
