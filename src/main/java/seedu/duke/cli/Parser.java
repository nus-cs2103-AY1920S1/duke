package seedu.duke.cli;

import seedu.duke.cli.annotations.Argument;
import seedu.duke.cli.annotations.CommandConstructor;
import seedu.duke.cli.commands.ListCommand;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.regex.Pattern;

public class Parser {
    // This class is to be used statically only.
    private Parser() {}

    private static HashMap<String, ConstructorCache> constructors;

    static {
        constructors = new HashMap<>();
        addConstructors(ListCommand.class);
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
                if (!a.trailing() || !a.prefix().isBlank()) {
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

    public static Command parse(String in) throws CommandException {
        String[] tok = in.split("\\s", 2);
        ConstructorCache ctor = constructors.get(tok[0].toLowerCase());
        if (ctor == null) {
            throw new CommandException("Invalid command");
        }

        Class<?>[] paramTypes = ctor.getParameterTypes();
        Argument[] paramAnns = ctor.getParameterAnnotations();
        Object[] params = new Object[paramTypes.length];

        for (int i = 0; i < params.length; ++i) {
            if (tok.length < 2) {
                // We have a new argument, but there are no more tokens
                throw new CommandException("Insufficient arguments");
            }
            // Three cases here:
            // (1) We are not a trailing argument. The next token is our value.
            // (2) We are a trailing argument, and there are no more arguments after this. The
            //     remaining tokens are our value.
            // (3) We are a trailing argument, and there is a prefixed trailing argument after this.
            //     The next token up to the prefix of the next argument is our value.

            String value;
            Argument me = paramAnns[i];
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
                params[i] = Integer.parseInt(value, 10);
            } else if (paramTypes[i].equals(String.class)) {
                params[i] = value;
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
