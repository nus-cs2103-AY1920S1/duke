import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.next();
            if (!input.equals("bye")) {
                System.out.println(input);
            } else {
                break;
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
