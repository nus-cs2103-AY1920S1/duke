package seedu.duke;

import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeSaveFailedException;
import seedu.duke.task.Task;
import seedu.duke.task.Event;
import seedu.duke.task.Deadline;
import seedu.duke.task.Todo;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedWriter;
import java.util.ArrayList;
import java.text.SimpleDateFormat;

public class Storage {

    private String directory;
    private FileReader fileReader;
    private FileWriter fileWriter;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");

    public Storage(String dir) {
        directory = dir;
    }

    public TaskList load() {
        try {
            fileReader = new FileReader(directory);
        } catch (FileNotFoundException e) {
            return new TaskList();
        }
        bufferedReader = new BufferedReader(fileReader);
        String[] tokens;

        String nextLine;
        try {
            nextLine = bufferedReader.readLine();
        } catch (IOException e) {
            nextLine = null;
        }
        ArrayList<Task> taskList = new ArrayList<Task>();

        // Savefile layout
        // Event type|done or not done(1 or 0)|Description|Date(if applicable)
        while (nextLine != null) {
            tokens = nextLine.split("\\|");

            switch (tokens[0]) {
            case "T":
                Todo todo = new Todo(tokens[2], tokens[1]);
                taskList.add(todo);
                break;

            case "D":
                Deadline deadline;
                try {
                    deadline = new Deadline(tokens[2], tokens[1], tokens[3]);
                } catch (DukeException e) {
                    e.printMessage();
                    break;
                }
                taskList.add(deadline);
                break;

            case "E":
                Event event;
                try {
                    event = new Event(tokens[2], tokens[1], tokens[3]);
                } catch (DukeException e) {
                    e.printMessage();
                    break;
                }
                taskList.add(event);
                break;

            default:
                Ui.print("Error reading task from save file.\n");
            }
            try {
                nextLine = bufferedReader.readLine();
            } catch (IOException e) {
                nextLine = null;
            }
        }

        return new TaskList(taskList);
    }

    public void save(TaskList taskListObject) throws DukeSaveFailedException {
        try {
            fileWriter = new FileWriter(directory);
        } catch (IOException e) {
            throw new DukeSaveFailedException();
        }
        bufferedWriter = new BufferedWriter(fileWriter);

        ArrayList<Task> taskList = taskListObject.getTaskArray();

        for (int z = 0; z < taskList.size(); z++) {
            try {
                bufferedWriter.write(taskList.get(z).toStorageString());
                bufferedWriter.write("\n");
                bufferedWriter.flush();
            } catch (IOException e) {
                System.out.println(e);
                throw new DukeSaveFailedException();
            }
        }
    }

}
