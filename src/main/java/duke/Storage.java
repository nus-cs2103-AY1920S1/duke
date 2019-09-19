package duke;

import duke.task.*;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Encapsulates a storage object to handle file input/output.
 */
public class Storage {
    private File file;
    public static final String DEFAULT_FILEPATH = "/src/main/data/duke.txt";
    public static final String EXECUTABLE_DIRECTORY = "data";

    public Storage(String filepath) {
        File temp = new File(filepath);
        if(temp.exists()) {
            this.file = temp;
        } else {
            File dataDir = new File(EXECUTABLE_DIRECTORY);
            if (!dataDir.exists()) {
                dataDir.mkdir();
            }
            try {
                temp = new File(EXECUTABLE_DIRECTORY, "duke.txt");
                temp.createNewFile();
                this.file = temp;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {

                String instruction = sc.nextLine();
                System.out.println(instruction);
                String[] lines = instruction.split(" \\| ");
                Task task = createTask(lines);

                tasks.add(task);
            }
            return tasks;

        } catch (FileNotFoundException ex) {
            throw new DukeException("File is not found");
        }
    }

    private Task createTask(String[] lines) throws DukeException {
        Task task;
        switch (lines[0]) {
            case "T":
                task = new ToDo(lines[2]);
                break;
            case "D":
                task = new Deadline(lines[2], lines[3]);
                break;
            case "E":
                task = new Event(lines[2], lines[3]);
                break;
            default:
                throw new DukeException("The task does not exist.");
        }

        if (lines[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    public void save(TaskList tasklist) throws DukeException {
        try {
            FileWriter filewriter = new FileWriter(file);

            for (Task task : tasklist.getList()) {
                filewriter.write(task.textFormat());
                filewriter.write(System.lineSeparator());
            }
            filewriter.close();

        } catch (IOException e) {
            throw new DukeException("Failed to save tasks.");
        }
    }
}
