package softeng.tasks;

import softeng.duke.Statistics;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import softeng.date.Date;

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
     * Adds a toDo task to the task list.
     * @param tsk the task to be added
     */
    public String addToDo(String tsk) {
        taskList.add(new toDo(tsk));
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1) + "\n");
        str.append("Now you have " + taskList.size() + " tasks in the list");
        return str.toString();
    }

    /**
     * Adds a Deadline task to the task list
     * @param tskBy the due date of the Deadline task
     */
    public String addDeadline(String tskBy) {
        Scanner ddlSc = new Scanner(tskBy).useDelimiter("\\s*/by\\s*");
        String ddlTsk = ddlSc.next();
        String ddlBy = ddlSc.next();
        if (Date.isDate(ddlBy)) {
            taskList.add(new Deadline(ddlTsk, new Date(ddlBy)));
        } else {
            taskList.add(new Deadline(ddlTsk, ddlBy));
        }
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1) + "\n");
        str.append("Now you have " + taskList.size() + " tasks in the list");
        return str.toString();
    }

    /**
     * Adds an Event task to the task list
     * @param tskAt the time of the event task
     */
    public String addEvent(String tskAt) {
        Scanner evtSc = new Scanner(tskAt).useDelimiter("\\s*/at\\s*");
        String evtTsk = evtSc.next();
        String evtAt = evtSc.next();
        if (Date.isDate(evtAt)) {
            taskList.add(new Event(evtTsk, new Date(evtAt)));
        } else {
            taskList.add(new Event(evtTsk, evtAt));
        }
        StringBuilder str = new StringBuilder();
        str.append("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1) + "\n");
        str.append("Now you have " + taskList.size() + " tasks in the list");
        return str.toString();
    }

    /**
     * Prints all the tasks in list.
     */
    public String list() {
        StringBuilder str = new StringBuilder();
        str.append("Here are the tasks in your list:\n");
        for (int i = 1; i <= taskList.size(); i++) {
            Task tsk = taskList.get(i-1);
            str.append(i + ". " + tsk + "\n");
        }
        return str.toString();
    }

    /**
     * Marks a specific task as done
     * @param index Index of the task to be marked as done in the task list.
     */
    public String done(int index, Statistics stats) {
        Task itemDone = taskList.get(index);
        stats.addToDone(itemDone);
        itemDone.mardAsDone();
        return "Nice! I've marked this task as done:\n  " + itemDone;
    }

    /**
     * Deletes a task from the list.
     * @param index Index of the task to be deleted in the task list.
     */
    public String delete(int index) {
        Task itemDelete = taskList.remove(index);
        StringBuilder str = new StringBuilder();
        str.append("Noted. I've removed the task:\n  " + itemDelete + "\n");
        str.append("Now you have " + taskList.size() + " tasks in the list.");
        return str.toString();
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

    public String find(String str) {
        List<Task> ls = new LinkedList<>();
        for (Task t : taskList) {
            if (t.match(str)) {
                ls.add(t);
            }
        }
        StringBuilder res = new StringBuilder();
        res.append("Here are the matching tasks in your list:");
        for (int i = 1; i <= ls.size(); i++) {
            Task tsk = ls.get(i-1);
            res.append(i + ". " + tsk + "\n");
        }
        return res.toString();
    }

    public String initialList() {
        StringBuilder str = new StringBuilder();
        if (taskList.isEmpty()) {
            str.append("Hi! You currently do not have any task!");
            return str.toString();
        } else {
            str.append("Here are your current tasks:\n");
            for (Task t : taskList) {
                str.append(t.toString() + "\n");
            }
            return str.toString();
        }
    }
}
