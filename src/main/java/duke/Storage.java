package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private String filepath;

    /**
     * Instantiates Storage to load/save tasks to the disk.
     * @param filepath The directory which holds the file this object will load/save tasks from/to.
     */
    public Storage(String filepath) {
        this.filepath = System.getProperty("user.dir") + filepath;
    }

    /**
     * Reads tasks from a disk file into a virtual list.
     * @return A Task ArrayList of the tasks from the disk file.
     * @throws DukeException If the reading of the disk file fails.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        File file = new File(filepath);
        if (!file.exists()) {
            return tasks;
        }

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(file));

            String line = reader.readLine();
            while (line != null) {
                Task taskToRead = null;

                // get task type and description
                String[] lineArray = line.split(" ~ ");
                String taskType = lineArray[0];
                String taskDescription;

                switch (taskType) {
                case "T":
                    taskDescription = lineArray[2];
                    taskToRead = new ToDo(taskDescription);
                    break;
                case "D":
                    // get description
                    taskDescription = lineArray[2].split("by: ")[0];
                    // remove space and closing bracket
                    taskDescription = taskDescription.substring(0, taskDescription.length() - 2);

                    // get deadline
                    String deadline = lineArray[2].split("by: ")[1];
                    // remove closing bracket
                    deadline = deadline.substring(0, deadline.length() - 1);

                    // read task with deadline
                    taskToRead = new Deadline(taskDescription, Parser.parseDateTimeString(deadline));
                    break;
                case "E":
                    // get description
                    taskDescription = lineArray[2].split("at: ")[0];
                    // remove space and closing bracket
                    taskDescription = taskDescription.substring(0, taskDescription.length() - 2);

                    // get time
                    String time = lineArray[2].split("at: ")[1];
                    // remove closing bracket
                    time = time.substring(0, time.length() - 1);

                    // read task with deadline
                    taskToRead = new Event(taskDescription, Parser.parseDateTimeString(time));
                    break;
                default:
                    throw new DukeException("Unknown task type detected.");
                }

                // check if the task is marked as done
                if (lineArray[1].equals("1")) {
                    taskToRead.markDone();
                }

                tasks.add(taskToRead);

                line = reader.readLine();
            }
        } catch (Exception e) {
            throw new DukeException(e.getMessage());
        } finally {
            // close reader
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        return tasks;
    }

    /**
     * Writes tasks to a disk file from the app's virtual task list.
     * @param tasks A TaskList representing the app's virtual task list.
     * @throws DukeException If the writing to the disk file fails.
     */
    public void save(TaskList tasks) throws DukeException {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(filepath));

            // empty file before writing
            FileWriter overwrite = new FileWriter(filepath);
            overwrite.close();

            // write all tasks in virtual list to file
            for (int i = 1; i <= tasks.size(); i++) {
                Task task = tasks.get(i);

                // task string structured as [D][+] description (by: datetime)
                char type = task.toString().toCharArray()[1];
                char done = task.toString().toCharArray()[4] == '+' ? '1' : '0';
                String description = task.toString().split("] ", 2)[1];

                // write task to file
                writer.write(String.format("%s ~ %s ~ %s", type, done, description));
                writer.newLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            throw new DukeException("Failed to save task list to file.");
        } finally {
            // close writer
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }
}
