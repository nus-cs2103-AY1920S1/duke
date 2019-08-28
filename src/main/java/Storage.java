import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File diskList;

    public Storage(String filePath) {
        diskList = new File(filePath);
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            Scanner s = new Scanner(diskList);
            while(s.hasNext()) {
                String[] savedTask = s.nextLine().split(" \\| ");

                switch (savedTask[0]) {
                case "T":
                    Todo t = new Todo(savedTask[2]);
                    if (savedTask[1].equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(savedTask[2], savedTask[3]);
                    if (savedTask[1].equals("1")) {
                        d.markAsDone();
                    }
                    taskList.add(d);
                    break;
                case "E":
                    Event e = new Event(savedTask[2], savedTask[3]);
                    if (savedTask[1].equals("1")) {
                        e.markAsDone();
                    }
                    taskList.add(e);
                    break;
                }
            }

        } catch (Exception e) {
            throw new DukeException();
        }

        return taskList;
    }

    public void overWrite(ArrayList<Task> taskList) {
        try {
            diskList.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Task t : taskList) {
            String s = t.formattedString();

            if (first) {
                sb.append(s);
                first = false;
            } else {
                sb.append(String.format("\n%s", s));
            }
        }

        try {
            FileWriter fw = new FileWriter(diskList);
            fw.write(sb.toString());
            fw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}