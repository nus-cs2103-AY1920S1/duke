import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "    ____________________________________________________________";

        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        Scanner sc = new Scanner(System.in);
        String userInput = sc.next();

        while (!userInput.equals("bye")) {
            System.out.println(horizontalLine);
            System.out.println("     " + userInput);
            System.out.println(horizontalLine + "\n");
            userInput = sc.next();
        }

        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }
}
