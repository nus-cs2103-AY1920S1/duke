import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> todolist = new ArrayList<Task>();

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("____________________________________________________________");
        //System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            String request = scanner.next();

            System.out.println("____________________________________________________________");
            if (request.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return ;
            } else if (request.equals("list")) {
                for (int i = 0; i < todolist.size(); i++) {
                    String todo = String.format("%d. %s", i+1, todolist.get(i).toString());
                    System.out.println(todo);
                }
            } else if (request.equals("done")) {
                int index = scanner.nextInt();
                Task task = todolist.get(index - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  ");
                System.out.println(task.toString());
            } else if (request.equals("todo")){
                String descrip = scanner.nextLine();
                Task task = new Todo(descrip);
                todolist.add(task);
                String add = String.format("added: %s", request);
                addTask(task);
            } else if (request.equals("event")) {
                String[] event = scanner.nextLine().split(" /at ");
                Task task = new Event(event[0], event[1]);
                todolist.add(task);
                addTask(task);
            } else if (request.equals("deadline")) {
                String[] event = scanner.nextLine().split(" /by ");
                Task task = new Deadline(event[0], event[1]);
                todolist.add(task);
                addTask(task);
            }

            System.out.println("____________________________________________________________");
        }
    }

    private static void addTask(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(String.format("  %s", task.toString()));
        System.out.println(String.format("Now you have %d tasks in the list.", todolist.size()));
    }
}