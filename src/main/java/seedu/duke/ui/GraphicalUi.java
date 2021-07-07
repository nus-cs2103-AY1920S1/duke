package seedu.duke.ui;

import seedu.duke.statistic.Statistic;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;

/**
 * Graphical Ui class, that handles returning formatted and parsed strings for DUKE GUI's implementation
 * Main difference is that ticks and crosses are swapped with V and X.
 * Unnecessary underscores and spaces have been removed from the CLI's implementation.
 */
public class GraphicalUi extends Ui {
    private Boolean isCommandLineInterface;
    private Boolean isGraphicalUserInterface;

    /**
     * Default constructor.
     */
    public GraphicalUi() {
        isCommandLineInterface = false;
        isGraphicalUserInterface = true;
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
        String intro =
                "Hello! I'm Duke " + "\n"
                + "What can I do for you?" + "\n";
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
        String output = "Nice! Another happy landing!:\n"
                + "[" + tasks.getTask(taskNum).getStatusIcon() + "] " + tasks.getTask(taskNum).getTaskName()
                + "\n";
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
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
        String output = "Got it. I've added this task:\n"
                + "As a wise person said, there is no to-do, there is only try\n"
                + "Oh wait...\n"
                + newTodo.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
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
        String output = "Got it. I've added this deadline task:\n"
                + "Remember to watch our for those wrist-rockets!\n"
                + newDeadline.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
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
        String output = "Understood. I've added this dangerous task:\n"
                + newEvent.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    /**
     * Returns the Delete sequence, when the user command "delete" is entered.
     *
     * @param tasks TaskList class, which contains a list of Task objects.
     * @param taskToDelete Task class, which will be deleted from the list.
     * @return String.
     */
    public String getDeleteSequence(TaskList tasks, Task taskToDelete) {
        String output = "Noted. I've removed this task from the archives.\n"
                + taskToDelete.toString() + getTasksRemainingSequence(tasks.getSize());
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    /**
     * Returns the Bye sequence, when the user command "bye" is entered.
     *
     * @return String.
     */
    public String getByeSequence() {
        String output = "\n" + "The Force will be with you, always" + "\n";
        return (output);
    }

    /**
     * Returns the string containing tasks in a pre-formatted form.
     * Eg. "1.[E][X] Run (at: ERC)
     *      2.[D][V] IPPT (by: 21st of December 2004, 8.15am)
     *      3.[E][V] Lecture (at: LT7A)".
     *
     * @param tasks TaskList object
     * @return String of tasks
     */
    public String getListSequence(TaskList tasks) {
        String output = "";
        for (int i = 0; i < tasks.getSize(); i++) {
            output += (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        output = output.replace("✓", "V");
        output = output.replace("✘", "X");
        return output;
    }

    /**
     * Returns the tasks which are similar for the "find" command.
     *
     * @param tasks TaskList object.
     * @return String.
     */
    public String getFoundTasks(TaskList tasks) {
        String output = "Here are the matching tasks from the Jedi Holocrons:\n";
        output += getListSequence(tasks);
        return (output);
    }

    /**
     * Returns the tasks remaining in the list.
     *
     * @param numOfTaskRemaining TaskList object.
     * @return String
     */
    public String getTasksRemainingSequence(int numOfTaskRemaining) {
        String output = "\nNow you have "
                + numOfTaskRemaining + " tasks in the list.\n";
        return output;
    }

    /**
     * Returns all statistics.
     *
     * @param stat Statistic.
     * @return String.
     */
    public String getAllStatSequence(Statistic stat, TaskList tasks) {
        String output = "Listing all statistics:"
                + "\nTotal Commands Executed:    " + stat.getTotalCommandsExecuted()
                + "\nTotal Tasks Deleted:                " + stat.getTotalTasksDeleted()
                + "\n\nTotal To-Dos Completed:              " + stat.getTotalTodosCompleted()
                + "\nTotal Todos Completed TODAY:   " + stat.getCompletedTodosFromOneDayAgo(tasks)
                + "\n\nTotal Deadlines Completed:              " + stat.getTotalDeadlinesCompleted()
                + "\nTotal Deadlines Completed TODAY:   " + stat.getCompletedDeadlinesFromOneDayAgo(tasks)
                + "\n\nTotal Events Completed:                  " + stat.getTotalEventsCompleted()
                + "\nTotal Events Completed TODAY:    " + stat.getCompletedEventsFromOneDayAgo(tasks)
                + "\n\n Death sticks consumed TODAY:     You don't want to sell me death sticks"


                + "\n";
        return output;
    }

    /**
     * Returns reset stat sequence.
     *
     * @param stat Statistic object.
     * @return String.
     */
    public String getResetStatSequence(Statistic stat, TaskList tasks) {
        String output = "All statistics have been reset\n";
        output += getAllStatSequence(stat, tasks);
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
            encouragement = "You can do better with some meditation :)";
        } else {
            encouragement = "Well Done!";
        }


        String output = "\nEvents completed today: "
                + eventsCompleted
                + "\n" + encouragement + "\n";
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
     * Returns completed deadline sequence.
     *
     * @param stat Statistic object.
     * @param tasks TaskList object.
     * @return String.
     */
    public String getCompletedDeadlineStatSequence(Statistic stat, TaskList tasks) {
        String encouragement = "";
        int deadlinessCompleted = stat.getCompletedEventsFromOneDayAgo(tasks);
        if (deadlinessCompleted == 0) {
            encouragement = "You can do better, young Padawan! :)";
        } else {
            encouragement = "Well Done!";
        }
        String output = "\nDeadlines completed today: "
                + deadlinessCompleted
                + "\n" + encouragement + "\n";
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
            encouragement = "You can do better! There is no try. :)";
        } else {
            encouragement = "Well Done!";
        }
        String output = "\nTo-dos completed today: "
                + todosCompleted
                + "\n" + encouragement + "\n";
        return output;
    }
}
