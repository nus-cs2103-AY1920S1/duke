package duke.task.factory;

import duke.task.Task;
import duke.task.tasks.entities.TaskType;
import error.command.DateTimeExtractionException;
import error.task.TaskCreationException;
import error.datetime.UnknownDateTimeException;
import util.command.TaskArguments;
import util.command.CommandUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Factory to produce tasks.
 */
public class TaskFactory {

    private static final String INVALID_ARGUMENTS_ERROR_MESSAGE = "☹ OOPS!!! Your task arguments are invalid! :-(";
    private static final String UNKNOWN_ERROR_MESSAGE = "☹ OOPS!!! Something went wrong while creating your task! :-(";
    private static final String EMPTY_DETAILS_ERROR_MESSAGE = "☹ OOPS!!! Your task description cannot be empty! :-(";

    /**
     * Parses user input to create corresponding task.
     *
     * @param input user input
     * @return optional of task
     * @throws TaskCreationException if arguments are invalid
     */
    public Optional<Task> getTask(String input) throws TaskCreationException {
        // gets first word of input
        String keyword = input.split(" ", 2)[0];

        // scans task types to find corresponding keyword
        Optional<TaskType> taskTypeOptional = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(keyword))
                .findFirst();

        if (taskTypeOptional.isEmpty()) {
            return Optional.empty();
        }

        // get constructor and parameters type of particular task
        Class<?> task = taskTypeOptional.get().task;
        Constructor<?> constructor = task.getConstructors()[0];
        Class<?>[] parameters = constructor.getParameterTypes();

        // parse arguments
        int numDates = taskTypeOptional.get().numDates;
        List<Object> argsList = getArguments(input, numDates);

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

    /**
     * Gets list of arguments from user input
     * @param input user input
     * @param numDates number of dates arguments should contain
     * @return list of argument objects
     * @throws TaskCreationException if arguments are invalid
     */
    private List<Object> getArguments(String input, int numDates) throws TaskCreationException {
        // gets arguments
        TaskArguments arguments = CommandUtils.getTaskArguments(input);

        List<Object> argsList = new ArrayList<>();

        try {
            // get datetime arguments
            List<LocalDateTime> times = arguments.extractLocalDateTime(numDates);
            argsList.addAll(times);

            // get description
            if (arguments.isEmpty()) {
                throw new TaskCreationException(EMPTY_DETAILS_ERROR_MESSAGE);
            }

            String description = arguments.getArguments();
            argsList.add(description);

        } catch (UnknownDateTimeException | DateTimeExtractionException e) {
            throw new TaskCreationException(INVALID_ARGUMENTS_ERROR_MESSAGE);
        }

        return argsList;
    }


    /**
     * Reorders arguments list to match order of parameters in order to invoke constructor properly
     * @param parameters array of parameters
     * @param arguments list of arguments
     * @return new argument array
     * @throws TaskCreationException if arguments and parameters differ in length to begin with
     */
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
