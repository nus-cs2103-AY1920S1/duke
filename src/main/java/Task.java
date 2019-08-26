import java.io.PrintWriter;
import java.util.ArrayList;

abstract class Task {
    private int taskNumber;
    private String taskCheck;
    private String taskName;
    private String type;

    public Task(int i, String tC, String tN, String t) {
        taskNumber = i;
        taskCheck = tC;
        taskName = tN;
        type = t;
    }

    public abstract String getAB();

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskCheck() {
        return taskCheck;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getType() {
        return type;
    }

    public void changeTaskCheck() {
        taskCheck = "[✓]";
    }

    //when input is "list"
    static void printList(ArrayList<Task> a) {
        System.out.println("Here are the tasks in your list:");
        for (Task t : a) {
            System.out.println(t);
        }
    }

    //when input is "done"
    static void markAsDone(int i, ArrayList<Task> a) {
        System.out.println("Nice! I've marked this task as done:");
        Task t = a.get(i - 1);
        String currentTask = t.getTaskName();
        String getType = t.getType();
        Task doneTask;
        if(getType.equals("todo")) {
            doneTask = new Todo(i, "[✓]", currentTask, getType);
            a.set(i - 1, doneTask);
        } else if (getType.equals("event")) {
            doneTask = new Event(i, "[✓]", currentTask, getType, t.getAB());
            a.set(i - 1, doneTask);
        } else {
            doneTask = new Deadline(i, "[✓]", currentTask, getType, t.getAB());
            a.set(i - 1, doneTask);
        }
        System.out.println("[✓] " + currentTask);
    }

    //to add task
    void addList(Task t, ArrayList<Task> a, int n) {
        System.out.println("Got it. I've added this task:");
        a.add(t);
        System.out.println(t);
        System.out.println("Now you have " + Integer.toString(n) + " tasks in the list.");

        try {
            PrintWriter savingAdd = new PrintWriter("/Users/hualun/Desktop/test.txt");
            for(Task task: a) {
                savingAdd.println(task);
            }
            savingAdd.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //to delete task
    void deleteTask(Task t, ArrayList<Task> a) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(t);
        int taskNumber = t.getTaskNumber();
        a.remove(taskNumber - 1);
        System.out.println("Now you have " + Integer.toString(a.size()) + " tasks in the list.");

        try {
            PrintWriter savingDelete = new PrintWriter("/Users/hualun/Desktop/test.txt");
            for(Task task: a) {
                savingDelete.println(task);
            }
            savingDelete.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return Integer.toString(taskNumber) + "." + taskCheck + " " + taskName;
    }
}
