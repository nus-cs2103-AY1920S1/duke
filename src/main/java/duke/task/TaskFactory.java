package duke.task;

import duke.exception.DukeUnknownInputException;

import java.util.Arrays;
import java.util.logging.Logger;

import static java.util.logging.Level.FINE;
import static java.util.logging.Level.INFO;

/**
 * Implements the Factory pattern. Handles all types of task creation.
 */
public class TaskFactory {
    private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Creates a specific task.
     *
     * @param taskType the type of task to create.
     * @param args the required arguments to initialise the task.
     * @return the created task.
     * @throws DukeUnknownInputException if an unknown TaskType is passed as argument.
     */
    public static Task getTask(TaskType taskType, String[] args) throws DukeUnknownInputException {
        logger.log(INFO, "Create {0} with raw args {1}", new Object[]{taskType, Arrays.toString(args)});

        switch (taskType) {
        case TODO:
            // example args: {"borrow", "book"}
            Todo todo = new Todo(String.join(" ", args));

            logger.log(FINE, "Create to-do {0}", new Object[]{todo});
            return todo;
        case EVENT:
            // example eventArgs: {"attend festival", "2pm-4pm"}
            String[] eventArgs = String.join(" ", args).split(" /at ");
            Event event = new Event(eventArgs[0].strip(), eventArgs[1].strip());

            logger.log(FINE, "Create event {0}", new Object[]{event});
            return event;
        case DEADLINE:
            // example deadlineArgs: {"homework", "2/12/2019 1800"}
            String[] deadlineArgs = String.join(" ", args).split(" /by ");
            Deadline deadline = new Deadline(deadlineArgs[0].strip(), deadlineArgs[1].strip());

            logger.log(FINE, "Create deadline {0}", new Object[]{deadline});
            return deadline;
        default:
            throw new DukeUnknownInputException("Hmm... I don't understand that Tasktype enum...");
        }
    }

    /**
     * Creates a specific task with specific completion status.
     *
     * @param taskType the type of task to create.
     * @param args the required arguments to initialise the task.
     * @param isDone the completion status of the task.
     * @return the created task.
     * @throws DukeUnknownInputException if an unknown TaskType is passed as argument.
     */
    public static Task getTask(TaskType taskType, String[] args, boolean isDone) throws DukeUnknownInputException {
        logger.log(INFO, "Create {0} {1} with raw args {2}",
                new Object[]{isDone, taskType, Arrays.toString(args)});

        switch (taskType) {
        case TODO:
            Todo todo = new Todo(String.join(" ", args), isDone);

            logger.log(FINE, "Create todo {0}", new Object[]{todo});
            return todo;
        case EVENT:
            String[] eventArgs = String.join(" ", args).split(" /at ");
            Event event = new Event(eventArgs[0].strip(), eventArgs[1].strip(), isDone);

            logger.log(FINE, "Create event {0}", new Object[]{event});
            return event;
        case DEADLINE:
            String[] deadlineArgs = String.join(" ", args).split(" /by ");
            Deadline deadline = new Deadline(deadlineArgs[0].strip(), deadlineArgs[1].strip(), isDone);

            logger.log(FINE, "Create deadline {0}", new Object[]{deadline});
            return deadline;
        default:
            throw new DukeUnknownInputException("Hmm... I don't understand that Tasktype enum...");
        }
    }
}
