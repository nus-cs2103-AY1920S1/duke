import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    public static String readFile() {
        String contents = "";
        try {
            FileReader reader = new FileReader("data/duke.txt");
            int i;
            while ((i = reader.read()) != -1) {
                contents += (char) i;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return contents;
    }

    public static void saveFile(TaskList tasks) {
        try {
            FileWriter fstream = new FileWriter("data/duke.txt");
            BufferedWriter out = new BufferedWriter(fstream);
            for (int i = 0; i < tasks.getSize(); i++) {
                out.write(tasks.getTask(i).saveString());
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
