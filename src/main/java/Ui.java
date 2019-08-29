import java.util.ArrayList;

public class Ui {
    TaskList tasks;
    Storage storage;
    public void Ui() {
    }
    public void link(TaskList t, Storage s) {
        tasks = t;
        storage = s;
    }
    public void showWelcome() {
        /**
         *  prints welcome message with lines to beautify
         * @params none
         *  @return none
         */
        printLine();
        System.out.println("Hello, I'm Duke\nWhat can I do for you?");
        printLine();
    }
    public void printLine() {
        /**
         *  helper function, prints formatted solid line
         * @params none
         *  @return none
         */
        System.out.println("____________________________________________________________");
    }
    public void markDone(Task doneTask) {
        printLine();
        System.out.print("Nice! I've marked this task as done:\n");
        System.out.println(doneTask.printTask());
        printLine();
    }
    public void addTask(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.print(" " + task.printTask() + "\n");
        System.out.println("Now you have " + tasks.getList().size() + " tasks in the list.");
        printLine();
    }
    public void delTask(Task task) {
        printLine();
        System.out.print("Noted. I've removed this task: \n");
        System.out.println(" " + task.printTask());
        System.out.println("Now you have " + tasks.getList().size() + " tasks in the list.");
        printLine();
    }
    public void goodBye() {
        storage.saveToTextFile();
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void list() {
        ArrayList<Task> taskArr = tasks.getList();
        /**
         *  returns all tasks in readable, formatted string
         *  @return string of all tasks with new line and spaces as required
         */
        String s = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskArr.size(); i++) {
            // printInt to put number for printing
            int printInt = i + 1;
            Task currTask = taskArr.get(i);
            s += currTask.printTask() + "\n";
        }
        printLine();
        System.out.println(s);
        printLine();
    }
}
