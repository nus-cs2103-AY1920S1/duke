package jermi.component;

import jermi.exception.JermiException;
import jermi.exception.LoadingException;
import jermi.exception.SavingException;
import jermi.task.Deadline;
import jermi.task.Event;
import jermi.task.Task;
import jermi.task.ToDo;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * A class that deals with loading tasks from the data file and saving tasks in the data file.
 */
public class Storage {
    /** File */
    private File file;
    /** Task list */
    private TaskList taskList;

    /**
     * Public constructor for class.
     * Loads tasks from data file upon instantiation.
     *
     * @param pathname Pathname of data file.
     * @param taskList Task list.
     * @throws JermiException {@link LoadingException} or {@link SavingException}.
     */
    public Storage(String pathname, TaskList taskList) throws JermiException {
        this.taskList = taskList;
        this.file = new File(pathname);
        this.fileToTaskList();
    }

    /**
     * Converts saved data in file to {@link Task} objects.
     *
     * @param fileFormat Saved data.
     * @return Task.
     */
    private Task fileFormatToTask(String fileFormat) {
        Task task = null;
        String[] components = fileFormat.split("\\|");

        switch (components[0]) {
        case "T":
            task = new ToDo(components[2], components[1]);
            break;
        case "D":
            task = new Deadline(components[2], components[3], components[1]);
            break;
        case "E":
            task = new Event(components[2], components[3], components[1]);
            break;
        }
        return task;
    }

    /**
     * Reads saved data in file and adds it to {@link TaskList}.
     *
     * @throws JermiException {@link LoadingException}.
     */
    private void fileToTaskList() throws JermiException {
        try {
            List<String> lines = Files.readAllLines(this.file.toPath());
            for (String line : lines) {
                this.taskList.add(this.fileFormatToTask(line));
            }
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Writes tasks in {@link TaskList} to file.
     *
     * @throws JermiException {@link SavingException}.
     */
    public void taskListToFile() throws JermiException {
        try {
            StringBuilder toWrite = new StringBuilder();
            for (Task task : this.taskList.getList()) {
                toWrite.append(task.toSaveFormat());
                toWrite.append("\n");
            }
            Files.writeString(this.file.toPath(), toWrite.toString());
        } catch (IOException e) {
            throw new SavingException(e.getMessage());
        }
    }
}
