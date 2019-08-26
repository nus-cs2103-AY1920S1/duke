import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File file;

    public Storage(String path) {
        this.filePath = path;
        file = new File(path);
    }

    public String load() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(file);
        StringBuilder myBuilder = new StringBuilder();
        while (fileScanner.hasNext()) {
            myBuilder.append(fileScanner.nextLine() + "\n");
        }
        return myBuilder.toString();
    }

    public void save(TaskList tasks) throws DukeException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter writer = new FileWriter(filePath);
            for (Task t : tasks.getTaskList()) {
                writer.write(t.toExportFormat() + "\n");
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
    }
}
