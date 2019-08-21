import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        String greet = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(greet);
        
        Scanner scanner = new Scanner(System.in);
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } 

            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    Task task = list.get(i);
                    System.out.println((i + 1) + "." + task);
                }
            } else if (input.matches("^done \\d+$")) {
                int taskId = Integer.parseInt(input.split(" ")[1]); 
                try {
                    Task task = list.get(taskId - 1);
                    task.markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("There is no task with ID " + taskId + ". Please enter a valid ID.");
                }
            } else {
                String[] inputBreakDown = input.split(" ", 2);
                String taskType = inputBreakDown[0];
                if (!(taskType.equals("todo") || taskType.equals("deadline") || taskType.equals("event"))) {
                    System.out.println("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    continue;
                }
                if (inputBreakDown.length < 2 || inputBreakDown[1].matches("\\s*")) {
                    System.out.println("\u2639 OOPS!!! Please provide more details about this task!\n");
                    continue;
                }
                String taskDetails = inputBreakDown[1];
                Task newTask = null;
                switch (taskType) {
                case "todo":
                    newTask = new Todo(taskDetails);
                    break;
                case "deadline":
                    String[] deadlineBreakDown = taskDetails.split(" /by ");
                    String deadlineDescription = deadlineBreakDown[0];
                    String by = deadlineBreakDown[1];
                    newTask = new Deadline(deadlineDescription, by);
                    break;
                case "event":
                    String[] eventBreakDown = taskDetails.split(" /at ");
                    String eventDescription = eventBreakDown[0];
                    String at = eventBreakDown[1];
                    newTask = new Event(eventDescription, at);
                    break;
                default:
                }
                if (newTask != null) {
                    list.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask);
                    int total = Task.getTotal();
                    System.out.printf("Now you have %d task%s in the list.\n", total, total > 1 ? "s" : "");
                }
            }
            System.out.println();
        }
    }
}
