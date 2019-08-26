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

    public Storage(String filePath, Ui ui) {
        this.filePath = filePath;
        this.ui = ui;
    }

    public List<Task> load() {

        File file = new File(filePath);
        List<Task> list = new LinkedList<>();

        try {
            Scanner sc = new Scanner(file);
            // read existing tasks into the list.
            while (sc.hasNext()) {
                Task task;
                String line = sc.nextLine();
                String description = line.substring(4);
                if (line.substring(0, 1).equals("T")) {
                    task = new Todo(description);
                } else {
                    String[] temp = description.split(" \\| ");
                    if (line.substring(0, 1).equals("D")) {
                        task = new Deadline(temp[0], temp[1]);
                    } else {
                        task = new Event(temp[0], temp[1]);
                    }
                }
                if (line.substring(2, 3).equals("1")) {
                    task.markAsDone();
                }
                list.add(task);
            }
        } catch (FileNotFoundException e) {
            ui.showError("Data file does not exist.");
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }

        return list;
    }

    public void updateData(List<Task> list) {

        try {
            FileWriter fw = new FileWriter(filePath);
            while (!list.isEmpty()) {
                Task task = list.remove(0);
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
        } finally {
        }
    }
}
