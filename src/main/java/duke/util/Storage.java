package duke.util;

import duke.task.Task;
import duke.task.TaskList;
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
        this.filepath = filepath;

        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdir();
        }

        assert directory.exists();
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
                Task taskToRead = parseTaskFromLine(line);
                tasks.add(taskToRead);
                line = reader.readLine();
            }
        } catch (IOException | ArrayIndexOutOfBoundsException | NullPointerException e) {
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
     * Parses a String representing a task into a Task object.
     * @param line The String to be parsed into a Task.
     * @return A Task object.
     * @throws DukeException If parsing fails.
     */
    private Task parseTaskFromLine(String line) throws DukeException {
        Task taskToRead = null;
        String[] lineParts = line.split(" ~ ");
        String taskType = lineParts[0];

        switch (taskType) {
        case "T":
            String taskDescription = lineParts[2];
            taskToRead = new ToDo(taskDescription);
            break;
        case "D":
            String[] descriptionAndDeadline = parseDescriptionAndTime(lineParts[2], "by: ");
            taskToRead = new Deadline(
                    descriptionAndDeadline[0],
                    Parser.parseDateTimeString(descriptionAndDeadline[1])
            );
            break;
        case "E":
            String[] descriptionAndTime = parseDescriptionAndTime(lineParts[2], "at: ");
            taskToRead = new Event(
                    descriptionAndTime[0],
                    Parser.parseDateTimeString(descriptionAndTime[1])
            );
            break;
        default:
            throw new DukeException("Unknown task type detected.");
        }

        // check if the task is marked as done
        if (lineParts[1].equals("1")) {
            taskToRead.markDone();
        }

        return taskToRead;
    }

    /**
     * Parses a String describing a task's description and time.
     * @param line The String containing the task's description and time.
     * @param splitRegex The String regex by which to separate the description and time.
     * @return A String array containing: (1) task description (2) task time.
     */
    private String[] parseDescriptionAndTime(String line, String splitRegex) {
        String description =  line.split(splitRegex)[0];
        // remove space and closing bracket
        description = description.substring(0, description.length() - 2);

        String time = line.split(splitRegex)[1];
        // remove closing bracket
        time = time.substring(0, time.length() - 1);

        return new String[] {description, time};
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
        } catch (ArrayIndexOutOfBoundsException | IOException e) {
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
