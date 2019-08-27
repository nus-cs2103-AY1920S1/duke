import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Storage {

    String filepath;
    File file;

    /**
     * Constructor of the Storage object. This object mainly reads and write information
     * to the save file in the hard disk.
     * @param filepath The file path of the save file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
    }

    /**
     * Reads the save file and produces the saved list of tasks.
     * @return An ArrayList of the saved list of tasks.
     * @throws DukeException Error occurs if the file path is invalid or the file is corrupted.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> listItems = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            while (br.ready()) {
                String item = br.readLine();
                Task newTask = Task.createFromFile(item);
                listItems.add(newTask);
            }
            return listItems;
        } catch (IOException e) { // this should not occur as the file is hard-coded.
            throw new DukeException("Error occurred while loading file, "
                    + "the file path is invalid:" + "\n" + e);
        } catch (DukeException e) {
            throw new DukeException("Error occurred while loading file, "
                    + "the file seems to be corrupted." + "\n" + e);
        }
    }

    /**
     * Stores a new task in the save file in a specified format.
     * @param item The String representation of the task specifically to be stored in the list.
     * @throws DukeException Occurs if the file cannot be found.
     */
    public void addNew(String item) throws DukeException {
        try {
            FileWriter fw = new FileWriter(new File(filepath), true); // append mode: true
            fw.write(item + "\n");
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error occurred while adding new task! + \n" + e);
        }
    }

    /**
     * Updates the description of a task in the save file.
     * @param list The entire list of tasks.
     * @throws DukeException Occurs if the file cannot be found.
     */
    void updateSaveFile(ArrayList<Task> list) throws DukeException {
        try {
            StringBuilder newFileContent = new StringBuilder();
            for (Task task : list) {
                newFileContent.append(task.toFileString()).append("\n");
            }
            FileWriter fw = new FileWriter(file);
            fw.write(newFileContent.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Error while updating task in save file: "
                    + "File not found!\n" + e);
        }
    }
}
