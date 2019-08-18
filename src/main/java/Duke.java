import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean exit = false;
        String exitMessage = "Bye. Hope to see you again soon!";
        String doneMessage = "Nice! I've marked this task as done:";
        List<Task> l = new ArrayList<>();
        while (!exit) {
            String input = sc.next();
            switch (input) {
                case "list":
                    for (int i = 0; i < l.size(); i++) {
                        Task t = l.get(i);
                        System.out.println(String.format("%d.%s", i + 1, t));
                    }
                    break;
                case "bye":
                    exit = true;
                    break;
                case "done":
                    int i = sc.nextInt();
                    l.get(i - 1).setDone(true);
                    System.out.println(doneMessage);
                    System.out.println(l.get(i-1));
                    break;
                default:
                    input += sc.nextLine();
                    Task t = new Task(input);
                    l.add(t);
                    System.out.println("added: " + input);
            }
        }
        System.out.println(exitMessage);
        sc.close();
    }
    
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        // Duke.levelOne();
        // Duke.levelTwo();
        // Duke.levelThree();
        Duke d = new Duke();
        d.run();
    }
}
