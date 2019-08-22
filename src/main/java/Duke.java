import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?");

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (input.equals("list")) {
                for (int i = 1; i <= list.size(); i++) {
                    Task t = list.get(i-1);
                    System.out.println( i + "." + t);
                }
            } else if (input.equals("done")) {
                int num = Integer.parseInt(sc.next());
                Task t = list.get(num - 1);
                t.markAsDone();
                System.out.println("Nice! I've marked this task as done: "+ "\n" + t);
            } else if (input.equals("delete")) {
            	int num = Integer.parseInt(sc.next());
            	Task t = list.remove(num);
            	System.out.println("Noted. I've removed this task: \n" + t + "\n Now you have "
                            + list.size() + " tasks in the list.")
            } else {
                try {
                    Task t;
                    String desc = sc.nextLine();
                    if (desc.equals("")) {
                        throw new DukeException("The description of a " + input+ " cannot be empty.");
                    }
                    if (input.equals("todo")) {
                        t = new Todo(desc);
                    } else if (input.equals("deadline")) {
                        String[] str = desc.split("/");
                        t = new Deadline(str[0], str[1].substring(3));
                    } else if (input.equals("event")) {
                        String[] str = desc.split("/");
                        t = new Event(str[0], str[1].substring(3));
                    } else {
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                    list.add(t);
                    System.out.println("Got it. I've added this task: \n" + t + "\n Now you have "
                            + list.size() + " tasks in the list.");
                    } catch (DukeException ex) {
                        System.out.println(ex);
                    }
            }
        }
    }
}
