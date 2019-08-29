import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents a storage of the task list.
 */
public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Retrieve the list of tasks from local file.
     * @return A list of current tasks in record.
     */
    public List<Task> load() {
        Path file = Paths.get(filePath);
        List<Task> tasks = new LinkedList<>();
        try {
            Scanner fileSc = new Scanner(file).useDelimiter("\\||\\n");
            while (fileSc.hasNext()) {
                String line = fileSc.nextLine();
                String[] lineBreakUp = line.split(" \\| ");
                switch (lineBreakUp[0]) {
                    case "T":
                        tasks.add(new toDo(lineBreakUp[2], lineBreakUp[1].equals("1")));
                        break;
                    case "D":
                        tasks.add(new Deadline(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    case "E":
                        tasks.add(new Event(lineBreakUp[2], lineBreakUp[3], lineBreakUp[1].equals("1")));
                        break;
                    default:
                        System.out.println("wrong input from file");
                }
            }
        } catch (IOException exp) {
            System.out.println("ioExeption caught when loading file!");
        }
        System.out.println("Here are your current tasks:");
        for (Task t : tasks) {
            System.out.println(t);
        }
        return tasks;
    }

    /**
     * Saves the current task list to local file.
     * @param taskList the list of tasks.
     */
    public void save(TaskList taskList) {
        Path file = Paths.get(filePath);
        try {
            List<String> lines = taskList.toSave();
            Files.write(file, lines, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            System.out.println("error when saving");
        }
    }
}
