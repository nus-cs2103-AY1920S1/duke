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
//        System.out.println(Arrays.toString(arr));
        try {
            boolean isDone = arr[1].equals("true");
            boolean isReminder = !arr[2].equals("null");
            String taskDescription = arr[3];
            switch (arr[0]) {
            case "todo":
                Task todo = new Todo(taskDescription);
//                System.out.println(todo);
                if(isDone) {
                    todo.markAsDone();
                }
                if(isReminder) {
                    todo.setReminder(DateParser.parse(arr[2]));
                }
                return todo;
            case "deadline":
                String deadlineBy = arr[4];
//                System.out.println(deadlineBy);
                Task deadline = new Deadline(taskDescription, DateParser.parse(deadlineBy));
                if(isDone) {
                    deadline.markAsDone();
                }
                if(isReminder) {
                    deadline.setReminder(DateParser.parse(arr[2]));
                }
//                System.out.println(deadline);
                return deadline;
            case "event":
                String eventAt = arr[4];
                Task event = new Event(taskDescription, DateParser.parse(eventAt));
                if(isDone) {
                    event.markAsDone();
                }
                if(isReminder) {
                    event.setReminder(DateParser.parse(arr[2]));
                }
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
}
