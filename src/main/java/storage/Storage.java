package parser;

import converter.StringDateConverter;
import task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (s.hasNext()) {
            String[] output = s.nextLine().split(" \\| ");
            boolean isDone;
            if (output[1].equals("Done")) {
                isDone = true;
            }
            else {
                isDone = false;
            }
            Date date = null;
            String description = output[2].trim();
            if (output.length > 3) {
                try {
                    StringDateConverter converter = new StringDateConverter();
                    date = converter.convertLongStringToDate(output[3]);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
            switch (output[0].trim()) {
            case "T":
                tasks.add(new ToDo(description, isDone));
                break;
            case "D":
                tasks.add(new Deadline(description, date, isDone));
                break;
            case "E":
                tasks.add(new Event(description, date, isDone));
                break;
            }
        }
        return tasks;
    }

    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task : tasks.getTasks()) {
            String description, textToAdd;
            Date date = null;
            textToAdd = "";
            description = task.getDescription();
            if (task instanceof Deadline) {
                textToAdd += "D";
                Deadline deadline = (Deadline) task;
                date = deadline.getBy();
            } else if (task instanceof Event) {
                textToAdd += "E";
                Event event = (Event) task;
                date = event.getAt();
            } else {
                textToAdd += "T";
            }
            if (task.getDoneIcon().equals("\u2713")) {
                textToAdd += " | Done";
            } else {
                textToAdd += " | Not done";
            }
            textToAdd += " | " + description.trim();
            if (date != null) {
                textToAdd += " | " + date;
            }
            fw.write(textToAdd + "\r\n");
        }

        fw.close();
    }
}
