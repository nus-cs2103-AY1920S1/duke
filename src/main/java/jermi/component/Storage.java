package jermi.component;

import static jermi.misc.Constant.DEADLINE_TYPE_CODE;
import static jermi.misc.Constant.EVENT_TYPE_CODE;
import static jermi.misc.Constant.STORAGE_DATE_TIME_INDEX;
import static jermi.misc.Constant.STORAGE_DESCRIPTION_INDEX;
import static jermi.misc.Constant.STORAGE_IS_DONE_INDEX;
import static jermi.misc.Constant.STORAGE_SAVE_FORMAT_DELIMITER;
import static jermi.misc.Constant.STORAGE_TYPE_CODE_INDEX;
import static jermi.misc.Constant.TO_DO_TYPE_CODE;

import jermi.exception.CorruptedSaveFormatException;
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
        this.initialise();
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
            String[] components = saveFormat.split(STORAGE_SAVE_FORMAT_DELIMITER);
            String typeCode = components[STORAGE_TYPE_CODE_INDEX];
            String isDone = components[STORAGE_IS_DONE_INDEX];
            String description = components[STORAGE_DESCRIPTION_INDEX];

            switch (typeCode) {
            case TO_DO_TYPE_CODE:
                task = new ToDo(description, isDone);
                break;
            default:
                String dateTime = components[STORAGE_DATE_TIME_INDEX];
                switch (typeCode) {
                case DEADLINE_TYPE_CODE:
                    task = new Deadline(description, dateTime, isDone);
                    break;
                case EVENT_TYPE_CODE:
                    task = new Event(description, dateTime, isDone);
                    break;
                default:
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
     * Sets up {@link TaskList} and file.
     *
     * @throws JermiException {@link LoadingException}.
     */
    private void initialise() throws JermiException {
        try {
            this.fileToTaskList();
        } catch (IOException e) {
            throw new LoadingException(e.getMessage());
        }
    }

    /**
     * Reads saved data in file and adds it to {@link TaskList} if file exists else creates new file.
     *
     * @throws IOException {@link IOException}.
     * @throws JermiException {@link CorruptedSaveFormatException}.
     */
    private void fileToTaskList() throws IOException, JermiException {
        try {
            List<String> lines = Files.readAllLines(this.file.toPath());
            for (String line : lines) {
                this.taskList.add(this.saveFormatToTask(line));
            }
        } catch (java.nio.file.NoSuchFileException e) {
            this.file.getParentFile().mkdirs();
            this.file.createNewFile();
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
