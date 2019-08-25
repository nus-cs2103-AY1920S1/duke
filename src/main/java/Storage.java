import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filepath;
    private ArrayList<Task> taskList;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> load() throws FileNotFoundException, ParseException {

        ArrayList<Task> taskList = new ArrayList<>();
        File f = new File(filepath); // create a File for the given file path
        Scanner sc = new Scanner(f); // create a ScanneZZ:1q!r using the File as the source
        while (sc.hasNext()) {
            Task t = new Task(""); // dummie task
            String type = sc.next();
            int done = sc.nextInt();
            String name = sc.next();

            switch(type) {
                case "T":
                    t = new Todo(name);
                    break;
                case "D":
                    t = new Deadline(name, sc.nextLine());
                    break;
                case "E":
                    t = new Event(name, sc.nextLine());
                    break;
            }

            if (done == 1) {
                t.markAsDone();
            }
            taskList.add(t);
        }
        this.taskList = taskList;
        return taskList;
    }

     void save() throws IOException {
        FileWriter fw = new FileWriter(filepath);
        for (Task task : taskList) {
            String name = task.getDescription();
            int status = task.getStatus()? 1 : 0;
            if (task instanceof Todo) {
                fw.write("T " + status + " " + name + "\n");
            } else if (task instanceof Deadline) {
                fw.write("D " + status + " " + name + " " + ((Deadline) task).getBy() + "\n");
            } else {
                fw.write("E " + status + " " + name + " " +((Event) task).getAt() + "\n");
            }
        }
        fw.close();
    }
}
