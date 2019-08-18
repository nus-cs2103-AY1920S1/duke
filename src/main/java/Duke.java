import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    List<Task> l;

    public void list() {
        for (int i = 0; i < l.size(); i++) {
            Task t = l.get(i);
            System.out.println(String.format("%d.%s", i + 1, t));
        }
    }

    public void done(int i) {
        String doneMessage = "Nice! I've marked this task as done:";
        l.get(i - 1).setDone(true);
        System.out.println(doneMessage);
        System.out.println(l.get(i - 1));
    }

    public void addToDo(String task) {
        ToDo toDo = new ToDo(task);
        l.add(toDo);
        System.out.println("Got it. I've added this task:");
        System.out.println(toDo);
        System.out.println(String.format("Now you have %d tasks in the list.", l.size()));
    }

    public void add(String task) {
        l.add(new Task(task));
        System.out.println("added: " + task);
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
        System.out.println(exitMessage);
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
