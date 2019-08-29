import java.io.File;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {
    private File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void updateFile(TaskList list) throws Exception {
        PrintWriter pw = new PrintWriter(file);
        for (Task task : list.getList()) {
            pw.println(Formatter.formatTaskForWriting(task));
        }
        pw.close();
    }

    public ArrayList<Task> readFile() throws Exception {
        ArrayList<Task> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        while ((st = br.readLine()) != null) {
            result.add(Parser.parseTaskString(st));
        }
        return result;
    }

}