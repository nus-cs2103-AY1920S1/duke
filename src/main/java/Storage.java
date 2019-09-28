import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;
    private BufferedReader reader;

    public Storage(String filePath) {
        this.filePath = filePath;
        try {
            reader = new BufferedReader(new FileReader("duke.txt"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] readData = line.split(" \\| ");
                Task newTask = null;
                if (readData[0].equals("T")) {
                    newTask = new ToDo(readData[1], readData[2]);
                } else if (readData[0].equals("D")) {
                    newTask = new Deadline(readData[1], readData[2], readData[3]);
                } else if (readData[0].equals("E")) {
                    newTask = new Event(readData[1], readData[2], readData[3]);
                }
                if (newTask != null) {
                    tasks.add(newTask);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return tasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("duke.txt"));
            for (Task task : tasks) {
                char type = task.getTaskType();
                String status = (task.getStatusIcon().equals("\u2713")) ? "1" : "0";
                String description = task.getDescription();
                String date = (type == 'T') ? "" : " | " + task.getDate();

                writer.write(type + " | " + status + " | " + description + date);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
