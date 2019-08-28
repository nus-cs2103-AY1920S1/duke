import java.io.*;
import java.util.ArrayList;

public class Storage {
    private ArrayList<Task> tasks = new ArrayList<>();
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public boolean changeStringToBoolean(String number) {
        return (number.equals("1"));
    }

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
