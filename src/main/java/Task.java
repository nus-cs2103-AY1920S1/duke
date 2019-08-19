import java.util.ArrayList;

public class Task {
    private int taskNumber;
    private String taskCheck;
    private String taskName;

    public Task(int i, String tC, String tN) {
        taskNumber = i;
        taskCheck = tC;
        taskName = tN;
    }

    public int getTaskNumber() {
        return taskNumber;
    }

    public String getTaskCheck() {
        return taskCheck;
    }

    public String getTaskName() {
        return taskName;
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
        Task doneTask = new Task(i, "[✓]", currentTask);
        a.set(i - 1, doneTask);
        System.out.println("[✓] " + currentTask);
    }

    static void addList(int i, String s, ArrayList<Task> a) {
        Task t = new Task(i, "[✗]", s);
        a.add(t);
        System.out.println("added: " + s);
    }

    @Override
    public String toString() {
        return Integer.toString(taskNumber) + "." + taskCheck + " " + taskName;
    }
    

}
