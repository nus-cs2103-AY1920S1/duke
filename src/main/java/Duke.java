import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Task> actions = new ArrayList<Task>(100);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        while(input.hasNext()) {
            String command = input.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (command.equals("list") && actions.size() == 0) {
                System.out.println("No text found.");
            } else if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < actions.size(); i++) {
                    Task current = actions.get(i);
                    System.out.println((i + 1) +  "." + current.toString());
                }
            } else if (command.startsWith("done")) {
                int taskNumber = Integer.valueOf(command.substring(command.length() - 1)).intValue();
                if(taskNumber > actions.size()) {
                    System.out.println("No task found.");
                } else {
                    System.out.println("Nice! I've marked this task as done:");
                    Task current = actions.get(taskNumber - 1);
                    current.markAsDone();
                    System.out.println(current.toString());
                }
            } else if (command.startsWith("deadline")) {
                System.out.println("Got it. I've added this task:");
                String[] temp = command.substring(8).split("/by");
                Task current = new Deadline(temp[0].trim(), temp[1].trim());
                actions.add(current);
                System.out.println(current.toString());
                System.out.println("Now you have " + actions.size() + " tasks in the list.");
            } else if (command.startsWith("event")) {
                System.out.println("Got it. I've added this task:");
                String[] temp = command.substring(5).split("/at");
                Task current = new Event(temp[0].trim(), temp[1].trim());
                actions.add(current);
                System.out.println(current.toString());
                System.out.println("Now you have " + actions.size() + " tasks in the list.");
            } else {
                System.out.println("Got it. I've added this task:");
                Task current = new Todo(command.substring(5));
                actions.add(current);
                System.out.println(current.toString());
                System.out.println("Now you have " + actions.size() + " tasks in the list.");
            }
        }
    }
}


