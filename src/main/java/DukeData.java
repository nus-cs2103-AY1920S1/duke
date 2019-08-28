import java.io.*;
import java.util.ArrayList;

/**
 * The DukeData class manages the data of the user's task list
 * updating the data file in the hard disk accordingly.
 */
public class DukeData {
    // every time Duke starts up, create and load a new file
    private File _saveFile;
    private FileWriter _fw;
    private BufferedWriter _bw;
    private static int _count = 0;
    // private static ArrayList<String> _allData = new ArrayList<>();

    /**
     * Generates a new DukeData object which handles creation of a
     * new txt file in the data directory.
     */
    public DukeData() {
        _count++;

        // create a new File in the data file
        File dir = new File("/Users/StudyBuddy/Desktop/CS2103/iP/duke/src/main/data");
        try {
            if (!dir.exists()) {
                dir.mkdir();
            } else {
                String fileName = "myDukeList" + _count + ".txt";
                File tagFile = new File(dir, fileName);
                try {
                    if (!tagFile.exists()) {
                        tagFile.createNewFile();
                    }
                    this._saveFile = tagFile;
                } catch (IOException e) {
                    System.err.println("Data file is null.");
                }
            }

            // attach the file to FileWriter and BufferedWriter
            this._fw = new FileWriter(this._saveFile);
            this._bw = new BufferedWriter(this._fw);
        } catch (IOException e) {
            System.err.println("Cant make Directory.");
        }
    }

    /**
     * Adds and updates the given task to the file saved in the hard disk.
     * @param index the index of the task to be added.
     * @param t Task object which can represent ToDo, Deadline, or Event.
     * @throws IOException
     */
    public void addTask(int index, Task t) throws IOException {
        this._bw.write(index + ". " + t.toData());
        this._bw.newLine();
        this._bw.flush();
    }

    /**
     * Updates the data file in the hard disk by removing the said task.
     * @param index the index of the task to be removed.
     * @throws IOException
     */
    public void removeTask(int index) throws IOException {
        ArrayList<String> updateData = new ArrayList<>();
        char indX = (char) (index + '0');
        BufferedReader br = new BufferedReader(
                new FileReader(this._saveFile));

        // add all lines into temp array except the one to be removed
        String line = br.readLine();
        while (line != null) {
            if (line.charAt(0) != indX) {
                updateData.add(line);
            }
            line = br.readLine();
        }
        br.close();

        // now we replace the file with the new updated data
        FileWriter updatedFW = new FileWriter(this._saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (String s : updateData) {
            updatedBW.write(s);
            updatedBW.newLine();
        }
        updatedBW.flush();
        this._fw = updatedFW;
        this._bw = updatedBW;
    }

    /**
     * Updates the data file in the hard disk by changing
     * the status of the given task.
     * @param index the index of the task from the task list
     * @param t the Task object that has just been updated.
     * @throws IOException
     * @throws FileNotFoundException when the file does not exist.
     */
    public void taskDone(int index, Task t)
            throws IOException, FileNotFoundException {
        ArrayList<String> updateData = new ArrayList<>();
        char indX = (char) (index + '0');
        BufferedReader br = new BufferedReader(
                new FileReader(this._saveFile));

        // add all lines into the temp array,
        // and edit the line which has task marked as done
        String line = br.readLine();
        while (line != null) {
            if (line.charAt(0) != indX) {
                updateData.add(line);
            } else { // add the corrected data line
                updateData.add(indX + ". " + t.toData());
            }
            line = br.readLine();
        }
        br.close();

        // now we replace the file with the new updated data
        FileWriter updatedFW = new FileWriter(this._saveFile);
        BufferedWriter updatedBW = new BufferedWriter(updatedFW);
        for (String s : updateData) {
            updatedBW.write(s);
            updatedBW.newLine();
        }
        updatedBW.flush();
        this._bw = updatedBW;
        this._fw = updatedFW;
    }

    /**
     * Closes the BufferedWriter file upon exit of the Duke program.
     */
    public void exit() throws IOException {
        this._bw.close();
    }

    /**
     * Prints out everything in the data file saved in the hard disk
     */
    public void printDataFile() throws IOException {
        BufferedReader out = new BufferedReader(
                new FileReader(this._saveFile));

        String line = out.readLine();
        while (line != null)  {
            System.out.println(line);
            line = out.readLine();
        }
        out.close();
    }
}
