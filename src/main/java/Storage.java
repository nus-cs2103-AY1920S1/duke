import Task.Deadline;
import Task.Event;
import Task.Task;
import Task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
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
    public boolean updateTasks(String filePath, ArrayList<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            String result = "";
            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);

                if (task instanceof Todo) {
                    String description = task.getDescription();
                    Boolean isDone = task.getStatus();
                    int done = 0;
                    if (isDone) {
                        done = 1;
                    }
                    result += "todo" + ">>" + done + ">>" + description + "\n";
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    String description = deadline.getDescription();
                    Boolean isDone = deadline.getStatus();
                    String by = dateToStringConverter(deadline.getBy());
                    int done = 0;
                    if (isDone) {
                        done = 1;
                    }
                    result += "deadline" + ">>" + done + ">>" + description + ">>" + by + "\n";
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    String description = event.getDescription();
                    Boolean isDone = event.getStatus();
                    String at = dateToStringConverter(event.getAt());
                    int done = 0;
                    if (isDone) {
                        done = 1;
                    }
                    result += "event" + ">>" + done + ">>" + description + ">>" + at + "\n";
                }
            }
            fw.write(result);
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }

    }

    /**
     * Converts a Date object to a String object in dd/MM/yyyy HHmm format
     *
     * @param date Date object
     * @return String
     */
    public static String dateToStringConverter(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HHmm");
        String sDate = sdf.format(date);
        return sDate;
    }

    /**
     * Reads the .txt file where the Tasks are stored and returns
     * an ArrayList of Tasks.
     *
     * @return ArrayList of Tasks
     */
    public ArrayList<Task> getTasks() throws FileNotFoundException, ParseException {
        assert this.file != null : "file should exist by now";
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
