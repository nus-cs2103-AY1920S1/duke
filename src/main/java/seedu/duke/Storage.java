package seedu.duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    protected static String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public static ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> arrlist = new ArrayList<>();
        try {
            findFile();
        } catch (IOException e) {
            Ui.showError(e.getMessage());
        }
        File f = new File(filepath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String full = sc.nextLine();
            String[] arr = full.split(" . ");
            Task t;
            String type = arr[0];
            int done = Integer.parseInt(arr[1]);
            if (type.equals("T")) {
                t = new ToDo(arr[2]);
            } else {
                long time = Long.parseLong(arr[3]);
                Date date = new Date(time);
                if (type.equals("D")) {
                    t = new Deadline(arr[2], date);
                } else {
                    t = new Event(arr[2], date);
                }
            }
            if (done == 1) {
                t.done();
            }
            arrlist.add(t);
        }
        return arrlist;
    }

    public static void findFile() throws IOException {
        File f = new File(filepath);
        f.getParentFile().mkdir();
        f.createNewFile();
    }

    public static void update(boolean append, String str, TaskList t) throws IOException {
        FileWriter fw;
        if (append == false) {
            fw = new FileWriter(filepath);
            String tasks = "";
            for (Task task : t.list) {
                int done = task.isDone ? 1 : 0;
                tasks += task.getType() + " . " + done + " . " + task.getFullDescription() + "\n";
            }
            fw.write(tasks);
            fw.close();
        } else {
            fw = new FileWriter(filepath, true);
            fw.append(str);
            fw.close();
        }
    }
}