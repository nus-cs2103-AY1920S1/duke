import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke" + "\n" + "What can I do for you?"); // greet user
        Scanner scanner = new Scanner(System.in); // reads user input
        List<Task> tasks = new ArrayList<>(); // instantiate array list of tasks
        while (true) {
            System.out.println();
            String line = scanner.nextLine();
            if (line.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }
            else if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i < tasks.size() + 1; i++) {
                    Task currentTask = tasks.get(i - 1);
                    System.out.println(String.format(i + "." + currentTask.toString(), currentTask.getStatusIcon()));
                }
            }
            else if (line.contains("done")) {
                int taskIndex = Integer.parseInt(line.replaceAll("\\D+","")) - 1;
                Task currentTask = tasks.get(taskIndex);
                currentTask.setIsDone(); // set current task to done (opposite of current state of isDone)
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(String.format(currentTask.toString(), currentTask.getStatusIcon()));
            }
            else {
                Task newTask = null;
                if (line.contains("todo")) { // task is a todo
                    String newLine = line.substring(4);
                    newTask = new ToDo(newLine);
                }
                else if (line.contains("deadline")) { // task is a deadline
                    int index = line.indexOf("/by "); // index of end of '\by ', which is ' '
                    String description = line.substring(8, index - 1); // from ' ' after 'deadline' to ' ' before '/by'
                    String by = line.substring(index + 4, line.length()); // from ' ' after '\by' to end of input
                    newTask = new Deadline(description, by);
                }
                else if (line.contains("event")){ // task is an event
                    int index = line.indexOf("/at ");
                    String description = line.substring(5, index - 1); // from ' ' after 'event' to ' ' before '/at'
                    String at = line.substring(index + 4, line.length()); // from ' ' after '\at' to end of input
                    newTask = new Event(description, at);
                }
                else { // normal statement
                    newTask = new Task(line);
                }
                tasks.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println(newTask.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
            }
        }
    }
}
