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
                case "todo":
                    String taskDescription = sc.nextLine();
                    addToDo(taskDescription);
                    break;
                case "deadline":
                    addDeadline(sc.nextLine());
                    break;
                case "event":
                    addEvent(sc.nextLine());
                    break;
                default:
                    //Provided input is a task
                    System.out.println("An invalid input was provided");
            }
        }
    }

    public void addToDo(String taskDescription) {
        Task t = new ToDo(taskDescription);
        this.storedTasks.add(t);
        printAddTaskOutput(t);
    }

    public void addDeadline(String taskDescriptionwithDeadline) {
        String[] strArr = taskDescriptionwithDeadline.split("/by");
        String description = strArr[0].trim();
        String deadline = strArr[1].trim();
        Task t = new Deadline(description, deadline);
        this.storedTasks.add(t);
        printAddTaskOutput(t);
    }

    public void addEvent(String taskDescriptionwithDuration) {
        String[] strArr = taskDescriptionwithDuration.split("/at");
        String description = strArr[0].trim();
        String duration = strArr[1].trim();
        Task t = new Event(description, duration);
        this.storedTasks.add(t);
        printAddTaskOutput(t);
    }

    public void printAddTaskOutput(Task t) {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + t);
        System.out.println("\tNow you have " + this.storedTasks.size() + " tasks in the list");
        System.out.println("\t____________________________________________________________");
    }

    public void completeTask(int taskNum) {
        Task t = this.storedTasks.get(taskNum - 1); //Because storedTasks is zero-indexed
        t.markAsDone();

        System.out.println("\t____________________________________________________________");
        System.out.println("\tNice! I've marked this task as done: ");
        System.out.println("\t\t" + t);
        System.out.println("\t____________________________________________________________");
    }

    public void listStoredTasks() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHere are the tasks in your list:");

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
