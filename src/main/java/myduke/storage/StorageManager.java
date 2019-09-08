package myduke.storage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.util.function.BiConsumer;

import myduke.exception.DukeException;
import myduke.task.Deadline;
import myduke.task.DoAfter;
import myduke.task.Event;
import myduke.task.Task;
import myduke.task.ToDo;
import myduke.type.LoggerMessageType;
import myduke.util.TaskFactoryHashMap;
import myduke.util.ThrowableFunction;

/**
 * Manages the Storage of Data Base Files.
 */
public class StorageManager {
    private static final String CsvDelimiter = "\\s\\|\\s";

    //Class Variables
    private String dataBaseLocation;
    private List<Task> taskList;
    private TaskFactoryHashMap taskConstuctorMapping;
    private BiConsumer<String, LoggerMessageType> loggerConsumer;

    /**
     * Constructor of Storage Manager.
     *
     * @param fileLocation  Location of Database file.
     * @param taskList      A List of tasks to complete.
     * @param logger        A BiConsumer to perform logging.
     */
    public StorageManager(String fileLocation, List<Task> taskList, BiConsumer<String, LoggerMessageType> logger) {
        this.dataBaseLocation = fileLocation;
        this.taskList = taskList;
        this.loggerConsumer = logger;
        this.taskConstuctorMapping = generateTaskMapping();
    }

    /**
     * Creates a map mapping the task type unique identifier to their respective constructors.
     *
     * @return a mapping from the task type unique identifier to their respective constructors.
     */
    private TaskFactoryHashMap generateTaskMapping() {

        TaskFactoryHashMap taskMap = new TaskFactoryHashMap();
        taskMap.put(ToDo.getDataBaseDescriptor(), in -> new ToDo(in.next()));
        taskMap.put(DoAfter.getDataBaseDescriptor(), in -> new DoAfter(in.next(), in.next()));
        taskMap.put(Deadline.getDataBaseDescriptor(), in -> new Deadline(in.next(), in.next()));
        taskMap.put(Event.getDataBaseDescriptor(), in -> new Event(in.next(), in.next()));

        return taskMap;
    }

    /**
     * Tries to load the list of task from the database File.
     *
     * @return A boolean indicating whether the list of task were successfully loaded from the database.
     */
    public boolean tryLoadFromDataBase() throws NullPointerException {

        if (taskList == null) {
            loggerConsumer.accept("taskList is a Null reference", LoggerMessageType.LOGGER_MESSAGE_ERROR);
            return false;
        }

        boolean successfullyLoadedDb = false;

        taskList.clear();
        try {
            File db = new File(this.dataBaseLocation);
            Scanner in = new Scanner(db);
            in.useDelimiter(CsvDelimiter);

            while (in.hasNextLine() && in.hasNext()) {
                String taskType = in.next().trim();

                if (taskType.equals("END")) {
                    successfullyLoadedDb = !in.hasNext();
                    break;
                }

                boolean markDone = (in.nextInt() != 0);
                ThrowableFunction<Scanner,Task, DukeException> constructor = taskConstuctorMapping.get(taskType);
                if (constructor == null) {
                    throw new NoSuchElementException("Task type [" + taskType + "] is not recognised");
                }

                Task newTask = constructor.accept(in);
                if (markDone) {
                    newTask.markAsDone();
                }
                taskList.add(newTask);

            }
            in.close();

            if (successfullyLoadedDb) {
                loggerConsumer.accept("Successfully loaded DB file.", LoggerMessageType.LOGGER_MESSAGE_INFO);
            } else {
                loggerConsumer.accept("Format of DB file is incorrect.", LoggerMessageType.LOGGER_MESSAGE_ERROR);
            }

        } catch (FileNotFoundException ex) {
            loggerConsumer.accept("Could find DB File.", LoggerMessageType.LOGGER_MESSAGE_ERROR);
        } catch (NoSuchElementException ex) {
            loggerConsumer.accept("DB File is corrupt. " + ex.getMessage(), LoggerMessageType.LOGGER_MESSAGE_ERROR);
        } catch (DukeException ex) {
            loggerConsumer.accept("DB File is corrupt. " + ex.getMessage(), LoggerMessageType.LOGGER_MESSAGE_ERROR);
        }

        if (!successfullyLoadedDb) {
            taskList.clear();
        }

        return successfullyLoadedDb;
    }

    /**
     * Tries to save the given list of task to the database file.
     *
     * @return A boolean indicating whether the list of task were successfully saved to the database.
     */
    public boolean tryWriteToFile() {
        try {
            File directory = new File(dataBaseLocation).getParentFile();
            if (!directory.exists()) {
                directory.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(dataBaseLocation));
            for (Task myTask : taskList) {
                writer.write(myTask.getDataBaseFormat());
            }
            writer.write("END");
            writer.close();

            loggerConsumer.accept("Saved DB File successfully", LoggerMessageType.LOGGER_MESSAGE_INFO);
        } catch (IOException ex) {
            loggerConsumer.accept("Could not save to DB File. " + ex.getMessage(),
                    LoggerMessageType.LOGGER_MESSAGE_ERROR);
            return false;
        }

        return true;
    }

}
