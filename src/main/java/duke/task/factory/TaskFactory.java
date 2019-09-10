package duke.task.factory;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import error.task.TaskCreationException;
import error.datetime.UnknownDateTimeException;
import util.CommandUtils;
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
    private static final String INVALID_ARGUMENTS_MESSAGE = "☹ OOPS!!! Your task arguments are invalid! :-(";



    public Optional<Task> getTask(String input) throws TaskCreationException {
        // gets first word of input
        String keyword = CommandUtils.getCommand(input);

        // scans task types to find corresponding keyword
        Optional<Class<? extends Task>> taskOptional = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(keyword))
                .findFirst()
                .map(t1 -> t1.task);

        if (taskOptional.isEmpty()) {
            return Optional.empty();
        }

        // get constructor and parameters type of particular task
        Class<?> task = taskOptional.get();
        Constructor<?> constructor = task.getConstructors()[0];
        Class<?>[] parameters = constructor.getParameterTypes();

        // parse arguments
        List<Object> argsList = getArguments(input);

        // for now tasks are always not done and not recurring when created
        argsList.add(false);
        argsList.add(false);

        try {

            Object[] argsArray = reorderArgsList(parameters, argsList);

            // try to instantiate task with arguments
            Task result = (Task) constructor.newInstance(argsArray);
            return Optional.of(result);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new TaskCreationException("");
        }
    }

    private List<Object> getArguments(String input) throws TaskCreationException {
        // gets arguments
        String arguments = CommandUtils.getArguments(input);

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
            throw new TaskCreationException(INVALID_ARGUMENTS_MESSAGE);
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
            throw new TaskCreationException(INVALID_ARGUMENTS_MESSAGE);
        }

        // returns sorted arguments list
        return Arrays.stream(parameters).map(type -> {
            Optional<Object> matchingArg = arguments.stream().findFirst();
            matchingArg.ifPresent(arguments::remove); // in case of multiple arguments of the same type
            return matchingArg.orElse(null);
        }).toArray();
    }
}
