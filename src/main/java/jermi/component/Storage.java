package jermi.component;

import jermi.exception.CorruptedSaveFormatException;
import jermi.exception.JermiException;
import jermi.exception.LoadingException;
import jermi.exception.SavingException;
import jermi.task.Deadline;
import jermi.task.Event;
import jermi.task.Task;
import jermi.task.ToDo;
import jermi.type.TaskType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * A class that deals with loading tasks from the data file and saving tasks in the data file.
 */
public class Storage {
    /** File. */
    private File file;
    /** Task list. */
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
     * Converts a single line of saved data in the file to a {@link Task} object.
     *
     * @param saveFormat A string containing information regarding the task.
     * @return Task.
     */
    private Task saveFormatToTask(String saveFormat) throws JermiException {
        try {
            Task task = null;
            String[] components = saveFormat.split("\\|");
            String typeCode = components[0];
            String isDone = components[1];
            String description = components[2];

            if (typeCode.equals(TaskType.TO_DO.getTypeCode())) {
                task = new ToDo(description, isDone);
            } else {
                String dateTime = components[3];
                if (typeCode.equals(TaskType.DEADLINE.getTypeCode())) {
                    task = new Deadline(description, dateTime, isDone);
                } else if (typeCode.equals(TaskType.EVENT.getTypeCode())) {
                    task = new Event(description, dateTime, isDone);
                } else {
                    throw new CorruptedSaveFormatException(saveFormat);
                }
            }
            assert task != null : "task cannot be null";
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CorruptedSaveFormatException(saveFormat);
        }
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
                this.taskList.add(this.saveFormatToTask(line));
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
            this.taskList.getList().forEach(task -> toWrite.append(task.toSaveFormat() + "\n"));
            Files.writeString(this.file.toPath(), toWrite.toString());
        } catch (IOException e) {
            throw new SavingException(e.getMessage());
        }
    }
}
