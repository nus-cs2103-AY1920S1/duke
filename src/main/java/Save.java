/**
 * This is the Save class. It is used to save the information from the list into a text
 * file.
 * @author Hua Lun
 */

import java.util.ArrayList;
import java.util.Formatter;

public class Save {
    private Formatter saveFile;

    /**
     * <p>
     *     openFile is used to open the specific text file.
     * </p>
     */

    public void openFile() {
        try {
            saveFile = new Formatter("text.txt");
        } catch (Exception e) {
            System.out.println("you have an error");
        }
    }

    /**
     * <p>
     *     addRecords adds all the task found in the list to the text file.
     * </p>
     * @param a the list of tasks
     */

    public void addRecords(ArrayList<TaskList> a) {
        StringBuilder addString = new StringBuilder();
        for (TaskList t : a) {
            addString.append(t);
            addString.append("\n");
        }
        addString.trimToSize();
        saveFile.format(addString.toString());
    }

    /**
     * <p>
     *     closeFile closes the text file after addRecords is complete.
     * </p>
     */

    public void closeFile() {
        saveFile.close();
    }

    /**
     * saveFile opens the file, adds the list of tasks into the text file and closes it.
     * @param s Save object
     * @param a list of task
     */

    public void saveFile(Save s, ArrayList<TaskList> a) {
        s.openFile();
        s.addRecords(a);
        s.closeFile();
    }

}
