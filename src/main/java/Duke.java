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
                for (Task task : lst) {
                    System.out.println(counter + ". " + task);
                    counter++;
                }
            } else if (word.startsWith("done ")) {
                String[] arr = word.split(" ");
                int i = Integer.valueOf(arr[1]);
                Task task = lst.get(i - 1);
                lst.get(i - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("\t" + task);
            } else {
                String[] arr = word.split("/");
                String[] arr2 = arr[0].split(" ", 2);

                Task task;
                if (arr2[0].equals("todo")) {
                    task = new Todo(arr2[1], "");
                } else  if (arr2[0].equals("deadline")) {
                    task = new Deadline(arr2[1], arr[1]);
                } else {
                    task = new Event(arr2[1], arr[1]);
                }
                lst.add(task);


                System.out.println("Got it. I've added this task:");
//                System.out.println("\t" + "[" + task.getSymbol() + "][" + task.getStatusIcon() + "] " + task.getDescription() + " (" + task.getInfo() + ")");
                System.out.println("\t" + task.toString());
                System.out.println("Now you have " +  lst.size()  +  " tasks in the list.");

            }
            word = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
