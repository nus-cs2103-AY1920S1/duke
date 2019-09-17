package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Saved {
    private String filePath;

    public Saved(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> loadData() throws FileNotFoundException, ParseException {
        File file = new File(filePath);
        Scanner scan = new Scanner(file);

        ArrayList<Task> list = new ArrayList<>();

        while(scan.hasNext()) {
            String[] text = scan.nextLine().split(" \\| ", 4);

            Task t;

            switch (text[0]) {
                case "T":
                    t = new Todo(text[2]);
                    break;
                case "D":
                    t = new Deadline(text[2], text[3]);
                    break;
                case "E":
                    t = new Event(text[2], text[3]);
                    break;
                default:
                    t = new Task("");
                    break;
            }

            if (text[1].equals("1")) {
                t.setAsDone();
            }

            list.add(t);
        }
        return list;
    }

    public void saveToFile(ArrayList<Task> list) throws IOException, ParseException {
        FileWriter newFile = new FileWriter(this.filePath);
        String listToFile = "";
        String s;

        for (Task task: list) {
            if (task instanceof Event) {
                s = "E" + " | "+ task.getDone() + " | " + task.getDesc() + " | " + task.getAt();
            } else if (task instanceof Deadline) {
                s = "D | " + task.getDone() + " | " + task.getDesc() + " | " + task.getBy();
            } else {
                s = "T | " + task.getDone() + " | " + task.getDesc();
            }
            newFile.write(s);
            newFile.write("\n");
        }


        newFile.write(listToFile);
        newFile.flush();
        //newFile.close();
    }
}
