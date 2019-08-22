import java.util.Scanner;
import java.util.LinkedList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList<Task> todos = new LinkedList<>();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        String userInput = sc.nextLine();

        while (!userInput.equals("bye")) {
            String[] command = userInput.split(" ");
            switch (command[0]) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < todos.size(); i++) {
                        System.out.println((i + 1) + "." + todos.get(i));
                    }
                    break;
                case "done":
                    System.out.println("Nice! I've marked this task as done:");
                    int i = Integer.parseInt(command[1]) - 1;
                    Task t = todos.remove(i);
                    t.markAsDone();
                    todos.add(i, t);
                    System.out.println(t);
                    break;
                default:
                    System.out.println("added: " + userInput);
                    Task todo = new Task(userInput);
                    todos.add(todo);
            }
            userInput = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");

    }
}
