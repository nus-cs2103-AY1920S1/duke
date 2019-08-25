import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading/saving tasks from/to a file in the hard drive.
 */
public class Storage {
    protected String filePath;

    /**
     * Creates a Storage object with the file's file path as the argument.
     *
     * @param filePath String of file's filepath.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Accesses the file and extracts tasks from it, saving the tasks into a TaskList object.
     *
     * @param taskList Tasks extracted from file will be added to this TaskList object
     * @throws FileNotFoundException Thrown when the file specified by the filepath does not exist.
     * @throws InvalidTaskArgumentDukeException Thrown when the file contains invalid information to create task objects.
     */
    public void getTasksFromFile(TaskList taskList) throws FileNotFoundException, InvalidTaskArgumentDukeException {
        File taskFile = new File(filePath);
        Scanner scanner = new Scanner(taskFile);
        while (scanner.hasNext()) {
            String textLine = scanner.nextLine();
            taskList.addTask(stringToTask(textLine));
        }
    }

    /**
     * Saves the tasks in the TaskList object into the file.
     *
     * @param taskList TaskList object with tasks to be saved.
     * @throws IOException thrown if an error occurs when writing into the file.
     */
    public void loadTasksToFile(TaskList taskList) throws IOException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);

            for (int i = 0; i < taskList.taskListSize(); i++) {
                fileWriter.write(taskToString(taskList.getTask(i)));

                if (i != taskList.taskListSize() - 1) {
                    fileWriter.write(System.lineSeparator());
                }
            }

            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }


    }

    /**
     * Converts a String in the valid format into a task object.
     * Used in getTasksFromFile method to extract task objects from a file.
     *
     * @param text String input in required format.
     * @return Task object created from processing the input text.
     * @throws InvalidTaskArgumentDukeException Thrown when the String text is in an invalid format so
     * the argument entered when creating a task object is invalid.
     */
    public Task stringToTask(String text) throws InvalidTaskArgumentDukeException {
        String[] textSplit = text.split("\\|");
        Task resultTask;

        if (textSplit[0].equals("T")) {
            resultTask = new ToDo(textSplit[2]);
        } else if (textSplit[0].equals("D")) {
            resultTask = new Deadline(textSplit[2], textSplit[3]);
        } else {
            resultTask = new Event(textSplit[2], textSplit[3]);
        }

        if (textSplit[1].equals("1")) {
            resultTask.markAsDone();
        }

        return resultTask;
    }

    /**
     * Converts a task object into its String representation to be saved into a file.
     * Used in loadTasksToFile method to save tasks objects into a file as Strings.
     *
     * @param task Task object to be represented as a String.
     * @return String representation of the argument Task object.
     */
    public String taskToString(Task task) {
        String taskType = "";
        String description = task.getDescription();
        String isDone = "0";

        if (task.isDone()) {
            isDone = "1";
        }

        if (task instanceof ToDo) {
            taskType = "T";
            return taskType + "|" + isDone + "|" + description;
        } else { // event or deadline
            String time = "";

            if (task instanceof Event) {
                taskType = "E";
                time = ((Event) task).getAt();
            } else {
                taskType = "D";
                time = ((Deadline) task).getBy();
            }

            return taskType + "|" + isDone + "|" + description + "|" + time;

        }
    }
}
