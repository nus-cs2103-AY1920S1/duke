import java.util.LinkedList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
//        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        LinkedList<Task> lst = new LinkedList<>();

        Scanner sc = new Scanner(System.in);
        String word = sc.nextLine();

        while (!word.equals("bye")) {
            if (word.equals("list")) {
                int counter = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task t : lst) {
                    System.out.println(counter + ". " + "[" + t.getStatusIcon() + "] " + t.getDescription());
                    counter++;
                }
            } else if (word.startsWith("done ")) {
                String[] arr = word.split(" ");
                int i = Integer.valueOf(arr[1]);
                Task task = lst.get(i - 1);
                lst.get(i - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
            } else {
                System.out.println("added: " + word);
                lst.add(new Task(word));
            }
            word = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
