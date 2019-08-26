package duke.util;

import java.util.List;
import java.util.ArrayList;
//CHECKSTYLE:OFF - Doing this because Paths need the * import
import java.nio.file.*;
//CHECKSTYLE:ON
import java.lang.IndexOutOfBoundsException;
import java.io.IOException;
import java.text.ParseException;
import duke.TaskList;
import duke.task.Task;
import duke.task.Event;
import duke.task.Deadline;
import duke.task.ToDo;
import duke.task.TaskType;

public class Storage {
    private Path path;
    
    /**
     * Constructor.
     */
    public Storage(String path) {
        this.path = Paths.get(path);
    }  
    
    /**
     * Save data to file and assumes that this destination is fixed.
     * Also overwrite file if it already exists.
     * @param data List of Task
     * @throws IOException if an error appears
     */
    public void save(TaskList data) throws IOException {
        String dataString = encodeData(data);
        Files.write(this.path, 
                    dataString.getBytes(), 
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
    }

    /**
     * Load data from file.
     * @return List of Task
     * @throws IOException if an error appears
     */
    public TaskList load() throws IOException {
        return this.decodeData(Files.readAllLines(this.path));
    }

    /**
     * Transform the Task List into Strings.
     * @param data List of Task
     * @return String
     */
    private String encodeData(TaskList data) {
        String result = "";
        for (int i = 1; i <= data.getSize(); i++) {
            Task task = data.get(i);
            String name = task.getName();
            TaskType type = task.getType();
            String isDone = task.getIsDone() ? "1" : "0";
            String time = task.getTime().isPresent()
                            ? String.format(" | %s", task.getTime().get())
                            : "";
            result += String.format("%s | %s | %s%s\n", type, isDone, name, time);
        }
        return result;
    }

    /**
     * Decode the data.
     * @param data List of String
     * @return List of Task
     */
    private TaskList decodeData(List<String> data) {
        TaskList result = new TaskList();
        for (String line: data) {
            try {
                // Need special character here or the split will mess up
                String[] lineArr = line.split(" \\| ");
                switch (lineArr[0]) {
                case "EVENT":
                    result.add(new Event(lineArr[2],
                               DateUtil.parseFileStringToDate(lineArr[3]),
                               lineArr[1].equals("1")));
                    break;
                case "TODO":
                    result.add(new ToDo(lineArr[2], lineArr[1].equals("1")));
                    break;
                case "DEADLINE":
                    result.add(new Deadline(lineArr[2], 
                               DateUtil.parseFileStringToDate(lineArr[3]), 
                               lineArr[1].equals("1")));
                    break;
                default:
                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Error decoding data from file");
            } catch (ParseException e) {
                System.out.println("Error decoding the date from the file");
            }            
        } 
        return result;
    } 
}
