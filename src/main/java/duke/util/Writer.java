package duke.util;

import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.TaskType;
import duke.error.InvalidFileException;
import java.util.List;
import java.util.ArrayList;
//CHECKSTYLE:OFF - Doing this because Paths need the * import
import java.nio.file.*;
//CHECKSTYLE:ON
import java.lang.IndexOutOfBoundsException;
import java.io.IOException;

public class Writer {
    private Path path;
    
    /**
     * Constructor.
     */
    public Writer(String path) {
        this.path = Paths.get(path);
    }  
    
    /**
     * Write data to file and assumes that this destination is fixed.
     * Also overwrite file if it already exists.
     * @param data List of Task
     * @throws IOException if an error appears
     */
    public void writeFile(List<Task> data) throws IOException {
        String dataString = encodeData(data);
        Files.write(this.path, 
                    dataString.getBytes(), 
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Read data from file.
     * @return List of Task
     * @throws IOException if an error appears
     */
    public List<Task> readFile() throws IOException {
        return this.decodeData(Files.readAllLines(this.path));
    }

    /**
     * Transform the Task List into Strings.
     * @param data List of Task
     * @return String
     */
    private String encodeData(List<Task> data) {
        String result = "";
        for (Task task: data) {
            String name = task.getName();
            TaskType type = task.getType();
            String isDone = task.getIsDone() ? "1" : "0";
            String timeUnformatted = task.getTime().orElse("");
            String time = timeUnformatted.equals("") 
                            ? "" 
                            : String.format(" | %s", timeUnformatted);
            result += String.format("%s | %s | %s%s\n", type, isDone, name, time);
        }
        return result;
    }

    /**
     * Decode the data.
     * @param data List of String
     * @return List of Task
     */
    private List<Task> decodeData(List<String> data) {
        List<Task> result = new ArrayList<>();
        for (String line: data) {
            try {
                // Need special character here or the split will mess up
                String[] lineArr = line.split(" \\| ");
                switch (lineArr[0]) {
                case "EVENT":
                    result.add(new Event(lineArr[2], lineArr[3], lineArr[1].equals("1")));
                    break;
                case "TODO":
                    result.add(new ToDo(lineArr[2], lineArr[1].equals("1")));
                    break;
                case "DEADLINE":
                    result.add(new Deadline(lineArr[2], lineArr[3], lineArr[1].equals("1")));
                    break;
                default:
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error decoding data from file");
            }            
        } 
        return result;
    } 
}
