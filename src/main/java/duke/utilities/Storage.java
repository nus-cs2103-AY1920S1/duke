package duke.utilities;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with reading-write processes of disk files.
 */
public class Storage {
    private File tasksFile;

    /**
     * Default constructor for Storage class, which creates a file given a filepath.
     *
     * @param filePath A filepath in <code>String</code> format
     */
    public Storage(String filePath) {
        this.tasksFile = new File(filePath);
    }

    /**
     * Write tasks from list into file.
     *
     * @param tasks A <code>TaskList</code> object that will be the source to write into the file
     */
    public void writeToTasksFile(TaskList tasks) {
        List<Task> taskList = tasks.getList();
        try {
            FileWriter taskFile = new FileWriter(tasksFile);
            for (Task task : taskList) {
                String fileString = task.convertTaskToFileString() + "\n";
                taskFile.write(fileString);
            }
            taskFile.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

    /**
     * Read tasks from file into list.
     *
     * @param tasks A <code>TaskList</code> object which will be the destination
     *              for each <code>Task</code> read from file.
     * @throws FileNotFoundException if file is not found
     */
    public void readFromTasksFileToList(TaskList tasks) throws FileNotFoundException {
        Scanner sc = new Scanner(tasksFile);
        while (sc.hasNext()) {
            String fileLine = sc.nextLine();
            try {
                Task t = convertFileLineToTask(fileLine);
                tasks.addTask(t);
            } catch (DukeException dukeError) {
                System.out.println("): OOPS!!! " + dukeError.getMessage());
            } catch (DateTimeParseException parseError) {
                System.out.println(parseError.getMessage()
                        + ". Please make sure dates from file are in this format DD/MM/YYYY HHMM");
            }
        }

    }

    /**
     * Convert one line from file into a <code>Task</code> object.
     *
     * @param fileLine A <code>String</code> representing a line from the file
     * @return converted <code>Task</code> object
     */
    private Task convertFileLineToTask(String fileLine)
            throws InvalidCommandException, DateTimeParseException {

        String[] parts = fileLine.split("\\|");
        String taskType = parts[0].toUpperCase();
        boolean isCompleted = parts[1].equals("Y");
        String taskString = parts[2];
        String tag = parts[3];

        assert tag.startsWith("#"); // wow this assertion saved my life

        switch (taskType) {
        case "TODO":
            return new Todo(taskString, isCompleted, tag);
        case "DEADLINE":
            String[] deadlineParts = taskString.split(" \\(by: ");
            String deadlineText = deadlineParts[0];
            String by = deadlineParts[1].replace(")", "");

            // assert by is in dd/MM/yyyy HH:mm format
            assert by.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}\\d{2}");

            return new Deadline(deadlineText, by, isCompleted, tag);
        case "EVENT":
            String[] eventParts = taskString.split(" \\(at: ");
            String eventText = eventParts[0];
            String at = eventParts[1].replace(")", "");

            assert !at.contains(")");
            return new Event(eventText, at, isCompleted, tag);
        default:
            throw new InvalidCommandException(
                    "There were some commands that I didn't recognize in your tasks file");
        }
    }
}