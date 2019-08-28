import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileHandle {
    public static void appendToFile(String filepath, List<Task> tasks) {
        try {
            Path p = Paths.get(filepath);
            FileWriter fr = new FileWriter(p.toRealPath().toString());
            fr.write("");
            fr.flush();
            fr.close();

            fr = new FileWriter(new File(filepath), true);
            BufferedWriter br = new BufferedWriter(fr);
            //for each task, br.write("data");
            //descr, id, iscompleted
            for (Task t : tasks) {
                String details = String.format("%s | %d |%s\r\n",
                        t.getTaskType(), boolToInt(t.isCompleted()), t.getDescription());
                br.write(details);
            }

            br.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static int boolToInt(boolean b) {
        return b ? 1 : 0;
    }
}
