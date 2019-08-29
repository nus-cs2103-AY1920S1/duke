import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns File object from a string.
     *
     * @param filePath location of the file as a string.
     * @return File object.
     */
    public File getFile(String filePath) {
        File f = new File(filePath);
        return f;
    }

    /**
     * Updates the .txt with the most recent changes.
     *
     * @param filePath location of the file as a string.
     * @param list The existing ArrayList of Tasks after user has made changes to it.
     */
    public void updateTasks(String filePath, ArrayList<Task> list) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        String result = "";
        for (int i = 0; i < list.size(); i++) {
            Task task = list.get(i);
            String c = task.getClass().toString();
            if (c.equals("class Task.Todo")) {
                String description = task.getDescription();
                Boolean isDone = task.getStatus();
                int done = 0;
                if (isDone) {
                    done = 1;
                }
                result += "todo" + ">>" + done + ">>" + description + "\n";
            } else if (c.equals("class Task.Deadline")) {
                Deadline deadline = (Deadline) task;
                String description = deadline.getDescription();
                Boolean isDone = deadline.getStatus();
                String by = Duke.dateToStringConverter(deadline.getBy());
                int done = 0;
                if (isDone) {
                    done = 1;
                }
                result += "deadline" + ">>" + done + ">>" + description + ">>" + by + "\n";
            } else if (c.equals("class Task.Event")) {
                Event event = (Event) task;
                String description = event.getDescription();
                Boolean isDone = event.getStatus();
                String at = Duke.dateToStringConverter(event.getAt());
                int done = 0;
                if (isDone) {
                    done = 1;
                }
                result += "event" + ">>" + done + ">>" + description + ">>" + at + "\n";
            }
        }
        fw.write(result);
        fw.close();
    }

    /**
     * Reads the .txt file where the Tasks are stored and returns
     * an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks
     */

    public ArrayList<Task> getTasks() throws FileNotFoundException, ParseException {
        File file = getFile(filePath);
        Scanner sc = new Scanner(file);
        ArrayList<Task> list = new ArrayList<>();

        while (sc.hasNext()) {
            String line = sc.nextLine();
            String[] strings = line.split(">>");
            String firstWord = strings[0];
            String isDone = strings[1];
            String description = strings[2];
            switch (firstWord) {
                case "todo":
                    Todo task = new Todo(description);
                    if (isDone.equals("1")) {
                        task.doTask();
                    }
                    list.add(task);
                    break;
                case "deadline": {
                    Deadline deadline = new Deadline(description, strings[3]);
                    if (isDone.equals("1")) {
                        deadline.doTask();
                    }
                    list.add(deadline);
                    break;
                }
                case "event": {
                    //split the string by /
                    Event event = new Event(description, strings[3]);
                    if (isDone.equals("1")) {
                        event.doTask();
                    }
                    list.add(event);
                    break;
                }
            }
        }
        return list;
    }
}
