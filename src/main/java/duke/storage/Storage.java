package duke.storage;

import duke.exception.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

public class Storage {

    private String filepath;

    /**
     * Initialises a Storage session.
     *
     * @param filepath The file to store tasks to.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Reads the input file line by line to add tasks into the TaskList.
     *
     * @return The LinkedList containing all loaded tasks.
     * @throws DukeException In the event that the file cannot be read properly.
     */
    public LinkedList<Task> load() throws DukeException {
        LinkedList<Task> allTasks = new LinkedList<>();
        try {
            File f = new File(filepath);
            if (!f.exists()) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(f));
            String currLine;
            while ((currLine = bufferedReader.readLine()) != null) {
                assert (currLine.contains("|")) : "No | detected in line";
                allTasks.add(convertLineToTask(currLine));
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        } catch (ParseException e) {
            throw new DukeException("Cannot parse items...");
        }
        return allTasks;
    }

    /**
     * Converts a line from file to a Task object.
     *
     * @param currLine The current line to be converted.
     * @return The Task object generated from the line.
     */
    private Task convertLineToTask(String currLine) throws ParseException {
        String[] formattedText = currLine.split("\\|");
        Task t;
        switch (formattedText[0]) {
        case "T":
            t = new ToDo(formattedText[2]);
            break;
        case "D":
            t = new Deadline(formattedText[2],
                    new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").parse(formattedText[3]));
            break;
        case "E":
            t = new Event(formattedText[2],
                    new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").parse(formattedText[3]),
                    new SimpleDateFormat("EEE, d MMM yyyy, hh:mm a").parse(formattedText[4]));
            break;
        default:
            t = new Task("");
            break;
        }
        if (formattedText[1].equals("1")) {
            t.markAsDone();
        }
        return t;
    }

    /**
     * Append a new task to the end of the file.
     *
     * @param t The task to be added.
     */
    public void appendTaskToFile(Task t) {
        try {
            BufferedWriter output = new BufferedWriter(new FileWriter(filepath, true));
            output.append(t.toStore()).append("\n");
            output.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a task from the file.
     *
     * @param t The task to be deleted.
     * @return The deletion status boolean.
     */
    public boolean deleteTaskFromFile(Task t) {
        File inputFile = new File(filepath);
        File tempFile = new File("data/temp.txt");
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            String lineToRemove = t.toStore();
            String currentLine;

            while ((currentLine = reader.readLine()) != null) {
                // trim newline when comparing with lineToRemove
                String trimmedLine = currentLine.trim();
                if (trimmedLine.equals(lineToRemove)) {
                    continue;
                }
                writer.write(currentLine + "\n");
            }
            writer.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tempFile.renameTo(inputFile);
    }
}
