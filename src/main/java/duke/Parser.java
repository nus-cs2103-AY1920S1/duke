package duke;

import duke.command.AddDeadlineTaskCommand;
import duke.command.AddEventTaskCommand;
import duke.command.AddTodoTaskCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindTaskCommand;
import duke.command.ListCommand;
import duke.command.UnknownCommandException;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static final Map<String, Class<? extends Command>> TOKEN_TO_COMMAND = Map.of(
            "done", DoneCommand.class,
            "delete", DeleteCommand.class,
            "todo", AddTodoTaskCommand.class,
            "deadline", AddDeadlineTaskCommand.class,
            "event", AddEventTaskCommand.class,
            "find", FindTaskCommand.class
    );
    private static final Pattern COMMAND_PARSER = Pattern.compile("\\A(?<command>\\S+)(?: (?<args>.+))?\\z");

    /**
     * Parses the given command input to extract the intended command and the arguments for that command.
     *
     * @param command The string representing a desired command.
     * @return A Command-implementing object that can be later executed.
     */
    public static Command parse(String command) {
        if (command.equals("bye")) {
            return new ExitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        }

        Matcher m = COMMAND_PARSER.matcher(command);
        if (m.find()) {
            final String commandWord = m.group("command");
            Class<? extends Command> commandClass = TOKEN_TO_COMMAND.get(commandWord);
            if (commandClass != null) {
                try {
                    // create a new instance of the Command-implementing class and pass the command arguments to it
                    return commandClass.getConstructor(String.class).newInstance(m.group("args"));
                } catch (ReflectiveOperationException exc) {
                    /*
                    If an exception is thrown within the Command-implementing class constructor, Java will wrap it in a
                    InvocationTargetException exception because reflection is being used to invoke the constructor.

                    We expect DukeException subclasses to be thrown within Command constructors so we'll check for it
                    and transparently unwrap the ReflectiveOperationException exception to throw it instead.

                    If it's some other ReflectiveOperationException exception, we'll wrap it inside
                    an UnknownCommandException instead.
                     */
                    if (exc.getCause() instanceof DukeException) {
                        throw (DukeException) exc.getCause();
                    } else {
                        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(", exc);
                    }
                }
            }
        }

        throw new UnknownCommandException("I'm sorry, but I don't know what that means :-(");
    }
}
