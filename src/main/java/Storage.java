import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String load() throws DukeException {
        try {
            File f = new File(filePath);
            f.createNewFile();
            Scanner sc = new Scanner(f);
            StringBuilder sb = new StringBuilder(sc.nextLine());

            while (sc.hasNextLine()) {
                sb.append("\n" + sc.nextLine());
            }
            return sb.toString();
        } catch (Exception e) {
            throw new DukeException("File not found");
        }
    }

    public void writeToFile(TaskList list) {
        try {
            FileWriter fileWriter = new FileWriter("data/duke.txt", false);
            for (Task t : list.getTaskList()) {
                String task = t.getFormattedString() + "\n";
                fileWriter.write(task);
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
