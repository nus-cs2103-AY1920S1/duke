import java.util.*;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<Task>();

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNext()) {
            String text = sc.nextLine();
            String[] s = text.split(" ");
            if (s[0].equals("todo")) {
                String task = text.substring(5);
                Todo todo = new Todo(task);
                list.add(todo);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + todo);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (s[0].equals("deadline")) {
                String[] t = text.split("/");
                Deadline deadline = new Deadline(t[0].substring(9, t[0].length() - 1), t[1].substring(3));
                list.add(deadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + deadline);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (s[0].equals("event")) {
                String[] t = text.split("/");
                Event event = new Event(t[0].substring(6, t[0].length() - 1), t[1].substring(3));
                list.add(event);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + event);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
            } else if (s[0].equals("list")) {
                int length = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= length; i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + ". " + task);
                }
            } else if (s[0].equals("done")) {
                int num = Integer.parseInt(s[1]);
                Task task = list.get(num - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + task);
            } else if (s[0].equals("blah")) {
                System.out.println("blah");
            } else if (s[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
        }

        sc.close();
    }
}