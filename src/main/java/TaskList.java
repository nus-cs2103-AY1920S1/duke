import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    protected List<Task> taskList;

    public TaskList(List<Task> ls) {
        this.taskList = ls;
    }

    public TaskList() {
        this.taskList = new LinkedList<>();
    }

        public void addToDo(String tsk) {
        taskList.add(new toDo(tsk));
        System.out.println("Got it. I've added this task:\n  " + taskList.get(taskList.size() - 1));
        System.out.println("Now you have " + taskList.size() + " tasks in the list");
    }

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

    public void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= taskList.size(); i++) {
            Task tsk = taskList.get(i-1);
            System.out.println(i + ". " + tsk);
        }
    }

    public void done(int index) {
        Task itemDone = taskList.get(index);
        itemDone.mardAsDone();
        System.out.println("Nice! I've marked this task as done:\n  " + itemDone);
    }

    public void delete(int index) {
        Task itemDelete = taskList.remove(index);
        System.out.println("Noted. I've removed the task:\n  " + itemDelete);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    public List<String> toSave() {
        List<String> lines = new LinkedList<>();
        for (Task t : taskList) {
            lines.add(t.toSave());
        }
        return lines;
    }

}
