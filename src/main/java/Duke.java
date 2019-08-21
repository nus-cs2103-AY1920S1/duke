import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<Task> tasks;

    public static void printGreeting() {
        String greet_msg = "    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________\n";
        System.out.println(greet_msg);
    }

    public static void printBye() {
        System.out.println("    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________");
    }

    public static void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < tasks.size(); i++) {
            int list_num = i + 1;
            Task task = tasks.get(i);
            System.out.println("    " + list_num + ".[" + task.getStatusIcon() + "] " + task.toString());
        }
        System.out.println("    ____________________________________________________________\n");
    }

    public static void printInput(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + input + '\n' +
                "    ____________________________________________________________\n");
    }

    public static void printDone(String task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       [" + "\u2713" + "] " + task + '\n' +
                "    ____________________________________________________________\n");
    }

    public static void main(String[] args) {
        printGreeting();
        tasks = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String user_input = sc.nextLine();
            String[] input_string = user_input.split(" ");
            if (input_string[0].equals("bye")) {
                printBye();
                System.exit(0);
            } else if (input_string[0].equals("list")) {
                printList();
            } else if (input_string[0].equals("done")) {
                int task_num = Integer.parseInt(input_string[1]) - 1;
                Task task = tasks.get(task_num);
                task.setDone();
                printDone(task.toString());
            } else {
                Task task = new Task(user_input);
                tasks.add(task);
                printInput(user_input);
            }
        }
    }
}
