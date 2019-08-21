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
        String outputString = "added: " + task.getDescription();
        System.out.println(outputString);
    }

    public void done(int number) {
        Task task = tasks.get(number - 1);
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
            if (command.equals("bye")) {
                break;
            } else if (command.equals("list")){
                duke.list();
            } else {
                String[] commandSplit = command.split(" ");
                if (!commandSplit[0].equals("done")) {
                    Task task = new Task(command);
                    duke.add(task);
                } else {
                    int index = Integer.parseInt(commandSplit[1]);
                    duke.done(index);
                }
            }
        }
        duke.bye();
    }
}