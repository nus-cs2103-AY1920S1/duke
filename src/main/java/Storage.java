import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Writes and loads Task data to the hard drive.
 */
public class Storage {
    /**
     * The file path of the stored data.
     */
    private String filePath;
    
    /**
     * The list of Tasks used as temporary storage.
     * The list is initialized with a capacity of 100.
     */
    private ArrayList<Task> returnTaskList = new ArrayList<>(100);
    
    /**
     * Creates a Storage object.
     *
     * @param filePath The file path of the stored data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     * Loads saved data from a given file path.
     * Saved data must be written in a specific format to be successfully loaded, as specified in this documentation.
     * If no file is detected in the given file path, creates a file at that location.
     *
     * @return Returns the list of Tasks stored in the specified file path location.
     * @throws IOException An Exception thrown to indicate an invalid file path.
     */
    ArrayList<Task> loadSavedList() throws IOException {
        File file = new File(filePath);
        file.createNewFile();
        List<String> savedList = Files.readAllLines(file.toPath());
        for (String line : savedList) {
            String[] lineElements = Parser.parseStoredLine(line);
            String lineType = Parser.parseStoredInstruction(lineElements);
            if (lineType.equals("T")) {
                Task currentTask = new ToDoTask(lineElements[2]);
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
            } else if (lineType.equals("D")) {
                Task currentTask = new DeadlineTask(lineElements[2], Parser.parseStoredTime(lineElements));
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
            } else if (lineType.equals("E")) {
                Task currentTask = new EventTask(lineElements[2], Parser.parseStoredTime(lineElements));
                Parser.parseTaskForMarking(lineElements, currentTask);
                returnTaskList.add(currentTask);
            }
        }
        return returnTaskList;
    }
    
    /**
     * Writes data to a file in the given file path location.
     * Data is pre-formatted to be parsable by the method loadSavedList().
     *
     * @param workingTaskList The inputted list of Tasks to be written to the hard drive.
     * @throws IOException An Exception thrown to indicate an invalid file path.
     */
    void writeSavedList(ArrayList<Task> workingTaskList) throws IOException {
        FileWriter fileWriter = new FileWriter(this.filePath);
        for (Task task : workingTaskList) {
            fileWriter.write(task.formattedString());
        }
        fileWriter.close();
    }
}
