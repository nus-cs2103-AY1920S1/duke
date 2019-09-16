package duke.storage;

import duke.util.Parser;
import duke.util.Formatter;
import duke.task.Task;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.ArrayList;

/**
 * A class that handles data file of the program.
 */
public class Storage {
    private File file;

    /**
     * Initialize a Storage object with a file path. A file object will be created
     * base on the file path given. If the file does not exist, create a file.
     * @param filePath The file path to the storage file.
     * @throws IOException If there is an error for io.
     */
    public Storage(String filePath) throws IOException {
        this.file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    /**
     * After a command is executed, the tasks in the list might be changed. The method
     * uses PrintWriter to iterate through the updated tasks list to write the most updated
     * information to the file.
     * @param list The TaskList that contains the current tasks.
     * @throws FileNotFoundException When the file cannot be located.
     */
    public void updateFile(TaskList list) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(file);
        for (Task task : list.getList()) {
            pw.println(Formatter.formatTaskForWriting(task));
        }
        pw.close();
    }

    /**
     * Read from the data file and parse the information to create corresponding
     * task objects.
     * @return An ArrayList that contains all the task objects created from data file information.
     * @throws IOException If file cannot be located.
     * @throws ParseException If the information cannot be parsed.
     */
    public ArrayList<Task> readFile() throws ParseException, IOException {
        ArrayList<Task> result = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(file));
        assert br != null;
        String st;
        while ((st = br.readLine()) != null) {
            result.add(Parser.parseTaskString(st));
        }
        return result;
    }

}