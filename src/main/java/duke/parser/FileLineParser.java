package duke.parser;

import duke.exception.InvalidDateTimeException;
import duke.exception.LineInFileParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * This is a parser used to parse each line in the file to a <code>Task</code>. The <code>Task</code> can be added into
 * a list of tasks in <code>TaskList</code>.
 */
public class FileLineParser {

    /**
     * This is the number of lines already parsed by the Parser.
     */
    static int lineCount;

    /**
     * Parses the string into a {@link Task} that can be added into a list of tasks in {@link duke.task.TaskList}.
     * @param line the line to be parsed
     * @return a task that can be added into a list of tasks
     * @throws LineInFileParseException if the line cannot be parsed by the <code>FileLineParser</code>
     */
    public static Task parse(String line) {
        lineCount++;
        String[] arr = line.split(",");
        try {
            boolean isDone = arr[1].equals("true");
            switch (arr[0]) {
                case "todo":
                    return new Todo(arr[2], isDone);
                case "deadline":
                    return new Deadline(arr[2], DateParser.parse(arr[3]), isDone);
                case "event":
                    return new Event(arr[2], DateParser.parse(arr[3]), isDone);
                default:
                    throw new LineInFileParseException(lineCount, line);
            }
        } catch(ArrayIndexOutOfBoundsException aiobe) {
            throw new LineInFileParseException(lineCount, line);
        } catch(InvalidDateTimeException idte) {
            throw new LineInFileParseException(lineCount, line);
        }
    }
}
