import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        greet();
        readInput();
        goodbye();
    }

    public static void greet() {
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
    }

    public static void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void readInput() {
        Scanner input = new Scanner(System.in);
        ArrayList<String> taskList = new ArrayList<>();

        String userInput = input.nextLine();

        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                printTaskList(taskList);
                userInput = input.nextLine();
            } else {
                taskList.add(userInput);
                System.out.println("added: " + userInput);
                userInput = input.nextLine();
            }
        }
    }

    public static void printTaskList(ArrayList<String> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("task list is empty");
        } else {
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }
}
