package duke.storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import duke.exception.CorruptedData;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/** Represents the storage class that is used to read and write from a file. */
public class Storage {
    private File dbFile;
    private Ui ui = new Ui();

    /**
     * Create a storage object to read and write from the specified file. If the
     * directory is not found, the dirctory will be created. If the specified file
     * is not found, an empty file will be created.
     * 
     * @param filename the filename of the file to read from and write to
     */
    public Storage(String filename) {
        String currDirPath = System.getProperty("user.dir");

        // Creates a new directory to store all the files if it does not exist
        String dbDirPath = currDirPath + "/db";
        File dbDir = new File(dbDirPath);

        if (!dbDir.exists()) {
            dbDir.mkdir();
        }

        assert dbDir.exists() : "Directory missing";

        // Creates a new text file within the database directory if it does not exist
        File dbFile = new File(dbDir, filename);
        try {
            dbFile.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }
        this.dbFile = dbFile;
    }

    /**
     * Loads the data from the database file into a ArrayList.
     * 
     * @return an ArrayList of tasks containing all the data converted to tasks
     */
    public ArrayList<Task> load() {
        ArrayList<Task> db = new ArrayList<>();
        try {
            BufferedReader readData = new BufferedReader(new FileReader(dbFile));
            fillList(db, readData);
            readData.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
            db = new ArrayList<>();
        } catch (IOException e) {
            System.out.println(e);
            db = new ArrayList<>();
        }
        return db;
    }

    private void fillList(ArrayList<Task> db, BufferedReader reader) throws IOException {
        String input;
        while ((input = reader.readLine()) != null) {
            try {
                db.add(textToTask(input));
            } catch (DukeException e) {
                // prints out to console if a data point is corrupted
                System.out.println(ui.errorMessage(e));
            }
        }
    }

    /**
     * Writes the data from a tasklist to the file.
     * 
     * @param db the tasklist containing all the existing tasks
     */
    public void write(TaskList db) {
        try {
            BufferedWriter writeData = new BufferedWriter(new FileWriter(dbFile));
            for (Task t : db.getList()) {
                writeData.write(t.toText());
                writeData.newLine();
            }
            writeData.flush();
            writeData.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static Task textToTask(String text) throws DukeException {
        String[] textComponent = text.split("\\|");
        Task newTask;
        switch (textComponent[0]) {
        case "T":
            newTask = new Todo(textComponent[3]);
            break;
        case "D":
            newTask = new Deadline(textComponent[3], textComponent[4]);
            break;
        case "E":
            newTask = new Event(textComponent[3], textComponent[4]);
            break;
        default:
            throw new CorruptedData(text);
        }
        if (textComponent[1].equals("1")) {
            newTask.doneTask();
        }

        switch (textComponent[2]) {
        case "L": newTask.setLowPriority();
            break;
        case "H": newTask.setHighPriority();
            break;
        default: newTask.setMedPriority();
        }
        return newTask;
    }
}