package duke.command.find.predicates;

import duke.command.UnknownCommandException;
import duke.model.Deadline;
import duke.model.Event;
import duke.model.Task;
import duke.model.Todo;

import java.util.Map;
import java.util.StringJoiner;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskTypePredicateFactory implements FindTaskCleanableCommandPredicateFactory {
    private static Pattern COMMAND_MATCHER = Pattern.compile(" ?/type (?<type>\\w+) ?");
    private static final Map<String, Class<? extends Task>> EVENT_TOKEN_TO_TYPE = Map.of(
            "todo", Todo.class,
            "deadline", Deadline.class,
            "event", Event.class
    );

    @Override
    public String cleanCommand(String command) {
        return COMMAND_MATCHER.matcher(command).replaceAll("");
    }

    @Override
    public boolean shouldEnable(String command) {
        return COMMAND_MATCHER.matcher(command).find();
    }

    @Override
    public Predicate<Task> createPredicate(String command) {
        final Matcher m = COMMAND_MATCHER.matcher(command);
        m.find();

        final String desiredTaskTypeToken = m.group("type");
        final Class<? extends Task> desiredTaskType = EVENT_TOKEN_TO_TYPE.get(desiredTaskTypeToken);

        if (desiredTaskType == null) {
            final StringJoiner errorMessage = new StringJoiner("");
            errorMessage.add(String.format(
                    "I'm not sure what kind of task you mean by a \"%s\" task.",
                    desiredTaskTypeToken
            ));
            errorMessage.add("\nThe types I understand are ");

            final StringJoiner supportedTypesString = new StringJoiner(", ");
            for (final String supportedTypeToken : EVENT_TOKEN_TO_TYPE.keySet()) {
                supportedTypesString.add(String.format("\"%s\"", supportedTypeToken));
            }
            errorMessage.add(supportedTypesString.toString());
            errorMessage.add(".");

            throw new UnknownCommandException(errorMessage.toString());
        }

        return desiredTaskType::isInstance;
    }
}
