import java.util.Scanner;

public class Duke {
    public static Task[] items = new Task[100];
    public static int counter = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String hello = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(hello);

        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                try {
                    handleInput(next);
                } catch (DukeException e) {
                    System.err.println(e.getMessage());
                }
            }
        }

        sc.close();
    }

    public static void handleInput(String next) throws DukeException {
        if (next.equals("list")) {
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
                if (next.length() < 6) {
                    throw new DukeException("The description of a todo cannot be empty.");
                } else {
                    items[counter] = new Todo(next.substring(5));
                }
            } else if (next.startsWith("deadline")) {
                String[] parts = next.split(" /by ");
                items[counter] = new Deadline(parts[0].substring(9), parts[1]);
            } else if (next.startsWith("event")) {
                String[] parts = next.split(" /at ");
                items[counter] = new Event(parts[0].substring(6), parts[1]);
            } else {
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

            String output = "Got it. I've added this task:\n  " + items[counter];
            output += "\nNow you have " + (counter + 1) + " tasks in the list.";
            System.out.println(output);
            counter++;
        }
    }
}
