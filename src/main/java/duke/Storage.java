package duke;

import duke.command.Command;
import duke.command.Commands;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private static String PARSE_ERROR_MSG =
            " \u2639 Oops! I encountered an error while reading your previous tasks,\n"
                    + " I ignored that line of input but you should check it out...\n";
    private static String DELIMITER = "|";
    private static String DELIMITER_REGEX = "\\|";
    private static int MIN_TASK_ARGUMENTS = 3;
    private static int MAX_TASK_ARGUMENTS = 4;

    private String filePath;
    private Ui ui;

    public Storage(String filePath) {
        this.filePath = filePath;
        ui = new Ui();
    }

    void loadTasksToList(TaskList taskList) {
        ArrayList<Task> existingTasks = new ArrayList<Task>();

        try {
            Scanner dataScanner = new Scanner(new File(filePath));
            String input;
            String inputs[];
            int lineNumber = 0;


            while (dataScanner.hasNextLine()) {
                lineNumber++;

                try {
                    inputs = dataScanner.nextLine().split(DELIMITER_REGEX);
                    validateDelimiter(inputs, lineNumber);

                    // validate respective inputs
                    TaskType taskType = getTaskType(inputs[0].trim(), lineNumber);
                    boolean isDone = getDoneStatus(inputs[1].trim(), lineNumber);
                    String description = getDescription(inputs[2].trim(), lineNumber);

                    String timing = null;
                    if (taskType.hasTime()) {
                        timing = getTime("", lineNumber);
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
                    ui.printLineDivider();
                    ui.printMsgLine(ex.getDisplayMsg());
                    ui.printLineDivider();
                    continue;
                }
            }

        } catch (FileNotFoundException ex) {
            throw new DukeTaskFileParseException(
                    "No existing file found in specified data directory",
                    " I did not find any previous data in your data directory,\n"
                            + "Creating one...");
        }

        return existingTasks;
    }

    public void saveTasksToDisk(TaskList tasks)
        throws DukeFileWriteException {

        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(filePath);
            TaskType taskType;

            //append tasks to disk
            for (Task task : tasks.getAllTasks()) {
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

    private void validateDelimiter(String[] inputs, int lineNumber)
            throws DukeTaskFileParseException {

        if (inputs.length < MIN_TASK_ARGUMENTS || inputs.length > MAX_TASK_ARGUMENTS) {
            throw new DukeTaskFileParseException(
                    "Missing delimiter or invalid number of delimiters encountered while parsing task file",
                    PARSE_ERROR_MSG
                            + String.format("  lineNumber: %d\n", lineNumber)
                            + String.format(
                                    "  Error Type: Invalid delimiter or number of delimiters \"%s\"",
                                    Storage.DELIMITER));
        }
    }

    private TaskType getTaskType(String input, int lineNumber)
            throws DukeTaskFileParseException {
        try {
            TaskType taskType = TaskType.valueOf(input);
            return taskType;
        } catch (IllegalArgumentException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task type encountered while parsing task file",
                    PARSE_ERROR_MSG
                            + String.format("  lineNumber: %d\n", lineNumber)
                            + "  Error Type: Invalid Task Type");
        }
    }

    private boolean getDoneStatus(String status, int lineNumber)
            throws DukeTaskFileParseException {
        try {
            int doneNumber = Integer.parseInt(status);

            if (doneNumber != 1 && doneNumber != 0) {
                throw new NumberFormatException();
            }

            return doneNumber == 1;
        } catch (NumberFormatException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid done status number encountered while parsing task file",
                    PARSE_ERROR_MSG
                            + String.format("  lineNumber: %d\n", lineNumber)
                            + "  Error Type: Your done status should only be a 0 or 1!");
        }
    }

    private String getDescription(String description, int lineNumber)
        throws DukeTaskFileParseException {

        try {
            TaskUtil.validateTaskDescription(description);
        } catch (DukeInvalidArgumentException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task description encountered when parsing task file",
                    PARSE_ERROR_MSG
                            + String.format("  lineNumber: %d\n", lineNumber)
                            + "  Error Type: Invalid task description");
        }

        return description;
    }

    private String getTime(String input, int lineNumber)
            throws DukeTaskFileParseException {

        try {

        } catch (IndexOutOfBoundsException ex)
            throw new DukeTaskFileParseException(
                    "Missing time information encountered when parsing task file",
                    PARSE_ERROR_MSG
                            + String.format("  lineNumber: %d\n", lineNumber)
                            + "  Error Type: " + "Empty time information for task requiring it");
        }

        return inputs[3].trim();
    }
}
