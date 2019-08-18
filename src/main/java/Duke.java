import java.util.Scanner;

public class Duke {
    private static void printBlock(String... text) {
        String horizontalLine = "____________________________________________________________";
        String indentation = "    ";
        System.out.println(indentation + horizontalLine);
        for (String line: text) {
            System.out.println(indentation + " " + line);
        }
        System.out.println(indentation + horizontalLine + "\n");
    }

    public static void main(String[] args) {
        String greeting = "Hello! I'm Duke";
        String question = "What can I do for you?";
        printBlock(greeting, question);

        Scanner in = new Scanner(System.in);
        while (true) {
            String command = in.nextLine();
            if (command.equals("bye")) {
                printBlock("Bye. Hope to see you again soon!");
                break;
            } else {
                printBlock(command);
            }
        }
    }

}