import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    /**
     * This class tests for chatbot Duke.
     */
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        */
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        int i = 0;

        while(sc.hasNext()) {
            String input = sc.next();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) { //go through the arrayList
                System.out.println("Here are the tasks in your list: ");
                for (int j = 0; j < arr.size(); j++) {
                    System.out.println((j + 1) + "." + arr.get(j));
                }
            } else if (input.equals("done")) {
                int num = sc.nextInt();
                System.out.println("Nice! I've marked this task as done: ");
                arr.get(num - 1).markAsDone();
                System.out.println(arr.get(num));
            } else if (input.equals("todo")) {
                String descTodo = sc.nextLine();
                if (descTodo.isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                arr.add(new Todo(descTodo));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
            } else if (input.equals("deadline")) {
                String rem = sc.nextLine();
                String[] descriptionNDate = rem.split("/by");
                String description = descriptionNDate[0];
                String by = descriptionNDate[1];
                arr.add(new Deadline(description, by));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
            } else if (input.equals("event")) {
                String rest = sc.nextLine();
                String[] descriptionNAt = rest.split("/at");
                String desc = descriptionNAt[0];
                String at = descriptionNAt[1];
                arr.add(new Event(desc, at));
                System.out.println("Got it. I've added this task: ");
                System.out.println(" " + arr.get(i));
                System.out.println("Now you have " + arr.size() + " tasks in the list.");
                i++;
            } else if (input.equals("delete")) {
                int deleteNum = sc.nextInt();
                Task toRemove = arr.get(deleteNum - 1);
                arr.remove(deleteNum-1);
                System.out.println("Noted. I've removed this task: ");
                System.out.println(" " + toRemove);
                System.out.println("Now you have " + arr.size() + " tasks in the list. ");
                i--;
            } else {
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
    }
}
