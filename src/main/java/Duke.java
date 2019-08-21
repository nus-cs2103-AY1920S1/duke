import java.util.*;
public class Duke {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int pos = 0;

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        while (sc.hasNextLine()) {
            if (sc.hasNext("done")) {
                String dummy = sc.next();
                int number = sc.nextInt();
                int pos1 = number - 1;
                String dummy1 = sc.nextLine();
                list[pos1].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("[" + list[pos1].getStatusIcon() + "] " + list[pos1]);
            } else {
                String input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    return;
                } else if (input.equals("list")) {
                    int k = 0;
                    while (k != pos) {
                        int bullet = k + 1;
                        System.out.println(bullet + ".[" + list[k].getStatusIcon() + "] " + list[k]);
                        k++;
                    }
                } else {
                    Task task = new Task(input);
                    list[pos] = task;
                    System.out.println("added: " + input);
                    pos++;
                }
            }

        }

    }

}
