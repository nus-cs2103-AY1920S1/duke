import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public final class DataManager {
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

    public static List<Task> load() throws IOException {
        FileReader reader = new FileReader("../../data/duke.txt");
        reader.close();
        return new ArrayList<>();
    }
}
