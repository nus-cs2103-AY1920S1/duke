package duke.storage;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the storage manager of Duke which maintains task data in a txt
 * file.
 */
public class Storage {

    private String filePath;

    /**
     * Constructs a storage object.
     * 
     * @param filePath the path to the txt file to be maintained
     */

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads the information from the file specified by the filepath and turns it
     * into an ArrayList of tasks.
     * 
     * @return ArrayList of tasks
     * @throws FileNotFoundException if filepath does not direct to a file
     */

    public ArrayList<Task> load() throws FileNotFoundException {
        File taskFile = new File(filePath);
        Scanner s = new Scanner(taskFile);
        ArrayList<Task> tasksList = new ArrayList<>();
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] details = line.split(" \\| ");
            switch (details[0]) {
            case "T":
                String taskName = " " + details[2];
                Task newTask = new Task(taskName, details[1].equals("1"));
                tasksList.add(newTask);
                break;
            case "D":
                String deadlineName = " " + details[2] + " ";
                String deadlineTime = " " + details[3];
                Deadline deadline = new Deadline(deadlineName, details[1].equals("1"), deadlineTime);
                tasksList.add(deadline);
                break;
            case "E":
                String eventName = " " + details[2] + " ";
                String eventTime = " " + details[3];
                Event event = new Event(eventName, details[1].equals("1"), eventTime);
                tasksList.add(event);
            default:
                System.out.println("Should not reach here.");
            }
        }
        s.close();
        return tasksList;
    }

    /**
     * Adds a Task into the txt file specified by filepath.
     * 
     * @param text String representing tasks in file form
     * @throws IOException if file cannot be found
     */

    public void writeToFile(String text) throws IOException {
        String textToAppend = text;
        File appendingFile = new File(filePath);
        if (appendingFile.length() != 0) {
            textToAppend = System.lineSeparator() + text;
        }
        FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Adds a Task into the txt file specified by the pathName param.
     * 
     * @param text     String representing tasks in file form
     * @param pathName txt file to write to
     * @throws IOException if file cannot be found
     */

    public void writeToFile(String text, String pathName) throws IOException {
        String textToAppend = text;
        File appendingFile = new File(pathName);
        if (appendingFile.length() != 0) {
            textToAppend = System.lineSeparator() + text;
        }
        FileWriter fw = new FileWriter(pathName, true); // create a FileWriter in append mode
        fw.write(textToAppend);
        fw.close();
    }

    /**
     * Updates the txt file with the current TaskList.
     * 
     * @param tasks most recent TaskList
     * @throws IOException if file cannot be found
     */

    public void updateFile(TaskList tasks) throws IOException {
        String tempPath = "C:\\Users\\Khairul\\Desktop\\Computing Resources\\CS2103T\\duke\\data\\temp.txt";
        File tempFile = new File(tempPath);
        tempFile.createNewFile();
        ArrayList<Task> tasksList = tasks.getTasks();
        for (Task task : tasksList) {
            writeToFile(task.toFile(), tempPath);
        }
        Files.copy(Paths.get(tempPath), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);
        Files.delete(Paths.get(tempPath));
    }

}
