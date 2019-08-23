import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;


public class Storage {
    private File file;
    private TaskList taskList;


    Storage(TaskList taskList) throws IOException {
        this.taskList = taskList;
        this.file = new File("../../../data/jermi.txt");
        this.fileToTaskList();
    }

    private Task fileFormatToTask(String fileFormat) {
        Task task = null;
        String[] components = fileFormat.split("|");

        switch (components[0]) {
        case "T":
            task = new ToDo(components[1], components[2]);
            break;
        case "D":
            task = new Deadline(components[1], components[2], components[3]);
            break;
        case "E":
            task = new Event(components[1], components[2], components[3]);
            break;
        }
        return task;
    }

    private void fileToTaskList() throws IOException {
        List<String> lines = Files.readAllLines(this.file.toPath());
        for (String line : lines) {
            this.taskList.add(this.fileFormatToTask(line));
        }
    }

    void taskListToFile() throws IOException {
        Files.writeString(this.file.toPath(), this.taskList
                .getList()
                .stream()
                .map(Task::toSaveFormat)
                .reduce((x, y) -> x + "\n" + y)
                .orElse(""));
    }




}
