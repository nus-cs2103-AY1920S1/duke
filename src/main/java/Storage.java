import java.io.File;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class Storage {
    String filePath;

    /**
     * Storage constructor.
     * @param filePath is the data file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * load() will read the data file.
     * @return will return an ArrayList of Task after reading data file
     */
    public ArrayList<Task> load() {
        try {
            File dataFile = createOrRetrieve(filePath);
            ArrayList<String> stringOfTasks = readFile(dataFile);
            ArrayList<Task> arrOfTasks = new ArrayList<>();

            String keyword = "";
            Boolean isDone = false;
            String description = "";
            String dateAndTime = "";
            String date = "";
            String time = "";
            Task t;

            for (String s : stringOfTasks) {
                String[] cmdList = s.split("\\|");
                keyword = cmdList[0].trim();
                isDone = Boolean.valueOf(cmdList[1].trim());
                description = cmdList[2].trim();
                if (cmdList.length > 3) { //aka got dateAndTime String
                    dateAndTime = cmdList[3].trim();
                    String[] dateTime = dateAndTime.split("\\s*,");
                    date = dateTime[0];
                    time = dateTime[1].trim();
                }
                if (keyword.equalsIgnoreCase("D")) {
                    t = new Deadline(description, date, time);
                } else if (keyword.equalsIgnoreCase("E")) {
                    t = new Event(description, date, time);
                } else { //(keyword.equalsIgnoreCase("T")) {
                    t = new Todo(description);
                }
                if (isDone) {
                    t.markAsDone();
                }
                arrOfTasks.add(t);
            }
            return arrOfTasks;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * createOrRetrieve will create the data file if not exist, or retrieve the existing file.
     * @param filePath is the path of the data file
     * @return will return a File object ref to the data file
     * @throws IOException if cannot be created
     */
    private static File createOrRetrieve(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
        }
        return file;
    }

    /**
     * readFile() will read from data file.
     * @param file is the File object reference
     * @return a List of Task objects after reading from data file
     */
    private static ArrayList<String> readFile(File file) {
        ArrayList<String> stringOfTasks = new ArrayList<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                stringOfTasks.add(line);
            }
            reader.close();
            return stringOfTasks;
        } catch (Exception e) {
            System.err.format("Exception occurred trying to read '%s'.", file);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * save() will save all Task from TaskArrayList into data file.
     * @param taskArrayList is the list of Tasks from duke
     */
    public void save(ArrayList<Task> taskArrayList) {
        File file = new File("./data/duke.txt");
        FileWriter writer;
        try {
            writer = new FileWriter(file);
            PrintWriter printer = new PrintWriter(writer);
            for (Task t : taskArrayList) {
                printer.append(t.toDataFormat() + "\n");
            }
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
