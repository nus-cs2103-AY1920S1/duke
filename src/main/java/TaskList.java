import java.util.ArrayList;

/** Class to represent a list of tasks. */
class TaskList {
    // ArrayList to maintain list of tasks
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    // add tasks
    public void addTask(Task task) {
        tasks.add(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append("       " + task + "\n");
        sb.append(String.format("     Now you have %d tasks in the list.", this.tasks.size()));
        prettyPrint(sb.toString());
    }

    // add task to list without message
    public void addTaskWithoutMessage(Task task) {
        tasks.add(task);
    }

    // list tasks
    public void listTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(String.format("     %d. %s\n", i + 1, this.tasks.get(i).toString()));
        }
        prettyPrint(sb.toString());
    }

    // mark task as done
    public void taskDone(int index) {
        this.tasks.get(index - 1).markDone();
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(String.format("     %s", this.tasks.get(index - 1).toString()));
        prettyPrint(sb.toString());
    }

    // remove task from list
    public void removeTask(int index) {
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(String.format("     %s\n", this.tasks.remove(index - 1).toString()));
        sb.append(String.format("     Now you have %d tasks in the list.", this.tasks.size()));
        prettyPrint(sb.toString());
    }

    // return tasks
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    // pretty print a string
    void prettyPrint(String str) {
        System.out.println("    --------------------------------------------------");
        System.out.println("     " + str);
        System.out.println("    --------------------------------------------------");
    }
}