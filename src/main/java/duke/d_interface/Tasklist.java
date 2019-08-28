package duke.d_interface;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringJoiner;

/**
 * Stores the task list of Duke so as to track the list of tasks that the
 * user currently has.
 */
public class Tasklist {
    /**
     * This field stores the Array List of tasks that the user has.
     */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds the task into the task list.
     * @param task Current task that needs to be added to the task list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Delete specified task in the task list.
     * @param size Takes in the specified index of the task to be deleted.
     * @param currTask Specific task to be deleted.
     */
    public void deleteComplete(int size, Task currTask) {
        printLine();
        System.out.println("     Noted. I've removed this task: \n       " +
                currTask +
                "\n     Now you have " + (size - 1) + " tasks in the list.");
        printLine();
        System.out.println();
    }

    /**
     * Filter the string command and get the description of the task.
     * @param commandArr User input for the current task.
     * @return the description of the current task.
     * @throws DukeException Exception thrown when the description is empty.
     */
    public String getDescription(String[] commandArr) throws DukeException {
        StringJoiner description = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("todo")) {
            index = commandArr.length;
        } else if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = 1; i < index; i++) {
            description.add(commandArr[i]);
        }

        if (index == -1) {
            throw new DukeException(
                    "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        } else if (description.toString().equals("")) {
            throw new DukeException(
                    "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The description of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return description.toString();
    }

    /**
     * Filter the string command and get the timing of the task.
     * @param commandArr User input for the current task.
     * @return the timing of the current task.
     * @throws DukeException Exception thrown when the timing is empty.
     */
    public String getTime(String[] commandArr) throws DukeException {
        StringJoiner timing = new StringJoiner(" ");
        int index;
        if (commandArr[0].equals("deadline")) {
            index = Arrays.asList(commandArr).indexOf("/by");
        } else {
            index = Arrays.asList(commandArr).indexOf("/at");
        }

        for (int i = index + 1; i < commandArr.length; i++) {
            timing.add(commandArr[i]);
        }

        if (index == -1 || timing.toString().equals("")) {
            throw new DukeException(
                    "    ____________________________________________________________\n" +
                    "     ☹ OOPS!!! The timing of a " + commandArr[0] + " cannot be empty.\n" +
                    "    ____________________________________________________________\n");
        }

        return timing.toString();
    }

    /**
     * Adds the new task on hand and print out the new task list.
     * @param task Current task to be added into the task list.
     * @param size Gives the current size of the task list.
     */
    public void printTask(Task task, int size) {
        printLine();
        System.out.println("     Got it. I've added this task: \n       " + task);
        System.out.printf("     Now you have %d tasks in the list.\n", size);
        printLine();
        System.out.println();
    }

    /**
     * Marks the current task as done and print out the command.
     * @param currTask Current task which has to be marked as done.
     */
    public void taskComplete(Task currTask) {
        printLine();
        System.out.println("     Nice! I've marked this task as done: \n       " + currTask);
        printLine();
        System.out.println();
    }

    /**
     * Print out the current list of tasks on hand when the "list" command is
     * prompted.
     */
    public void printArray() {
        printLine();
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("     " + (i + 1) + "." + taskList.get(i));
        }
        printLine();
        System.out.println();
    }

    /**
     * Getter method which access the task list and retrieve the desired task.
     * @param index Index of the specific task needed.
     * @return the specific task indicated.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the specific task indicated in the task list.
     * @param index Index of the specific task to be deleted in the task list.
     */
    public void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Getter method which gives the size of the current task list.
     * @return the size of the task list currently.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Loads the duke.txt file and print out the current task list in it.
     * @param textArr String of text from the duke.txt file.
     */
    public void loadEvents(ArrayList<String> textArr) {
        for (int i = 0; i < textArr.size(); i++) {
            String[] str = textArr.get(i).split(" \\| ");
            Task task;

            if (str[0].equals("T")) {
                task = new ToDo(str[2]);
            } else if (str[0].equals("D")) {
                task = new Deadline(str[2], str[3]);
            } else {
                task = new Event(str[2], str[3]);
            }

            if (str[1].equals("\u2713")) {
                task.markAsDone();
            }

            System.out.println("     " + (i + 1) + "." + task.toString());

            taskList.add(task);
        }

        printLine();
        System.out.println();
    }

    /**
     * Generic line used for indentation purposes.
     */
    private static void printLine() {
        System.out.println("    ____________________________________________________________");
    }

    /**
     * Filters out the task list and gives the tasks which matches the keyword.
     * @param commandArr Input that the user gave in order to filter out the keyword.
     */
    public void searchKeyword(String[] commandArr) {
        StringJoiner sj = new StringJoiner(" ");
        for (int i = 1; i < commandArr.length; i++) {
            sj.add(commandArr[i]);
        }

        String phrase = sj.toString();

        printLine();
        System.out.println("     Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            if (currTask.getDescription().contains(phrase)) {
                System.out.println("     "  + currTask.toString() +
                        " {List index: " + (i + 1) + "}");
            }
        }
        printLine();
        System.out.println();
    }
}
