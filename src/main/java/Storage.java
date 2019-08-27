import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(filePath);
        ArrayList<Task> tasks = new ArrayList<>();
        Scanner sc = new Scanner(f);
        while(sc.hasNextLine()) {
            String s = sc.nextLine();
            String[] data = s.split(" \\| ");
            String command = data[0];
            if (command.equals("T")) {
                Todo t = new Todo(data[2]);
                if (Integer.parseInt(data[1]) == 1) t.setDone();
                tasks.add(t);
            } else if (command.equals("D")) {
                Deadline d = new Deadline(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) d.setDone();
                tasks.add(d);
            } else {
                Event e = new Event(data[2], data[3]);
                if (Integer.parseInt(data[1]) == 1) e.setDone();
                tasks.add(e);
            }
        }
        return tasks;
    }

    public void appendToFile(String textToAppend) {
        try {
            FileWriter fw = new FileWriter(filePath, true); // create a FileWriter in append mode
            fw.write(textToAppend);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    public void rewriteFile(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String textToWrite = "";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                String type = t.getType();
                if (type.equals("todo")) {
                    String s = "T | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + '\n';
                    textToWrite += s;
                } else if (type.equals("event")) {
                    String s = "E | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                } else {
                    String s = "D | ";
                    if (t.getStatus()) {
                        s += "1";
                    } else {
                        s += "0";
                    }
                    s += " | " + t + " | " + t.getDate() + '\n';
                    textToWrite += s;
                }
            }
            fw.write(textToWrite);
            fw.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
