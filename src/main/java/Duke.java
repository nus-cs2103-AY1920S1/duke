import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void printList(ArrayList<Task> list) {
        int idx = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : list) {
            System.out.println(idx + ". " + t);
            idx++;
        }
    }

    public static void processor(){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String command;
        while(!(command = sc.nextLine()).equals("bye")) {
            if(command.equals("list")) {
                printList(tasks);
            } else {
                String[] words = command.split(" ");
                if (words[0].equals("done")) {
                    int i = Integer.parseInt(words[1]);
                    Task t = tasks.get(i - 1);
                    t.markAsDone();
                    System.out.println("Nice! I've marked this task as done: \n  " + t);
                } else {
                    Task t = new Task(command);
                    tasks.add(t);
                    System.out.println("added: " + command);
                }
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        processor();
    }
}
