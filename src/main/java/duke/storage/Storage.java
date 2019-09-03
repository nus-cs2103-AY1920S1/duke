package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.io.BufferedWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Storage is the class which loads tasks from a hard drive into the <code>Duke</code>
 * application to be processed, and also allows for the saving of tasks into the hard
 * drive when the user terminates the application.
 *
 * @author Clarence Koh
 * @version 1.0
 * @since 29th August 2019
 */
public class Storage {
    /**
     * The file path containing the hard drive from which tasks is read from and
     * into the <code>Duke</code> application. The same file from this file path is
     * updated when the user terminates the application.
     */
    private String filePath;

    /**
     * Class constructor specifying which hard drive to retrieve tasks from and
     * the hard drive to write tasks into after application is terminated.
     *
     * @param filePath The location of the file containing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * This method loads the tasks currently in the hard drive into an
     * <code>ArrayList</code> of tasks that is processed in the application.
     *
     * @return The array of tasks that are currently in the hard drive.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public ArrayList<Task> loadFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>(100);
        try {
            File f = new File(this.filePath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
            } else {
                BufferedReader br = new BufferedReader(new FileReader(f));
                String text;
                while ((text = br.readLine()) != null) {
                    String taskType = text.charAt(1) + "";
                    switch (taskType) {
                    case "T": {
                        Task todo = new Todo(text.substring(7));
                        if (text.substring(4, 5).equals("V")) {
                            todo.markedAsDone();
                        }
                        tasks.add(todo);
                        break;
                    }
                    case "D": {
                        Task deadline = new Deadline(text.substring(7, text.indexOf("by:") - 2),
                                fileTaskDateConverter(text.substring(text.indexOf("by:") + 4, text.length() - 1)));
                        if (text.substring(4, 5).equals("V")) {
                            deadline.markedAsDone();
                        }
                        tasks.add(deadline);
                        break;
                    }
                    case "E": {
                        Task event = new Event(text.substring(7, text.indexOf("at:") - 2),
                                fileTaskDateConverter(text.substring(text.indexOf("at:") + 4, text.length() - 1)));
                        if (text.substring(4, 5).equals("V")) {
                            event.markedAsDone();
                        }
                        tasks.add(event);
                        break;
                    }
                    default: {
                        throw new DukeException("Error occurred, invalid Task type found.");
                    }
                    }
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tasks;
    }

    /**
     * This method formats the date of deadlines and events in the hard disk
     * and reformats it into a <code>Date</code> object.
     *
     * @param date The string input of date to be reformatted.
     * @return The <code>Date</code> in a formatted manner.
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    private Date fileTaskDateConverter(String date) throws DukeException {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd 'of' MMMM yyyy, hh:mm a");
            Date parseDate = formatter.parse(date);
            return parseDate;
        } catch (ParseException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * This method loads the tasks currently in the
     * <code>TaskList</code> of tasks into the hard disk.
     *
     * @throws DukeException If there is a problem with data processing, loading or saving.
     */
    public void saveToFile(ArrayList<Task> listOfInputs) throws DukeException {
        try {
            File f = new File(this.filePath);
            f.getParentFile().mkdirs();
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            for (Task task : listOfInputs) {
                bw.append(task.toString());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("Something went wrong: " + e.getMessage());
        }
    }
}