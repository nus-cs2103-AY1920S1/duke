package duke.util;

import duke.task.DukeTask;
import duke.task.DukeTaskDeadline;
import duke.task.DukeTaskEvent;
import duke.task.DukeTaskToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DukeStorage {

    private BufferedReader taskFileInputBuffer;
    private BufferedWriter taskFileOutputBuffer;
    private File taskFilePath;

    /**
     * This constructor takes in the path of the data file stored on the hard disk.
     * @param taskFilePath Relative/Full path to the data file.
     */
    public DukeStorage (String taskFilePath) throws NullPointerException {
        this.taskFilePath = new File(taskFilePath);
    }

    /**
     * Initializes the BufferedWriter object to prepare for writing to the specified file in {@link #taskFilePath}.
     * @throws IOException If there are any errors like insufficient permissions, file not found or other file errors.
     */
    private void initializeFileOutputStream() throws IOException {
        taskFileOutputBuffer = new BufferedWriter(new FileWriter(taskFilePath, false));
    }

    /**
     * Initializes the BufferedReader object to prepare for reading from the specified file in {@link #taskFilePath}. If
     * the data file does not exist, create it along with the necessary folders.
     * @throws IOException
     */
    private void initializeFileInputStream() throws IOException {
        if (!taskFilePath.exists()) {
            taskFilePath.getParentFile().mkdirs();
        }
        taskFileInputBuffer = new BufferedReader(new FileReader(taskFilePath));
    }

    /**
     * Writes a specified List<duke.task.DukeTask> into the specified file in {@link #taskFilePath}. Each task will be written
     * on a new line.
     * @param userTasks List<duke.task.DukeTask> to save tasks from, to the file.
     * @throws IOException File parsing error.
     */
    private void writeDukeTasks(List<DukeTask> userTasks) throws IOException {
        for (DukeTask task : userTasks) {
            taskFileOutputBuffer.write(processWriteTask(task));
            taskFileOutputBuffer.newLine();
        }
    }

    /**
     * Reads the specified file in {@link #taskFilePath} and initializes a List<duke.task.DukeTask> object to be returned.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @return An initialized List of duke.task.DukeTask objects, can be an empty List if there are no tasks in the read file.
     * @throws IOException File parsing error.
     */
    private List<DukeTask> readDukeTasks(DukeUi ui) throws IOException {
        List<DukeTask> userTasks = new ArrayList<>();
        String line;
        while ((line = taskFileInputBuffer.readLine()) != null) {
            Optional<DukeTask> readTask = processReadTask(line);
            if (!readTask.isEmpty()) {
                userTasks.add(readTask.get());
            } else {
                ui.displayUnknownTask();
            }
        }
        return userTasks;
    }

    /**
     * Takes in a duke.task.DukeTask object and determines how the final String to write to the file should be formatted.
     * @param task {@link DukeTask} object to save to file as a String.
     * @return Formatted String to be written to the file.
     */
    private String processWriteTask(DukeTask task) {
        int taskComplete = task.getTaskIsComplete() ? 1 : 0;
        String taskConstraint = "";
        String writtenString = "";

        if (task instanceof DukeTaskDeadline) {
            taskConstraint = ((DukeTaskDeadline) task).getTaskDeadline();
        } else if (task instanceof  DukeTaskEvent) {
            taskConstraint = ((DukeTaskEvent) task).getTaskLocation();
        }

        writtenString = task.getTaskType() + " | " + taskComplete + " | " + task.getTaskName();
        if (taskConstraint.length() > 0) {
            writtenString += " | " + taskConstraint;
        }

        return writtenString;
    }

    /**
     * Takes in a line read from the data file and determines what duke.task.DukeTask should be re-constructed.
     * @param line A single line from the data file.
     * @return Optional<duke.task.DukeTask> which could be Optional.empty() if the String has an unexpected formatting.
     */
    private Optional<DukeTask> processReadTask(String line) {
        DukeTask task;
        String[] lineTokens = line.split(" \\| ");
        String taskType = lineTokens[0];
        boolean isComplete = lineTokens[1].equals("1") ? true : false;
        String taskName = lineTokens[2];

        if (taskType.equals("T")) {
            task = new DukeTaskToDo(taskName, isComplete);
        } else if (taskType.equals("D")) {
            task = new DukeTaskDeadline(taskName, isComplete, lineTokens[3]);
        } else if (taskType.equals("E")) {
            task = new DukeTaskEvent(taskName, isComplete, lineTokens[3]);
        } else {
            return Optional.empty();
        }

        return Optional.of(task);
    }

    /**
     * Loads the data file and reads it, initializing a List<duke.task.DukeTask> to be returned to the caller. This List will be
     * populated with duke.task.DukeTask from the data file.
     * @param ui Instance of {@link DukeUi} which will show output to the user.
     * @return Optional<duke.task.DukeTask> which could be Optional.empty() if there are file parsing errors.
     * @throws IOException File parsing error.
     */
    public List<DukeTask> load(DukeUi ui) throws IOException {
        initializeFileInputStream();
        List<DukeTask> retrievedTasks = readDukeTasks(ui);
        taskFileInputBuffer.close();
        return retrievedTasks;
    }

    /**
     * Saves the List<duke.task.DukeTask> into the data file. Saving process is opening the target data file for writing without
     * append option, meaning the file will be reset each time.
     * @param userTasks List<duke.task.DukeTask> to be written to the data file in String format.
     * @throws IOException File parsing error.
     */
    public void save(List<DukeTask> userTasks) throws IOException {
        initializeFileOutputStream();
        writeDukeTasks(userTasks);
        taskFileOutputBuffer.close();
    }
}
