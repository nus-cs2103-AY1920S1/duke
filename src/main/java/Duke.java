import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import todo.ToDoList;

public class Duke {
    public static void main(String[] args) {
        Duke duke = new Duke();
        Duke.run();
    }

    private static void run() {
        Scanner sc = new Scanner(System.in);
        ToDoList todo = new ToDoList();
        String input = "";

        File file = new File("/data/duke.txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Error in accessing data file");
        }

        printFormattedText("    Hello! I'm Duke\n" +
                "    What can I do for you?");

        while (true) {
            try {
                input = sc.next().trim();
                if (input.equals("bye"))
                    break;
                else if (input.contains("done")) {
                    int index = sc.nextInt();
                    printFormattedText(todo.markTaskDone(index));
                } else if (input.equals("list"))
                    printFormattedText(todo.displayList());
                else if (input.equals("delete")) {
                    int index = sc.nextInt();
                    printFormattedText(todo.removeTask(index));
                }
                else if (input.equals("todo")
                        || input.equals("deadline")
                        || input.equals("event")) {
                    String remainingInput = sc.nextLine();
                    if (remainingInput.isBlank()) {
                        throw new DukeException("     \u2639 OOPS!!! The description of a " + input + " cannot be empty");
                    }
                    printFormattedText(todo.addTask(input, remainingInput));
                } else throw new DukeException("     \u2639 OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DukeException e) {
            printFormattedText(e.getMessage());
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
