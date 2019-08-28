import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Storage {
    protected String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() {
        ArrayList<Task> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(this.filePath));
            String read;
            while ((read = reader.readLine()) != null) {
                System.out.println(read);
                String type = read.substring(1, 2);
                String status = read.substring(4, 5);
                String info = read.substring(7);

                Task task;
                if (type.equals("T")) {
                    task = new ToDo(info.trim());
                    if (status.equals("\u2713")) {
                        task.markAsDone();
                    }
                    list.add(task);
                } else if (type.equals("E")) {
                    String[] infoArr = info.split("(:at)");
                    String description = infoArr[0].trim();
                    String at = infoArr[1].substring(1, infoArr[1].length());
                    task = new Event(description, at);
                    if (status.equals("\u2713")) {
                        task.markAsDone();
                    }
                    list.add(task);
                } else if (type.equals("D")) {
                    String[] infoArr = info.split("(:by)");
                    String description = infoArr[0].trim();
                    String by = infoArr[1].substring(1, infoArr[1].length());
                    task = new Deadline(description, by);
                    if (status.equals("\u2713")) {
                        task.markAsDone();
                    }
                    list.add(task);
                }
            }
        } catch (Exception e) {
            System.out.print(e);
        }
        return list;
    }
}