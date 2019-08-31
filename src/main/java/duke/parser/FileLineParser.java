package duke.parser;

import duke.exception.InvalidDateTimeException;
import duke.exception.LineInFileParseException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class FileLineParser {

    static int lineCount;

    public static Task parse(String line) {
        lineCount++;
        String[] arr = line.split(",");
        try {
            boolean isDone = arr[2].equals("true");
            switch (arr[0]) {
                case "todo":
                    return new Todo(arr[1], isDone);
                case "deadline":
                    return new Deadline(arr[1], DateParser.parse(arr[3]), isDone);
                case "event":
                    return new Event(arr[1], DateParser.parse(arr[3]), isDone);
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
