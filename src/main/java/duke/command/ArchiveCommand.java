package duke.command;

import duke.storage.DukeFileWriteException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.MainWindow;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstraction of an archive command to write all tasks to disk.
 */
class ArchiveCommand extends WritableCommand {
    /** The directory name to use in storing the archived data. */
    private static final String RECURSIVE_PARENT_DIR_NAME = "archive";
    /** Base file name to use for storing the archived data. */
    private static final String BASE_FILE_NAME = "taskArchive";
    /** Date time format to concatenate to the BASE_FILE_NAME. */
    private static final String DATE_TIME_SUFFIX = "'@'dMMMy_HH'hours'mm'minutes'ss'seconds.txt'";
    /** The dateTime formatter that uses the DATE_TIME_SUFFIX pattern. */
    private static final DateTimeFormatter FILE_SUFFIX_FORMATTER =
            DateTimeFormatter.ofPattern(DATE_TIME_SUFFIX);

    /**
     * Generic constructor of the command from its arguments.
     *
     * @param commandArgs String array of arguments.
     */
    ArchiveCommand(String[] commandArgs) {
        super(commandArgs);
    }

    /**
     * Saves the task list to disk then wipes the current task list.
     * The file name is derived from the base_file_name and concatenated with
     * the current date time, formatted by DATE_TIME_FORMAT.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     */
    @Override
    void run(TaskList tasks, MainWindow ui, Storage storage) {
        String archiveFileName = BASE_FILE_NAME
                + FILE_SUFFIX_FORMATTER.format(LocalDateTime.now());
        Storage archive = new Storage(archiveFileName, RECURSIVE_PARENT_DIR_NAME, ui);

        try {
            archive.saveTasksToDisk(tasks);
            tasks.deleteAllTasks();
            ui.showMessage("Got it. I've archived all your tasks to disk and wiped the task list!:\n");
        } catch (DukeFileWriteException ex) {
            ui.showMessage(" =X  OOPS!!! I couldn't archive your tasks!,\n"
                    + " I've left the task list as is!");
        }
    }

    /**
     * Checks if there are any tasks in the task list.
     * Also checks if there are extraneous arguments provided.
     *
     * @param tasks TaskList of tasks to use.
     * @param ui MainWindow to use for displaying command output.
     * @param storage Storage for WritableCommands to execute write-to-disk operations.
     * @throws DukeInvalidArgumentException If there are no tasks in the task list or
     *     there are extraneous arguments.
     */
    @Override
    void validate(TaskList tasks, MainWindow ui, Storage storage) throws DukeInvalidArgumentException {
        if (commandArgs.length >= 1) {
            throw new DukeInvalidArgumentException(
                    "Encountered extraneous arguments after archive command",
                    " =X  OOPS!!! There shouldn't be anything following 'archive',\n"
                            + " are you sure you wanted to exit?");
        }

        if (tasks.getSize() == 0) {
            throw new DukeInvalidArgumentException(
                    "Attempted to archive empty task list.",
                    " =X  OOPS!!! I cannot archive an empty task list!,\n");
        }
    }
}
