import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Storage {
    private File file;
    private TaskList taskList;

    Storage(String pathName, TaskList taskList) throws IOException, EmptyDescriptionException {
        this.taskList = taskList;
        this.file = new File(pathName);
        this.fileToTaskList();
    }

    private Task fileFormatToTask(String fileFormat) throws EmptyDescriptionException {
        Task task = null;
        String[] components = fileFormat.split("\\|");

        switch (components[0]) {
        case "T":
            task = new ToDo(components[2], components[1]);
            break;
        case "D":
            task = new Deadline(components[2], components[3], components[1]);
            break;
        case "E":
            task = new Event(components[2], components[3], components[1]);
            break;
        }
        return task;
    }

    private void fileToTaskList() throws EmptyDescriptionException, IOException {
        List<String> lines = Files.readAllLines(this.file.toPath());
        for (String line : lines) {
            this.taskList.add(this.fileFormatToTask(line));
        }
    }

    void taskListToFile() throws IOException {
        String toWrite = "";
        for (Task task : this.taskList.getList()) {
            toWrite += task.toSaveFormat();
            toWrite += "\n";
        }
        Files.writeString(this.file.toPath(), toWrite);
    }




}
