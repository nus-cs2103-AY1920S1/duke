package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

import tasks.Task;
import tasks.ToDo;
import tasks.Deadline;
import tasks.Event;

import exceptions.DukeException;

/**
 * Storage is a class that deals with reading from the specified file
 * and instantiating a list of tasks as well as saving those tasks to the file.
 */
public class Storage {

    /** The path of the file to be read from and written to. */
    private String filePath;

    /**
     * The Storage constructor takes the path of the
     * file to be read from as input.
     *
     * @param filePath The path of the file to be read from and written to.
     */
    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Returns an ArrayList of Tasks after reading
     * from the file specified by the file path.
     *
     * @return Arraylist of Tasks.
     * @throws DukeException  if there is invalid input.
     * @throws IOException  if there is an error reading from the file.
     */
    ArrayList<Task> load() throws DukeException, IOException {

        File f = new File(filePath);
        // Create a new txt file if it currently does not exist
        f.createNewFile();
        Scanner sc = new Scanner(f);
        ArrayList<Task> taskLst = new ArrayList<>();

        while (sc.hasNext()) {
            String s = sc.nextLine();
            String line = s.substring(8);
            if (s.charAt(0) == 'T') {
                taskLst.add(new ToDo(line, s.charAt(4) == '1'));
            } else if (s.charAt(0) == 'D' || s.charAt(0) == 'E') {
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '|') {
                        if (s.charAt(0) == 'D') {
                            taskLst.add(new Deadline(line.substring(0, i - 1),
                                    LocalDateTime.parse(line.substring(i + 2),
                                            DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma")),
                                    s.charAt(4) == '1'));
                        } else { // if s.charAt(0) == 'E'
                            taskLst.add(new Event(line.substring(0, i - 1),
                                    LocalDateTime.parse(line.substring(i + 2),
                                            DateTimeFormatter.ofPattern("d MMMM yyyy, h:mma")),
                                    s.charAt(4) == '1'));
                        }
                    }
                }
            } else {
                throw new DukeException("Invalid file format.");
            }
        }

        return taskLst;
    }

    /**
     * Saves the updated list of tasks to the same file that was read from.
     *
     * @param tasks the TaskList object that contains the ArrayList of Tasks.
     * @throws IOException  if there is an error saving to the file.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        StringBuilder textToAdd = new StringBuilder();
        for (Task task : tasks.getTaskLst()) {
            textToAdd.append(task.fileString()).append("\n");
        }
        fw.write(textToAdd.toString());
        fw.close();
    }
}
