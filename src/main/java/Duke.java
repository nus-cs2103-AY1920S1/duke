import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    List<Task> l;
    String doneMessage = "Nice! I've marked this task as done:";
    String addedMessage = "Got it. I've added this task:";

    public void list() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < l.size(); i++) {
            Task t = l.get(i);
            System.out.println(String.format("%d.%s", i + 1, t));
        }
        System.out.println("____________________________________________________________");
    }

    public void done(int i) {
        l.get(i - 1).setDone(true);
        System.out.println("____________________________________________________________");
        System.out.println(doneMessage);
        System.out.println(l.get(i - 1));
        System.out.println("____________________________________________________________");
    }

    public void addToDo(String task) {
        l.add(new ToDo(task));
        this.printAddedMessage();
    }

    public void addDeadline(String task, String date) {
        l.add(new Deadline(task, date));
        this.printAddedMessage();
    }

    public void addEvent(String task, String date) {
        l.add(new Event(task, date));
        this.printAddedMessage();
    }

    public void printAddedMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(addedMessage);
        System.out.println(l.get(l.size() - 1));
        System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
        System.out.println("____________________________________________________________");
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String exitMessage = "Bye. Hope to see you again soon!";
        l = new ArrayList<>();
        while (!exit) {
            String command = sc.next();
            switch (command) {
            case "todo":
                this.addToDo(sc.nextLine().trim());
                break;
            case "deadline":
                String[] arrDeadLine = sc.nextLine().split("/by");
                String taskDeadLine = arrDeadLine[0].trim();
                String dateDeadLine = arrDeadLine[1].trim();
                this.addDeadline(taskDeadLine, dateDeadLine);
                break;
            case "event":
                String[] arrEvent = sc.nextLine().split("/at");
                String taskEvent = arrEvent[0].trim();
                String dateEvent = arrEvent[1].trim();
                this.addEvent(taskEvent, dateEvent);
                break;
            case "list":
                this.list();
                break;
            case "bye":
                exit = true;
                break;
            case "done":
                this.done(sc.nextInt());
                break;
            default:
                System.err.println("Unrecognised command!");
            }
        }
        System.out.println("____________________________________________________________");
        System.out.println(exitMessage);
        System.out.println("____________________________________________________________");
        sc.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        Duke d = new Duke();
        d.run();
    }
}
