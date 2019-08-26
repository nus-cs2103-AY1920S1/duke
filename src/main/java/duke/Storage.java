package duke;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Storage object in the Duke. A <code>Storage</code> object corresponds to
 * writing and loading data to a file.
 */
class Storage {
    private String filepath;

    Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Returns the list of tasks saved previously.
     * If there is input output exception, Duke Exception is thrown.
     *
     * @return ArrayList list of tasks.
     * @throws DukeException If there is input output exception.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> aList = new ArrayList<>();
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(filepath));
            while ((line = br.readLine()) != null) {
                String description = line.substring(7);
                if (line.charAt(1) == 'T') {
                    Todo todo = new Todo(description);
                    if (line.charAt(4) == '\u2713') {
                        todo.markDone();
                    }
                    aList.add(todo);
                } else if (line.charAt(1) == 'D') {
                    Deadline deadline = new Deadline(description);
                    if (line.charAt(4) == '\u2713') {
                        deadline.markDone();
                    }
                    aList.add(deadline);
                } else {
                    Event event = new Event(description);
                    if (line.charAt(4) == '\u2713') {
                        event.markDone();
                    }
                    aList.add(event);
                }
            }
            br.close();
        } catch(IOException ex) {
            throw new DukeException(ex.getMessage());
        }
        return aList;
    }
    /**
     * Saves all tasks to the file.
     * If there is input output exception, Duke Exception is thrown.
     *
     * @param tasks  TaskList containing list of tasks
     * @throws DukeException  If there is input output exception.
     */
     void writeToHardDisk(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filepath);
            for(Task t: tasks.taskList) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }

    }
}
