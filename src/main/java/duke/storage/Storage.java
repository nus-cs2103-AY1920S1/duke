package duke.storage;

import duke.command.CommandCentre;
import duke.exception.DukeException;
import duke.task.Task;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Todo;
import duke.parser.Parser;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

/**
 * A Storage class loads and writes data into an output file.
 */
public class Storage {
    private String dataFilePath;
    private String aliasFilePath = "data/alias.txt";

    /**
     * Constructs a new Storage object to read and write to a text file stored
     * on the hard disk.
     *
     * @param dataFilePath the file path of the file stored on the hard disk.
     */
    public Storage(String dataFilePath) {
        this.dataFilePath = dataFilePath;
    }

    /**
     * Returns the file path of the file stored on the hard disk.
     *
     * @return the file path.
     */
    public String getDataFilePath() {
        return dataFilePath;
    }

    /**
     * Loads the file from the hard disk and reads it's text. The text would then
     * be converted into Task objects and stored in an ArrayList for duke to process.
     *
     * @return an ArrayList that contains the tasks stored on the hard disk.
     * @throws DukeException if the file stated in the file path does not exist.
     */
    public ArrayList<Task> loadData() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(dataFilePath);
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
                    Deadline deadline = new Deadline(line[2], Parser.parseDate(line[3]));
                    if (line[1].equals("1")) {
                        deadline.markDone();
                        tasks.add(deadline);
                    } else {
                        tasks.add(deadline);
                    }
                    break;
                case "E":
                    Event event = new Event(line[2], Parser.parseDate(line[3]));
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
    public void writeData(TaskList taskList) {
        try {
            FileWriter fw = new FileWriter(dataFilePath);
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
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Loads the list of aliases and adds the aliases to the CommandCenter.
     *
     * @throws DukeException if the file stated in the filepath does not exist.
     */
    public void loadAlias() throws DukeException {
        try {
            File file = new File(aliasFilePath);
            Scanner sc = new Scanner(file);

            while(sc.hasNext()) {
                String line = sc.nextLine();
                String[] input = {line};
                CommandCentre.getCommand("alias").execute(Optional.of(input));
                CommandCentre.addAlias(line);
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("The file of aliases cannot be found.");
        }
    }

    /**
     * Writes the list of aliases onto the file stored on the hard disk.
     */
    public void writeAlias() {
        try {
            FileWriter fw = new FileWriter(aliasFilePath);
            boolean first = true;
            String textToAdd = "";

            for (String alias : CommandCentre.getAliases()) {
                if (first) {
                    textToAdd = alias;
                    first = false;
                } else {
                    textToAdd = String.format("%s\n%s", textToAdd, alias);
                }
            }
            fw.write(textToAdd);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
