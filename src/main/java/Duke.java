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
            else if (line.contains("todo")) {
                ToDo newToDo = new ToDo(line);
                System.out.println("Got it. I've added this task:");
                tasks.add(newToDo);
                System.out.println(newToDo.toString());
                System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
            }
            else {
                Task newTask = new Task(line);
                tasks.add(newTask); // add user input to toDos
                System.out.println("added: " + newTask.toString()); // echos user input
            }
        }
    }
}
