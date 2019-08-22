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
            if (s[0].equals("list")) {
                int length = list.size();
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= length; i++) {
                    Task task = list.get(i - 1);
                    System.out.println(i + ". " + "[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            } else if (s[0].equals("done")) {
                int num = Integer.parseInt(s[1]);
                Task task = list.get(num - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
            } else if (s[0].equals("blah")) {
                System.out.println("blah");
            } else if (s[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                Task task = new Task(text);
                list.add(task);
                System.out.println("added: " + text);
            }
        }

        sc.close();
    }
}
