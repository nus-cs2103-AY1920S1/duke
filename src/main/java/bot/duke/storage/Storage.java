package bot.duke.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;

import bot.duke.task.Deadline;
import bot.duke.task.Event;
import bot.duke.task.Task;
import bot.duke.task.TaskList;
import bot.duke.task.Todo;

;

public class Storage {

    /**
     * Path to text file.
     */
    String filePath;

    /**
     * Constructs the Storage object with the specified path.
     *
     * @param filePath Specified path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves TaskList list of Task objects to a text file.
     *
     * @param taskList TaskList list of Task objects
     * @throws IOException If there is problem writing to the file
     */
    public void save(TaskList taskList) throws IOException {
        ArrayList<Task> tasks = taskList.getTasks();
        FileWriter fileWriter = new FileWriter(this.filePath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (Task task : tasks) {
            printWriter.println(task.toDelimitedString());
        }

        printWriter.close();
    }

    /**
     * Loads list of tasks from a text file.
     *
     * @return List of tasks
     * @throws IOException    If there is a problem reading the file
     * @throws ParseException If there is a problem with the datetime format
     */
    public ArrayList<Task> load() throws IOException, ParseException {
        ArrayList<Task> tasks = new ArrayList<Task>();

        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));

        String line;
        while ((line = br.readLine()) != null) {
            String[] lineSplit = line.split(" \\| ");
            switch (lineSplit[0]) {
            case "T":
                Todo newTodo = new Todo(lineSplit[2]);
                newTodo.setDone(lineSplit[1].equals("T"));
                tasks.add(newTodo);
                break;
            case "D":
                Deadline newDeadline = new Deadline(lineSplit[2],
                        Task.DATE_FORMAT.parse(lineSplit[3]));
                newDeadline.setDone(lineSplit[1].equals("T"));
                tasks.add(newDeadline);
                break;
            case "E":
                Event newEvent = new Event(lineSplit[2],
                        Task.DATE_FORMAT.parse(lineSplit[3]), Task.DATE_FORMAT.parse(lineSplit[4]));
                newEvent.setDone(lineSplit[1].equals("T"));
                tasks.add(newEvent);
                break;
            default:
                break;
            }
        }

        return tasks;
    }
}
