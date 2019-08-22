import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while(true) {
            try {
                run();
            } catch(IllegalStateException | NoSuchElementException e) {
                break;
            }
        }
    }
    private static LinkedList<Task> tasks = new LinkedList<>();
    private static void run() {
        Scanner sc = new Scanner(System.in);

        String next = sc.next();
        switch(next) {
            case "list":
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(1 + i + "." + tasks.get(i).toString());
                }
                break;
            case "bye":
                System.out.println("Bye. Hope to see you again soon!");
                sc.close();
                break;
            case "done":
                int n = sc.nextInt();
                tasks.get(n - 1).setDone();
                System.out.println("Nice! I've marked this task as done:\n" + tasks.get(n - 1).toString());
                break;
            default:
                Task newt = new Task(next + sc.nextLine());
                tasks.add(newt);
                System.out.println("added: " + newt.toString());
        }
    }
}