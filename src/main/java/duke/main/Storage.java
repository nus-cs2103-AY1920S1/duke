package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

public class Storage {
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the user's hard drive.
     *
     * @return The list of tasks currently stored in the user's hard drive.
     */
    ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return tasks;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String input;
            while ((input = br.readLine()) != null) {
                String[] splitInput = input.split(" \\| ");

                Task task;
                switch (splitInput[0]) {
                case "T":
                    task = new Todo(splitInput[2]);
                    break;
                case "D":
                    if (Parser.isDate(splitInput[3])) {
                        task = new Deadline(splitInput[2], Parser.parseDate(splitInput[3]));
                        break;
                    }
                    task = new Deadline(splitInput[2], splitInput[3]);
                    break;
                case "E":
                    if (Parser.isDate(splitInput[3])) {
                        task = new Event(splitInput[2], Parser.parseDate(splitInput[3]));
                        break;
                    }
                    task = new Event(splitInput[2], splitInput[3]);
                    break;
                default:
                    throw new DukeException("Error occurred during file parsing, unexpected task type encountered.");
                }

                if (Integer.parseInt(splitInput[1]) == 1) {
                    task.markAsDone();
                }
                tasks.add(task);
            }
        } catch (IOException e) {
            throw new DukeException("An IOException occurred. " + e);
        } catch (NumberFormatException e) {
            throw new DukeException("An error occurred during file parsing, unexpected done value encountered.");
        }

        return tasks;
    }


    /**
     * Saves the given list to the user's hard drive.
     *
     * @param list The given list to be saved.
     */
    public void save(ArrayList<Task> list) throws DukeException {
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (Task l : list) {
                bw.append(l.getOutputFormat());
                bw.append("\n");
            }
            bw.close();
        } catch (IOException e) {
            throw new DukeException("An IOException occurred. " + e);
        }
    }
}
