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
                System.out.println("Here are the tasks in your list:");
                taskList.printAllTasks();
                command = input.nextLine();
            } else if (command.contains("done")) {
                // Check if the first word is done
                String[] sentence = command.split(" ");
                if (sentence[0].equals("done")) {
                    int completedTaskIndex = Integer.parseInt(sentence[1]);
                    taskList.markAsDone(completedTaskIndex); // If it wasn't marked before, this would print out a notification saying it is now marked.
                }
                command = input.nextLine();
            } else {
                if (!command.isEmpty()) {
                    Task newTask = new Task(command);
                    taskList.addTask(newTask);
                    System.out.println("added: " + command);
                }
                command = input.nextLine();
            }
        }

        String farewell = "Bye. Hope to see you again soon!";
        System.out.println(farewell);
    }
}
