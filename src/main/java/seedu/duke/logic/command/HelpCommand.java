package seedu.duke.logic.command;

import java.util.Arrays;

import seedu.duke.model.task.TaskList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

/**
 * Defines a Command object that lists out command definitions specified.
 * Instructs users on how to use commands of this program.
 */
public class HelpCommand extends Command {

    // A list of constant definitions and usage instructions for all commands known to the program that may be of
    // help to a user who requests for these definitions.
    private static final String TODOHELP = "todo: Adds a todo into the tasklist.\n"
            + "Requires input to be in the format \'todo [task]\'.\n"
            + "eg. todo buy bread";
    private static final String DEADLINEHELP = "deadline: Adds a deadline into the tasklist.\n"
            + "Requires input to be in the format \'deadline [task] /by [DD/MM/YYYY]\', "
            + "or \'deadline [task] /by [DD/MM/YYYY] [HHMM]\', where dates and times (24-hour clock) are valid.\n"
            + "eg. deadline finish homework /by 15/09/2019\n"
            + "eg. deadline submit assignment /by 16/09/2019 2200";
    private static final String EVENTHELP = "event: Adds an event into the tasklist.\n"
            + "Requires input to be in the format \'event [task] /at [DD/MM/YYYY] [HHMM]\', "
            + "where dates and times (24-hour clock) are valid.\n"
            + "eg. event upcoming concert /at 22/09/2019 1500-1600";
    private static final String DONEHELP = "done: Marks a task or multiple tasks in the tasklist as done.\n"
            + "Requires input to be in the format \'done [number representing task in tasklist]\' or \n"
            + "\'done [range1, range2, range3, ...] or \'done all\' to mark all tasks as done.\n"
            + "eg. done 2-4, 6, 8-9\n"
            + "eg. done all";
    private static final String UNDONEHELP = "undone: Marks a task or multiple tasks in the tasklist as undone.\n"
            + "Requires input to be in the format \'done [number representing task in tasklist]\' or \n"
            + "\'undone [range1, range2, range3, ...]\' or \'undone all\' to mark all tasks as undone.\n"
            + "eg. undone 2-4, 6, 8-9\n"
            + "eg. undone all";
    private static final String DELETEHELP = "delete: Deletes a task or multiple tasks from the tasklist.\n"
            + "Requires input to be in the format \'delete [number representing task in tasklist]\' or \n"
            + "\'delete [range1, range2, range3, ...]\' or \'delete all\' to remove all tasks.\n"
            + "eg. delete 2-4, 6, 8-9\n"
            + "eg. delete all";
    private static final String LISTHELP = "list: Lists all tasks in the tasklist stored.\n"
            + "Requires input to be in the format \'list\'.\n"
            + "eg. list";
    private static final String FINDHELP = "find: Finds tasks in the tasklist given keywords.\n"
            + "Requires input to be in the format \'find [keyword]\'.\n"
            + "Keywords could be name of tasks, substrings of it, type of tasks, date in DD/MM/YYYY format, "
            + "time in HHMM format, whether or not the task is done in the form \'done\', \'not done\', "
            +  "or \'undone\', or   `at` (for events) / `by` (for deadlines).\n"
            + "Keywords are not case-sensitive."
            + "eg. find buy milk\n"
            + "eg. find event\n"
            + "eg. find 15/12/2019\n"
            + "eg. find 2019\n"
            + "eg. find done\n"
            + "eg. find undone\n"
            + "eg. find not done";
    private static final String UNDOHELP = "undo: Reverts, if possible, the tasklist to the version before the most "
            + "recent command was executed, or executes undo the number of times specified by the user.\n"
            + "Requires input to be in the format \'undo\' (performs undo once) or "
            + "\'undo [number of times to undo]\'.\n"
            + "eg. undo\n"
            + "eg. undo 5";
    private static final String REDOHELP = "redo: Reverts, if possible, the tasklist to the version before the most "
            + "recent undo command was executed, or executes redo the number of times specified by the user.\n"
            + "Requires input to be in the format \'redo\' (performs redo once) or "
            + "\'redo [number of times to redo]\'.\n"
            + "eg. redo\n"
            + "eg. redo 5";
    private static final String SORTHELP = "sort: Sorts the list (and saves it) based on the criteria requested for by "
            + "the user.\n" + "Requires input to be in the format \'sort [criteria]\'.\n"
            + "Criteria include: \n"
            + "- description: Sorts the list of tasks by order of task descriptions in alphabetical order.\n"
            + "- revdescription: Sorts the list of tasks by order of task descriptions in "
            + "reversed alphabetical order.\n"
            + "- type: Sorts the list of tasks by order of type of task in alphabetical order.\n"
            + "- revtype: Sorts the list of tasks by order of type of task in reversed alphabetical order.\n"
            + "- date: Sorts the list of tasks by order of date from soonest to latest.\n"
            + "- revdate: Sorts the list of tasks by order of date from latest to soonest.\n"
            + "- id: Sorts the list of tasks by order of creation from earliest to latest.\n"
            + "- revid: Sorts the list of tasks by order of creation from latest to earliest.\n"
            + "- done: Sorts the list of tasks by order of done status with done tasks arranged first.\n"
            + "- undone: Sorts the list of tasks by order of done status with undone tasks arranged first.\n"
            + "eg. sort description\n"
            + "eg. sort date\n"
            + "eg. sort revid\n"
            + "eg. sort undone\n"
            + "eg. sort type";
    private static final String RANDOMHELP = "random: Generates a random list of tasks.\n"
            + "Requires input to be in the format \'random [size of random list]\'."
            + "Note: This command OVERRIDES the existing list. An overriden list, however, may be retrieved "
            + "using the \'undo\' command.\n"
            + "eg. random 1000";
    private static final String BYEHELP = "bye: Instructs the program to exit.\n"
            + "Requires input to be in the format \'bye\'.\n"
            + "eg. bye";
    private static final String HELPHELP = "help: Shows a list of specified commands or all known commands so far, "
            + "sorted in alphabetical order.\n"
            + "Requires input to be in the format \'help\' or \'help [command1] [command2] etc.\'.\n"
            + "eg. help\n"
            + "eg. help todo sort unknown undo bye ";
    private static final String ABOUTHELP = "about: Shows information about the developer and this Duke program.\n"
            + "Requires input to be in the format \'about\'.\n"
            + "eg. about";
    private static final String CLEARCACHEHELP = "clearcache: Removes all versions of task lists in the timeline used "
            + "for undo and redo operations, except for the current version.\n"
            + "WARNING: THIS OPERATION CANNOT BE UNDONE.\n"
            + "Requires input to be in the format \'clearcache\'. "
            + "The program will thereafter prompt for user to confirm by keying in \'CoNfiRMtOclEaR\' before "
            + "proceeding. This operation may be useful if the size of the timeline happens to get too large and "
            + "slows down other operational procedures. The key will not be recognised on it's own without first "
            + " entering \'clearcache\'. The process is aborted if input right after \'clearcache\' does not match the "
            + "required key and the user will have to enter \'clearcache\' again if he/ she intends to re-initiate "
            + "the process.\n"
            + "eg. clearcache\n"
            + "* program prompts for confirmation *\n"
            + "eg. CoNfiRMtOclEaR";
    private static final String UNKNOWN = "Unknown command.";
    private static final String ADDITIONALINFO = "Note that all commands are NOT case sensitive.";
    private static final String FURTHERPROMPT = "Can't find what you're looking for?\nInput \'help\' "
            + "for the full list of commands known to the this program and information on their usage.";
    private static final String USERGUIDE = "You may also refer to this program's user guide via: "
            + "http://whneo97.github.io/duke";


    private String taskString;

    /**
     * Creates an instance of HelpCommand.
     * Stores to attributes of HelpCommand relevant search information required to create a HelpCommand.
     * @param taskString String representation of keywords user needs help with.
     */
    public HelpCommand(String taskString) {
        this.taskString = taskString;
    }

    /**
     * Returns a String containing compiled definitions for commands requested by a client that calls this method.
     * Takes in variable number of arguments depending on the number of commands required by the user.
     * @param requestedCommandList A list of Strings containing possible commands the user needs help with.
     * @return String containing compiled definitions for commands the user needs help with.
     */
    private String getHelpDefinitions(String...requestedCommandList) {
        String out = "";
        Arrays.sort(requestedCommandList);
        for (String requestedCommand : requestedCommandList) {
            if (requestedCommand.equals("todo")) {
                out += TODOHELP;
            } else if (requestedCommand.equals("deadline")) {
                out += DEADLINEHELP;
            } else if (requestedCommand.equals("event")) {
                out += EVENTHELP;
            } else if (requestedCommand.equals("done")) {
                out += DONEHELP;
            } else if (requestedCommand.equals("undone")) {
                out += UNDONEHELP;
            } else if (requestedCommand.equals("delete")) {
                out += DELETEHELP;
            } else if (requestedCommand.equals("list")) {
                out += LISTHELP;
            } else if (requestedCommand.equals("find")) {
                out += FINDHELP;
            } else if (requestedCommand.equals("undo")) {
                out += UNDOHELP;
            } else if (requestedCommand.equals("redo")) {
                out += REDOHELP;
            } else if (requestedCommand.equals("sort")) {
                out += SORTHELP;
            } else if (requestedCommand.equals("random")) {
                out += RANDOMHELP;
            } else if (requestedCommand.equals("about")) {
                out += ABOUTHELP;
            } else if (requestedCommand.equals("clearcache")) {
                out += CLEARCACHEHELP;
            } else if (requestedCommand.equals("bye")) {
                out += BYEHELP;
            } else if (requestedCommand.equals("help")) {
                out += HELPHELP;
            } else {
                out += requestedCommand + ": " + UNKNOWN;
            }
            out += "\n\n";
        }
        return out.trim();
    }
    
    /** Executes a HelpCommand object using information stored in instance attributes.
     * Displays a message that shows all help information requested
     * @param tasks TaskList that was being used by a client of this command.
     * @param ui Ui with methods that are called to display a message showing all command definitions requested.
     * @param storage Storage that contains saved String representation of the given TaskList.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String helpDefinitions;
        if (taskString.equals("")) {
            helpDefinitions = getHelpDefinitions("todo", "deadline", "event", "done", "undone",
                    "delete", "list", "find", "undo", "redo", "sort", "random", "about", "clearcache", "bye", "help")
                    + "\n\n"
                    + ADDITIONALINFO;
        } else {
            helpDefinitions = getHelpDefinitions(taskString.split(" ")) + "\n\n"
                    + ADDITIONALINFO + "\n\n" + FURTHERPROMPT;
        }

        helpDefinitions += "\n\n" + USERGUIDE;

        ui.showHelpMessage(helpDefinitions);
    }
}