package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.text.ParseException;

/**
 * Helper class for converting task strings from file
 * to <code>Task</code> objects
 */
public class FileReaderHandler {
    TaskList tasks;

    /**
     * Default constructor for FileReaderHandler class
     *
     * @param tasks A <code>TaskList</code> object
     */
    public FileReaderHandler(TaskList tasks) {
        this.tasks = tasks;
    }

    /**
     * Reads file and converts each line to <code>Task</code> and
     * adds each task to the <code>TaskList</code> object.
     *
     * @param taskType    A <code>String</code> representing the type of task e.g., "TODO", "DEADLINE", "EVENT"
     * @param isCompleted A <code>boolean</code> indicating if the task is completed
     * @param taskString  A <code>String</code> representing taskName and taskDetails
     */
    public void readLineFromFileToList(String taskType, boolean isCompleted, String taskString) {
        try {
            switch (taskType) {
            case "TODO":
                tasks.addTask(new Todo(taskString, isCompleted), false);
                break;
            case "DEADLINE":
                String[] deadlineParts = taskString.split(" \\(by: ");
                String deadlineText = deadlineParts[0];
                try {
                    tasks.addTask(new Deadline(deadlineText, deadlineParts[1], isCompleted), false);
                } catch (ParseException error) {
                    System.out.println(error.getMessage() + ". Please make sure date from file is in this format DD/MM/YYYY HHMM");
                }
                break;
            case "EVENT":
                String[] eventParts = taskString.split(" \\(at: ");
                String eventText = eventParts[0];
                try {
                    tasks.addTask(new Event(eventText, eventParts[1], isCompleted), false);
                } catch (ParseException error) {
                    System.out.println(error.getMessage() + ". Please enter date from file in this format DD/MM/YYYY HHMM - DD/MM/YYYY HHMM");
                }
                break;
            }
        } catch (DukeException error) {
            System.out.println("\t ☹ OOPS!!! " + error.getMessage());
        }

    }
}
