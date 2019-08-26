import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class dealing with storage.
 */
public class Storage {
    private String filePath;

    /**
     * Class constructor specifying the path of file that stores data.
     * @param filePath the path of file that stores data.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns a list of tasks that are stored in disk.
     *
     * @throws FileNotFoundException If the file specified by the filePath is not found.
     * @throws IllegalDescriptionException If the description of tasks is illegal.
     */
    public ArrayList<Task> load() throws FileNotFoundException, IllegalDescriptionException {
        File f = new File(filePath);
        ArrayList<Task> list = new ArrayList<>();

        Scanner in = new Scanner(f);
        while (in.hasNextLine()) {
            String[] str = in.nextLine().split(" \\| ");
            Task task;
            switch (str[0]) {
            case "T":
                task = new ToDo(str[2]);
                break;
            case "E":
                task = new Event(str[2], LocalDateTime.parse(str[3]));
                break;
            case "D":
                task = new Deadline(str[2], LocalDateTime.parse(str[3]));
                break;
            default:
                continue;
            }
            if (str[1].equals("1")) {
                task.setDone();
            }
            list.add(task);
        }
        return list;
    }

    /**
     * Stores tasks in a task list into the file.
     * @param tasks a list of tasks.
     * @throws IOException If  an input or output exception occurred.
     */
    public void store(TaskList tasks) throws IOException{
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < tasks.getSize(); i++) {
            fw.write(tasks.getTaskAtIndex(i + 1).toStringForFile() + "\n");
        }
        fw.close();
    }
}
