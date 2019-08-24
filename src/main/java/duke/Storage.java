package duke;

import duke.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class Storage {

    private final String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList loadTasks() throws FileNotFoundException, ParseException {
        TaskList tasks = new TaskList();
        File file = new File(this.filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            Task task = Parser.convertSavedTextToTask(scanner.nextLine());
            tasks.add(task);
        }
        return tasks;
    }

    public void saveTasks(TaskList tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        fileWriter.write(Parser.convertTasksToSavedText(tasks));
        fileWriter.close();
    }

}
