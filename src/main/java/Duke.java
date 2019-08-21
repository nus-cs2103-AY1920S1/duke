import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        Scanner scn = new Scanner(System.in);

        String input = scn.nextLine();
        Task tasks[] = new Task[100];
        int task_count = 0;

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < task_count; i++) {
                    Task t = tasks[i];
                    System.out.println((i + 1) + "." + t.toString());
                }
            } else if (input.substring(0, 4).equals("done")) {
                int do_Index = Integer.parseInt(input.substring(5)) - 1;
                Task chosen_Task = tasks[do_Index];
                chosen_Task.markAsDone();

                System.out.println("Nice! I've marked this task as done:\n" +
                        chosen_Task.toString());
            } else {
                tasks[task_count] = new Task(input);
                task_count++;
                System.out.println("added: " + input);
            }
            input = scn.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
