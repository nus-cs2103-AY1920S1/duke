import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int pos = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            if (sc.hasNext("done")) {
                String dummy = sc.next();
                int number = sc.nextInt();
                int pos1 = number - 1;
                String dummy1 = sc.nextLine();
                list[pos1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(list[pos1]);
            } else if (sc.hasNext("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (sc.hasNext("todo")) {
                try {
                    String dummy = sc.next();
                    String description = sc.nextLine();
                    if (description.equals("")) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    Task curr = new Todo(description);
                    list[pos] = curr;
                    pos++;
                    System.out.println("Got it. I've added this task:");
                    System.out.println(curr);
                    System.out.println("Now you have " + pos + " tasks in the list.");
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
            } else if (sc.hasNext("deadline")) {
                String dummy = sc.next();
                String[] split = new String[2];
                split = sc.nextLine().split("/by");
                Task curr = new Deadline(split[0], split[1]);
                list[pos] = curr;
                pos++;
                System.out.println("Got it. I've added this task:");
                System.out.println(curr);
                System.out.println("Now you have " + pos + " tasks in the list.");
            } else if (sc.hasNext("event")) {
                String dummy = sc.next();
                String[] split = new String[2];
                split = sc.nextLine().split("/at");
                Task curr = new Event(split[0], split[1]);
                list[pos] = curr;
                pos++;
                System.out.println("Got it. I've added this task:");
                System.out.println(curr);
                System.out.println("Now you have " + pos + " tasks in the list.");
            } else {
                try {
                    String input = sc.nextLine();
                    if (!input.equals("list")) {
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                    System.out.println("Here are the tasks in your list:");
                    int k = 0;
                    while (k != pos) {
                        int bullet = k + 1;
                        System.out.println(bullet + "." + list[k]);
                        k++;
                    }
                } catch (DukeException e) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }

        }

    }

}
