package command;

import main.*;
import task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class ArchiveCommand extends Command {
    
    String archiveCommand;
    int taskNum = -1;
    
    public ArchiveCommand(String archiveCommand) {
        this.archiveCommand = archiveCommand.toLowerCase();
    }

    /**
     * Executes a full command if valid.
     *
     * @param tasks   The existing task list
     * @param ui      The Ui object which interacts with the current user
     * @param storage The Storage object which reads and writes to a specified file
     * @param archive The Archive object
     * @return The message to be displayed upon successful execution
     * @throws DukeException If command is invalid
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, Archive archive) throws DukeException {

        try {
            taskNum = Integer.parseInt(archiveCommand);
            if (taskNum != -1) {
                archiveCommand = "number";
            }
        } catch (NumberFormatException e) {
            //Do nothing. It means command is one of the other formats.
        }

        switch (archiveCommand){
        case "all":
            archiveAllTasks(tasks, storage, archive);
            return "Alright, I have archived all existing tasks.";
        case "today":
            archiveAllBeforeToday(tasks, storage, archive);
            return "I have archived all tasks which have expired before today.";
        case "done":
            archiveCompletedTasks(tasks, storage, archive);
            return "I have archived all completed tasks.";
        case "number":
            Task archivedTask = archiveByTaskId(tasks, storage, archive);
            return "I have archived the task: " + archivedTask;
        default:
            throw new DukeException("Invalid archive command. Archive only supports all/done/today/taskID.");
        }
    }

    private Task archiveByTaskId(TaskList tasks, Storage storage, Archive archive) throws DukeException {
        assert taskNum > -1: "Archive task at " + taskNum;
        if (taskNum <= 0 || taskNum > tasks.size()) {
            throw new DukeException("I can only archive tasks from 1 to " + tasks.size());
        }
        Task archivedTask = tasks.deleteTask(taskNum);
        try {
            archive.addTaskToArchive(archivedTask);
        } catch (IOException e) {
            throw new DukeException("I can't seem to write to your archive file.");
        }
        storage.save(tasks);
        return archivedTask;
    }

    private void archiveCompletedTasks(TaskList tasks, Storage storage, Archive archive) throws DukeException{
        ArrayList<Task> completedTasks = tasks.removeCompletedTasks();
        try {
            for (Task task : completedTasks) {
                archive.addTaskToArchive(task);
            }
        } catch (IOException e) {
            throw new DukeException("I can't seem to write to your archive file.");
        }
        storage.save(tasks);
    }

    private void archiveAllBeforeToday(TaskList tasks, Storage storage, Archive archive) throws DukeException {
        ArrayList<Task> expiredTasks = tasks.removeExpiredTasks();
        try {
            for (Task task : expiredTasks) {
                archive.addTaskToArchive(task);
            }
        } catch (IOException e) {
            throw new DukeException("I can't seem to write to your archive file.");
        }
        storage.save(tasks);
    }

    private void archiveAllTasks(TaskList tasks, Storage storage, Archive archive) throws DukeException {
        try {
            for (Task task : tasks) {
                archive.addTaskToArchive(task);
            }
        } catch (IOException e) {
            throw new DukeException("I can't seem to write to your archive file.");
        }
        tasks.clearAll();
        storage.save(tasks);
    }
}
