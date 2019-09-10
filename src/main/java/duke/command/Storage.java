package duke.command;

import duke.task.Deadline;
import duke.task.Status;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Note;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


/**
 * duke.command.Storage class. Handles the reading/writing of data to disk.
 */
public class Storage {

    /** Stores the location of the file to be read/written to. */
    private String fileName;
    /** Stores the format by which the Date/Time should be read/written as. */
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Creates a new duke.command.Storage object.
     * @param fileName The location of the file to be read/written to.
     */
    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Loads previously-saved tasks from the file.
     * @return An ArrayList containing the tasks previously stored.
     */
    ArrayList<Task> loadFromFile() {

        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);

            String current;
            String[] params;
            ArrayList<Task> taskList = new ArrayList<>(100);

            while (scanner.hasNext()) {
                current = scanner.nextLine();
                params = current.split("\\|");
                Status status = (params[1].equals("0") ? Status.INCOMPLETE : Status.COMPLETE);

                switch (params[0]) {
                case "T":
                    taskList.add(new ToDo(status, params[2]));
                    break;

                case "D":
                    taskList.add(new Deadline(status, params[2], LocalDateTime.parse(params[3], formatter)));
                    break;

                case "E":
                    taskList.add(new Event(status, params[2], LocalDateTime.parse(params[3], formatter)));
                    break;

                case "N":
                    taskList.add(new Note(params[1]));
                    break;

                default:
                    break;
                }
            }
            return taskList;

        } catch (FileNotFoundException e) {
            return new ArrayList<Task>(100);
        }
    }

    /**
     * Saves the tasks to file.
     * @param taskList The ArrayList of tasks to be written to file.
     * @throws IOException If it fails to write the file.
     */
    void saveToFile(ArrayList<Task> taskList) throws IOException {

        File f = new File(fileName);
        if (!f.exists()) {
            f.getParentFile().mkdirs();
            f.createNewFile();
        }

        FileWriter fw = new FileWriter(f);
        StringBuilder s = new StringBuilder();
        Task current;

        for (int i = 0; i < taskList.size(); i++) {
            current = taskList.get(i);

            if (i == taskList.size() - 1) {
                s.append(current.toSaveString());
            } else {
                s.append(current.toSaveString());
                s.append(System.getProperty("line.separator"));
            }
        }

        fw.write(s.toString());
        fw.close();
    }


}
