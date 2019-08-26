import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    SimpleDateFormat dateFormatter = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyyy");
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);
        Scanner fileSc;
        if (!file.canRead()) {
            throw new FileNotFoundException();
        }
        fileSc = new Scanner(file);
        while (fileSc.hasNextLine()) {
            try {
                String[] nextLine = fileSc.nextLine().strip().split("`");
                Task newTask;
                boolean isDone = nextLine[1].equals("1");
                switch (nextLine[0]) {
                case ("T"):
                    newTask = new ToDo(nextLine[2], isDone);
                    break;
                case ("D"):
                    newTask = new Deadline(nextLine[2], isDone, dateFormatter.parse(nextLine[3]));
                    break;
                case ("E"):
                    newTask = new Event(nextLine[2], isDone, dateFormatter.parse(nextLine[3]));
                    break;
                default:
                    newTask = new ToDo("Unknown task");
                    break;
                }
                tasks.add(newTask);
            } catch (ParseException e) {
                System.out.println(e.toString());
            }
        }
        fileSc.close();
        return tasks;
    }
}
