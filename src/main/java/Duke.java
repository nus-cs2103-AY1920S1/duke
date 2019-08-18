import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greetUser();

        ArrayList<Task> tasks = new ArrayList<Task>();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                exit();
                scanner.close();
                break;
            } else if (command.equals("list")) {
                list(tasks);
            } else if (command.contains("done")) {
                int index = Integer.parseInt(command.split(" ")[1]);
                done(index, tasks);
            } else {
                Task newTask = new Task(command);
                add(newTask, tasks);
            }
        }
    }

    public static void greetUser() {
        printLine();
        System.out.println("\t Hello! I'm Duke");
        System.out.println("\t What can I do for you?");
        printLine();
    }

    public static void list(ArrayList<Task> tasks) {
        printLine();
        System.out.println("\t Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.print("\t " + i + ". ");
            tasks.get(i - 1).printTask();
        }
        printLine();
    }

    public static void done(int index, ArrayList<Task> tasks) {
        Task doneTask = tasks.get(index - 1);
        doneTask.markAsDone();
        printLine();
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.print("\t \t");
        doneTask.printTask();
        printLine();
    }

    public static void add(Task task, ArrayList<Task> tasks) {
        printLine();
        System.out.println("\t added: " + task.getDescription());
        tasks.add(task);
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\t Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printLine() {
        System.out.println("\t -------------------------------------");
    }

}
