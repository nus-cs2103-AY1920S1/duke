import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String initialMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(initialMessage);

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            if (input.equals("list")) {
                System.out.println("list");
            } else if (input.equals("blah")) {
                System.out.println("blah");
            } else if (input.equals(("bye"))) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else {
                System.out.println("Invalid input!");
            }
        }

    }
}
