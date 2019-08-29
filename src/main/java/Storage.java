import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;

        // create data file if it doesn't exist
        File file = new File(filePath);
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.out.print("");
        }
    }

    public void write(TaskList list) throws DukeException {
        ArrayList<Task> tasks = list.tasks;
        try (FileWriter fileWriter = new FileWriter(filePath, false)) {
            StringBuilder content = new StringBuilder();
            for (Task t : tasks) {
                String type = t instanceof Deadline ? "D"
                        : t instanceof Event ? "E" : "T";
                String isDone = t.isDone ? "1" : "0";
                String taskData = type + "@" + isDone + "@" + t.description;
                content.append(taskData).append("\n");
            }
            fileWriter.write(content.toString());
        } catch (IOException e) {
            throw new DukeException("Cannot write to file");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> list = new ArrayList<Task>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("@");
                Task newTask;
                boolean isDone = parts[1].equals("1");
                switch (parts[0]) {
                    case "T":
                        newTask = new Todo(parts[2], isDone);
                        break;
                    case "E":
                        newTask = new Event(parts[2], isDone);
                        break;
                    case "D":
                        newTask = new Deadline(parts[2], isDone);
                        break;
                    default:
                        newTask = new Task("");
                }
                list.add(newTask);
            }
        } catch (IOException e) {
            throw new DukeException("Cannot load from file");
        }
        return list;
    }
}