package duke.parser;

import duke.exception.FailedToLoadIoException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskManager;
import duke.task.Todo;

/**
 * This is a parser used to parse each line in the file to a <code>Task</code>. The <code>Task</code> can be added into
 * a list of tasks in <code>TaskManager</code>.
 */
public class FileToTaskParser {

    /**
     * This is the number of lines already parsed by the Parser.
     */
    static int lineCount;

    /**
     * Parses the string into a {@link Task} that can be added into a list of tasks in {@link TaskManager}.
     * @param line the line to be parsed
     * @return a task that can be added into a list of tasks
     * @throws FailedToLoadIoException if the line cannot be parsed by the <code>FileToTaskParser</code>
     */
    public static Task parse(String line) throws FailedToLoadIoException {
        lineCount++;
        String[] arr = line.split(",");
        assert (arr.length >= 5);
        try {

            String isDone = getIsDone(arr);
            String reminderDate = getReminder(arr);

            String taskDescription = getTaskDescription(arr);

            switch (getTask(arr)) {

            case "todo":
                Task todo = new Todo(taskDescription);
                todo.markAsDoneIfTrue(isDone);
                todo.setReminderIfPresent(reminderDate);
                return todo;
            case "deadline":
                Task deadline = new Deadline(taskDescription, DateParser.parse(getTaskDate(arr)));
                deadline.markAsDoneIfTrue(isDone);
                deadline.setReminderIfPresent(reminderDate);
                return deadline;
            case "event":
                Task event = new Event(taskDescription, DateParser.parse(getTaskDate(arr)));
                event.markAsDoneIfTrue(isDone);
                event.setReminderIfPresent(reminderDate);
                return event;
            default:
                throw new FailedToLoadIoException(lineCount, line);
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new FailedToLoadIoException(lineCount, line);
        } catch (InvalidDateTimeException idte) {
            throw new FailedToLoadIoException(lineCount, line);
        }
    }

    /**
     * Gets the type of the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the type of task
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getTask(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[0];
    }

    /**
     * Gets a string representation of the boolean value whether the task is done.
     * @param arr the array which contains the information
     * @return a string representation of the boolean value that indicates whether the task is done
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getIsDone(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[1];
    }

    /**
     * Gets the reminder of the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the reminder for the task
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getReminder(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[2];
    }

    /**
     * Gets the description of the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the task description
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getTaskDescription(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[3];
    }

    /**
     * Gets the date field of the task from the array.
     * @param arr the array which contains the information
     * @return a string representation of the date field for the task
     * @throws ArrayIndexOutOfBoundsException if the information does not exist in the array
     */
    private static String getTaskDate(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[4];
    }

}
