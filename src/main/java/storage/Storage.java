package storage;

import converter.StringDateConverter;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.StringJoiner;

/**
 * Represents the file used to store task list.
 */
public class Storage {
    private String filePath;

    /**
     * Instantiate Storage with path of the file where task list is stored.
     *
     * @param filePath represents the path of the file where task list is stored
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the file where task list is stored.
     *
     * @return list of task if the file is found
     * @throws FileNotFoundException when no task list file is found
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        File f = new File(this.filePath);
        Scanner s = new Scanner(f);
        ArrayList<Task> tasks = new ArrayList<Task>();
        while (s.hasNext()) {
            String[] outputs = s.nextLine().split(" \\| ");
            boolean isDone;
            if (outputs[1].equals("Done")) {
                isDone = true;
            } else {
                isDone = false;
            }
            Date date = null;
            String description = outputs[2].trim();
            if (outputs.length > 3) {
                try {
                    StringDateConverter converter = new StringDateConverter();
                    date = converter.convertLongStringToDate(outputs[3]);
                } catch (ParseException e) {
                    System.out.println("Please enter a valid date.");
                }
            }
            switch (outputs[0].trim()) {
            case "T":
                tasks.add(new ToDo(description, isDone));
                break;
            case "D":
                tasks.add(new Deadline(description, date, isDone));
                break;
            default: //case "E":
                tasks.add(new Event(description, date, isDone));
                break;
            }
        }
        return tasks;
    }

    /**
     * Saves task list in designated file path.
     *
     * @param tasks is the task list
     * @throws IOException if designated file path is not found
     */
    public void save(TaskList tasks) throws IOException {
        // create new directory called data if it does not exist
        String currDir = System.getProperty("user.dir");
        File createDataDir = new File(currDir + "/data");
        createDataDir.mkdir();

        FileWriter fw = new FileWriter(this.filePath);
        StringJoiner textToAdd = new StringJoiner(System.lineSeparator());
        for (Task task : tasks.getTasks()) {
            textToAdd.add(task.toSaveFormat());
        }
        fw.write(textToAdd.toString());
        fw.write(System.lineSeparator());

        fw.close();
    }
}
