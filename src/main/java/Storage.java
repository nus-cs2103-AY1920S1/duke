import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class Storage {
    public static void save(List<Task> tasks) throws IOException {
        String directoryName = "/home/yuan/cs2103t/duke/data";
        File directory = new File(directoryName);
        directory.mkdir();
        FileWriter writer = new FileWriter(directoryName + "/duke.txt");
        StringJoiner sj = new StringJoiner("\n");
        for (Task task : tasks) {
            sj.add(task.getSaveString());
        }
        writer.write(sj.toString());
        writer.close();
    }

    public static List<Task> load() throws DukeException, IOException {
        String filePath = "/home/yuan/cs2103t/duke/data/duke.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        List<Task> tasks = new ArrayList<>();
        String line = null;
        while ((line = reader.readLine()) != null) {
            switch (line.charAt(0)) {
            case 'T':
                Todo todo = Todo.parseSaveString(line);
                tasks.add(todo);
                break;
            case 'D':
                Deadline deadline = Deadline.parseSaveString(line);
                tasks.add(deadline);
                break;
            case 'E':
                Event event = Event.parseSaveString(line);
                tasks.add(event);
                break;
            default:
                throw new DukeException("Unknown save file format");
            }
        }
        return tasks;
    }
}
