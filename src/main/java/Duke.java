import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        String next = sc.next();
        int count = 1;
        while (!next.equals("bye")) {
            if (next.equals("list")) {
                for (Task task : list) {
                    System.out.println(count + "." + task);
                    count++;
                }
            } else if (next.equals("done")) {
                int number = sc.nextInt() - 1;
                Task taskDone = list.get(number);
                taskDone.markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + taskDone);
            } else {
                if (sc.hasNext()) {
                    next += sc.nextLine();
                }
                Task nextTask = new Task(next);
                list.add(nextTask);
                System.out.println("added: " + next);
            }
            next = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

}