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

        String[] inputs = new String[100];
        String input;
        String smallIndent = " ";
        int nextTaskIndex = 0;

        mainLoop:
            while (true) {
                input = scanner.nextLine();

                System.out.print(horizontalLine);

                switch (input) {
                    case "list":
                        printInputArray(inputs, nextTaskIndex - 1);
                        System.out.println(horizontalLine);
                        break;
                    case "bye":
                        System.out.println(" Bye. Hope to see you again soon!");
                        System.out.println(horizontalLine);
                        break mainLoop;
                    default:
                        inputs[nextTaskIndex] = input;
                        nextTaskIndex++;
                        System.out.println(" added: " + input);
                        System.out.println(horizontalLine);
                        break;
                }
            }

    }

    private static void printInputArray(String[] inputs, int n) {
        for (int i = 0; i <= n; i++) {
            System.out.printf(" %d. %s\n", i + 1, inputs[i]);
        }
    }
}