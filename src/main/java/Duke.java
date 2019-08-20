import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");

        while(true) {
            String next = sc.next();


            if (next.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    int taskNumber = i + 1;
                    String taskString = Integer.toString(taskNumber);
                    System.out.println(taskString + ".[" + store.get(i).getStatusIcon() + "] " + store.get(i).toString());
                }
            } else if (next.equals("done")) {
                int taskNo = sc.nextInt();
                store.get(taskNo-1).markAsDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("  " + store.get(taskNo-1).toString());
            } else if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (next.equals("todo") || next.equals("deadline") || next.equals("event")){
                Task newTask;
                String remainder = sc.nextLine();
                if (next.equals("todo")) {
                    newTask = new Todo(remainder);
                } else if (next.equals("deadline")) {
                    int position = remainder.indexOf("/");
                    newTask = new Deadline(remainder.substring(0,position), remainder.substring(position+3));
                } else {
                    int position = remainder.indexOf("/");
                    newTask = new Event(remainder.substring(0,position), remainder.substring(position+3));
                }
                store.add(newTask);
                System.out.println("Got it. I've added this task: ");
                System.out.println("  " + newTask.toString());
                String size = Integer.toString(store.size());
                System.out.println("Now you have " + size + " tasks in the list.");
            } else {
                //exception handling
            }
        }
    }
}
