import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "____________________________________________________________\n";
        String greetingMsg = horizontalLine
                + " Hello! I'm Duke\n"
                + " What can I do for you?\n"
                + horizontalLine;
        System.out.println(greetingMsg);

        Scanner scanner = new Scanner(System.in);

        String input;
        String smallIndent = " ";
        while (true) {
            input = scanner.nextLine();

            System.out.print(horizontalLine);
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else {
                System.out.println(smallIndent + input);
                System.out.println(horizontalLine);
            }
        }

    }
}