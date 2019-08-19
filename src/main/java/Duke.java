import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] items = new String[100];
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
                    sb.append(". ");
                    sb.append(items[i]);
                    sb.append('\n');
                }
                System.out.print(sb);
            } else {
                items[counter] = next;
                counter++;
                System.out.println("added: " + next);
            }
        }

        sc.close();
    }
}
