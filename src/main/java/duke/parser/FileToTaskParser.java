package duke.parser;

import duke.exception.FailedToLoadIOException;
import duke.exception.InvalidDateTimeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This is a parser used to parse each line in the file to a <code>Task</code>. The <code>Task</code> can be added into
 * a list of tasks in <code>TaskList</code>.
 */
public class FileToTaskParser {

    /**
     * This is the number of lines already parsed by the Parser.
     */
    static int lineCount;

    /**
     * Parses the string into a {@link Task} that can be added into a list of tasks in {@link duke.task.TaskList}.
     * @param line the line to be parsed
     * @return a task that can be added into a list of tasks
     * @throws FailedToLoadIOException if the line cannot be parsed by the <code>FileToTaskParser</code>
     */
    public static Task parse(String line) throws FailedToLoadIOException {
        lineCount++;
        String[] arr = line.split(",");
        try {
            boolean isDone = arr[1].equals("true");
            String taskDescription = arr[2];
            switch (arr[0]) {
            case "todo":
                return new Todo(taskDescription, isDone);
            case "deadline":
                String deadlineBy = arr[3];
                return new Deadline(taskDescription, DateParser.parse(deadlineBy), isDone);
            case "event":
                String eventAt = arr[3];
                return new Event(arr[2], DateParser.parse(eventAt), isDone);
            default:
                throw new FailedToLoadIOException(lineCount, line);
            }
        } catch (ArrayIndexOutOfBoundsException aiobe) {
            throw new FailedToLoadIOException(lineCount, line);
        } catch (InvalidDateTimeException idte) {
            throw new FailedToLoadIOException(lineCount, line);
        }
    }
}
