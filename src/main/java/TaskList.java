import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * Represent a list containing all the tasks keyed in by the user.
 */
public class TaskList {
    protected List<Task> taskList;

    public TaskList(List<Task> ls) {
        this.taskList = ls;
    }

    public TaskList() {
        this.taskList = new LinkedList<>();
    }

    /**
     * Adds a toDo task to the task list
     * @param tsk the task to be added
     */
    public void addToDo(String tsk) {
        taskList.add(new toDo(tsk));
        System.out.println("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Adds a Deadline task to the task list
     * @param tskBy the due date of the Deadline task
     */
    public void addDeadline(String tskBy) {
        Scanner ddlSc = new Scanner(tskBy).useDelimiter("\\s*/by\\s*");
        String ddlTsk = ddlSc.next();
        String ddlBy = ddlSc.next();
        if (Date.isDate(ddlBy)) {
            taskList.add(new Deadline(ddlTsk, new Date(ddlBy)));
        } else {
            taskList.add(new Deadline(ddlTsk, ddlBy));
        }
        System.out.println("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Adds an Event task to the task list
     * @param tskAt the time of the event task
     */
    public void addEvent(String tskAt) {
        Scanner evtSc = new Scanner(tskAt).useDelimiter("\\s*/at\\s*");
        String evtTsk = evtSc.next();
        String evtAt = evtSc.next();
        if (Date.isDate(evtAt)) {
            taskList.add(new Event(evtTsk, new Date(evtAt)));
        } else {
            taskList.add(new Event(evtTsk, evtAt));
        }
        System.out.println("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

    /**
     * Prints all the tasks in list.
     */
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task tsk = taskList.get(i-1);
            System.out.println(i + ". " + tsk);
        }
    }

    /**
     * Marks a specific task as done
     * @param index Index of the task to be marked as done in the task list.
     */
    public void done(int index) {
        Task itemDone = taskList.get(index);
        itemDone.mardAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + itemDone);
    }

    /**
     * Deletes a task from the list.
     * @param index Index of the task to be deleted in the task list.
     */
    public void delete(int index) {
        Task itemDelete = taskList.remove(index);
        System.out.println("Noted. I've removed the task:\n  " + itemDelete);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Converts the content in the task list to a String to be stored in local file.
     * @return A string representing the current task list.
     */
    public List<String> toSave() {
        List<String> lines = new LinkedList<>();
        for (Task t : taskList) {
            lines.add(t.toSave());
        }
        return lines;
    }

}
