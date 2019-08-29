package duke.storage;

import duke.exception.DukeException;
import duke.task.*;
import duke.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A Storage class loads and writes data into an output file.
 */
public class Storage {
    private String filePath;
    private SimpleDateFormat formatter;

    /**
     * Constructs a new Storage object to read and write to a text file stored
     * on the hard disk.
     *
     * @param filePath the file path of the file stored on the hard disk.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
        this.formatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
    }

    /**
     * Returns the file path of the file stored on the hard disk.
     *
     * @return the file path.
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * Loads the file from the hard disk and reads it's text. The text would then
     * be converted into Task objects and stored in an ArrayList for duke to process.
     *
     * @return an ArrayList that contains the tasks stored on the hard disk.
     * @throws DukeException if the file stated in the file path does not exist.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split(" \\| ");
                switch (line[0]) {
                case "T":
                Todo todo = new Todo(line[2]);
                if (line[1].equals("1")) {
                    todo.markDone();
                    tasks.add(todo);
                } else {
                    tasks.add(todo);
                }
                break;
                case "D":
                Deadline deadline = new Deadline(line[2], Parser.parseDate(formatter, line[3]));
                if (line[1].equals("1")) {
                    deadline.markDone();
                    tasks.add(deadline);
                } else {
                    tasks.add(deadline);
                }
                break;
                case "E":
                Event event = new Event(line[2], Parser.parseDate(formatter, line[3]));
                if (line[1].equals("1")) {
                    event.markDone();
                    tasks.add(event);
                } else {
                    tasks.add(event);
                }
                break;
                default:
                break;
                }
            }
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeException("The file stated in the file path cannot be found.");
        }
    }

    /**
     * Writes the TaskList onto the file stored on the hard disk. The TaskList is gone
     * through and the tasks stored in it are concatenated into a String before it is
     * written to the file stored on the hard disk.
     *
     * @param taskList The TaskList that will be written to the file on the hard disk.
     */
    public void write(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(filePath);
            boolean first = true;
            String textToAdd = "";

            for (Task task : taskList.getTaskList()) {
                if (first) {
                    textToAdd = task.formatToWrite();
                    first = false;
                } else {
                    textToAdd = String.format("%s\n%s", textToAdd, task.formatToWrite());
                }
            }
            System.out.print(textToAdd);
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
