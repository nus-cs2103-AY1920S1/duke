package duke.command.find.predicates;

import duke.model.Task;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TaskDoneStatePredicateFactory implements FindTaskCleanableCommandPredicateFactory {
    private static Pattern COMMAND_MATCHER = Pattern.compile(" ?/isdone (?<argument>yes|no) ?");

    @Override
    public boolean shouldEnable(String command) {
        return COMMAND_MATCHER.matcher(command).find();
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final Matcher m = COMMAND_MATCHER.matcher(command);
        m.find();

        final boolean desiredDoneState = m.group("argument").equals("yes");

        return (task) -> {
            return task.isDone() == desiredDoneState;
        };
    }

    @Override
    public String cleanCommand(String command) {
        return COMMAND_MATCHER.matcher(command).replaceAll("");
    }
}
