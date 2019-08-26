package duke.storage;

import duke.command.DukeInvalidArgumentException;
import duke.ui.Ui;
import duke.task.*;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Map;

/**
 * Abstraction of the file storage of tasks.
 * Requires a root folder dirName to search for recursively upwards.
 */
public class Storage {

    private static String PARSE_ERROR_MSG =
            " \u2639 Oops! I encountered an error while reading your previous tasks,\n"
                    + " I ignored that line of input but you should check it out...\n";
    private static final String FALLBACK_FILE_PATH = "../data";
    private static final String DATA_FILE_NAME = "taskData.txt";
    private static final int DIRECTORY_SEARCH_LIMIT = 5;
    private String filePath;
    private Ui ui;

    public Storage(String dirName, Ui ui) {
        setFilePath(dirName);
        this.ui = ui;
    }

    public void loadTasksToList(TaskList taskList) {
        Scanner dataScanner;

        try {
            dataScanner = new Scanner(new File(filePath));
        } catch (FileNotFoundException ex) {
            ui.printLineDivider();
            ui.printMsgLine(" I did not find any previous data in your data directory\n"
                    + " I'll create one when you save something!");
            ui.printLineDivider();
            ui.printEmptyLine();
            return;
        }

        String input;
        Map<StorageKey, String> inputs;

        TaskType taskType;
        boolean isTaskDone;
        String taskDescription = "";
        String taskTiming = "";
        int lineNumber = 0;

        while (dataScanner.hasNextLine()) {
            input = dataScanner.nextLine();
            lineNumber++;

            try {
                inputs = StorageParser.parseJsonLine(input);

                taskType = getTaskType(inputs.get(StorageKey.type));
                isTaskDone = getDoneStatus(inputs.get(StorageKey.done));
                taskDescription = getDescription(inputs.get(StorageKey.description));
                taskTiming = inputs.get(StorageKey.time);

                Task taskToAdd;

                switch (taskType) {
                case todo:
                    taskToAdd = new TodoTask(taskDescription);
                    break;
                case deadline:
                    taskToAdd = new DeadlineTask(taskDescription, taskTiming);
                    break;
                case event:
                    taskToAdd = new EventTask(taskDescription, taskTiming);
                    break;
                default:
                    throw new DukeTaskFileParseException(
                            "Unhandled taskType encountered",
                            " \u2639 Oops! I am not trained to handle this type of Tasks...\n");
                }

                taskToAdd.setDone(isTaskDone);
                taskList.addTask(taskToAdd);
            } catch (DukeTaskFileParseException | DukeInvalidArgumentException ex) {
                ui.printLineDivider();
                ui.printMsgLine(ex.getDisplayMsg());
                ui.printMsgLine(String.format("   Error in storage file line number: %d", lineNumber));
                ui.printLineDivider();
            }
        }
    }

    public void saveTasksToDisk(TaskList tasks) throws DukeFileWriteException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (Task task : tasks.getAllTasks()) {
                String jsonLineStart = String.format(
                        "{ %s: %s, %s: %s, %s: %s",
                        StorageKey.type.toString(), task.getTaskType().toString(),
                        StorageKey.done.toString(), ((Boolean) task.isDone()).toString(),
                        StorageKey.description.toString(), task.getDescription());

                fileWriter.write(jsonLineStart);

                if (task.getTiming() != null) {
                    fileWriter.write(
                            String.format(", %s: %s",
                                    StorageKey.time.toString(),
                                    task.getTiming()));
                }

                fileWriter.write(" }\n");
            }
            fileWriter.close();
        } catch (IOException ex) {
            throw new DukeFileWriteException(
                    ex.getMessage(),
                    " \u2639 OOPS!!! I failed to write the task data to disk!");
        }
    }

    private TaskType getTaskType(String input) throws DukeTaskFileParseException {
        try {
            return TaskType.valueOf(input);
        } catch (IllegalArgumentException | NullPointerException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task type encountered while parsing task file",
                    " \u2639 Oops! I encountered an invalid task type value while\n"
                            + "   reading your file.");
        }
    }

    private boolean getDoneStatus(String status) throws DukeTaskFileParseException {
        if (status != null) {
            if (status.equalsIgnoreCase("true")) {
                return true;
            } else if (status.equalsIgnoreCase("false")) {
                return false;
            }
        }

        throw new DukeTaskFileParseException(
                "Invalid done status number encountered while parsing task file",
                " \u2639 Oops! I encountered an invalid or missing done value\n"
                        + "   while reading your file.");
    }

    private String getDescription(String description) throws DukeTaskFileParseException {
        try {
            TaskUtil.validateTaskDescription(description);
            return description;
        } catch (DukeInvalidArgumentException | NullPointerException ex) {
            throw new DukeTaskFileParseException(
                    "Invalid task description encountered when parsing task file",
                    " \u2639 Oops! I encountered an empty description while\n"
                            + "   reading your file.");
        }
    }

    private void setFilePath(String dirName) {
        String workingDir = System.getProperty("user.dir");
        Path currentDir = Paths.get(workingDir);

        int recursiveSearchCount = 1;

        while (!Files.isDirectory(Paths.get(currentDir.toString(), dirName))
                && recursiveSearchCount <= DIRECTORY_SEARCH_LIMIT) {
            currentDir = currentDir.getParent();
            recursiveSearchCount++;
        }

        if (recursiveSearchCount > 5) {
            this.filePath = Paths.get(FALLBACK_FILE_PATH, DATA_FILE_NAME).toString();
        } else {
            this.filePath = Paths.get(currentDir.toString(), dirName, DATA_FILE_NAME).toString();
        }
    }
}
