package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * This class deals with reading-write processes of disk files
 */
public class Storage {
    private File tasksFile;

    /**
     * Default constructor for Storage class, which creates a file given a filepath
     *
     * @param filePath A filepath in <code>String</code> format
     */
    public Storage(String filePath) {
        this.tasksFile = new File(filePath);
    }

    /**
     * Write tasks from list into file
     *
     * @param tasks A <code>TaskList</code> object that will be the source to write into the file
     */
    public void writeToTasksFile(TaskList tasks) {
        List<Task> taskList = tasks.getList();
        try {
            FileWriter fileWriter = new FileWriter(tasksFile);
            for (Task task : taskList) {
                String fileString = task.convertTaskToFileString() + "\n";
                fileWriter.write(fileString);
            }
            fileWriter.close();
        } catch (IOException error) {
            System.out.println(error.getMessage());
        }
    }

        /**
         * Read tasks from file into list
         *
         * @param tasks A <code>TaskList</code> object which will be the destination
         *              for each <code>Task</code> read from file.
         * @throws FileNotFoundException if file is not found
         */
        public void readFromTasksFileToList (TaskList tasks) throws FileNotFoundException {
            FileReaderHandler fileReaderHandler = new FileReaderHandler(tasks);
            Scanner sc = new Scanner(tasksFile);
            while (sc.hasNext()) {
                String fileLine = sc.nextLine();
                String[] parts = fileLine.split(" \\| ");

                String taskType = parts[0].toUpperCase();
                boolean isCompleted = parts[1].equals("T");
                String taskString = parts[2];

                fileReaderHandler.readLineFromFileToList(taskType, isCompleted, taskString);
            }

        }


    }
