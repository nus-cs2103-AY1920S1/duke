package duke.module;

import duke.exception.DukeIoException;
import duke.task.Task;
import duke.task.DeadlineTask;
import duke.task.EventTask;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

/**
 * Stores all the user tasks and the status of them. Loads the tasks when Duke is rebooted.
 */
public class Storage {

    private String cwd;
    private String fileName;
    private File savedFile;

    /**
     * Initializes a save file to keep track of user tasks.
     *
     * <p>Creates a new file if it does not exist.
     *
     * <p>Finds the file if it exists.
     *
     * @throws DukeIoException When an error occurs during the input-output process.
     */
    public Storage() throws DukeIoException {
        this.cwd = System.getProperty("user.home");
        this.fileName = "tasks.txt";
        this.savedFile = new File(this.cwd + File.separator + this.fileName);
        try {
            this.savedFile.createNewFile();
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }

    /**
     * Saves the tasks in the given TaskList to the save file initialized.
     *
     * @param taskList {@link TaskList} storing the user tasks.
     * @throws DukeIoException When an error occurs while writing to the saved file.
     */
    public void saveTasks(TaskList taskList) throws DukeIoException {
        assert taskList != null : "Storage.java (line 62) : taskList should not be null";

        // Each task's information delimited by " | "
        StringBuilder lines = new StringBuilder();
        Iterator<Task> it = taskList.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            /* Append type of task,
             * whether the task is finished or not (1 if finished, 0 if not),
             * and the description
             */
            lines.append(task.getType())
                 .append(" | ")
                 .append(task.isDone() ? "1 | " : "0 | ")
                 .append(task.getDescription());
            // Append the date/time (if applicable)
            if (task instanceof DeadlineTask) {
                lines.append(" | ")
                     .append(((DeadlineTask) task).getDueDate());
            } else if (task instanceof EventTask) {
                lines.append(" | ")
                     .append(((EventTask) task).getTime());
            }
            lines.append("\n");
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(this.savedFile));
            bw.write(lines.toString());
            bw.close();
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }

    /**
     * Parses each line in the save file to a {@link Task} and returns it as a List of Tasks.
     *
     * @return a list of Tasks in correspondence to each line of the save file.
     * @throws DukeIoException When an error occurs while reading the save file.
     */
    public List<Task> load() throws DukeIoException {
        try {
            List<Task> lines = new ArrayList<>();
            BufferedReader br = new BufferedReader(new FileReader(this.savedFile));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(Parser.parseToTask(line.replace(" | ", "-")));
            }
            br.close();
            return lines;
        } catch (IOException e) {
            throw new DukeIoException(e.getMessage());
        }
    }

}
