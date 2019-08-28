import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] tasks = new String[100];
        int index = 0;

        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }
            if (input.equals("list")) {
                for (int i = 0; i < index; i++) {
                    System.out.printf("     %d: %s\n", i + 1, tasks[i]);
                }
            } else {
                tasks[index] = input;
                index++;
                System.out.println("     added: " + input);
            }
        }
    }
}
