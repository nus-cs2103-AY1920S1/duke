package duke.storage;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Event;
import duke.task.Deadline;

import duke.ui.Ui;

import duke.exception.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Storage {

    String filePath;
    Ui ui;

    /**
     * Constructor to create a Storage object.
     *
     * @param filePath The directory of our database.
     * @param ui The Ui object we are currently using.
     */
    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    /**
     * Gets the current task list in the database if any.
     *
     * @return Returns a list of tasks.
     */
    public List<Task> load() {
        File file = new File(filePath);
        List<Task> list = new LinkedList<>();

        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task;
                String line = sc.nextLine();
                String description = line.substring(4);
                if (line.substring(0, 1).equals("T")) {
                    task = new Todo(description);
                } else {
                    String[] temp = description.split(" \\| ");
                    if (line.substring(0, 1).equals("D")) {
                        task = Deadline.storedDeadline(temp[0], temp[1]);
                    } else {
                        task = Event.storedEvent(temp[0], temp[1]);
                    }
                }
                if (line.substring(2, 3).equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Data file does not exist.");
        }
        return list;
    }

    /**
     * Updates the database.
     *
     * @param list The most updated list of tasks.
     */
    public void updateData(List<Task> list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task task: list) {
                StringBuilder sb = new StringBuilder();
                if (task instanceof Todo) {
                    sb.append("T ");
                } else if (task instanceof Deadline) {
                    sb.append("D ");
                } else {
                    sb.append("E ");
                }
                if (task.isCompleted()) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
                sb.append(task.getDescription());
                fw.write(sb.toString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            ui.showError("Data file does not exist.");
        }
    }
}
