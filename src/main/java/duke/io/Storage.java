package duke.io;

import duke.place.Place;
import duke.place.PlaceList;
import duke.task.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.Scanner;

/**
 * Interface used by duke to interact with files.
 */
public class Storage {
    private String filePath;
    private String placePath;
    private File file;
    private File places;

    public Storage(String filePath) {
        this.filePath = filePath;
        file = new File(filePath);
    }

    /**
     * Overloaded constructor for place data.
     *
     * @param filePath file path for duke task data
     * @param placePath file path for places data
     */
    public Storage(String filePath, String placePath) {
        this.filePath = filePath;
        this.placePath = placePath;
        file = new File(filePath);
        places = new File(placePath);
    }

    /**
     * Loads places data from save file.
     *
     * @param ui User interface for output.
     */
    public void loadPlaces(Ui ui) {
        try {
            Scanner scanner = new Scanner(places);
            while (scanner.hasNext()) {
                String[] code = scanner.nextLine().split("\\|");
                Place.getList().add(Parser.initPlace(code));
            }
            scanner.close();
        } catch (FileNotFoundException ex) {
            ui.out("You do not have any saved places.");
        }
    }

    /**
     * Loads all data from file into task list.
     *
     * @param ui User-Interface for display/messages.
     * @return TaskList for Duke.
     */
    public TaskList loadTasks(Ui ui) {
        TaskList taskList = new TaskList();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String[] code = scanner.nextLine().split("\\|");
                taskList.add(Parser.init(code));
            }
            scanner.close();
            ui.list(taskList);
        } catch (FileNotFoundException ex) {
            ui.out("You do not have any outstanding tasks.");
        } catch (ParseException ex) {
            ui.out("There was an error with the data file.");
        }
        return taskList;
    }

    /**
     * Writes all data from task list to file.
     *
     * @param taskList from Duke.
     */
    public void writeTasks(TaskList taskList) {
        try {
            if (taskList.isEmpty() && file.exists()) {
                Files.delete(Paths.get(filePath));
                return;
            }
            StringBuilder allTasks = new StringBuilder();
            for (duke.task.Task task : taskList) {
                allTasks.append(task.store()).append(System.lineSeparator());
            }
            if (!file.exists() && !file.getParentFile().mkdirs() && !file.createNewFile()) {
                throw new IOException("Error creating file");
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(allTasks.toString());
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }

    /**
     * Stores places data.
     */
    public void writePlaces() {
        PlaceList list = Place.getList();
        try {
            if (list.isEmpty() && places.exists()) {
                Files.delete(Paths.get(placePath));
                return;
            }
            StringBuilder allTasks = new StringBuilder();
            for (Place place : list) {
                allTasks.append(place.store()).append(System.lineSeparator());
            }
            if (!places.exists() && !places.getParentFile().mkdirs() && !places.createNewFile()) {
                throw new IOException("Error creating file");
            }
            FileWriter fileWriter = new FileWriter(places);
            fileWriter.write(allTasks.toString());
            fileWriter.close();
        } catch (IOException ex) {
            System.out.println("Something went wrong: " + ex.getMessage());
        }
    }
}
