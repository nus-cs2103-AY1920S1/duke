import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            String input = scanner.nextLine();
            String instruction = input.split(" ", 2)[0];
            if (instruction.equals("bye")) { // First, check if 'bye' is called
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (instruction.equals("list")) { // Then, check if 'list' is called
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
            } else if (instruction.equals("done")) { // Then, check if task is done
                // EXCEPTION: What if what follows 'done' is not an integer?
                // EXCEPTION: What if what follows 'done ' is not a current task number?
                int index = Integer.parseInt(input.split(" ", 2)[1]);
                taskList.get(index - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(taskList.get(index - 1));
            } else if (instruction.equals("todo")||instruction.equals("deadline")||instruction.equals("event")) {
                // EXCEPTION: What if the word is not any of the three tasks?
                System.out.println("Got it. I've added this task:");
                String taskDescription = input.split(" ", 2)[1]; // Remaining content of task
                // EXCEPTION: What if the instruction has no accompanying description?
                if (instruction.equals("todo")) {
                    Task currentTask = new ToDoTask((taskDescription));
                    taskList.add(currentTask);
                    System.out.println("  " + currentTask);
                } else {
                    // EXCEPTION: What if there is no task time given?
                    String taskContent = taskDescription.split(" /", 2)[0];
                    String taskTime = taskDescription.split(" /by | /at ", 2)[1]; // For other tasks, time must be parsed
                    if (instruction.equals("deadline")) {
                        Task currentTask = new DeadlineTask(taskContent, taskTime);
                        taskList.add(currentTask);
                        System.out.println("  " + currentTask);
                    } else {
                        Task currentTask = new EventTask(taskContent, taskTime);
                        taskList.add(currentTask);
                        System.out.println("  " + currentTask);
                    }
                }
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            }
        }
    }
}
