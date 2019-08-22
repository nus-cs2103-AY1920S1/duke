import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void printList(ArrayList<Task> list) {
        int i = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task task : list) {
            System.out.println(i + "." + task.toString());
            i += 1;
        }
    }

    public static void taskDone(int i, ArrayList<Task> list) {
        list.get(i - 1).markAsDone();
    }

    public static void addTask(String input, ArrayList<Task> list) {
        Task task;
        String detail;
        String dueDetail;
        String command = input.substring(0, input.indexOf(' '));
        String rest = input.substring(input.indexOf(' ') + 1, input.length());
        System.out.println("Got it. I've added this task:");

        if (command.equals("todo")) {
            task = new ToDos(rest);
            list.add(task);
            System.out.println("\t" + task.toString());
        } else if (command.equals("deadline")) {
            String[] detAsArr = rest.split(" /by ");
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Deadline(detail, dueDetail);
            list.add(task);
            System.out.println("\t" + task.toString());
        } else {
            String[] detAsArr = rest.split(" /at ");
            detail = detAsArr[0];
            dueDetail = detAsArr[1];
            task = new Event(detail, dueDetail);
            list.add(task);
            System.out.println("\t" + task);
        }

        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        String input;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner scanner = new Scanner(System.in);
        
        input = scanner.nextLine();

        while (! input.equals("bye")) {
            if (input.equals("list")) {
                printList(list);
            } else if (input.substring(0, input.indexOf(' ')).equals("done")) {
                taskDone(Integer.parseInt(input.substring(input.indexOf(' ') + 1, input.length())), list);
            } else {
               addTask(input, list); 
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");



    }
}
