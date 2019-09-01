import java.io.*;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;

    /**
     * Constructor for storage that reads and writes to data file.
     * @param filePath where the data file is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Reads data from filepath and creates an arraylist of Task.
     * @return an arraylist of Task from read data
     * @throws IOException file is not found
     */
    public ArrayList<Task> load() throws IOException{
        try {
            File file = new File(filePath);
            BufferedReader in = new BufferedReader(new FileReader(file));
            String line;
            while ((line = in.readLine()) != null) {
                String regex = " " +  "\\|"  + " ";
                String[] data = line.split(regex);
                Task t;
                if (data[0].equals("T")) {
                    t = new Todo(data[2], changeStringToBoolean(data[1]));
                } else if (data[0].equals("D")) {
                    t = new Deadline(data[2], data[3], changeStringToBoolean(data[1]));
                } else {
                    t = new Event(data[2], data[3], changeStringToBoolean(data[1]));
                }
                tasks.add(t);
            }
            in.close();
        } finally {
            System.out.println("Attempting to load from: " + filePath);
            return tasks;

        }
    }

    /**
     * Converts number into boolean. 1 for true and 0 for false.
     * @param number number to be determined
     * @return true or false
     */
    public boolean changeStringToBoolean(String number) {
        return (number.equals("1"));
    }

    /**
     * Writes the data into filepath.
     */
    public void rewriteData() {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (Task t : tasks) {
                fw.write(t.toDataString() + "\n");
            }
            System.out.println("Saving data to: " + filePath);
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
