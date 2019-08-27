package duke.storage;

import duke.tasks.Task;
import duke.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves tasks in specified path
     *
     * @param tasks
     */
    public void save(String tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        fw.write(tasks);
        fw.close();
    }

    /**
     * Returns tasks as a list from file path
     */

    public List<Task> load() throws FileNotFoundException, ParseException {
        Task t;
        List<Task> tl = new ArrayList<>();
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNextLine()) {
            t = Parser.parseTask(s.nextLine());
            tl.add(t);
        }
        return tl;
    }
}