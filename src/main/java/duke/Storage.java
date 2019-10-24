package duke;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

public class Storage {
    File file;

    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    public void createNew() {
        try {
            file.createNewFile();
        } catch (Exception e) {

        }
    }

    /**
     * Creates the task object from String array and adds it to the ArrayList list.
     *
     */
    public void loadTask(ArrayList<Task> list, String[] oldList) {
        Task t;
        switch (oldList[0].trim()) {
        case "T":
            t = new Todo(oldList[2].trim());
            break;
        case "D":
            t = new Deadline(oldList[2].trim(), oldList[3].trim());
            break;
        default:
            t = new Event(oldList[2].trim(), oldList[3].trim());
            break;
        }
        if (oldList[1].trim().equals("1")) {
            t.markAsDone();
        }
        list.add(t);
    }
    /**
     * Loads the task list from disk.
     *
     * @return list of tasks
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        ArrayList<Task> list = new ArrayList<>();
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] oldList = scanner.nextLine().split(" // ");
            loadTask(list, oldList);
        }
        return list;
    }


    /**
     * This method is used to update current items in the list.
     *
     * @param list the current task list
     */
    public void updateList(ArrayList<Task> list) {
        Collections.sort(list);
        try (PrintWriter out = new PrintWriter(file)) {
            for (int i = 1; i <= list.size(); i++) {
                Task t = list.get(i - 1);
                if (t.getSymbol().equals("T")) {
                    out.println(t.getSymbol() + " // " + (t.isDone() ? 1 : 0) + " // " + t.getDescription()
                            + t.getNotes());
                } else {
                    assert (t.getSymbol().equals("D") || t.getSymbol().equals("E"));
                    out.println(t.getSymbol() + " // " + (t.isDone() ? 1 : 0) + " // " + t.getDescription()
                            + " // " + t.getExtraInfo() + t.getNotes());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
