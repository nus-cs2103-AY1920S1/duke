import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) { // First, check if 'bye' is called
                break;
            } else if (input.equals("list")) { // Then, check if 'list' is called
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (input.startsWith("done")) { // Then, check if task is done
                int index = Integer.parseInt(input.split(" ", 2)[1]);
                taskList.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index - 1));
            } else { // If not, is assumed to be a new Task.
                System.out.println("Got it. I've added this task:");
                String taskType = input.split(" ", 2)[0]; // Obtains type of task
                String taskWords = input.split(" ", 2)[1]; // Remaining content of task
                if (taskType.equals("todo")) { // For to-do tasks, task content is all content
                    Task currentTask = new ToDoTask((taskWords));
                    taskList.add(currentTask);
                    System.out.println("  " + currentTask);
                } else {
                    String taskContent = taskWords.split(" /", 2)[0];
                    String taskTime = taskWords.split(" /by | /at ", 2)[1]; // But for other tasks, time must be parsed
                    if (taskType.equals("deadline")) {
                        Task currentTask = new DeadlineTask(taskContent, taskTime);
                        taskList.add(currentTask);
                        System.out.println("  " + currentTask);
                    } else if (taskType.equals("event")) {
                        Task currentTask = new EventTask(taskContent, taskTime);
                        taskList.add(currentTask);
                        System.out.println("  " + currentTask);
                    }
                }
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
