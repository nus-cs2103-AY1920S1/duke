import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Represents a Storage object that reads and writes to files.
 * @author Ang Kai Qi
 * @version 0.1.3
 */
public class Storage {
    protected File file;
    protected String output;

    /**
     * Creates a Storage object with the path of the file to be read.
     */
    public Storage() {
        String folderPath = System.getProperty("user.dir") + File.separator + "data";
        File directory = new File(folderPath);
        directory.mkdirs();
        String filePath = System.getProperty("user.dir") + File.separator + "data" + File.separator + "duke.txt";
        this.file = new File(filePath);
        this.output = "";
    }

    /**
     * Reads the input from the file.
     * @throws IOException If the input of the file is wrong.
     */
    public ArrayList<Task> readInputFile() {
        try {
            ArrayList<Task> savedTaskList = new ArrayList<Task>();
            String line;

            BufferedReader br = new BufferedReader(new FileReader(this.file));
            while ((line = br.readLine()) != null) {
                savedTaskList.add(parse(line.trim()));
            }
            return savedTaskList;
        } catch (IOException ioe) {
            return new ArrayList<Task>();
        }
    }

    private Task parse(String line) {
        String[] lineArr = line.split(" \\| ");
        final String TASK = lineArr[0];
        final boolean COMPLETED = lineArr[1].equals("1") ? true : false;
        final String DESCRIPTION = lineArr[2];
        final String DATE = TASK.equals("T") ? null : lineArr[3];
        switch (TASK) {
        case "T":
            Task todo = new ToDo(DESCRIPTION);
            if (COMPLETED) {
                todo.setDone();
            }
            return todo;
        case "D":
            Task deadline = new Deadline(DESCRIPTION, DATE);
            if (COMPLETED) {
                deadline.setDone();
            }
            return deadline;
        case "E":
            Task event = new Event(DESCRIPTION, DATE);
            if (COMPLETED) {
                event.setDone();
            }
            return event;
        }
        return null;
    }

    /**
     * Saves the output of the Storage object into a file.
     * @param tasklist List of tasks to be written into the file.
     */
    public void save(ArrayList<Task> tasklist) throws IOException {
        this.file.createNewFile();
        BufferedWriter bw = new BufferedWriter(new FileWriter(this.file));
        this.output = "";
        for (Task t : tasklist) {
            this.output += t.saveString();
        }
        bw.write(output);
        bw.close();
    }
}
