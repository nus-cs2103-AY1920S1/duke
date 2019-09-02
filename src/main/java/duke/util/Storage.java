package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private File storage;
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
        storage = new File(filepath);
    }

    public ArrayList<Task> load() throws DukeException {
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner fileScanner = new Scanner(storage);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                taskList.add(convertToTask(task));
            }
            return taskList;
        } catch (FileNotFoundException err) {
            throw new DukeException("File not found!");
        }
    }

    void writeToFile(ArrayList<Task> taskList) throws DukeException {
        StringBuilder toSave = new StringBuilder();
        for (Task task : taskList) {
            toSave.append(task.saveData().append("\n"));
        }
        try {
            FileWriter fw = new FileWriter(filepath);
            fw.write(toSave.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("    File path not defined!");
        }
    }

    private Task convertToTask(String task) throws DukeException {
        String[] taskComponents = task.split("\\|");
        String taskType = taskComponents[0];
        boolean isDone = taskComponents[1].equals("1");
        String description = taskComponents[2];
        switch (taskType) {
        case "T":
            Task todo = new Todo(description);
            if (isDone) {
                todo.markAsDone();
            }
            return todo;
        case "E":
            String eventTime = taskComponents[3];
            Task event = new Event(description, eventTime);
            if (isDone) {
                event.markAsDone();
            }
            return event;
        case "D":
            String deadlineTime = taskComponents[3];
            Task deadline = new Deadline(description, deadlineTime);
            if (isDone) {
                deadline.markAsDone();
            }
            return deadline;
        default:
            throw new DukeException("Unrecognised data type in task.txt!");
        }
    }
}
