import java.util.*;
/**
 *  Week 2 IP Deliverable
 *
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner sc = new Scanner(System.in);
        boolean hi = true;
        String input = "";
        int value = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(hi) {
            input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                hi = false;
            } else if (input.equals("list")) {
                if (tasks.size() == 0) {
                    System.out.println("List is empty!");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i+1) + "." + tasks.get(i));
                    }
                }
            } else if (input.contains("done")) {
                value = Integer.parseInt(input.substring(input.length() - 1));
                tasks.get(value - 1).setDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(value - 1));
            } else {
                tasks.add(new Task(input));
                System.out.println("added: " + input);
            }
        }
        sc.close();
    }
}
