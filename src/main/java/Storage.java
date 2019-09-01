import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File tasksFile;

    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.tasksFile = new File(this.filePath);
        if (!this.tasksFile.exists()) {
            //the file does not exist yet
            this.tasksFile.getParentFile().mkdirs();
            this.tasksFile.createNewFile();
            this.tasksFile = new File(this.filePath);
        }
    }

    public TaskList load() throws DukeException, IOException {
        Scanner fileScanner = new Scanner(this.tasksFile);
        TaskList tasks = new TaskList();
        while (fileScanner.hasNext()) {
            tasks.addTaskToList(Parser.parseStoredMessage(fileScanner.nextLine()));
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(tasks.listAllTasksAsString());
        fileWriter.close();
    }
}
