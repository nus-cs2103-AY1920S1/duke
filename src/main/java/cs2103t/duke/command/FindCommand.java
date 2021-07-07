package cs2103t.duke.command;

import cs2103t.duke.file.Storage;
import cs2103t.duke.task.NoteList;
import cs2103t.duke.task.Task;
import cs2103t.duke.task.TaskList;
import cs2103t.duke.ui.Ui;

import java.util.List;

/**
 * Represents a find command. Searches for occurrence of a given phrase in the description of task.
 * This includes searches into the notes of a task, or also searching for general notes.
 */
public class FindCommand extends Command {
    /** Keyword to find in task strings. */
    private String wordToFind;

    /**
     * Constructs a find command.
     * @param keyword phrase to find in task descriptions.
     */
    public FindCommand(String keyword) {
        this.wordToFind = keyword;
        super.isExit = false;
    }

    /**
     * Finds tasks that contain keyword.
     * @param taskList TaskList agent to handle list of tasks.
     * @param ui Ui in charge of printing.
     * @param storageTasks Storage agent in charge of reading/writing to file.
     * @param storageNotes
     * @param noteList
     * @return String containing list of tasks that have that keyword.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storageTasks, Storage storageNotes, NoteList noteList) {
        List<Task> foundTasks = taskList.findTasks(this.wordToFind);
        if (foundTasks.isEmpty()) {
            return ui.dukeRespond("Sorry! I can't find any matching tasks in your list. Try another phrase?");
        }

        return ui.dukeRespond(getStringArray(foundTasks));
    }

    private String[] getStringArray(List<Task> tasks) {
        String[] strings = new String[tasks.size() + 1];
        strings[0] = "Here are the matching tasks in your list:";
        int listIndex = 0;
        for (Task task : tasks) {
            listIndex++;
            strings[listIndex] = String.format("%d.%s", listIndex, task.toString());
        }
        return strings;
    }
}
