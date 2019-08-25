import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Database {

    static final String FILEPATH = "data/duke.txt";

    public Database() {}

    public static List<Task> retrieveData() {

        File file = new File(FILEPATH);
        List<Task> list = new LinkedList<>();

        try {
            Scanner sc = new Scanner(file);
            // read existing tasks into the list.
            while (sc.hasNext()) {
                Task task;
                String line = sc.nextLine();
                String description = line.substring(4);
                if (line.substring(0, 1).equals("T")) {
                    task = new Todo(description);
                } else {
                    String[] temp = description.split(" \\| ");
                    if (line.substring(0, 1).equals("D")) {
                        task = new Deadline(temp[0], temp[1]);
                    } else {
                        task = new Event(temp[0], temp[1]);
                    }
                }
                if (line.substring(2, 3).equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Data file does not exist.");
        } catch (DukeException e) {
            System.err.println(e);
        }

        return list;
    }

    public static void updateData(List<Task> list) {

        try {
            FileWriter fw = new FileWriter(FILEPATH);
            while (!list.isEmpty()) {
                Task task = list.remove(0);
                StringBuilder sb = new StringBuilder();
                if (task instanceof Todo) {
                    sb.append("T ");
                } else if (task instanceof Deadline) {
                    sb.append("D ");
                } else {
                    sb.append("E ");
                }
                if (task.getIsDone()) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
                sb.append(task.getDescription());
                fw.write(sb.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.err.println("Data file does not exist.");
        } finally {
        }
    }
}
