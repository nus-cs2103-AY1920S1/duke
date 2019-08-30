import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> loadSavedList() throws IOException {
        ArrayList<Task> returnTaskList = new ArrayList<>(100);
        File file = new File(filePath);
        file.createNewFile();
        List<String> savedList = Files.readAllLines(file.toPath());
        for (String line : savedList) {
            String[] lineElements = Parser.parseStoredLine(line);
            String lineType = Parser.parseStoredInstruction(lineElements);
            Task currentTask;
            switch (lineType) {
            case "T":
                currentTask = new ToDoTask(lineElements[2]);
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
                break;
            case "D":
                currentTask = new DeadlineTask(lineElements[2], Parser.parseStoredTime(lineElements));
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
                break;
            case "E":
                currentTask = new EventTask(lineElements[2], Parser.parseStoredTime(lineElements));
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
                break;
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
