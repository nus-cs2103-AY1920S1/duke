import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Map<String, Class<? extends Command>> TOKEN_TO_COMMAND = Map.of(
            "done", DoneCommand.class,
            "delete", DeleteCommand.class,
            "todo", AddTodoTaskCommand.class,
            "deadline", AddDeadlineTaskCommand.class,
            "event", AddEventTaskCommand.class
    );
    private static final Pattern COMMAND_PARSER = Pattern.compile("\\A(?<command>\\S+)(?: (?<args>.+))?\\z");

    public static Command parse(String command) {
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        }

        Matcher m = COMMAND_PARSER.matcher(command);
        if (m.find()) {
            final String commandWord = m.group("command");
            Class<? extends Command> commandClass = TOKEN_TO_COMMAND.get(commandWord);
            if (commandClass != null) {
                try {
                    return commandClass.getConstructor(String.class).newInstance(m.group("args"));
                } catch (InvocationTargetException exc) {
                    if (exc.getCause() instanceof DukeException) {
                        throw (DukeException) exc.getCause();
                    } else {
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(", exc);
                    }
                } catch (ReflectiveOperationException exc) {
                    throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(", exc);
                }
            }
        }

        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
    }
}
