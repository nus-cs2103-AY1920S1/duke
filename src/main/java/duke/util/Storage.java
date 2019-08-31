package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String location) {
        this.file = new File(location);
    }

    private void setup() throws DukeException {
        try {
            if (!this.file.exists()) {
                File directory = new File(this.file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                this.file.createNewFile();
            }
        } catch (IOException e) {
            throw new DukeException("☹ OOPS! Errors occurred while setting up database!");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<Task> taskList = new ArrayList<Task>();
            while (sc.hasNextLine()) {
                String[] description = sc.nextLine().split(" \\| ");
                Task task;
                switch (description[0]) {
                case "T":
                    task = new ToDo(description[2]);
                    break;
                case "D":
                    task = new Deadline(description[2], description[3]);
                    break;
                case "E":
                    task = new Event(description[2], description[3]);
                    break;
                default:
                    throw new DukeException("☹ OOPS! Task description invalid!");
                }
                if (description[1].equals("1")) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
            sc.close();
            return taskList;
        } catch (FileNotFoundException e) {
            throw new DukeException("☹ OOPS! File not found!");
        }
    }

    public void store(ArrayList<Task> taskList) throws DukeException {
        try {
            this.setup();
            FileWriter fw = new FileWriter(this.file);
            for (Task task : taskList) {
                fw.write(task.toDataString() + '\n');
            }
            fw.close();
        } catch (IOException e) {
            throw new DukeException("☹ OOPS! Errors occurred while updating database!");
        }
    }
}
