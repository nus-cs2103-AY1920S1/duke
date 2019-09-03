package duke.tasklist;

import java.util.List;
import java.util.ArrayList;

import duke.DukeException;
import duke.ui.Ui;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Deadline;
import duke.task.Event;

/**
 * Class for operations to task list.
 */
public class TaskList {
    /**
     * ArrayList of tasks.
     */
    private ArrayList<Task> tasks;

    /**
     * Default constructor for TaskList. Used in case of unable to locate file.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * Constructor for TaskList.
     *
     * @param tasks ArrayList of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Carry out operations according to commands from user.
     *
     * @param result integer passed from Parser class
     * @param input  original String input from user
     * @return ArrayList of tasks
     */
    public List<Task> doCommand(int result, String input) {
        String[] s = input.split("\\s");
        switch (result) {
            case 0:
                return tasks;
            case 1:
                System.out.print(Ui.line);
                System.out.println(Ui.indent + " Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(Ui.indent + " " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(Ui.line);
                return tasks;
            case 2:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("Task number cannot be empty.\n");
                    }
                    int n = Integer.parseInt(s[1]) - 1;
                    if (n < 0 || n > tasks.size() - 1) {
                        throw new DukeException("Task number is not in the list.\n");
                    }
                    tasks.get(n).markAsDone();
                    System.out.print(Ui.line);
                    System.out.println(Ui.indent + " Nice! I've marked this task as done: \n" +
                            Ui.indent + "   " + tasks.get(n));
                    System.out.println(Ui.line);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            case 3:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("Task number cannot be empty.\n");
                    }
                    int n = Integer.parseInt(s[1]) - 1;
                    if (n < 0 || n > tasks.size() - 1) {
                        throw new DukeException("Task number is not in the list.\n");
                    }
                    System.out.print(Ui.line);
                    System.out.println(Ui.indent + " Noted. I've removed this task: \n" +
                            Ui.indent + "   " + tasks.get(n) + "\n" +
                            Ui.indent + " Now you have " + (tasks.size() - 1) + " tasks in the list.");
                    System.out.println(Ui.line);
                    tasks.remove(n);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            case 4:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("The description cannot be empty.\n");
                    }
                    Task task = new ToDo(input.substring(5));
                    tasks.add(task);
                    System.out.print(Ui.line);
                    System.out.println(Ui.indent + " Got it. I've added this task: \n" +
                            Ui.indent + "   " + task + "\n" +
                            Ui.indent + " Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(Ui.line);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            case 5:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("The description cannot be empty.\n");
                    }
                    if (!input.contains("/by")) {
                        throw new DukeException("Wrong input.\n");
                    }
                    String desc = input.substring(9, input.indexOf('/') - 1);
                    String date = input.substring(input.indexOf("/by") + 4);
                    Task task = new Deadline(desc, date);
                    tasks.add(task);
                    System.out.print(Ui.line);
                    System.out.println(Ui.indent + " Got it. I've added this task: \n" +
                            Ui.indent + "   " + task + "\n" +
                            Ui.indent + " Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(Ui.line);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            case 6:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("The description cannot be empty.\n");
                    }
                    if (!input.contains("/at")) {
                        throw new DukeException("Wrong input.\n");
                    }
                    String desc = input.substring(6, input.indexOf('/') - 1);
                    String date = input.substring(input.indexOf("/at") + 4);
                    Task task = new Event(desc, date);
                    tasks.add(task);
                    System.out.print(Ui.line);
                    System.out.println(Ui.indent + " Got it. I've added this task: \n" +
                            Ui.indent + "   " + task + "\n" +
                            Ui.indent + " Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(Ui.line);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            case 7:
                try {
                    if (s.length <= 1) {
                        throw new DukeException("The description cannot be empty.\n");
                    }
                    ArrayList<Task> foundTasks = new ArrayList<Task>();
                    String search = input.substring(5).toLowerCase();
                    for (Task t : tasks) {
                        if (t.getDescription().toLowerCase().contains(search)) {
                            foundTasks.add(t);
                        }
                    }
                    System.out.print(Ui.line);
                    if (foundTasks.isEmpty()) {
                        System.out.println(Ui.indent + " There are no matching tasks.");
                    } else {
                        for (int i = 0; i < foundTasks.size(); i++) {
                            System.out.println(Ui.indent + " " + (i + 1) + "." + foundTasks.get(i));
                        }
                    }
                    System.out.println(Ui.line);
                } catch (DukeException ex) {
                    System.out.print(Ui.line);
                    System.out.print(Ui.indent + ex.getMessage());
                    System.out.println(Ui.line);
                }
                return tasks;
            default:
                System.out.print(Ui.line);
                System.out.print(Ui.indent + "Wrong input.\n");
                System.out.println(Ui.line);
                return tasks;
        }
    }
}
