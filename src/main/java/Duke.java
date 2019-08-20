import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        //Greets user
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");


        Scanner scan = new Scanner(System.in);
        Task[] list = new Task[100];
        int index = 1;
        String input = scan.nextLine();
        
        while (!input.equals("bye")) {
            String arr[] = input.split(" ",2 );
            String command = arr[0];
            switch (command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 1; i < index; i++) {
                        System.out.println(i + "." + list[i]);
                    }
                    break;
                case "done":
                    int num = Integer.parseInt(arr[1]);
                    System.out.println(list[num].done());
                    break;
                case "todo":
                    list[index] = new Task(arr[1]);
                    System.out.println("Got it. I've added this task:\n" + list[index]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                case "deadline":
                    String[] deadline = arr[1].split("/by ", 2);
                    list[index] = new Deadline(deadline[0], deadline[1]);
                    System.out.println("Got it. I've added this task:\n" + list[index]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                case "event":
                    String[] event = arr[1].split("/at ", 2);
                    list[index] = new Event(event[0], event[1]);
                    System.out.println("Got it. I've added this task:\n" + list[index]);
                    System.out.println("Now you have " + index + " tasks in the list");
                    index++;
                    break;
                default:
                    System.out.println("Invalid command");
            }
            //Taking in the next line
            input = scan.nextLine();
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
