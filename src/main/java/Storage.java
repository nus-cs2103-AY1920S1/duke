import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (s.hasNext()) {
            String[] output = s.nextLine().split(" \\| ");
            boolean isDone;
            if (output[1].equals("Done")) {
                isDone = true;
            }
            else {
                isDone = false;
            }
            Date date = null;
            String description = output[2];
            if (output.length > 3) {
                try {
                    StringDateConverter converter = new StringDateConverter();
                    date = converter.convertLongStringToDate(output[3]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (output[0]) {
            case "T":
                tasks.add(new ToDo(description));
                break;
            case "D":
                tasks.add(new Deadline(description, date, isDone));
                break;
            case "E":
                tasks.add(new Event(description, date, isDone));
                break;
            }
        }
        return tasks;
    }

}
