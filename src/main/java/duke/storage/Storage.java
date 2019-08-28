package duke.storage;

import duke.DukeException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.Scanner;

public class Storage {
    private File file;

    private static final String ERROR_FAILED_TO_READ     = "Failed to read save data. Creating new task list.";
    private static final String ERROR_FAILED_SAVE        = "Failed to save file.";

    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("EEE, d MMM uuuu, hh.mma");

    public Storage(String filePath) {
        file = new File(filePath);
    }

    public String getFilePath() {
        return file.getAbsolutePath();
    }

    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNext()) {
                String[] input = sc.nextLine().split(" [|] ");
                Task task;
                switch (input[0]) {
                case "T":
                    task = new Todo(input[2], input[1].equals("1"));
                    break;
                case "D":
                    task = new Deadline(input[2], LocalDateTime.parse(input[3], DATE_TIME_FORMATTER),
                            input[1].equals("1"));
                    break;
                case "E":
                    task = new Event(input[2], LocalDateTime.parse(input[3], DATE_TIME_FORMATTER),
                            input[1].equals("1"));
                    break;
                default:
                    throw new DukeException(ERROR_FAILED_TO_READ);
                }
                tasks.add(task);
            }
        } catch (FileNotFoundException | DukeException | ArrayIndexOutOfBoundsException
                | DateTimeParseException ex) {
            throw new DukeException(ERROR_FAILED_TO_READ);
        }
        return tasks;
    }

    /**
     * Saves tasks onto disk.
     * Tasks will be saved in the following format: T | 1 | read book.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file.getAbsolutePath());
            for (Task task : tasks) {
                if (task instanceof Deadline) {
                    fw.append(String.format("D | %d | %s | %s\n",
                            task.isDone() ? 1 : 0,
                            task.getDescription(),
                            ((Deadline) task).getDeadline()));
                } else if (task instanceof Event) {
                    fw.append(String.format("E | %d | %s | %s\n",
                            task.isDone() ? 1 : 0,
                            task.getDescription(),
                            ((Event) task).getTime()));
                } else {
                    fw.append(String.format("T | %d | %s\n",
                            task.isDone() ? 1 : 0,
                            task.getDescription()));
                }
            }
            fw.close();
        } catch (IOException ex) {
            throw new DukeException(ERROR_FAILED_SAVE);
        }
    }
}
