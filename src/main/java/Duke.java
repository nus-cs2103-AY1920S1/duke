import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
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
        ArrayList<Task> todolist = new ArrayList<Task>();

        while (scanner.hasNext()) {
            String request = scanner.nextLine();

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
            } else if (request.startsWith("done")) {
                char num = request.charAt(request.length() - 1);
                int index = Character.getNumericValue(num);
                Task task = todolist.get(index - 1);
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.print("  ");
                System.out.println(task.toString());
            } else {
                todolist.add(new Task(request));
                String add = String.format("added: %s", request);
                System.out.println(add);
            }

            System.out.println("____________________________________________________________");
        }
    }
}