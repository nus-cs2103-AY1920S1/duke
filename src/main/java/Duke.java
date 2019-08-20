import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>(100);

        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        while (true) {
            try {
                String input = scanner.nextLine();
                String instruction = input.split(" ", 2)[0];
                if (instruction.equals("bye")) { // First, check if 'bye' is called
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (instruction.equals("list")) { // Then, check if 'list' is called
                    // EXCEPTION: What if you call "list" with no tasks logged?
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
                } else if (instruction.equals("todo") || instruction.equals("deadline") || instruction.equals("event")) {
                    try {
                        if (instruction.equals("todo")) {
                            String taskDescription = input.split(" ", 2)[1]; // Remaining content of task
                            if (taskDescription.matches("\\s*")) {
                                throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                            }
                            Task currentTask = new ToDoTask((taskDescription));
                            taskList.add(currentTask);
                            System.out.println("Got it. I've added this task:\n  " + currentTask);
                        } else {
                            // EXCEPTION: What if there is no task time given?
                            String taskDescription = input.split(" ", 2)[1];
                            String taskContent = taskDescription.split(" /", 2)[0];
                            String taskTime = taskDescription.split(" /by | /at ", 2)[1]; // For other tasks, time must be parsed
                            if (instruction.equals("deadline")) {
                                Task currentTask = new DeadlineTask(taskContent, taskTime);
                                taskList.add(currentTask);
                                System.out.println("  " + currentTask);
                            } else {
                                Task currentTask = new EventTask(taskContent, taskTime);
                                taskList.add(currentTask);
                                System.out.println("Got it. I've added this task:\n  " + currentTask);
                            }
                        }
                    } catch (IndexOutOfBoundsException error) {
                        throw new EmptyTaskDescriptionException("OOPS!!! The description of a task cannot be empty.");
                    }
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                } else {
                    throw new InvalidInstructionException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException error) {
                System.out.println(error.getMessage());
            }
        }
    }
}
