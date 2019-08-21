import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> tasks = new ArrayList<>();
    public static int numberOfTasks = 0;

    public Duke() {
    }

    public void greet() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    //Lists out all the tasks in Duke
    public void list() {
        int number = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : tasks) {
            String outputString = number + ". " + task.toString();
            System.out.println(outputString);
            number++;
        }
    }

    public void add(Task task) {
        numberOfTasks++;
        tasks.add(task);
        String outputString = "Got it. I've added this task: \n" + task.toString();
        System.out.println(outputString);
        System.out.println("Now you have " + numberOfTasks + " tasks in the list.");
    }

    public void done(int number) {
        Task task = Duke.tasks.get(number - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(task.toString());
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                duke.bye();
                break;
            } else if (command.equalsIgnoreCase("list")){
                duke.list();
            } else {
                String[] commandSplit = command.split(" ");
                String deadline = "deadline";
                String event = "event";
                String todo = "todo";
                if (!commandSplit[0].equalsIgnoreCase("done")) {
                    if (commandSplit[0].equalsIgnoreCase(deadline)) {
                        String[] detail = command.substring(deadline.length()).split("/by");
                        Task addTask = new Deadline(detail[0], detail[1]);
                        duke.add(addTask);
                    } else if (commandSplit[0].equalsIgnoreCase(event)) {
                        String[] detail = command.substring(event.length()).split("/at");
                        Task addTask =  new Event(detail[0], detail[1]);
                        duke.add(addTask);
                    } else {
                        Task addTask = new Todo(command.substring(todo.length()));
                        duke.add(addTask);
                    }

                } else {
                    int index = Integer.parseInt(commandSplit[1]);
                    duke.done(index);
                }
            }
        }

    }
}