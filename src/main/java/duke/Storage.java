package duke;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Storage class deals with loading tasks from the file and saving tasks in the file.
 *
 * @author scwaterbear
 */
public class Storage {

    private File f;

    /**
     * Class constructor.
     *
     * @param filePath location of file to load and store data.
     */
    public Storage(String filePath) {
        f = new File(filePath);
    }

    /**
     * Loads a list of tasks from a file.
     * If no file is present, a new file is created and an empty list is returned.
     *
     * @return List<Task> list of tasks.
     * @throws DukeException  If there has been IOException while performing an operation on the file.
     */
    public List<Task> load() throws DukeException {

        List<Task> tasks = new ArrayList<>(100);
        try {
            if (!f.createNewFile()) {
//                System.out.println("New file created");
//            } else {
//                System.out.println("File already exists");

                //read file contents into List
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String line;



                while((line = br.readLine()) != null){
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
                        tasks.add(new Event(lines[2],lines[3], isDone));
                    } else {
                        System.out.println("Corrupted data.");
                    }
                }

                br.close();
                fr.close();
            }
        } catch (IOException e) {
            throw new DukeException("There has been an IOException while creating the data file.");
        }
        return tasks;
    }

    /**
     * Saves a list of tasks to a file.
     *
     * @param tasks a list of tasks.
     * @throws DukeException If there has been IOException while performing an operation on the file.
     */
    public void saveDataToFile(List<Task> tasks) throws DukeException {
        //write to a completely new file
        try {
            FileWriter fw = new FileWriter(f, false);
            Iterator<Task> itr = tasks.iterator();
            while (itr.hasNext()) {
                String s = itr.next().dataFormat();
                fw.write(s + "\n");
            }

            fw.close();
        } catch (IOException e) {
            throw new DukeException("Unable to write to datafile.");
        }
    }

}
