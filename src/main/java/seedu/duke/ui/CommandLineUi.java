package seedu.duke.ui;

import seedu.duke.statistic.Statistic;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;

/**
 * CommandLine Ui class, that handles returning formatted and parsed strings for DUKE CLI's implementation.
 */
public class CommandLineUi extends Ui {
    private static final String underscore = "    ____________________________________________________________" + "\n";
    private Boolean isCommandLineInterface;
    private Boolean isGraphicalUserInterface;

    /**
     * Default constructor.
     */
    public CommandLineUi() {
        isCommandLineInterface = true;
        isGraphicalUserInterface = false;
    }

    /**
     * Returns welcome string.
     *
     * @return String.
     */
    public static String getWelcomeString() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String intro = underscore
                + "      Hello! I'm Duke " + "\n"
                + "      What can I do for you?" + "\n"
                + underscore;
        return (logo + intro);
    }


    /**
     * Prints the Done Sequence, when the user command "done" is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param taskNum Integer index of the task which has been selected as "done".
     * @return String.
     */
    public String getDoneSequence(TaskList tasks, int taskNum) {
        String output = underscore + "     Nice! I've marked this task as done:\n"
                + "       [" + tasks.getTask(taskNum).getStatusIcon() + "] " + tasks.getTask(taskNum).getTaskName()
                + "\n" + underscore;
        return output;
    }

    /**
     * Returns the Todo Sequence, when the user command "Todo" is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param newTodo Todo class, which has been newly created.
     * @return String.
     */
    public String getTodoSequence(TaskList tasks, Todo newTodo) {
        String output = underscore + "     Got it. I've added this task:\n       "
                + newTodo.toString() + getTasksRemainingSequence(tasks.getSize());
        return output;
    }


    /**
     * Returns the Deadline sequence, when the user command "deadline" is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param newDeadline Deadline class, which has been newly created.
     * @return String.
     */
    public String getDeadlineSequence(TaskList tasks, Deadline newDeadline) {
        String output = underscore + "     Got it. I've added this task:\n       "
                + newDeadline.toString() + getTasksRemainingSequence(tasks.getSize());
        return output;
    }

    /**
     * Returns the Event sequence, when the user command "event" is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param newEvent Event class, which has been newly created.
     * @return String.
     */
    public String getEventSequence(TaskList tasks, Event newEvent) {
        String output = underscore + "     Got it. I've added this task:\n       "
                + newEvent.toString() + getTasksRemainingSequence(tasks.getSize());
        return output;
    }

    /**
     * Returns the Delete sequence, when "delete" command is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param taskToDelete Task class, which will be deleted from the list.
     * @return String
     */
    public String getDeleteSequence(TaskList tasks, Task taskToDelete) {
        String output = underscore + "     Noted. I've removed this task.\n       "
                + taskToDelete.toString() + getTasksRemainingSequence(tasks.getSize());
        return output;
    }


    /**
     * Returns the Bye sequence, when the user command "bye" is entered.
     *
     * @return String.
     */
    public String getByeSequence() {
        String output = underscore + "\n" + "     " + "Bye. Hope to see you again soon!" + "\n" + underscore + "\n";
        return output;
    }

    /**
     * Returns the string containing tasks in a pre-formatted form.
     * Eg. "1.[E][✘] Run (at: ERC)
     *      2.[D][✓] IPPT (by: 21st of December 2004, 8.15am)
     *      3.[E][✓] Lecture (at: LT7A)".
     *
     * @param tasks TaskList object
     * @return String of tasks
     */
    public String getListSequence(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            output += "     " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        output += underscore;
        return output;
    }

    /**
     * Returns the tasks which are similar for the "find" command.
     *
     * @param tasks TaskList object.
     * @return String.
     */
    public String getFoundTasks(TaskList tasks) {
        String output = underscore + "     Here are the matching tasks in your list:\n";
        output += getListSequence(tasks);
        return output;
    }

    /**
     * Returns the tasks remaining in the list.
     *
     * @param numOfTaskRemaining TaskList object.
     * @return String.
     */
    public String getTasksRemainingSequence(int numOfTaskRemaining) {
        String output = "\n     Now you have "
                + numOfTaskRemaining + " tasks in the list.\n" + underscore;
        return output;
    }

    /**
     * Returns all statistics.
     *
     * @param stat Statistic.
     * @return String.
     */
    public String getAllStatSequence(Statistic stat) {
        String output = "\n     Listing all statistics:"
                + "\n     Total Commands Executed:    " + stat.getTotalCommandsExecuted()
                + "\n     Total Tasks Deleted:        " + stat.getTotalTasksDeleted()
                + "\n     Total To-Dos Completed:     " + stat.getTotalTodosCompleted()
                + "\n     Total Deadlines Completed:  " + stat.getTotalDeadlinesCompleted()
                + "\n     Total Events Completed:     " + stat.getTotalEventsCompleted()
                + "\n" + underscore;
        return output;
    }

    /**
     * Returns reset stat sequence.
     *
     * @param stat Statistic object.
     * @return String.
     */
    public String getResetStatSequence(Statistic stat) {
        String output = "\n     All statistics have been reset";
        output += getAllStatSequence(stat);
        return output;
    }

    /**
     * Returns completed events sequence.
     *
     * @param stat Statistic object.
     * @param tasks TaskList object.
     * @return String.
     */
    public String getCompletedEventStatSequence(Statistic stat, TaskList tasks) {
        String encouragement = "";
        int eventsCompleted = stat.getCompletedEventsFromOneDayAgo(tasks);
        if (eventsCompleted == 0) {
            encouragement = "You can do better! :)";
        } else {
            encouragement = "Well Done!";
        }
        String output = "\n     Events completed today: "
                + stat.getCompletedEventsFromOneDayAgo(tasks)
                + "\n     " + encouragement + "\n" + underscore;
        return output;
    }

    /**
     * Returns IsCommandLineInterface.
     *
     * @return Boolean.
     */
    public Boolean isCommandLineInterface() {
        return this.isCommandLineInterface;
    }

    /**
     * Returns isGUI.
     *
     * @return Boolean.
     */
    public Boolean isGraphicalUserInterface() {
        return this.isGraphicalUserInterface;
    }

    /**
     * Prints the String to the Command Line.
     *
     * @param output String to be printed.
     */
    public void printToCommandLine(String output) {
        System.out.println(output);
    }


    /**
     * Returns completed deadline sequence.
     *
     * @param stat Statistic object.
     * @param tasks TaskList object.
     * @return String.
     */
    public String getCompletedDeadlineStatSequence(Statistic stat, TaskList tasks) {
        String encouragement = "";
        int eventsCompleted = stat.getCompletedDeadlinesFromOneDayAgo(tasks);
        if (eventsCompleted == 0) {
            encouragement = "You can do better! :)";
        } else {
            encouragement = "Well Done!";
        }
        String output = "\n     Events completed today: "
                + stat.getCompletedDeadlinesFromOneDayAgo(tasks)
                + "\n     " + encouragement + "\n" + underscore;
        return output;
    }


    /**
     * Returns completed todo sequence.
     *
     * @param stat Statistic object.
     * @param tasks TaskList object.
     * @return String.
     */
    public String getCompletedTodoStatSequence(Statistic stat, TaskList tasks) {
        String encouragement = "";
        int todosCompleted = stat.getCompletedTodosFromOneDayAgo(tasks);
        if (todosCompleted == 0) {
            encouragement = "You can do better! :)";
        } else {
            encouragement = "Well Done!";
        }
        String output = "\n     Events completed today: "
                + stat.getCompletedTodosFromOneDayAgo(tasks)
                + "\n     " + encouragement + "\n" + underscore;
        return output;
    }

}
