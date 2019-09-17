package SerSlacksalot.storage;

import SerSlacksalot.exception.DukeException;
import SerSlacksalot.task.Deadline;
import SerSlacksalot.task.Event;
import SerSlacksalot.task.Task;
import SerSlacksalot.task.ToDo;
import SerSlacksalot.taskList.TaskList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a storage file.
 * Contains a file name to read and write from.
 * Contains an archive file name to read and write from.
 * Contains a boolean that decides if the archive file should be used.
 */
public class Storage {

    protected String fileName;
    protected String archiveFileName = "src\\main\\resources\\storageData\\dukeArchive.txt";
    protected String initialTaskList;
    protected boolean hasInitialized = false;
    protected boolean isArchive = false;

    public Storage(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Writes the contents of the task list onto the file.
     *
     * @param tasks The task list to get tasks from.
     * @return output Message indicating that task list has been saved.
     * @throws Exception If unable to write to file.
     */
    public String saveTaskList(ArrayList<Task> tasks) throws Exception{
        StringBuilder sb = new StringBuilder();
        String output = "";
        String strLine = "";
        try {
            String fileToOpen = this.fileName;
            if (isArchive) {
                fileToOpen = archiveFileName;
            }
            FileWriter fw = new FileWriter(fileToOpen, false);
            //Appends the string to the file
            int index = 1;
            for (Task x : tasks) {
                String type = "";
                switch (x.type) {
                    case "T":
                        type = "To-Do   ";
                        break;
                    case "E":
                        type = "Event   ";
                        break;
                    case "D":
                        type = "Deadline";
                        break;
                }
                String isDone = "";
                if (x.isDone) {
                    isDone = "Done    ";
                } else {
                    isDone = "Not done";
                }
                String description = x.description;
                String time = x.getTime();
                strLine = type + " | " + isDone + " | " + description + time + "\n";
                fw.write(strLine);
                index++;
            }
            fw.write("End of file");
            fw.close();
            if (isArchive) {
                output += "Archive task list has been saved! Current task list has been cleared.";
            } else {
                output += "Task list has been saved!";
            }
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            throw new DukeException("Storage Exception: Error when writing to file.");
        }
        return output;
    }

    /**
     * Reads the file to add tasks to the task list.
     *
     * @param fileInput The String read from file.
     * @param taskList  The task list to get tasks from.
     * @return taskList The updated task list.
     */
    public TaskList loadFromFile(String fileInput, TaskList taskList) {
        boolean isDone = true;
        if (fileInput.contains("Not done")) {
            isDone = false;
        }
        String newCommand = fileInput.substring(22);
        if (fileInput.contains("/by")) {
            newCommand = " " + newCommand;
            Task newDeadline = new Deadline(newCommand);
            newDeadline.isDone = isDone;
            TaskList.tasks.add(newDeadline);
        } else if (fileInput.contains("/at")) {
            newCommand = " " + newCommand;
            Task newEvent = new Event(newCommand);
            newEvent.isDone = isDone;
            TaskList.tasks.add(newEvent);
        } else {
            newCommand = " " + newCommand;
            Task newToDo = new ToDo(newCommand);
            newToDo.isDone = isDone;
            TaskList.tasks.add(newToDo);
        }
        return taskList;
    }

    /**
     * Loads from the specified file for initialization.
     * Saves the task list to the storage.Storage object.
     * Does not load from file if already initialized.
     *
     * @return taskList The updated task list.
     * @throws DukeException If file is empty.
     */
    public TaskList displayTaskList() throws DukeException {
        TaskList taskList = new TaskList();
        StringBuilder sb = new StringBuilder();
        String fileToOpen = fileName;
        if (isArchive) {
            fileToOpen = archiveFileName;
        }
        String strLine = "";
        String printQueue = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileToOpen));
            //read the file content
            printQueue += ("Here is your task list file:");
            while (strLine != null) {
                sb.append(strLine);
                sb.append(System.lineSeparator());
                strLine = br.readLine();

                if (strLine.contains("End of file")) {
                    break;
                }
                if (!this.hasInitialized) {
                    loadFromFile(strLine, taskList);
                }
                printQueue += strLine;
            }
            br.close();
            this.hasInitialized = true;
        } catch (IOException ioe) {
            System.err.println("IOException: " + ioe.getMessage());
            throw new DukeException("Storage Exception: Error when reading " + fileToOpen);
        }
        initialTaskList = printQueue;
        return taskList;
    }

    /**
     * Writes the contents of the task list onto the archive file.
     *
     * @param tasks The task list to get tasks from.
     * @return output Message indicating that archive task list has been saved.
     */
    public String archiveSaveTaskList(ArrayList<Task> tasks) {
        String output = "";
        try {
            isArchive = true;
            output += saveTaskList(tasks);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            isArchive = false;
        }
        return output;
    }

    public TaskList archiveLoadTaskList() throws DukeException {
        isArchive = true;
        try {
            isArchive = true;
            hasInitialized = false;
            TaskList output = displayTaskList();
            isArchive = false;
            return output;
        } catch (Exception e) {
            e.printStackTrace();
            throw new DukeException("storage.Storage Exception: Error when loading from archive");
        }
    }
}
