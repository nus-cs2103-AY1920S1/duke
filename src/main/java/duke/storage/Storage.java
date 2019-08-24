package duke.storage;

import duke.exception.DukeException;
import duke.exception.IoDukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() throws DukeException {
        List<Task> tasks = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split("\\|");
                if (tokens.length < 3) {
                    throw new IoDukeException("The task file is corrupted");
                }

                Task task;
                boolean done = Boolean.parseBoolean(tokens[1]);
                String description = tokens[2];
                SimpleDateFormat dateFormat = new SimpleDateFormat("d/M/yyyy HHmm");

                switch (tokens[0]) {
                case "T":
                    task = new Todo(description, done);
                    break;
                case "D":
                    task = new Deadline(description, dateFormat.parse(tokens[3]));
                    break;
                case "E":
                    task = new Event(description, dateFormat.parse(tokens[3]));
                    break;
                default:
                    throw new IoDukeException("Invalid task type found");
                }

                tasks.add(task);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            throw new IoDukeException("Error opening task file for reading");
        } catch (IOException e) {
            throw new IoDukeException("Error closing file reader");
        } catch (ParseException e) {
            throw new IoDukeException("Error parsing date in task file");
        }

        return tasks;
    }

    public void save(TaskList tasks) throws DukeException {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(filePath)));
            for (Task task : tasks.getTaskList()) {
                String output = String.format("%s|%b|%s",
                        task.getType(), task.getDoneStatus(), task.getDescription());
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
            throw new IoDukeException("Error opening file for saving");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
