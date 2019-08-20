import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void start() throws DukeException {
        String input;
        int pos;
        ArrayList<Task> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String divider = "_____________________________________________________";
        while(sc.hasNext()) {
            input = sc.next();
            try {
                switch (input) {
                    case ("bye"):
                        System.out.println(divider);
                        System.out.println("Bye. Hope to see you again soon!");
                        System.out.println(divider);
                        return;

                    case ("list"):
                        System.out.println(divider);
                        System.out.println("Here are the tasks in your list:");

                        for (int i = 0; i < arr.size(); i++) {
                            System.out.println(i + 1 + "." + arr.get(i));
                        }
                        System.out.println(divider);
                        break;

                    case ("done"):
                        System.out.println(divider);
                        pos = sc.nextInt();
                        arr.get(pos - 1).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(arr.get(pos - 1));
                        System.out.println(divider);
                        break;


                    case ("todo"):
                        System.out.println(divider);
                        String remaining = sc.nextLine();
                        if (remaining.equals("")) {
                            throw new DukeException("OOPS!!! The Description of a todo cannot be empty");
                        }
                        Todo t = new Todo(remaining.substring(1));
                        arr.add(t);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(t);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println(divider);
                        break;

                    case ("event"):
                        System.out.println(divider);
                        String remainingStuff = sc.nextLine();
                        input = input + remainingStuff;
                        int end = input.indexOf('/');
                        String description = remainingStuff.substring(1, remainingStuff.indexOf('/'));
                        String time = input.substring(end + 4);
                        Event m = new Event(description, time);
                        arr.add(m);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(m);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println(divider);
                        break;

                    case ("deadline"):
                        System.out.println(divider);
                        String remainingStuff2 = sc.nextLine();
                        input = input + remainingStuff2;
                        int endies = input.indexOf('/');
                        String description2 = remainingStuff2.substring(1, remainingStuff2.indexOf('/'));
                        String timezies = input.substring(endies + 4);
                        Deadline k = new Deadline(description2, timezies);
                        arr.add(k);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(k);
                        System.out.println("Now you have " + arr.size() + " tasks in the list.");
                        System.out.println(divider);
                        break;

                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException dx) {
                System.out.println(dx.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException{
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
        Duke.start();
    }
}

