import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;
    private File fileData;
    private Scanner fileScanner;

    public Storage(String filePath) {
        this.filePath = filePath;

        try {
            this.fileData = new File(this.filePath);

            if (!this.fileData.getParentFile().exists()) {
                this.fileData.getParentFile().mkdirs();
            }

            if (!this.fileData.exists()) {
                this.fileData.createNewFile();
            }

            this.fileScanner = new Scanner(this.fileData);
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        while (this.fileScanner.hasNext()) {
            tasks.add(Parser.getTaskFromLine(this.fileScanner.nextLine()));
        }

        this.fileScanner.close();
        return tasks;
    }

    public void save(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(this.fileData, false);
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < tasks.getTasks().size(); i++) {
                Task task = tasks.get(i);
                sb.append(task.getFileLine());
                sb.append('\n');
            }

            fw.write(sb.toString());
            fw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
