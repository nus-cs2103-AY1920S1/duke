package duke.io;

import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Interface used by duke to interact with files.
 */
public class Storage {
    private String filePath;
    private File file;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Loads all data from file into task list.
     * @param ui User-Interface for display/messages.
     * @return TaskList for Duke.
     */
    public TaskList loadTasks(Ui ui) {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] code = scanner.nextLine().split("\\|");
                taskList.add(Parser.init(code));
            }
            scanner.close();
            ui.list(taskList);
        } catch (FileNotFoundException ex) {
            ui.out("You do not have any outstanding tasks.");
        } catch (ParseException ex) {
            ui.out("There was an error with the data file.");
        }
        return taskList;
    }

    /**
     * Writes all data from task list to file.
     * @param taskList from Duke.
     */
    public void writeTasks(TaskList taskList) {
        try {
            if (taskList.isEmpty() && file.exists()) {
                Files.delete(Paths.get(filePath));
            } else if (!taskList.isEmpty()) {
                StringBuilder allTasks = new StringBuilder();
                for (int i = 0; i < taskList.size(); i++) {
                    allTasks.append(taskList.get(i).store()).append(System.lineSeparator());
                }
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(allTasks.toString());
                fileWriter.close();
            }
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
