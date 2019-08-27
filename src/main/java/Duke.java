import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String greeting = "     Hello! I'm Duke\n" +
                "     What can I do for you?";
        System.out.println(greeting);

        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                break;
            }
            System.out.println("     " + input);
        }
    }
}
