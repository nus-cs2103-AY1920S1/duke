import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a Storage object that reads and writes to files.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Storage {
    protected File file;
    protected String input;
    protected String output;

    /**
     * Creates a Storage object with the path of the file to be read.
     * @param path Is the path of the file to be read.
     */
    public Storage(String path) {
        try {
            this.file = new File(path);
            readInputFile(this.file);
            this.output = "";
        } catch (IOException e) {
            System.out.println("Input: Something serious happened here...");
        }
    }

    /**
     * Returns the input from the file read.
     * @return The input from the file read.
     */
    public String getInput() {
        return this.input;
    }

    /**
     * Reads the input from the file.
     * @param file To read inputs from.
     * @throws IOException If the input of the file is wrong.
     */
    public void readInputFile(File file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder sb = new StringBuilder("");
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line + "\n");
        }
        this.input = sb.toString();
    }

    /**
     * Saves the output of the Storage object into a file.
     * @param tasklist List of tasks to be written into the file.
     */
    public void save(ArrayList<Task> tasklist) {
        this.output = "";
        for (Task t : tasklist) {
            this.output += (t.saveString());
        }
        try {
            File outFile = new File("C:\\Users\\AngKa\\duke\\data\\duke.txt");
            BufferedWriter out = new BufferedWriter(new FileWriter(outFile));
            out.write(output);
            out.close();
        } catch (IOException e) {
            System.out.println("Output: Something serious happened here...");
        }
    }
}
