import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String greetings = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(greetings);

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        TaskList taskList = new TaskList();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                taskList.printAllTasks();
                command = input.nextLine();
            } else {
                taskList.addTask(command);
                System.out.println("added: " + command);
                command = input.nextLine();
            }
        }

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }
}
