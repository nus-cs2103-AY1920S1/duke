package seedu.duke.cli;

import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.cli.commands.ByeCommand;
import seedu.duke.cli.commands.DeadlineCommand;
import seedu.duke.cli.commands.DeleteCommand;
import seedu.duke.cli.commands.DoneCommand;
import seedu.duke.cli.commands.EventCommand;
import seedu.duke.cli.commands.FindCommand;
import seedu.duke.cli.commands.ListCommand;
import seedu.duke.cli.commands.TodoCommand;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Parser {
    private final static DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/uuuu HH:mm");
    private static HashMap<String, ConstructorCache> constructors;

    static {
        constructors = new HashMap<>();
        addConstructors(ListCommand.class);
        addConstructors(ByeCommand.class);
        addConstructors(DoneCommand.class);
        addConstructors(TodoCommand.class);
        addConstructors(EventCommand.class);
        addConstructors(DeadlineCommand.class);
        addConstructors(DeleteCommand.class);
        addConstructors(FindCommand.class);
    }

    // This class is to be used statically only.
    private Parser() {
    }

    @SuppressWarnings("unchecked")
    private static <A extends Command> void addConstructors(Class<A> clazz) {
        for (Constructor<?> c : clazz.getDeclaredConstructors()) {
            CommandConstructor ccAnn = c.getAnnotation(CommandConstructor.class);
            if (ccAnn == null) {
                continue;
            }

            ConstructorCache cc = new ConstructorCache((Constructor<? extends Command>) c);
            constructors.put(ccAnn.value().toLowerCase(), cc);
            boolean seenTrailing = false;
            for (Argument a : cc.getParameterAnnotations()) {
                if (a == null || !a.trailing() || !a.prefix().isBlank()) {
                    continue;
                }

                if (!seenTrailing) {
                    seenTrailing = true;
                } else {
                    throw new RuntimeException("Cannot have multiple trailing arguments without a prefix");
                }
            }
        }
    }

    /**
     * Parse a command line and return an object implementing {@link Command} that represents
     * the command.
     *
     * @param in The command line to parse
     * @return An object representing the parsed command, or {@code null} if no matching command was
     * found
     * @throws CommandException if there were insufficient arguments or some arguments were invalid
     *                          for their type
     */
    public static Command parse(String in) throws CommandException {
        String[] tok = in.split("\\s", 2);
        ConstructorCache ctor = constructors.get(tok[0].toLowerCase());
        if (ctor == null) {
            return null;
        }

        Class<?>[] paramTypes = ctor.getParameterTypes();
        Argument[] paramAnns = ctor.getParameterAnnotations();
        Object[] params = new Object[paramTypes.length];

        for (int i = 0; i < params.length; ++i) {
            Argument me = paramAnns[i];
            if (tok.length < 2) {
                if (me.trailing()) {
                    params[i] = null;
                    continue;
                } else {
                    // We have a new argument, but there are no more tokens
                    throw new CommandException("Insufficient arguments");
                }
            }
            // Three cases here:
            // (1) We are not a trailing argument. The next token is our value.
            // (2) We are a trailing argument, and there are no more arguments after this. The
            //     remaining tokens are our value.
            // (3) We are a trailing argument, and there is a prefixed trailing argument after this.
            //     The next token up to the prefix of the next argument is our value.

            String value;
            if (me == null || !me.trailing()) {
                // Case 1
                tok = tok[1].split("\\s", 2);
                value = tok[0];
            } else if (i + 1 < params.length) {
                // Case 3
                Argument next = paramAnns[i + 1];
                tok = tok[1].split("\\s" + Pattern.quote(next.prefix()) + "\\s", 2);
                value = tok[0];
            } else {
                // Case 2
                value = tok[1];
            }

            if (paramTypes[i].equals(int.class)) {
                try {
                    params[i] = Integer.parseInt(value, 10);
                } catch (NumberFormatException nfe) {
                    throw new CommandException("Invalid number " + value, nfe);
                }
            } else if (paramTypes[i].equals(String.class)) {
                params[i] = value;
            } else if (paramTypes[i].equals(LocalDateTime.class)) {
                try {
                    params[i] = LocalDateTime.parse(value, DATE_FORMAT);
                } catch (DateTimeParseException ex) {
                    throw new CommandException("Invalid date \"" + value + "\"; format is d/m/yyyy hh:mm", ex);
                }
            } else {
                throw new RuntimeException("Unhandled argument type");
            }
        }

        try {
            return ctor.getConstructor().newInstance(params);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Unexpected exception invoking constructor", e);
        }
    }

    private static class ConstructorCache {
        private final Class<?>[] parameterTypes;
        private final Argument[] parameterAnnotations;
        private final Constructor<? extends Command> constructor;

        public ConstructorCache(Constructor<? extends Command> ctor) {
            this.parameterTypes = ctor.getParameterTypes();
            this.parameterAnnotations = new Argument[this.parameterTypes.length];
            this.constructor = ctor;

            Annotation[][] paramAnns = ctor.getParameterAnnotations();
            for (int i = 0; i < paramAnns.length; i++) {
                Annotation[] as = paramAnns[i];
                for (Annotation a : as) {
                    if (!a.annotationType().equals(Argument.class)) {
                        continue;
                    }

                    this.parameterAnnotations[i] = (Argument) a;
                }
            }
        }

        public Class<?>[] getParameterTypes() {
            return parameterTypes;
        }

        public Argument[] getParameterAnnotations() {
            return parameterAnnotations;
        }

        public Constructor<? extends Command> getConstructor() {
            return constructor;
        }
    }
}
