import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Storage {
    File file;
    Scanner sFile;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
    FileWriter fw;

    public Storage() {
        file = new File("data/duke.txt");
    }

    public void readFile(TaskList list) {
        if (!file.exists()) {
            return;
        }
        try {
            sFile = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sFile.hasNextLine()) {
            String ln = sFile.nextLine();
            String[] lnSplit = ln.split(",");
            if (lnSplit[0].equals("T")) {
                list.add(new Todo(lnSplit[2], Integer.parseInt(lnSplit[1])));
            } else if (lnSplit[0].equals("E")) {
                list.add(new Event(lnSplit[2], Integer.parseInt(lnSplit[1]), LocalDateTime.parse(lnSplit[3], formatter),
                        LocalDateTime.parse(lnSplit[4], formatter)));
            } else if (lnSplit[0].equals("D")) {
                list.add(new Deadline(lnSplit[2], Integer.parseInt(lnSplit[1]), LocalDateTime.parse(lnSplit[3], formatter)));
            }
        }
    }

    public void writeFile(TaskList list) {
        try {
            file.createNewFile();
            fw = new FileWriter(file);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            for (Task t : list.list) {
                fw.write(t.toFile() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}