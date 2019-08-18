import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void greet() {
        String input;
        int pos;
        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            input = sc.next();
            switch (input) {
                case ("bye") :
                    System.out.println("_____________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("_____________________________________________________");
                    return;

                case ("list") :
                    System.out.println("_____________________________________________________");
                    System.out.println("Here are the tasks in your list:");

                    for (int i = 0; i < arr.size(); i++) {
                        System.out.println(i+1 + "." + arr.get(i));
                    }
                    System.out.println("_____________________________________________________");
                    break;

                case ("done") :
                    pos = sc.nextInt();
                    arr.get(pos - 1).markAsDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(arr.get(pos - 1));
                    break;

                default :
                    System.out.println("_____________________________________________________");
                    String rest = sc.nextLine();
                    input = input + rest;
                    Task t = new Task(input);
                    arr.add(t);
                    System.out.println("added: " + input);
                    System.out.println("_____________________________________________________");
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("_____________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you");
        System.out.println("_____________________________________________________");
        Duke.greet();
    }
}

