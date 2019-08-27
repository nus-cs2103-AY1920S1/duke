import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public LinkedList<String> load() throws DukeException {
        LinkedList<String> lst = new LinkedList<>();

        try {
            Scanner fs = new Scanner(file);
            while (fs.hasNext()) {
                String next = fs.nextLine();
                lst.addLast(next);
            }
        } catch (Exception e) {
//            e.printStackTrace();
            file.getParentFile().mkdir();
            throw new DukeException("File not found");
        }

        return lst;
    }

    public void save(LinkedList<String> lst) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (String task : lst) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
