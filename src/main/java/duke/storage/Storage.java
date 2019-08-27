package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.util.DateUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String pathname) {
        this.file = new File(pathname);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] params = input.split("\\s\\|\\s");
                String type = params[0];
                Boolean done = params[1].equals("1");
                String description = params[2];

                Task task;
                switch (type) {
                case "D":
                    Date by = DateUtil.parse(params[3]);
                    task = new Deadline(description, by);
                    break;

                case "E":
                    Date at = DateUtil.parse(params[3]);
                    task = new Event(description, at);
                    break;

                case "T":
                    task = new ToDo(description);
                    break;

                default:
                    throw new DukeException("Invalid parameter specified");
                }

                if (done) {
                    task.markAsDone();
                }

                tasks.add(task);
            }
            return tasks;
        } catch (FileNotFoundException e) {
            file.getParentFile().mkdirs();
            try {
                FileWriter fw = new FileWriter(file);
                fw.close();
                throw new DukeException("Storage file did not exist");
            } catch (IOException ex) {
                throw new DukeException("An error occurred when setting up the storage file: " + ex.getMessage());
            }
        }
    }

    public void save(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred when saving data to storage");
        }
    }

    public void append(String data) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file, true);
            fw.append(data);
            fw.close();
        } catch (IOException e) {
            throw new DukeException("An error occurred while appending data to storage");
        }
    }
}
