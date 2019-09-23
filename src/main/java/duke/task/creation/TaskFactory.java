package duke.task.creation;

import duke.task.Task;
import error.datetime.UnknownDateTimeException;
import error.task.TaskArgumentsException;
import error.task.TaskCreationException;

import java.util.Arrays;
import java.util.Optional;

/**
 * Factory to produce tasks according to user inputs.
 */
public class TaskFactory {
    /**
     * Parses user input to create corresponding task.
     *
     * @param keyword keyword of the user's input.
     * @param arguments remaining of user's input.
     * @return Optional of task if it is a valid keyword.
     * @throws TaskCreationException if arguments are invalid.
     */
    public Optional<Task> getTask(String keyword, String arguments) throws TaskCreationException {
        // scans task types to find corresponding keyword
        Optional<TaskType> taskTypeOptional = Arrays.stream(TaskType.values())
                .filter(t -> t.keyword.equals(keyword))
                .findFirst();

        if (taskTypeOptional.isEmpty()) {
            return Optional.empty();
        }

        try {
            TaskArguments taskArguments = new TaskArgumentsParser(arguments, taskTypeOptional.get().numDates).parse();
            return Optional.of(TaskBuilder.buildTask(taskTypeOptional.get(), taskArguments));

        } catch (TaskArgumentsException e) {
            throw new TaskCreationException("Something unexpected happened.");
        } catch (UnknownDateTimeException e) {
            throw new TaskCreationException("Please enter a valid date and time.");
        }
    }
}
