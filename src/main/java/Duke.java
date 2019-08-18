import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean bye = false;
        LinkedList<Task> lst = new LinkedList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "    ____________________________________________________________";
        System.out.println("Hello from\n" + logo);

        System.out.println(line + "\n" + "     Hello! I'm Duke\n     What can I do for you?\n" + line);

        while (!bye) {
            String command = sc.nextLine();
            switch (command) {
                case "bye":
                    bye = true;
                    System.out.println(line + "\n" + "     Bye. Hope to see you again soon!\n" + line);
                    break;
                case "list":
                    int size = lst.size();
                    System.out.println(line);
                    System.out.println("     Here are the tasks in your list:");
                    for (int i = 1; i <= size; i++) {
                        Task temp = lst.removeFirst();
                        System.out.printf("     %d.%s\n", i, temp);
                        lst.addLast(temp);
                    }
                    System.out.println(line);
                    break;
                default:
                    String[] arr = command.split(" ");
                    if (arr[0].equals("done")) {
                        int done = Integer.parseInt(arr[1]);
                        Task temp = lst.get(done - 1);
                        temp.markAsDone();
                        System.out.println(line);
                        System.out.println("     Nice! I've marked this task as done:");
                        System.out.println("       " + temp);
                        System.out.println(line);
                    } else {
                        Task task = new Task(command);
                        lst.addLast(task);
                        System.out.println(line + "\n     added: " + command + "\n" + line);
                    }
                    break;
            }
        }
    }
}
