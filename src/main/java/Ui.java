import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

/**
 * Takes in the user input into a Duke object and displays output to the user.
 */
public class Ui {
    protected String longLine = "----------------------------------------------------------------------\n";
    protected String shortLine = "-----------------------------------\n";
    protected String welcomeMessage = "What can I do for you?\n"
            + "\n Enter 'help' for list of commands you can use!";
    protected String byeMessage = "Bye. Hope to see you again soon!\n";
    Scanner scanner;

    /**
     * Creates an Ui object to take input user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next input from the user.
     *
     * @return the String command input by the user.
     */
    public String readCommand() {
        return this.scanner.nextLine();
    }

    /**
     * Returns the welcome message when a Duke object is started.
     *
     * @return String welcome message when Duke is started.
     */
    public String showWelcomeMessage() {
        return welcomeMessage;
    }

    /**
     * Returns the list of tasks in the TaskList object argument.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     * @return String list of tasks in TaskList.
     */
    public String showListMessage(TaskList tasks) {
        String list = "Here are the tasks in your list:" + "\n";
        for (int i = 0; i < tasks.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + tasks.getTask(i) + "\n";
        }

        return list;
    }

    /**
     * Returns the exception message of the argument exception to the user.
     *
     * @param e Exception whose message needs to be displayed.
     * @return String exception message of the argument exception.
     */
    public String showExceptionError(Exception e) {
        return e.getMessage() + "\n";
    }

    /**
     * Returns message when a task is added to the TaskList object arugment.
     *
     * @param task Task object to be added to TaskList object.
     * @param taskListSize Number of Task objects in TaskList object after the Task object is added.
     * @return String message when a task is added to the TaskList.
     */
    public String showAddTaskMessage(Task task, int taskListSize) {
        String output = "Got it. I've added this task: \n" + "       " + task.toString() + "\n"
                + "Now you have " + taskListSize + " task(s) in the list.\n";

        return output;
    }

    /**
     * Returns message when a Task object is marked as done.
     *
     * @param task Task object that is marked as done.
     * @return String message when a Task object is marked as done.
     */
    public String showMarkTaskAsDoneMessage(Task task) {
        return "Nice! I've marked this task as done:\n       " + task.toString() + "\n";
    }

    /**
     * Returns String message when a Task object is deleted.
     *
     * @param taskListSize Number of Task objects in TaskList object after the Task object is deleted.
     * @return String message when a Task object is deleted.
     */
    public String showDeleteTaskMessage(Task task, int taskListSize) {
        return "Noted. I've removed this task:\n       " + task.toString()
                + "\n     Now you have " + taskListSize + " task(s) in the list.\n";
    }

    /**
     * Returns exit message when a Duke object stops running.
     *
     * @return String exit message when a Duke object stops running.
     */
    public String showByeMessage() {
        return byeMessage;
    }

    /**
     * Returns the list of tasks in the TaskList object argument.
     * The tasks in the TaskList object argument contains the keyword entered by the user.
     *
     * @param tasks TaskList object with tasks to be displayed to the user in a list.
     * @return String list of tasks in the TaskList object argument.
     */
    public String showFindTasksMessage(TaskList tasks) {
        String list = "Here are the matching tasks in your list:\n";
        for (int i = 0; i < tasks.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        return list;
    }

    /**
     * Returns confirmation message that a new archive has been created.
     *
     * @param archiveName Name of new archive being created.
     * @return Confirmation message that a new archive has been created.
     */
    public String showCreateArchiveMessage(String archiveName) {
        return "New archive created: " + archiveName + "\n";
    }

    /**
     * Returns the message that a task has been archived and lists the tasks of the archive it was added to.
     *
     * @param archiveName Name of archive that the task was added to.
     * @param archivedTasks TaskList of the archive.
     * @param archivedTask The Task object that was archived.
     * @return Message that a task has been archived and lists the tasks of the archive it was added to.
     */
    public String showAddArchiveTaskMessage(String archiveName, TaskList archivedTasks, Task archivedTask) {
        String list =  archivedTask.getDescription() + " has been archived to " + archiveName + ":\n";
        for (int i = 0; i < archivedTasks.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + archivedTasks.getTask(i).toString() + "\n";
        }

        return list;
    }

    /**
     * Returns the list of tasks in an archive.
     *
     * @param archiveName Name of archive.
     * @param archive TaskList of archive.
     * @return The list of tasks in an archive.
     */
    public String showViewArchiveMessage(String archiveName, TaskList archive) {
        String list = "Here are the tasks in the " + archiveName + " archive:\n";
        for (int i = 0; i < archive.taskListSize(); i++) {
            list = list + "     " + (i + 1) + "." + archive.getTask(i).toString() + "\n";
        }

        return list;
    }

    /**
     * Returns the lists of tasks in all archives.
     *
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @retur The lists of tasks in all archives.
     */
    public String showViewAllArchiveMessage(HashMap<String, TaskList> archives) {
        String list = "Here are all your saved archives:\n\n";
        Set<String> archiveNames = archives.keySet();
        for (String archive: archiveNames) {
            list += archive + "\n" + shortLine;

            TaskList archivedTasks = archives.get(archive);
            for (int i = 0; i < archivedTasks.taskListSize(); i++) {
                list = list + "     " + (i + 1) + "." + archivedTasks.getTask(i).toString() + "\n";
            }

            list += "\n";
        }

        return list;
    }

    /**
     * Returns the list of archive names.
     *
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return The list of archive names.
     */
    public String showListArchiveMessage(HashMap<String, TaskList> archives) {
        String list = "Here are all your saved archives:\n\n";
        Set<String> archiveNames = archives.keySet();
        for (String archive: archiveNames) {
            list = list + "-- " + archive + "\n";
        }
        return list;
    }

    /**
     * Returns the confirmation message of when a task is deleted from an archive.
     *
     * @param removedTask Task that was removed from an archive.
     * @param archiveName Name of archive that had a task removed from it.
     * @return Confirmation message of when a task is deleted from an archive.
     */
    public String showDeleteArchiveTaskMessage(Task removedTask, String archiveName) {
        return "Noted. I've removed this task:\n       " + removedTask.toString()
                + "\nfrom the " + archiveName + "archive.";
    }

    /**
     * Returns the confirmation message of when an entire archive deleted, and
     * also displays the list of remaining archive names.
     *
     * @param archiveName Archive that is being deleted.
     * @param archives HashMap of all TaskList archives, with their archive name as keys.
     * @return he confirmation message of when an entire archive deleted,
     *        and also displays the list of remaining archive names.
     */
    public String showDeleteAllArchiveMessage(String archiveName, HashMap<String, TaskList> archives) {
        String list = "Noted. I've removed the " + archiveName + " archive\n"
                + "Here are the rest of your archives:\n";
        Set<String> archiveNames = archives.keySet();
        for (String archive: archiveNames) {
            list = list + "-- " + archive + "\n";
        }
        return list;
    }

    /**
     * Returns the confirmation message of when an archive task is unarchived and reverted back to the list of tasks.
     *
     * @param archiveName Name of archive that has task being reverted.
     * @param revertedTask Task being unarchived and reverting back to the list of tasks.
     * @return Confirmation message of when an archive task is unarchived and reverted back to the list of tasks.
     */
    public String showRevertArchiveTaskMessage(String archiveName, Task revertedTask) {
        return "Noted. I've removed this task:\n       " + revertedTask.toString()
                + "\nfrom the " + archiveName + " archive and added back to your list of tasks.";
    }

    /**
     * Returns the help list of commands to use Duke's features.
     *
     * @return list of commands to use Duke's features.
     */
    public String showHelpMessage() {
        String helpList = "Hello! I see you need some help using Duke.\n"
                + "Don't worry! Here is the complete list of commands:\n" + longLine;
        helpList += "To add a new task (Todo, event or deadline):\n"
                + "'todo [todo task description]'\n\n"
                + "'event [event task description] /at [event date and time in 24 hour format (dd/mm/yyyy hh:mm)]'\n\n"
                + "'deadline [deadline task description] /by [deadline date and time in 24 hour format "
                + "(dd/mm/yyyy hh:mm)]'\n" + longLine;
        helpList += "To display current list of tasks:\n"
                + "'list'\n" + longLine;
        helpList += "To delete a task:\n" + "'delete [task list number]'\n" + longLine;
        helpList += "To mark a task as done:\n" + "'done [task list number]'\n" + longLine;
        helpList += "To search for a task with a certain keyword in its description:\n"
                + "'find [keyword]'\n" + longLine;
        helpList += "To exit and close Duke:\n" + "'bye'\n" + longLine;
        helpList += "Archives Instructions:\n\n"
                + "Create a new empty archive:\n'archive create [archive name]'\n\n"
                + "Add task from task list to an existing archive:\n'archive add [task list index] [archive name]'\n\n"
                + "Display list of existing archive names:\n'archive list'\n\n"
                + "Display list of tasks in an existing archive:\n'archive view [archive name]'\n\n"
                + "Display lists of tasks of all existing archives:\n'archive viewall'\n\n"
                + "Delete a task in an archive:\n'archive delete [task archive list index] [archive name]'\n\n"
                + "Delete an entire archive:\n'archive deleteall [archive name]'\n\n"
                + "To unarchive a task in an existing archive and revert it back to the current list of tasks:\n"
                + "'revert [task archive list index] [archive name]'\n"
                + longLine;
        helpList += "I hope this helps :)\nEnter 'help' again to display this again!!";
        return helpList;


    }

}
