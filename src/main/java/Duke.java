import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void printList(ArrayList<Task> list) {
        int i = 1;
        for (Task task : list) {
            System.out.println(i + "." + task.toString());
            i += 1;
        }
    }

    public static void taskDone(int i, ArrayList<Task> list) {
        list.get(i - 1).markAsDone();
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
                System.out.println("added: " + input);
                list.add(new Task(input));
            }
            input = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");



    }
}
