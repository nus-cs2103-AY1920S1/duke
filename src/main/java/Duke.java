import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(welcomeMessage);

        Scanner sc = new Scanner(System.in);
        String input = "";

        while (true) {
            input = sc.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            } else {
                System.out.println(input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
