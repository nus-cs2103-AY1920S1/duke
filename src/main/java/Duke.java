import java.util.Scanner;
import todo.ToDoList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ToDoList todo = new ToDoList();
        String input = "";

        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");

        while (true) {
            input = sc.next().trim();
            if (input.equals("bye"))
                break;
            else if (input.contains("done")) {
                int index = Integer.parseInt(sc.next());
                printFormattedText(todo.markTaskDone(index));
            }
            else if (input.equals("list"))
                printFormattedText(todo.displayList());
            else {
                String remainingInput = sc.nextLine();
                printFormattedText(todo.addTask(input, remainingInput));
            }
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
