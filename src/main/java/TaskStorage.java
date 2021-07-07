import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading/saving a Duke object's list of tasks from/to a file in the hard drive.
 */
public class TaskStorage extends Storage {
    /**
     * Creates a TaskStorage object with the file's file path as the argument.
     *
     * @param filePath String of file's filepath.
     */
    public TaskStorage(String filePath) {
        super(filePath);
    }

    /**
     * Accesses the file and extracts tasks from it, saving the tasks into a TaskList object.
     *
     * @param taskList Tasks extracted from file will be added to this TaskList object.
     */
    public void getTasksFromFile(TaskList taskList) {

        try {
            File taskFile = new File(filePath);
            new File("./data").mkdirs();
            if (!taskFile.exists()) {
                taskFile.createNewFile();
                this.fileAccessStatus = "Any previously saved list of tasks was not loaded: new file was created";
            } else {
                Scanner scanner = new Scanner(taskFile);
                while (scanner.hasNext()) {
                    String textLine = scanner.nextLine();
                    taskList.addTask(stringToTask(textLine));
                }
                this.fileAccessStatus = "Previously saved list of tasks successfully loaded :)";
            }
        } catch (IOException e) {
            this.fileAccessStatus = "Any previously saved list of tasks was not loaded: Could not create new file :(";
        } catch (InvalidTaskArgumentDukeException e) {
            this.fileAccessStatus = "Any previously saved list of tasks was not loaded: Invalid format in file :(";
        }

    }

    /**
     * Saves the tasks in the TaskList object into the file.
     *
     * @param taskList TaskList object with tasks to be saved.
     */
    public void loadTasksToFile(TaskList taskList) {
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
}
