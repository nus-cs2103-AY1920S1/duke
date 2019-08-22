import java.util.Scanner;

public class Duke {
    private static void printWithIndentation(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }
    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.matches("bye")) {
                printWithIndentation("Bye. Hope to see you again soon!");
                break;
            } else {
                printWithIndentation(userInput);
            }
        }
    }
}
