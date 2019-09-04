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
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner s = new Scanner(diskList);

            while (s.hasNext()) {
                String[] savedTasks = s.nextLine().split(" \\| ");

                switch (savedTasks[0]) {
                case "T":
                    Todo t = new Todo(savedTasks[2]);
                    if (savedTasks[1].equals("1")) {
                        t.setDone();
                    }
                    tasks.add(t);
                    break;
                case "D":
                    Deadline d = new Deadline(savedTasks[2], savedTasks[3]);
                    if (savedTasks[1].equals("1")) {
                        d.setDone();
                    }
                    tasks.add(d);
                    break;
                case "E":
                    Event e = new Event(savedTasks[2], savedTasks[3]);
                    if (savedTasks[1].equals("1")) {
                        e.setDone();
                    }
                    tasks.add(e);
                    break;
                }
            }
        } catch (Exception e) {
            throw new DukeException();
        }

        return tasks;
    }

    public void overWrite(ArrayList<Task> tasks) {

        try {
            diskList.createNewFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        boolean first = true;

        for (Task t : tasks) {
            String s = t.formatString();

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