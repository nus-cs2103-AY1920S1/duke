import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private ArrayList<Task> storedTasks;

    public Duke() {
        this.storedTasks = new ArrayList<Task>();
    }

    public static void main(String[] args) {
        Duke d = new Duke();

        d.initDuke();
        d.runDuke();
        d.terminateDuke();
    }

    public void runDuke() {
        Scanner sc = new Scanner(System.in);
        boolean contRunning = true;

        while (contRunning) {
            String command = sc.next();
            switch (command) {
                case "bye":
                    contRunning = false;
                    break;
                case "list":
                    listStoredTasks();
                    break;
                case "done":
                    completeTask(sc.nextInt());
                    break;
                default:
                    //Provided input is a task
                    String taskName = command + sc.nextLine();
                    addTaskToStore(taskName);
            }
        }
    }

    public void completeTask(int taskNum) {
        Task t = this.storedTasks.get(taskNum - 1); //Because storedTasks is zero-indexed
        t.markAsDone();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t" + t);
        System.out.println("\t____________________________________________________________");
    }

    public void addTaskToStore(String taskName) {
        this.storedTasks.add(new Task(taskName));

        System.out.println("\t____________________________________________________________");
        System.out.println("\tadded: " + taskName);
        System.out.println("\t____________________________________________________________");
    }

    public void listStoredTasks() {
        System.out.println("\t____________________________________________________________");

        int counter = 1;
        for (Task t : this.storedTasks) {
            System.out.println("\t" + counter + ". " + t);
            counter++;
        }

        System.out.println("\t____________________________________________________________");
    }

    public void initDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tHello! I'm Duke\n"
                          +"\tWhat can I do for you?\n"
                          +"\t____________________________________________________________\n");
    }

    public void terminateDuke() {
        System.out.println("\t____________________________________________________________\n"
                          +"\tBye. Hope to see you again soon!\n"
                          +"\t____________________________________________________________\n");
    }
}
