package slave.elements;

import slave.task.*;
import slave.exception.DukeException;
import slave.exception.IOWentWrongException;
import slave.exception.NoStorageFileDetectedException;
import slave.exception.UnableToReadFileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.Files;

/**
 * Storage class that writes and loads data from local .txt file
 */
public class Storage{

    private File file;
    private String filePath;

    /**
     * Constructor that initialises a File object
     * @param filePath filePath of .txt file to write/load
     */
    public Storage(String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Clears storage of data
     * @throws NoStorageFileDetectedException storagefile not found
     */
    public void clearStorage() throws NoStorageFileDetectedException {
        try {
            PrintWriter writer = new PrintWriter(this.file);
            writer.close();
        } catch ( FileNotFoundException e ) {
            throw new NoStorageFileDetectedException();
        }
    }

    /**
     * Method which loads tasks from .txt file
     * @return An arraylist of tasks parsed from .txt file
     * @throws DukeException Throws if file cannot be found or input went wrong
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList;
        try {
            taskList = new ArrayList<>();
            List<String> list = Files.readAllLines(this.file.toPath());
            int index = 1;
            for (String line : list) {
                taskList.add(formatFileToTask(line, index));
                index++;
            }
        } catch ( IOException e ) {
            throw new IOWentWrongException();
            }
        return taskList;
    }

    /**
     * Refreshes storage and updates list of tasks to the stored local .txt file
     * @param taskList current task list
     * @throws DukeException throws if file cannot be found
     */
    void refreshStorage(ArrayList<Task> taskList) throws DukeException{
        try {
            PrintWriter writer = new PrintWriter(this.file);
            writer.close();
            for (Task task : taskList) {
                addTask(task);
            }
        } catch ( FileNotFoundException e ) {
            throw new NoStorageFileDetectedException();
        }
    }

    /**
     * Method to encode task and add it to .txt file
     * @param task task to store in .txt file
     * @throws DukeException throws if input went wrong in writing file
     */
    void addTask(Task task) throws DukeException{
        try {
            FileWriter fw = new FileWriter(this.file, true);
            switch (task.getType()) {
            case TODO:
                fw.write(task.getId() + " ~ " +
                        "ToDo" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() +
                        System.lineSeparator());
                fw.close();
                break;
            case DEADLINE:
                fw.write(task.getId() + " ~ " +
                        "Deadline" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() + " ~ " +
                        task.getDate() +
                        System.lineSeparator());
                fw.close();
                break;
            case EVENT:
                fw.write(task.getId() + " ~ " +
                        "Event" + " ~ " +
                        task.getStatusIcon() + " ~ " +
                        task.getDescription() + " ~ " +
                        task.getDate() +
                        System.lineSeparator());
                fw.close();
            }
        } catch ( IOException error) {
            throw new IOWentWrongException();
        }
    }

    /**
     * Method which parses the line from local storage .txt file
     * to a task to be added into current program's taskList
     * @param line line to parse
     * @param index current line of file
     * @return Task that has been parsed
     * @throws DukeException
     */
    private Task formatFileToTask(String line, int index) throws DukeException{
        String[] tokens = line.split(" ~ ");
        switch(tokens[1]){
        case "ToDo":
            ToDo toDoTask = new ToDo(tokens[3], Integer.parseInt(tokens[0]));
            if (tokens[2].equals("Done")){
                toDoTask.setDone();
            }
            return toDoTask;
        case "Deadline":
            Deadline deadlineTask = new Deadline(tokens[3], Integer.parseInt(tokens[0]), tokens[4]);
            if (tokens[2].equals("Done")){
                deadlineTask.setDone();
            }
            return deadlineTask;
        case "Event":
            Event eventTask = new Event(tokens[3], Integer.parseInt(tokens[0]), tokens[4]);
            if (tokens[2].equals("Done")){
                eventTask.setDone();
            }
            return eventTask;
        default:
            throw new UnableToReadFileException(index);
        }
    }


}