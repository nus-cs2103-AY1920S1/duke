import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Store the tasks list into a .txt file after User exits the Duke application.
 * It also loads up the tasks list from the selected .txt file when User starts up
 * the Duke application.
 */
public class Storage {
    private String filepath;
    private ArrayList<Task> tasklist;

    /**
     * Load the file contents of the .txt file and to input the saved tasks into
     * an Arraylist.
     * @param filePath Directory of the .txt file.
     * @param storinglist An Arraylist to store the tasks in the .txt save file.
     * @throws FileNotFoundException if there are no .txt file found in the directory
     * @throws DukeException if the .txt file is an empty file
     */
    private static void loadFileContents(String filePath, ArrayList<Task> storinglist)
            throws FileNotFoundException, DukeException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        if (f.length() == 0) {
            throw new DukeException("No listings found!");
        }
        while (s.hasNextLine()) {
            String currentline = s.nextLine();
            String[] splitcurrentline = currentline.split("-");
            if (splitcurrentline[0].equals("D")) {
                storinglist.add(new Deadline(splitcurrentline[2],
                        "by " + splitcurrentline[3]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            } else if (splitcurrentline[0].equals("E")) {
                storinglist.add(new Events(splitcurrentline[2],
                        "at " + splitcurrentline[3]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            } else if (splitcurrentline[0].equals("T"))  {
                storinglist.add(new ToDos(splitcurrentline[2]));
                storinglist.get(storinglist.size()-1).recoverStatus(splitcurrentline[1]);
            }
        }
    }

    /**
     * Save the tasks into the .txt file allocated in the directory.
     * @param filepath Directory for the .txt file.
     * @param tasklist Arraylist of tasks that the User inputs.
     * @throws IOException if there is an error of copying the list to the file.
     */
    private static void WriteTextToFile(String filepath, ArrayList<Task> tasklist)
            throws IOException {

        FileWriter fileOut = new FileWriter(filepath);

        for (int i = 0; i < tasklist.size(); i++) {
            if (i == tasklist.size() - 1) {
                fileOut.write(tasklist.get(i).formatString());
            } else {
                fileOut.write(tasklist.get(i).formatString() + "\n");
            }
        }
            fileOut.close();
    }

    /**
     * Instantiate a Storage object.
     * @param filepath the directory for the allocated .txt file.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
        this.tasklist = new ArrayList<Task>();
    }

    /**
     * A method to save the tasks that is being keyed in by the User
     * @param tasklist the list of tasks User inputs
     */
    public void save(ArrayList<Task> tasklist) {
        try {
            Storage.WriteTextToFile(this.filepath, tasklist);
        } catch (IOException e) {
            System.err.println("Something went wrong!");
        }


    }

    /**
     * A method to load the .txt file and to store the tasks into
     * an ArrayList.
     * @return the Arraylist of tasks.
     * @throws DukeException if there are no tasks found in the .txt file.
     */
    public ArrayList<Task> load() throws DukeException {
        try {
            Storage.loadFileContents(this.filepath, this.tasklist);
        } catch (FileNotFoundException e) {
            System.err.println("No file found, creating new file");
            File file = new File(this.filepath);
        }

        return this.tasklist;
    }

}
