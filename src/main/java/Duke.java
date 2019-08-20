import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printWelcomeMsg();


        List<Task> list = new LinkedList<>();
        String command = sc.nextLine();

        while (!command.equals("")) {
            printHorizontalLine();
            if (command.equals("bye")) {
                System.out.println("     Bye. Hope to see you again soon!");
                printHorizontalLine();
                break;
            } else if (command.equals("list")) {
                printTasks(list);
            } else if (command.length() > 4 && command.substring(0, 4).equals("done")) {
                doneTask(list, command);
            } else {
                list.add(new Task(command));
                System.out.println("     added: " + command);
            }
            printHorizontalLine();
            command = sc.nextLine();
        }

        sc.close();
    }

    private static void doneTask(List<Task> list, String command) {
        String[] done = command.split(" ");
        int number = Integer.valueOf(done[1]);
        list.get(number - 1).markAsDone();
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + list.get(number - 1));
    }

    private static void printTasks(List<Task> list) {
        System.out.println("     Here are the tasks in your list:");
        int count = 0;
        for (Task task:list) {
            count++;
            System.out.println("     " + count + "." + task);
        }
    }

    private static void printWelcomeMsg() {
        printHorizontalLine();
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
