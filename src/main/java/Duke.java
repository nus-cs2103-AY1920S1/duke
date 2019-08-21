import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<String> todos = new LinkedList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            switch (userInput) {
                case "list":
                    for (int i = 0; i < todos.size(); i++) {
                        System.out.println((i + 1) + ": " + todos.get(i));
                    }
                    break;
                default:
                    System.out.println("added: " + userInput);
                    todos.add(userInput);
            }
            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
