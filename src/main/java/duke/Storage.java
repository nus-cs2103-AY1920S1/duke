package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles all local storage for Duke
 */
public class Storage {
    private String path_string;

    /**
     * Creates a new Storage object.
     * The task list will be stored locally at the specified path
     * @param path_string String for the storage path
     */
    public Storage(String path_string) {
        this.path_string = path_string;
    }

    /**
     * Attempts to read the task list at the storage path
     * @return An ArrayList of Strings, corresponding to the task list
     * @throws FileNotFoundException
     */
    public ArrayList<String> read() throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        File f = new File(path_string);
        Scanner sc = new Scanner(f);
        while (sc.hasNextLine()) {
            input.add(sc.nextLine());
        }
        return input;
    }

    /**
     * Attempts to write to the task list at the storage path
     * @throws IOException
     */
    public void write(ArrayList<String> formatStrings) throws IOException {
        Path path = Paths.get(path_string);
        Files.write(path, formatStrings);
    }
}
