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
            //Adding the command "list" to list all added tasks
            if (input.equals("list")) {
                for (int i = 1; i < index; i++) {
                    System.out.println(list[i]);
                }
            } else if (input.contains("done")){
                String arr[] = input.split(" ");
                int num = Integer.parseInt(arr[1]);
                System.out.println(list[num].done());
            } else {
                //Adds input to list
                list[index] = new Task(input, index);
                index++;
                System.out.println("added: " + input);
            }
            input = scan.nextLine();
        }

        //Exit
        System.out.println("Bye. Hope to see you again soon!");

    }
}
