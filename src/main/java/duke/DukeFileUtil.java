package duke;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.TodoTask;
import duke.task.TaskType;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DukeFileUtil {

    private static String PARSE_ERROR_MSG =
            " \u2639 Oops! I encountered an error while reading your previous tasks,\n"
                    + " I ignored that line of input but you should check it out...\n";
    private static String DELIMITER = "|";
    private static String DELIMITER_REGEX = "\\|";

    static ArrayList<Task> loadTasksFromDisk(String filePath) {
        ArrayList<Task> existingTasks = new ArrayList<Task>();

        try {
            Scanner dataScanner = new Scanner(new File(filePath));
            String inputs[];
            int lineNumber = 0;

            while (dataScanner.hasNextLine()) {
                try {
                    lineNumber++;
                    inputs = dataScanner.nextLine().split(DELIMITER_REGEX);

                    validateDelimiter(inputs, lineNumber);

                    // validate respective inputs
                    TaskType taskType = getTaskType(inputs[0].trim(), lineNumber);
                    boolean isDone = getDoneStatus(inputs[1].trim(), lineNumber);
                    String description = getDescription(inputs[2].trim(), lineNumber);

                    //assume time is correct for now, will be corrected post level-8 merge, but check for its existence
                    String timing = null;
                    if (taskType.hasTime()) {
                        timing = getTime(inputs, lineNumber);
                    }

                    Task taskToAdd = null;
                    switch (taskType) {
                        case todo:
                            taskToAdd = new TodoTask(description);
                            break;
                        case deadline:
                            taskToAdd = new DeadlineTask(description, timing);
                            break;
                        case event:
                            taskToAdd = new EventTask(description, timing);
                            break;
                    }
                    taskToAdd.setDone(isDone);

                    existingTasks.add(taskToAdd);

                } catch (DukeTaskFileParseException | DukeInvalidArgumentException ex) {
                    System.out.print(Duke.HORIZONTAL_LINE);
                    System.out.println(ex.getDisplayMsg());
                    System.out.println(Duke.HORIZONTAL_LINE);
                    continue;
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.print(Duke.HORIZONTAL_LINE);
            System.out.println(" I did not find any previous data in your data directory,\n"
                    + "Creating one...");
            System.out.println(Duke.HORIZONTAL_LINE);

        }

        return existingTasks;
    }

    static void writeTasksToDisk(ArrayList<Task> tasks, String filePath)
        throws DukeFileWriteException {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);
            TaskType taskType;

            //append tasks to disk
            for (Task task : tasks) {
                taskType = task.getTaskType();

                fileWriter.write(taskType.toString());
                fileWriter.write(String.format(" %s ", DELIMITER));
                fileWriter.write(task.isDone() ? "1" : "0");
                fileWriter.write(String.format(" %s ", DELIMITER));
                fileWriter.write(task.getDescription());

                if (taskType.hasTime()) {
                    fileWriter.write(String.format(" %s ", DELIMITER));
                    fileWriter.write(task.getTiming());
                }

                fileWriter.write("\n");
            }

            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeFileWriteException(
                    ex.getMessage(),
                    " \u2639 OOPS!!! I failed to write the task data to disk!");
        }
    }

    private static void validateDelimiter(String[] inputs, int lineNumber)
            throws DukeTaskFileParseException {

        if (inputs.length != 3 && inputs.length != 4) {
            throwParseException(
                    "Missing delimiter or invalid number of delimiters encountered while parsing task file",
                    String.format("Invalid delimiter or number of delimiters \"%s\"",
                            DukeFileUtil.DELIMITER),
                    lineNumber);
        }
    }

    private static TaskType getTaskType(String input, int lineNumber)
            throws DukeTaskFileParseException {
        TaskType taskType = null;

        try {
            taskType = TaskType.valueOf(input);

        } catch (IllegalArgumentException ex) {
            throwParseException(
                    "Invalid task type encountered while parsing task file",
                    "Invalid Task Type",
                    lineNumber);
        }

        return taskType;
    }

    private static boolean getDoneStatus(String status, int lineNumber)
            throws DukeTaskFileParseException {

        try {
            int doneNumber = Integer.parseInt(status);

            if (doneNumber != 1 && doneNumber != 0) {
                throw new NumberFormatException();
            }

            return doneNumber == 1
                    ? true
                    : false;

        } catch (NumberFormatException ex) {
            throwParseException(
                    "Invalid done status number encountered while parsing task file",
                    "Your done status should only be a 0 or 1!",
                    lineNumber);

            return false;
        }
    }

    private static String getDescription(String description, int lineNumber)
        throws DukeTaskFileParseException {

        try {
            Duke.validateTaskDescription(description);
        } catch (DukeInvalidArgumentException ex) {
            throwParseException(
                    "Invalid task description encountered when parsing task file",
                    "Invalid task description",
                    lineNumber);
        }

        return description;
    }

    private static String getTime(String[] inputs, int lineNumber)
            throws DukeTaskFileParseException {

        if (inputs.length != 4) {
            throwParseException(
                    "Missing time information encountered when parsing task file",
                    "Empty time information for task requiring it",
                    lineNumber);
        }

        return inputs[3].trim();
    }

    private static void throwParseException(String errorMsg, String displayMsg, int lineNumber)
            throws DukeTaskFileParseException {

        throw new DukeTaskFileParseException(
                errorMsg,
                PARSE_ERROR_MSG
                        + String.format("  lineNumber: %d\n", lineNumber)
                        + "  Error Type: " + displayMsg);
    }
}
