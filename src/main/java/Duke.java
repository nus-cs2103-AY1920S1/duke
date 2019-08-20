import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
/*        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> commandList = new ArrayList<Task>();
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                int size = commandList.size();
                for (int i = 1; i < size + 1; i++) {
                    System.out.print(i + ".");
                    System.out.println(commandList.get(i - 1));
                }
            } else if (input.substring(0,4).equals("done")) {
                String[] arr = input.split(" ");
                int number = Integer.parseInt(arr[1]);
                commandList.get(number-1).complete();
            } else {
                commandList.add(new Task(input));
                System.out.print("added : ");
                System.out.println(input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
        }
    }
