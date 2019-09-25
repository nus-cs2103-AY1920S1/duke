package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 *
 * @author scwaterbear
 */
public class Storage {

    private File file;

    /**
     * Class constructor.
     *
     * @param filePath location of file to load and store data.
     */
    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Loads a list of tasks from a file. If no file is present, a new file is created and an empty list is returned.
     *
     * @return List list of tasks.
     * @throws IOException If there has been IOException while reading from the file.
     * @throws DukeException If there has been corrupted data in the file.
     */
    public List<Task> load() throws IOException, DukeException {

        List<Task> tasks = new ArrayList<>(100);
            if (!file.createNewFile()) {

                //read file contents into List
                FileReader fr = new FileReader(file);
                BufferedReader br = new BufferedReader(fr);
                String line;

                while ((line = br.readLine()) != null) {
                    String[] lines = line.split(" \\| ");
                    boolean isDone;
                    if (lines[1].equals("1")) {
                        isDone = true;
                    } else {
                        isDone = false;
                    }
                    if (lines[0].equals("T")) {
                        tasks.add(new Todo(lines[2], isDone));
                    } else if (lines[0].equals("D")) {
                        tasks.add(new Deadline(lines[2], lines[3], isDone));
                    } else if (lines[0].equals("E")) {
                        tasks.add(new Event(lines[2], lines[3], isDone));
                    } else {
                        throw new DukeException("Corrupted data in file.");
                    }
                }

                br.close();
                fr.close();
            }

        return tasks;
    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks a list of tasks.
     * @throws DukeException If there has been IOException while performing an operation on the file.
     */
    public void saveDataToFile(List<Task> tasks) throws IOException {
        //write to a completely new file
            FileWriter fw = new FileWriter(file, false);
            for (Task task : tasks) {
                String s = task.toDataFormat();
                fw.write(s + "\n");
            }

            fw.close();
    }

}
