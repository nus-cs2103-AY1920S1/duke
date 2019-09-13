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

/**
 * Factory to produce tasks.
 */
public class TaskFactory {
    private final String dateTimeRegex =
            "(([0]?[1-9]|[1|2][0-9]|[3][0|1])[./-]([0]?[1-9]|[1][0-2])[./-]([0-9]{4}|[0-9]{2})\\s([0-9]{4}))";

    private static final String INVALID_ARGUMENTS_ERROR_MESSAGE = "☹ OOPS!!! Your task arguments are invalid! :-(";
    private static final String UNKNOWN_ERROR_MESSAGE = "☹ OOPS!!! Something went wrong while creating your task! :-(";
    private static final String EMPTY_DETAILS_ERROR_MESSAGE = "☹ OOPS!!! Your task description cannot be empty! :-(";

    /**
     * Parses user input to create corresponding task.
     * @param input user input
     * @return optional of task
     * @throws TaskCreationException if arguments are invalid
     */
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
            throw new TaskCreationException(UNKNOWN_ERROR_MESSAGE);
        }
    }

    private List<Object> getArguments(String input) throws TaskCreationException {
        // gets arguments
        String arguments = CommandUtils.getArguments(input);

        List<Object> argsList = new ArrayList<>();

        try {

            // get datetime arguments
            List<LocalDateTime> times = extractLocalDateTime(arguments);
            argsList.addAll(times);

            // get description
            String description = arguments.replaceAll(dateTimeRegex, "").trim();
            if (description.equals("")) {
                throw new TaskCreationException(EMPTY_DETAILS_ERROR_MESSAGE);
            }

            argsList.add(description);

        } catch (UnknownDateTimeException e) {
            throw new TaskCreationException(INVALID_ARGUMENTS_ERROR_MESSAGE);
        }

        return argsList;
    }

    private List<LocalDateTime> extractLocalDateTime(String arguments) throws UnknownDateTimeException {
        Pattern pattern = Pattern.compile(dateTimeRegex);
        Matcher matcher = pattern.matcher(arguments);

        List<String> dateTimePatterns = new ArrayList<>();

        // find all substrings that match regex pattern
        while (matcher.find()) {
            dateTimePatterns.add(matcher.group(1));
        }

        // cannot use streams because of checked exception :(
        List<LocalDateTime> dateTimeArguments = new ArrayList<>();
        for (String s : dateTimePatterns) {
            dateTimeArguments.add(DateTime.parse(s));
        }

        return dateTimeArguments;
    }

    private Object[] reorderArgsList(Class<?>[] parameters, List<Object> arguments) throws TaskCreationException {
        // if arguments length and parameters length different, throw exception
        if (parameters.length != arguments.size()) {
            throw new TaskCreationException(INVALID_ARGUMENTS_ERROR_MESSAGE);
        }

        // returns sorted arguments list
        return Arrays.stream(parameters).map(type -> {
            Optional<Object> matchingArg = arguments.stream()
                    .filter(arg -> type.equals(arg.getClass()))
                    .findFirst();

            matchingArg.ifPresent(arguments::remove); // delete in case of multiple arguments of the same type
            return matchingArg.orElse(null);
        }).toArray();
    }
}
