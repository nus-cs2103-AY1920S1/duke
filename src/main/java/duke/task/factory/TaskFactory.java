package duke.task.factory;

import duke.task.Task;
import duke.task.TaskType;
import error.task.TaskCreationException;
import error.task.UnknownDateTimeException;
import util.DateTime;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TaskFactory {
    private final String dateTimeRegex =
            "(([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})\\s([0-9]{4}))";


    public Task getTask(String command) throws TaskCreationException {
        // gets first word of command
        String keyword = command.split(" ", 2)[0];

        // scans task types to find corresponding keyword
        Optional<Class<? extends Task>> taskOptional = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(keyword))
                .findFirst()
                .map(t1 -> t1.task);

        if (taskOptional.isEmpty()) {
            throw new TaskCreationException();
        }

        // get constructor and parameters type of particular task
        Class<?> task = taskOptional.get();
        Constructor<?> constructor = taskOptional.get().getConstructors()[0];
        Class<?>[] parameters = constructor.getParameterTypes();

        // parse arguments
        List<Object> argsList = getArguments(command);
        argsList.add(false);
        argsList.add(false);

        try {
            Object[] argsArray = reorderArgsList(parameters, argsList);
            return (Task) constructor.newInstance(argsArray);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new TaskCreationException();
        }
    }

    private List<Object> getArguments(String command) throws TaskCreationException {
        // gets arguments
        String arguments = command.split(" ", 2)[1];

        List<Object> argsList = new ArrayList<>();

        try {
            // get description
            String description = arguments.replaceAll(dateTimeRegex, "").trim();
            argsList.add(description);

            // get datetime arguments
            List<LocalDateTime> times = extractLocalDateTime(arguments);
            assert times.size() <= 2;
            argsList.addAll(times);

        } catch (UnknownDateTimeException | AssertionError e) {
            throw new TaskCreationException();
        }

        return argsList;
    }

    private List<LocalDateTime> extractLocalDateTime(String arguments) throws UnknownDateTimeException {
        Pattern pattern = Pattern.compile(dateTimeRegex);
        Matcher matcher = pattern.matcher(arguments);

        List<String> dateTimePatterns = new ArrayList<>();

        while (matcher.find()) {
            dateTimePatterns.add(matcher.group(1));
        }

        List<LocalDateTime> dateTimeArguments = new ArrayList<>();
        for (String s : dateTimePatterns) {
            dateTimeArguments.add(DateTime.parse(s));
        }

        return dateTimeArguments;
    }

    private Object[] reorderArgsList(Class<?>[] parameters, List<Object> arguments) throws TaskCreationException {
        // if arguments length and parameters length different, throw exception
        if (parameters.length != arguments.size()) {
            throw new TaskCreationException();
        }

        // returns sorted arguments list
        return Arrays.stream(parameters).map(type -> {
            Optional<Object> matchingArg = arguments.stream().findFirst();
            matchingArg.ifPresent(arguments::remove); // in case of multiple arguments of the same type
            return matchingArg.orElse(null);
        }).toArray();
    }
}
