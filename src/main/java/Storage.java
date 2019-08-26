import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

public class Storage {
    private String filePath;
    private List<String> savedList;
    private ArrayList<Task> returnTaskList = new ArrayList<>(100);

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    ArrayList<Task> loadSavedList() {
        try {
            File file = new File(filePath);
            //file.createNewFile();
            this.savedList = Files.readAllLines(file.toPath());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
                Calendar taskTime = new GregorianCalendar(Integer.parseInt(taskTimeParsed[2]), Integer.parseInt(taskTimeParsed[1]) - 1,
                        Integer.parseInt(taskTimeParsed[0]), Integer.parseInt(taskTimeParsed[3].substring(0, 2)),
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

    void writeSavedList(ArrayList<Task> workingTaskList) {
        try {
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : workingTaskList) {
                fileWriter.write(task.formattedString());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
