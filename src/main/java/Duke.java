import java.util.Scanner;
import todo.ToDo;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDo todo = new ToDo();
        String input = "";

        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");

        while (true) {
            input = sc.nextLine();
            if (input.equals("bye"))
                break;
            else if (input.contains("done")) {
                int index = Integer.parseInt(input.substring(5));
                printFormattedText(todo.markTaskDone(index));
            }
            else if (input.equals("list"))
                printFormattedText(todo.displayList());
            else
                printFormattedText(todo.addTask(input));
        }

        printFormattedText("    Bye. Hope to see you again soon!");
    }

    private static void printFormattedText(String text) {
        printDivider();
        System.out.println(text);
        printDivider();
    }

    private static void printDivider() {
        indent();
        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    private static void indent() {
        for (int i = 0; i < 4; i++) System.out.print(" ");
    }
}
