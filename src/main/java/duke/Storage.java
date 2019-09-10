package duke;

import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Class to handle opening and saving of files.
 */
public class Storage {
    private File file;

    public Storage(String filePath) {
        file = new File(filePath);
    }

    /**
     * Reads the file with the given filePath and saves it as a LinkedList to be
     * processed by the TaskList. Creates the relevant directory and files if none are present.
     *
     * @return LinkedList which contains the list of tasks in the saved format
     * @throws DukeException File not found DukeException
     */
    LinkedList<String> load() throws DukeException {
        LinkedList<String> lst = new LinkedList<>();

        try {
            Scanner fs = new Scanner(file);
            while (fs.hasNext()) {
                String next = fs.nextLine();
                lst.addLast(next);
            }
        } catch (Exception e) {
            file.getParentFile().mkdir();
            throw new DukeException("File not found");
        }

        return lst;
    }

    /**
     * Saves the LinkedList element by element separated by a lineSeparator
     * into the given filePath.
     *
     * @param lst The LinkedList to be saved
     * @throws DukeException General DukeException
     */
    public void save(LinkedList<String> lst) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            for (String task : lst) {
                fw.write(task + System.lineSeparator());
            }
            fw.close();

            assert file.exists() == true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
