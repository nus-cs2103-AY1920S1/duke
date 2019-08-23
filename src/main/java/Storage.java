import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws DukeException, IOException {

        File f = new File(filePath);
        // Create a new txt file if it currently does not exist
        f.createNewFile();
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskLst = new ArrayList<Task>();

        while (sc.hasNext()) {
            String s = sc.nextLine();
            String line = s.substring(8);
            if (s.charAt(0) == 'T') {
                taskLst.add(new ToDo(line, s.charAt(4) == '1' ? true: false));
            } else if (s.charAt(0) == 'D') {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '|') {
                        taskLst.add(new Deadline(line.substring(0, i - 1),
                            LocalDateTime.parse(line.substring(i + 2), DateTimeFormatter.ofPattern("d MMMM yyyy, ha")),
                                s.charAt(4) == '1' ? true : false));
                    }
                }
            } else if (s.charAt(0) == 'E') {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '|') {
                        taskLst.add(new Event(line.substring(0, i - 1),
                            LocalDateTime.parse(line.substring(i + 2), DateTimeFormatter.ofPattern("d MMMM yyyy, ha")),
                                s.charAt(4) == '1' ? true : false));
                    }
                }
            } else {
                throw new DukeException("Invalid file format.");
            }
        }

        return taskLst;
    }

    // Saving new task list to the hard disk
    private static void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String textToAdd = "";
        for (Task task : Task.itemsLst) {
            textToAdd += task.fileString() + "\n";
        }
        fw.write(textToAdd);
        fw.close();
    }
}
