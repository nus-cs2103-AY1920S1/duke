import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        String lines = "    ______________________________________________";
        System.out.println(lines);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> arr = new ArrayList<>();

        String str = sc.nextLine();
        while(!str.equals("bye")) {
            String[] strArr = str.split(" ");
            if (str.equals("list")) {
                int i = 1;
                System.out.println(lines);
                System.out.println("    Here are the tasks in your list:");
                for (Task t : arr) {
                    System.out.println("    " + i + ". " + t);
                    i++;
                }
                System.out.println(lines);
            } else if(strArr[0].equals("done")) { //mark task as done
                int index = Integer.parseInt(strArr[1]) - 1;
                Task t = arr.get(index);
                t.markAsDone();
                System.out.println(lines);
                System.out.println("    Nice! I've marked this task as done:");
                System.out.println("      " + t);
                System.out.println(lines);
            } else {
                Task t;
                if (strArr[0].equals("todo")) { //todo
                    t = new Todo(str.substring(5));
                    arr.add(t);
                } else if (strArr[0].equals("deadline")) { //deadline
                    int indexOfSlash = str.indexOf(47);
                    t = new Deadline(str.substring(9, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                    arr.add(t);
                } else { //event
                    int indexOfSlash = str.indexOf(47);
                    t = new Event(str.substring(6, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                    arr.add(t);
                }

                System.out.println(lines);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + t);
                System.out.println("    Now you have " + arr.size() + " tasks in the list.");
                System.out.println(lines);
            }
            str = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }
}
