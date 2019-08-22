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
                items[counter] = new Task(next);
                counter++;
                System.out.println("added: " + next);
            }
        }

        sc.close();
    }
}
