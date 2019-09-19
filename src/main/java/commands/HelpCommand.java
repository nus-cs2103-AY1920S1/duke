package commands;

import storage.Storage;

import ui.Ui;

import tasks.TaskList;

/**
 * The HelpCommand class provides assistance to user who are not familiar
 * with Duke's functions.
 */
public class HelpCommand extends Command {
    /**
     * Constructs and initializes the attributes of a new HelpCommand object.
     */
    public HelpCommand() {
        super();
    }

    /**
     * Carries out the command and does the necessary changes and prompts
     * user about the change.
     * @param task Current list of tasks.
     * @param ui Ui for user interactions.
     * @param storage Storage for writing of information to text file.
     */
    public String execute(TaskList task, Ui ui, Storage storage) {
        String listHelp = "list - checks all current tasking in your task list\n";
        String doneHelp = "done <task number> - marks the task as done. (eg. 'done 2' will mark the second task "
                + "on the list as done)\n";
        String deleteHelp = "delete <task number> - removes the task from the list. (eg. 'delete 3' will remove "
                + "the third task on the list)\n";
        String todoHelp = "todo <task name> - keys in a ToDo Task. (eg. todo drink water)\n";
        String eventHelp = "event <task name> /at <dd/mm/yyyy hhmm> - keys in an Event Task. (eg. event book "
                + "convention /at 02/09/2019 1400)\n";
        String deadlineHelp = "deadline <task name> /by <dd/mm/yyyy hhmm> - keys in a deadline Task. (eg. "
                + "clear rubbish /by 16/09/2019 2200)\n";
        String findHelp = "find <keyword> - searches for an existing task containing the keyword. (keyword is "
                + "CASE SENSITIVE)\n";
        String byeHelp = "bye - terminates the application.\n";

        return String.format("%s\n%s\n%s\n%s\n%s\n%s\n%s\n%s",
                listHelp, doneHelp, deleteHelp, todoHelp, eventHelp, deadlineHelp, findHelp, byeHelp);
    }
}
