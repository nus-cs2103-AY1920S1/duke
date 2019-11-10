package duke.storage;

import duke.task.Task;
import duke.task.Deadline;
import duke.task.Event;
import duke.tasklist.TaskList;
import duke.parser.Parser;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Storage class which handles the loading and saving of Task data.
 */
public class Storage implements StorageInterface {

    /** Filepath to load from and save to. */
    private File file;
    private static final String DEFAULT_PATH = "data" + File.separator + "savedTasks.txt";
    private static final Logger logger = Logger.getLogger(Storage.class.getName());

    /**
     * Initialises a Storage object with the default file path.
     */
    public Storage() {
        this.file = new File(DEFAULT_PATH);
    }

    /**
     * Initialises a Storage object.
     * @param filePath File path to load to and save from.
     */
    public Storage(String filePath) {
        this.file = new File(filePath);
    }

    /**
     * Loads a file and parses the data into Tasks which are stored in a TaskList.
     * @return Tasklist that contains the loaded tasks.
     * @throws IOException Exception is thrown when an invalid file path is provided.
     */
    public ArrayList<Task> load() throws IOException {

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
            BufferedReader br = new BufferedReader(isr);

            String line;
            while ((line = br.readLine()) != null) {
                Task newTask = Parser.parseStringToTask(line);
                tasks.add(newTask);
            }
        } catch (ParseException e) {
            logger.severe("Failed to parse String to task");
        }

        return tasks;
    }

    /**
     * Saves the data of the TaskList in a text file.
     * @param taskList TaskList that is to be saved.
     */
    public void save(TaskList taskList) {
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : taskList.getTasks()) {
                String formattedData = formatData(task);

                writer.append(formattedData);
                writer.append("\n");
            }

            writer.close();
        } catch (IOException e) {
            logger.severe("invalid filePath");
        }
    }

    /**
     * Formats the data of a Task in the format of type|isDone|desc|startDate(optional)|startTime(optional)|endTime
     * (optional).
     * @param currTask Task that will be formatted.
     * @return String of the formatted Task data.
     */
    public String formatData(Task currTask) {
        String data = "";
        data += currTask.getType() + "|";
        data += currTask.getDone() ? "1|" : "0|";
        data += currTask.getDescription();


        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.ENGLISH);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm", Locale.ENGLISH);

        if (currTask instanceof Deadline) {
            data += "|" + ((Deadline) currTask).getStartDate().format(dateFormatter)
                    + "|" + ((Deadline) currTask).getStartTime().format(timeFormatter);
        }

        if (currTask instanceof Event) {
            data += "|" + ((Event) currTask).getStartDate().format(dateFormatter)
                    + "|" + ((Event) currTask).getStartTime().format(timeFormatter)
                    + "|" + ((Event) currTask).getEndTime().format(timeFormatter);
        }

        return data;
    }
}

