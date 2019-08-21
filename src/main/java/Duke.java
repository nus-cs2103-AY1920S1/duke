import java.util.Scanner;
import java.util.LinkedList;

public class Duke {

    static LinkedList<Task> list = new LinkedList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String command;
        printHello();

        while (!( command = sc.next()).equals("bye")) {
            if (command.equals("list")) {
                System.out.println("    _____________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < list.size(); i++) {
                    int number = i + 1;
                    System.out.println("     " + number + "." + list.get(i));
                }
                System.out.println("    _____________________________________\n");
                sc.nextLine();
            } else if (command.equals("done")) {
                int taskNumber = sc.nextInt() - 1;
                list.get(taskNumber).markAsDone();
                System.out.println("    _____________________________________");
                System.out.println("     Nice! I've marked this task as done:");
                System.out.println("       " + list.get(taskNumber));
                System.out.println("    _____________________________________\n");
                sc.nextLine();
            } else if (command.equals("todo")) {
                Task newTodo = new Todo(sc.nextLine().trim());
                list.add(newTodo);
                printAddTask(newTodo);
            } else if (command.equals("deadline")) {
                String[] statement = sc.nextLine().split("/by");
                Task newDeadline = new Deadline(statement[0].trim(), statement[1].trim());
                list.add(newDeadline);
                printAddTask(newDeadline);
            } else if (command.equals("event")) {
                String[] statement = sc.nextLine().split("/at");
                Task newEvent = new Event(statement[0].trim(), statement[1].trim());
                list.add(newEvent);
                printAddTask(newEvent);
            }
        }
        printBye();
    }
    public static void printHello() {
        System.out.println("    _____________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    _____________________________________\n");
    }

    public static void printBye() {
        System.out.println("    _____________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________");
    }

    public static void printAddTask(Task newTask) {
        System.out.println("    _____________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + list.size() + " tasks in the list.");
        System.out.println("    _____________________________________\n");
    }
}
