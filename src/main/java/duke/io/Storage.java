package duke.io;

import duke.error.DukeException;

import duke.tasklist.ToDo;
import duke.tasklist.Deadline;
import duke.tasklist.Event;
import duke.tasklist.Task;
import duke.tasklist.TaskList;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.regex.Pattern;


/**
 * File handler responsible for reading/writing from/to files in order to load/save the TaskList
 * in between sessions.
 */
public class Storage {

    private static final String DEADLINE_FLAG = "D";
    private static final String EVENT_FLAG = "E";
    private static final String TODO_FLAG = "T";
    private static final String COMPLETE = "1";
    private static final String INCOMPLETE = "0";

    private File saveFile;

    /**
     * Constructs a file reader-writer to load/save a TaskList.
     *
     * @param fileName The name of the save file for the Task List
     */
    public Storage(String fileName) throws DukeException {
        assert fileName != null;
        assert Pattern.matches("\\p{Alnum}+", fileName);

        if (!fileName.matches("\\p{Alnum}+")) {
            throw new DukeInvalidFileNameException(fileName);
        }

        String parentDirectoryPath =
                new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath()).getParent();
        String saveFolderName = "DukeSaveFiles";
        String storageName = fileName + ".txt";

        File saveFolder = Paths.get(parentDirectoryPath, saveFolderName).toFile();

        if (saveFolder.exists() && !saveFolder.isDirectory()) {
            int count = 1;
            while (!saveFolder.isDirectory()) {
                saveFolder = Paths.get(parentDirectoryPath, saveFolderName + "(" + Integer.toString(count) + ")")
                        .toFile();
                count++;
            }
        }

        saveFile = Paths.get(parentDirectoryPath, saveFolderName, storageName).toFile();

        saveFile.getParentFile().mkdir();

        try {
            // create new file if does not already exist
            if (saveFile.createNewFile()) {
                new FileWriter(saveFile).append("0").flush();
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            throw new DukeInvalidLoadFilePathException(saveFile.getAbsolutePath());
        }
    }

    /**
     * Returns the file name of the Storage with the file extension.
     *
     * @return the file name of the Storage with the file extension.
     */
    public String getFileName() {
        return saveFile.getName();
    }


    /**
     * Helper method to write a Deadline Task to a file.
     */
    private static void writeFromDeadline(Deadline task, FileWriter file) throws IOException {
        assert task != null;
        assert file != null;
        writeLinesToFile(
                file,
                DEADLINE_FLAG,
                task.isComplete() ? COMPLETE : INCOMPLETE,
                task.getDescription(),
                task.time);
    }

    /**
     * Helper method to write a Event Task to a file.
     */
    private static void writeFromEvent(Event task, FileWriter file) throws IOException {
        assert task != null;
        assert file != null;
        writeLinesToFile(
                file,
                EVENT_FLAG,
                task.isComplete() ? COMPLETE : INCOMPLETE,
                task.getDescription(),
                task.time);
    }

    /**
     * Helper method to write a ToDo Task to a file.
     */
    private static void writeFromToDo(ToDo task, FileWriter file) throws IOException {
        assert task != null;
        assert file != null;
        writeLinesToFile(
                file,
                TODO_FLAG,
                task.isComplete() ? COMPLETE : INCOMPLETE,
                task.getDescription());
    }

    /**
     * Helper method to write multiple lines to a file.
     */
    private static void writeLinesToFile(FileWriter file, String... lines) throws IOException {
        for (String line : lines) {
            file.append(line);
            file.append(System.lineSeparator());
        }
    }

    /**
     * Returns the TaskList read from the save file.
     *
     * @return The TaskList read from data stored in the file
     * @throws DukeException Exception thrown when error occurs when trying to recreate the task list
     */
    public TaskList loadTaskList() throws DukeException {
        try {
            BufferedReader bufferedFileReader = new BufferedReader(new FileReader(saveFile));

            // populate tasklist
            TaskList taskList = new TaskList();

            for (int tasksRemaining = Integer.parseInt(bufferedFileReader.readLine());
                 tasksRemaining > 0; tasksRemaining--) {

                switch (bufferedFileReader.readLine()) {
                case DEADLINE_FLAG:
                    taskList.add(readAsDeadline(bufferedFileReader));
                    break;
                case EVENT_FLAG:
                    taskList.add(readAsEvent(bufferedFileReader));
                    break;
                case TODO_FLAG:
                    taskList.add(readAsToDo(bufferedFileReader));
                    break;
                default:
                    throw new DukeCorruptFileException(saveFile);
                }
            }
            return taskList;
        } catch (FileNotFoundException exception) {
            throw new DukeInvalidLoadFilePathException(saveFile.getAbsolutePath());
        } catch (IOException | NumberFormatException exception) {
            throw new DukeCorruptFileException(saveFile);
        }
    }

    private Deadline readAsDeadline(BufferedReader file) throws IOException {
        assert file != null;

        return new Deadline(!file.readLine().equals(INCOMPLETE), file.readLine(), file.readLine());
    }

    private ToDo readAsToDo(BufferedReader file) throws IOException {
        assert file != null;

        return new ToDo(!file.readLine().equals(INCOMPLETE), file.readLine());
    }

    private Event readAsEvent(BufferedReader file) throws IOException {
        assert file != null;

        return new Event(!file.readLine().equals(INCOMPLETE), file.readLine(), file.readLine());
    }

    /**
     * Writes/Saves the TaskList into the save file.
     *
     * @param taskList The TaskList to be saved in the file
     * @throws DukeException when error occurs while trying to save the TaskList
     */
    public void save(TaskList taskList) throws DukeException {
        assert taskList != null : "tasklist is null";
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(saveFile);

            // list size
            fileWriter.append(Integer.toString(taskList.size()));
            fileWriter.append(System.lineSeparator());

            // per task in list
            for (Task task : taskList.list()) {
                switch (task.getClass().getSimpleName()) {
                case "ToDo":
                    writeFromToDo((ToDo) task, fileWriter);
                    break;
                case "Event":
                    writeFromEvent((Event) task, fileWriter);
                    break;
                case "Deadline":
                    writeFromDeadline((Deadline) task, fileWriter);
                    break;
                default:
                    break;
                }
            }

            fileWriter.close();
        } catch (FileNotFoundException exception) {
            throw new DukeInvalidSaveFilePathException(saveFile.getAbsolutePath());
        } catch (IOException exception) {
            // FileNotFoundException should the only exception, if it is not then:
            System.err.println(exception.getMessage());
        }
    }

}