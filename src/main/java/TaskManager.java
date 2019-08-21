import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TaskManager {
    private List<Task> taskList;
    private String fileName;

    public TaskManager(String fileName) throws DukeException {
        this.taskList = new ArrayList<>();
        this.fileName = fileName;
        readTasks();
    }

    public void addTask(Task task) throws DukeException {
        taskList.add(task);
        saveTasks();
    }

    public void deleteTask(int index) throws DukeException {
        taskList.remove(index);
        saveTasks();
    }

    public Task getTask(int index) {
        if (index < 0 || index >= taskList.size()) {
            return null;
        }

        return taskList.get(index);
    }

    public void printTasks() {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            String output = String.format(" %d.%s", i + 1, taskList.get(i));
            System.out.println(output);
        }
    }

    public void saveTasks() throws DukeException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
            for (Task task : taskList) {
                String output = String.format("%s|%b|%s", task.getType(), task.getDoneStatus(), task.getDescription());
                if (task instanceof Deadline) {
                    SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HHmm");
                    output += "|" + formatter.format(((Deadline)task).getBy());
                } else if (task instanceof Event) {
                    output += "|" + ((Event)task).getAt();
                }

                output += "\n";

                writer.write(output);
            }
        } catch (IOException e) {
            throw new IODukeException("Error opening file for saving");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public void readTasks() throws DukeException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(fileName));

            String line = null;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length < 3) {
                    throw new IODukeException("The task file is corrupted");
                }

                Task task = null;
                boolean done = Boolean.parseBoolean(tokens[1]);
                String description = tokens[2];
                SimpleDateFormat formatter = new SimpleDateFormat("d/M/yyyy HHmm");

                switch (tokens[0]) {
                case "T":
                    task = new Todo(description, done);
                    break;
                case "D":
                    String date = tokens[3];
                    task = new Deadline(description, formatter.parse(date));
                    break;
                case "E":
                    task = new Event(description, tokens[3]);
                    break;
                default:
                    throw new IODukeException("Invalid task type found");
                }

                taskList.add(task);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            // We may ignore this exception and create the file later.
        } catch (IOException e) {
            throw new IODukeException("Error opening task file for reading");
        } catch (ParseException e) {
            throw new IODukeException("Error parsing date in task file");
        }
    }

    public List<Task> getTaskList() {
        return taskList;
    }
}
