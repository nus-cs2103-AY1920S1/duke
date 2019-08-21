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
        System.out.println("    ____________________________________________________________\n" +
                "     Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int list_num = i + 1;
            Task task = tasks.get(i);
            if (task.getType().equals("todo")) {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString());
            } else if (task.getType().equals("event")) {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString() + " (at: " + task.getDate() + ")");
            } else {
                System.out.println("    " + list_num + "." + task.getTypeIcon() + '[' + task.getStatusIcon() + "] " + task.toString() + " (by: " + task.getDate() + ")");
            }
        }
        System.out.println("    ____________________________________________________________");
    }

    public static void printInput(String input) {
        System.out.println("    ____________________________________________________________\n" +
                "     added: " + input + '\n' +
                "    ____________________________________________________________\n");
    }

    public static void printDone(String task) {
        System.out.println("    ____________________________________________________________\n" +
                "     Nice! I've marked this task as done: \n" +
                "       [" + '+' + "] " + task + '\n' +
                "    ____________________________________________________________");
    }

    public static void printTodo(Todo t) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [T]" + "[ ]" + ' ' + t.toString() + '\n' +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    public static void printDeadline(Deadline d) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [D][ ] " + d.toString() + " (by: " + d.getDate() + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
    }

    public static void printEvent(Event e) {
        System.out.println("    ____________________________________________________________\n" +
                "     Got it. I've added this task: \n" +
                "       [E][ ] " + e.toString() + " (at: " + e.getDate() + ")\n" +
                "     Now you have " + tasks.size() + " tasks in the list.\n" +
                "    ____________________________________________________________");
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
            } else if (input_string[0].equals("todo")) {
                String task_name = ((user_input.split(" ", 2))[1]).strip();
                Todo t = new Todo(task_name);
                tasks.add(t);
                printTodo(t);
            } else if (input_string[0].equals("deadline")) {
                String[] separate_task_date = user_input.split("/");
                String task_name = ((separate_task_date[0].split(" ", 2))[1]).strip();
                String date = ((separate_task_date[1].split(" ", 2))[1]).strip();
                Deadline d = new Deadline(task_name, date);
                tasks.add(d);
                printDeadline(d);
            } else if (input_string[0].equals("event")) {
                String[] separate_task_date = user_input.split("/");
                String task_name = ((separate_task_date[0].split(" ", 2))[1]).strip();
                String date = ((separate_task_date[1].split(" ", 2))[1]).strip();
                Event e = new Event(task_name, date);
                tasks.add(e);
                printEvent(e);
            } else {
                Task task = new Task(user_input);
                tasks.add(task);
                printInput(user_input);
            }
        }
    }
}
