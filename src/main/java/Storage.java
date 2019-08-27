import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;
    private List<String> savedList;
    private ArrayList<Task> returnTaskList = new ArrayList<>(100);

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> loadSavedList() throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        this.savedList = Files.readAllLines(file.toPath());
        for (String line : savedList) {
            String[] lineElements = line.split(" \\| ");
            String lineType = lineElements[0];
            if (lineType.equals("T")) {
                Task currentTask = new ToDoTask(lineElements[2]);
                if (lineElements[1].equals("+")) {
                    currentTask.markAsDone();
                }
                returnTaskList.add(currentTask);
            } else if (lineType.equals("D") || (lineType.equals("E"))) {
                String[] taskTimeParsed = lineElements[3].split("[ /]");
                LocalDateTime taskTime = LocalDateTime.of(Integer.parseInt(taskTimeParsed[2]),
                        Integer.parseInt(taskTimeParsed[1]), Integer.parseInt(taskTimeParsed[0]),
                        Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
                        Integer.parseInt(taskTimeParsed[3].substring(2, 4)));
                if (lineType.equals("D")) {
                    Task currentTask = new DeadlineTask(lineElements[2], taskTime);
                    if (lineElements[1].equals("+")) {
                        currentTask.markAsDone();
                    }
                    returnTaskList.add(currentTask);
                } else {
                    Task currentTask = new EventTask(lineElements[2], taskTime);
                    if (lineElements[1].equals("+")) {
                        currentTask.markAsDone();
                    }
                    returnTaskList.add(currentTask);
                }
            }
        }
        return returnTaskList;
    }

    void writeSavedList(ArrayList<Task> workingTaskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        for (Task task : workingTaskList) {
            fileWriter.write(task.formattedString());
        }
        fileWriter.close();
    }
}
