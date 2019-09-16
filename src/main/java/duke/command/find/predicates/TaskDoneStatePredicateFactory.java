package duke.command.find.predicates;

import duke.command.UnknownCommandException;
import duke.model.Task;

import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TaskDoneStatePredicateFactory implements FindTaskCleanableCommandPredicateFactory {
    private static Pattern COMMAND_MATCHER = Pattern.compile(" ?/isdone (?<argument>\\w+) ?");

    @Override
    public boolean shouldEnable(String command) {
        return COMMAND_MATCHER.matcher(command).find();
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final Matcher m = COMMAND_MATCHER.matcher(command);
        m.find();

        final String commandArgument = m.group("argument");
        final boolean isValidArgument = commandArgument.equals("yes") || commandArgument.equals("no");
        if (!isValidArgument) {
            throw new UnknownCommandException(String.format(
                    "I don't understand \"/isdone %s\".\nTry using \"/isdone yes\" or \"/isdone no\" instead.",
                    commandArgument
            ));
        }

        final boolean desiredDoneState = commandArgument.equals("yes");

        return (task) -> {
            return task.isDone() == desiredDoneState;
        };
    }

    @Override
    public String cleanCommand(String command) {
        return COMMAND_MATCHER.matcher(command).replaceAll("");
    }
}
