import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class Storage {

    private String filepath;
    private File file;
    private LinkedList<String> previousSaves;

    /**
     * Constructor of the Storage object. This object mainly reads and write information
     * to the save file in the hard disk.
     *
     * @param filepath The file path of the save file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.file = new File(filepath);
        this.previousSaves = new LinkedList<>();
    }

    /**
     * Reads the save file and produces the saved list of tasks.
     *
     * @return An ArrayList of the saved list of tasks.
     * @throws IceBearException Error occurs if the file path is invalid or the file is corrupted.
     */
    public ArrayList<Task> load() throws IceBearException {
        ArrayList<Task> listItems = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            StringBuilder sb = new StringBuilder();
            while (br.ready()) {
                String item = br.readLine();
                Task newTask = Task.createFromFile(item);
                sb.append(item).append("\n");
                listItems.add(newTask);
            }
            previousSaves.add(sb.toString());
            return listItems;
        } catch (IOException e) { // this should not occur as the file is hard-coded.
            throw new IceBearException("Error occurred while loading file, "
                    + "the file path is invalid:" + "\n" + e);
        } catch (IceBearException e) {
            throw new IceBearException("Error occurred while loading file, "
                    + "the file seems to be corrupted." + "\n" + e);
        }
    }

    /**
     * Stores a new task in the save file in a specified format.
     *
     * @param item The String representation of the task specifically to be stored in the list.
     * @throws IceBearException Occurs if the file cannot be found.
     */
    public void addNew(String item) throws IceBearException {
        try {
            FileWriter fw = new FileWriter(new File(filepath), true); // append mode: true
            fw.write(item + "\n");
            fw.close();
            String s = previousSaves.getLast();
            this.previousSaves.add(s + item + "\n");
        } catch (IOException e) {
            throw new IceBearException("Error occurred while adding new task! + \n" + e);
        }
    }

    /**
     * Updates the description of a task in the save file.
     *
     * @param list The entire list of tasks.
     * @throws IceBearException Occurs if the file cannot be found.
     */
    public void updateSaveFile(ArrayList<Task> list) throws IceBearException {
        StringBuilder newFileContent = new StringBuilder();
        for (Task task : list) {
            newFileContent.append(task.toFileString()).append("\n");
        }
        String save = newFileContent.toString();
        update(save);
        this.previousSaves.add(save);
    }

    /**
     * Stores the file content without saving.
     *
     * @param newFileContent the new file content.
     * @throws IceBearException Occurs if the file cannot be found.
     */
    private void update(String newFileContent) throws IceBearException {
        try {
            FileWriter fw = new FileWriter(file);
            fw.write(newFileContent);
            fw.close();
        } catch (IOException e) {
            throw new IceBearException("Error while updating task in save file: "
                    + "File not found!\n" + e);
        }
    }

    /**
     * Undoes the previous action of the user by loading the previous state of the task list.
     *
     * @return The last known state of the task list.
     * @throws IceBearException Exception thrown when there is nothing to undo.
     */
    public ArrayList<Task> undo() throws IceBearException {
        if (this.previousSaves.size() <= 1) {
            throw new IceBearException("There is nothing to undo!");
        }
        this.previousSaves.removeLast();
        String save = this.previousSaves.getLast();
        ArrayList<Task> listItems = new ArrayList<>();
        for (String item : save.split("\n")) {
            if (item.equals("")) {
                break;
            }
            Task newTask = Task.createFromFile(item);
            listItems.add(newTask);
        }
        update(save);
        return listItems;
    }
}
