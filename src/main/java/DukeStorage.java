import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DukeStorage {

    private final String DUKE_ERR_TASK_UNKNOWN = "An error occurred when trying to re-create a task from the saved file!";
    private final String DUKE_ERR_FILE_IO_EXCEPTION = "Failed to open file! Is the path correct?";

    private BufferedReader taskFileInputBuffer;
    private BufferedWriter taskFileOutputBuffer;
    private String taskFilePath;

    public DukeStorage (String taskFilePath) {
        this.taskFilePath = taskFilePath;
    }

    /**
     * Initializes the BufferedWriter object to prepare for writing to the specified file in {@link #taskFilePath}.
     * @throws IOException
     */
    private void initializeFileOutputStream() throws IOException {
        taskFileOutputBuffer = new BufferedWriter(new FileWriter(taskFilePath, false));
    }

    /**
     * Initializes the BufferedReader object to prepare for reading from the specified file in {@link #taskFilePath}.
     * @throws IOException
     */
    private void initializeFileInputStream() throws IOException {
        taskFileInputBuffer = new BufferedReader(new FileReader(taskFilePath));
    }

    /**
     * Writes a specified List<DukeTask> into the specified file in {@link #taskFilePath}. Each task will be written
     * on a new line.
     * @param userTasks List<DukeTask> to save tasks from, to the file.
     * @throws IOException File parsing error.
     */
    private void writeDukeTasks(List<DukeTask> userTasks) throws IOException {
        for (DukeTask task : userTasks) {
            taskFileOutputBuffer.write(processWriteTask(task));
            taskFileOutputBuffer.newLine();
        }
    }

    /**
     * Reads the specified file in {@link #taskFilePath} and initializes a List<DukeTask> object to be returned.
     * @return An initialized List of DukeTask objects, can be an empty List if there are no tasks in the read file.
     * @throws IOException File parsing error.
     */
    private List<DukeTask> readDukeTasks() throws IOException {
        List<DukeTask> userTasks = new ArrayList<>();
        String line;
        while ((line = taskFileInputBuffer.readLine()) != null) {
            Optional<DukeTask> readTask = processReadTask(line);
            if (!readTask.isEmpty()) {
                userTasks.add(readTask.get());
            } else {
                System.out.println(DUKE_ERR_TASK_UNKNOWN);
            }
        }
        return userTasks;
    }

    /**
     * Takes in a DukeTask object and determines how the final String to write to the file should be formatted.
     * @param task DukeTask object to save to file as a String.
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
     * Takes in a line read from the data file and determines what DukeTask should be re-constructed.
     * @param line A single line from the data file.
     * @return Optional<DukeTask> which could be Optional.empty() if the String has an unexpected formatting.
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
     * Loads the data file and reads it, initializing a List<DukeTask> to be returned to the caller. This List will be
     * populated with DukeTask from the data file.
     * @return Optional<DukeTask> which could be Optional.empty() if there are file parsing errors.
     */
    public Optional<List<DukeTask>> load() {
        try {
            initializeFileInputStream();
            List<DukeTask> retrievedTasks = readDukeTasks();
            taskFileInputBuffer.close();
            return Optional.of(retrievedTasks);
        } catch (IOException ex) {
            System.out.println(DUKE_ERR_FILE_IO_EXCEPTION);
            return Optional.empty();
        }
    }

    /**
     * Saves the List<DukeTask> into the data file. Saving process is opening the target data file for writing without
     * append option, meaning the file will be reset each time.
     * @param userTasks List<DukeTask> to be written to the data file in String format.
     */
    public void save(List<DukeTask> userTasks) {
        try {
            initializeFileOutputStream();
            writeDukeTasks(userTasks);
            taskFileOutputBuffer.close();
        } catch (IOException ex) {
            System.out.println(DUKE_ERR_FILE_IO_EXCEPTION);
        }
    }
}
