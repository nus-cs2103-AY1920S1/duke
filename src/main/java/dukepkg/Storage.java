package dukepkg;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static String filePath;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    public static ArrayList<Task> loadList() throws FileNotFoundException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            String arr[] = line.split(" # ");
            Task t;
            if (arr.length == 3) {
                t = new Todo(arr[2]);
            } else if (arr[0].equals("D")) {
                t = new Deadline(arr[2], arr[3]);
            } else {
                t = new Event(arr[2], arr[3]);
            }
            if (Integer.parseInt(arr[1]) == 1) {
                t.markDone();
            }
            tasks.add(t);
        }
        return tasks;
    }

   public static void saveList(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath, false);
        for (Task t : tasks) {
            String s = (t instanceof Todo) ? "T # " : ((t instanceof Event) ? "E # " : "D # ");
            s += t.isDone() ? "1 # " : "0 # ";
            s += t.getTask();
            s += (t instanceof Todo) ? "" : ((t instanceof Event) ? " # " + ((Event) t).getAt() : " # " + ((Deadline) t).getBy());

            fw.write(s + "\n");
        }
        fw.close();
    }
}
