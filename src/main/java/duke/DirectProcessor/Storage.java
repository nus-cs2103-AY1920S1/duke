package duke.DirectProcessor;

import duke.DukeException;
import duke.Tasks.Deadline;
import duke.Tasks.Event;
import duke.Tasks.Task;
import duke.Tasks.Todo;

import java.io.*;
import java.util.ArrayList;

/**
 * This class is in charge of reloading previous task list from the hard disk.
 *  It reads the task list from the file from a given file name,
 *  In this file, each line is a task.
 *  Its only method is static, so there is no necessity to initialize one.
 */
public class Storage {

    private static BufferedWriter recorder;
    private static BufferedReader reader;

    /**
     * Reload the task list as an array list of tasks from the target file.
     * @param fileName The path of the file.
     * @return an array list of tasks saved in the file.
     * @throws IOException If loading process is wrong.
     * @throws DukeException If the message read from the file is invalid, although not likely.
     */
    public static ArrayList<Task> reload(String fileName) throws IOException, DukeException {
        reader = new BufferedReader(new FileReader(fileName));
        ArrayList<Task> toReturn = new ArrayList<>();
        if (reader == null) return toReturn;
        String line = reader.readLine();
        while (line != null) {
            String[] lineComponents = line.split("\\|");
            boolean finished;
            if (lineComponents[1].equals("0")) finished = false;
            else finished = true;
            String taskname = lineComponents[2];
            if (lineComponents[0].equals("T")) {
                Task toAdd = new Todo(taskname);
                if (finished) toAdd.setAsFinish();
                toReturn.add(toAdd);
            } else if (lineComponents[0].equals("D")) {
                String tasktime = lineComponents[3];
                Task toAdd = new Deadline(taskname, tasktime);
                if (finished) toAdd.setAsFinish();
                toReturn.add(toAdd);
            } else {
                String tasktime = lineComponents[3];
                Task toAdd = new Event(taskname, tasktime);
                if (finished) toAdd.setAsFinish();
                toReturn.add(toAdd);
            }
            line = reader.readLine();
        }
        reader.close();
        return toReturn;
    }
}
