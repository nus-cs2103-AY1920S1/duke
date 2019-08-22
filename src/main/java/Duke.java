import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task[] items = new Task[100];
        int counter = 0;

        String hello = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(hello);

        while (sc.hasNext()) {
            String next = sc.nextLine();

            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("list")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < counter; i++) {
                    sb.append(i + 1);
                    sb.append(".");
                    sb.append(items[i]);
                    sb.append('\n');
                }
                System.out.print(sb);
            } else if (next.startsWith("done")) {
                String[] parts = next.split(" ");
                int num = Integer.parseInt(parts[1]);
                items[num - 1].setDone(true);
                String output = "Nice! I've marked this task as done:\n  " + items[num - 1];
                System.out.println(output);
            } else {
                if (next.startsWith("todo")) {
                    items[counter] = new Todo(next.substring(5));
                } else if (next.startsWith("deadline")) {
                    String[] parts = next.split(" /by ");
                    items[counter] = new Deadline(parts[0].substring(9), parts[1]);
                } else if (next.startsWith("event")) {
                    String[] parts = next.split(" /at ");
                    items[counter] = new Event(parts[0].substring(6), parts[1]);
                } else {
                    items[counter] = new Task(next);
                }

                String output = "Got it. I've added this task:\n  " + items[counter];
                output += "\nNow you have " + (counter + 1) + " tasks in the list.";
                System.out.println(output);
                counter++;
            }
        }

        sc.close();
    }
}
