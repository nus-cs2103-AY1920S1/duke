import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;


public class Storage {
    private File file;
    private TaskList taskList;


    Storage(TaskList taskList) {
        this.taskList = taskList;
        this.file = new File("data/jermi.txt");
        this.fileToTaskList();
    }

    private Task fileFormatToTask(String fileFormat) {
        Task task = null;
        String[] components = fileFormat.split("\\|");

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

    private void fileToTaskList() {
        try {
            List<String> lines = Files.readAllLines(this.file.toPath());
            for (String line : lines) {
                this.taskList.add(this.fileFormatToTask(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
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
