package duke.parser;

import duke.exception.FailedToLoadIOException;
import duke.exception.InvalidDateTimeException;
import duke.task.*;

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
     * @throws FailedToLoadIOException if the line cannot be parsed by the <code>FileToTaskParser</code>
     */
    public static Task parse(String line) throws FailedToLoadIOException {
        lineCount++;
        String[] arr = line.split(",");
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
                throw new FailedToLoadIOException(lineCount, line);
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new FailedToLoadIOException(lineCount, line);
        } catch (InvalidDateTimeException idte) {
            throw new FailedToLoadIOException(lineCount, line);
        }
    }

    private static String getTask(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[0];
    }

    private static String getIsDone(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[1];
    }

    private static String getReminder(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[2];
    }

    private static String getTaskDescription(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[3];
    }

    private static String getTaskDate(String[] arr) throws ArrayIndexOutOfBoundsException {
        return arr[4];
    }
}
