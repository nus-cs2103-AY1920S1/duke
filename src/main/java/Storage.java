import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class Storage {

    private Scanner file;
    private String filePath;
    private int index = 0;
    private Ui ui;


    /**
     * Instantiates a new Storage object.
     * Storage object will store the path of the list .txt file.
     * After which, it will scan the list file for the existing tasks.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;
        try {
            // Delimiters are "|" and a new line
            this.file = new Scanner(new File(filePath)).useDelimiter("[|\\n]");
        } catch (Exception e) {
            throw new DukeException("File not found.");
        }
    }

    public String getFilePath() {
        return this.filePath;
    }


    /**
     * Load list of tasks and store into an ArrayList.
     * If file has a filled, loop through entire file.
     * Check for the type of task in each line:
     * Either [T] Todo, [D] Deadline, [E] Event,
     * and create each type of task and store into the list.
     *
     * @return ArrayList of Tasks from the file
     * @throws DukeException If file is empty.
     */
    public ArrayList loadFile() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        try {
            if (!file.hasNext()) { // Check if file is empty
                throw new DukeException("File is empty.");
            }
        } catch (Exception e) {
            throw new DukeException("File is empty.");
        }

        while (file.hasNext()) {
            String action = file.next().trim();
            if (action.equals("T")) {
                String status = file.next();
                String description = file.next().trim();
                list.add(new ToDo(description));
                if (status.contains("1")) {
                    list.get(index).isDone = true;
                }
                index += 1;
            } else {
                String status = file.next();
                String description = file.next().trim();
                String time = file.next().trim();

                if (action.equals("E")) {
                    list.add(new Event(description, time));
                } else if (action.equals("D")) {
                    list.add(new Deadline(description, time));
                }

                if (status.contains("1")) {
                    list.get(index).isDone = true;
                }
                index += 1;
            }

        }
        return list;
    }

    public void closeFile() {
        file.close();
    }


    /**
     * Save the current list of tasks TaskList into a .txt file.
     * Fetch the tasks from the list and output into the .txt
     * line by line and close the file once saved.
     *
     * @param tasks    Contains entire list of tasks objects
     * @param saveFile The file path of where to save the list. By default same as source.
     * @throws DukeException Throws exception if file not found.
     */
    public void saveFile(TaskList tasks, String saveFile) throws DukeException {
        try {
            FileWriter fw = new FileWriter(saveFile);
            BufferedWriter output = new BufferedWriter(fw);
            int size = tasks.getSize();
            for (int i = 0; i < size; i++) {
                output.write(tasks.get(i).toSave());
                output.newLine();
            }
            output.close();
        } catch (Exception e) {
            throw new DukeException("");
        }
    }
}
