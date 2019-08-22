import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner scanner  = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    scanner.close();
                    break;
                } else if (input.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + "." + tasks.get(i));
                    }
                } else if (input.contains("done")) {
                    String[] inputs = input.split(" ");
                    int number =  Integer.parseInt(inputs[1]) - 1;
                    tasks.get(number).complete();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(number));
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4);
                    Todo todo = new Todo(description);
                    tasks.add(todo);
                    printout(todo, tasks);
                } else if (input.startsWith("deadline")) {
                    int index = input.lastIndexOf("/by");
                    String description = input.substring(8, index);
                    String by = input.substring(index + 4);
                    Deadline deadline = new Deadline(description, by);
                    tasks.add(deadline);
                    printout(deadline, tasks);
                } else if (input.startsWith("event")) {
                    int index = input.lastIndexOf("/at");
                    String description = input.substring(5, index);
                    String at = input.substring(index + 4);
                    Event event = new Event(description, at);
                    tasks.add(event);
                    printout(event, tasks);
                }
                else {
                }
        }
    }

    public static void printout(Task task, ArrayList<Task> tasksList) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
    }
}

