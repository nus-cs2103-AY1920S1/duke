package duke.dukeinterface;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.lang.StringBuilder;

import java.util.ArrayList;

/**
 * Stores the task list of Duke so as to track the list of tasks that the
 * user currently has.
 */
public class Tasklist {
    /**
     * This field combines strings together and form Duke's replies to user's inputs.
     */
    private StringBuilder sb;

    /**
     * This field stores the Array List of tasks that the user has.
     */
    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds the task into the task list.
     *
     * @param task Current task that needs to be added to the task list.
     */
    void add(Task task) {
        taskList.add(task);
    }


    /**
     * Reply the user when they added in a new task into the task list.
     *
     * @param task the current task that the user is referring to.
     * @param size the size of the user's task list.
     * @return Duke's reply in adding the task into the task list.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    String printTask(Task task, int size) {
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("     Got it. I've added this task: \n       " + task + "\n");
        sb.append("     Now you have " + size + " tasks in the list.\n");
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }


    /**
     * Reply the user with the tasks in the task list.
     *
     * @return Duke's reply which shows all the tasks in the task list.
     */
    @SuppressWarnings("StringConcatenationInsideStringBufferAppend")
    String printArray() {
        sb = new StringBuilder();
        sb.append(printLine());
        sb.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("     " + (i + 1) + "." + taskList.get(i) + "\n");
        }
        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Getter method which access the task list and retrieve the desired task.
     *
     * @param index Index of the specific task needed.
     * @return the specific task indicated.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the specific task indicated in the task list.
     *
     * @param index Index of the specific task to be deleted in the task list.
     */
    void remove(int index) {
        taskList.remove(index);
    }

    /**
     * Getter method which gives the size of the current task list.
     *
     * @return the size of the task list currently.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Print out the task currently on duke.txt during startup.
     *
     * @param textArr list formed during startup consisting of current tasks.
     * @return the list of task that are saved in duke.txt.
     */
    @SuppressWarnings({"StringConcatenationInsideStringBufferAppend", "IfCanBeSwitch"})
    public String loadEvents(ArrayList<String> textArr) {
        sb = new StringBuilder();
        for (int i = 0; i < textArr.size(); i++) {
            String[] str = textArr.get(i).split(" \\| ");
            Task task;

            if (str[0].equals("T")) {
                task = new ToDo(str[2]);
            } else if (str[0].equals("D")) {
                task = new Deadline(str[2], str[3]);
            } else if (str[0].equals("E")) {
                task = new Event(str[2], str[3]);
            } else {
                str = new String[]{"T", "✓", "Start Duke"};
                task = new ToDo(str[2]);
            }

            if (str[1].equals("✓")) {
                task.markAsDone();
            }

            sb.append("     " + (i + 1) + "." + task.toString() + "\n");

            taskList.add(task);
        }

        sb.append(printLine());
        sb.append("\n");
        return sb.toString();
    }

    /**
     * Gives a string as a border.
     *
     * @return a string that forms the border for duke.
     */
    private String printLine() {
        return "    ____________________________________________________________\n";
    }
}
