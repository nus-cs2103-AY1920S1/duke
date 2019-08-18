import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static ArrayList<Task> arr = new ArrayList<>();
    public static String lines = "    ____________________________________________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(lines);
        System.out.println("    Hello! I'm Duke\n    What can I do for you?");
        System.out.println(lines);

        Scanner sc = new Scanner(System.in);

        String str = sc.nextLine();
        while (!str.equals("bye")) {
            try {
                input(str);
            } catch (DukeException e) {
                System.out.println(lines);
                System.err.println("    " + e.getMessage());
                System.out.println(lines);
            }
            str = sc.nextLine();
        }
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }

    public static void input(String str) throws DukeException {

        String[] strArr = str.split(" ");

        if (str.equals("list")) { //list

            int i = 1;
            System.out.println(lines);
            System.out.println("    Here are the tasks in your list:");

            for (Task t : arr) {
                System.out.println("    " + i + "." +  t);
                i++;
            }

            System.out.println(lines);

        } else if(strArr[0].equals("done")) { //mark task as done

            if (strArr.length == 1) {
                throw new DukeException("OOPS!!! Please state the task number you want to mark as done.");
            }

            int index = Integer.parseInt(strArr[1]) - 1;

            if (index >= arr.size()) {
                throw new DukeException("OOPS!!! The number is too large.");
            }

            Task t = arr.get(index);
            t.markAsDone();

            System.out.println(lines);
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("    " + t);
            System.out.println(lines);

        } else if(strArr[0].equals("todo")
                || strArr[0].equals("deadline")
                || strArr[0].equals("event")) { //add task to list

            Task t;

            if (strArr[0].equals("todo")) { //todo

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                }

                t = new Todo(str.substring(5));
                arr.add(t);

            } else if (strArr[0].equals("deadline")) { //deadline

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                }
                if (!str.contains(" /by ")) {
                    throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
                }

                int indexOfSlash = str.indexOf(47);
                String ss1 = str.substring(0, indexOfSlash);
                String[] ss1Arr = ss1.split(" ");
                String ss2 = str.substring(indexOfSlash);
                String[] ss2Arr = ss2.split(" ");

                if (ss1Arr.length == 1) {
                    throw new DukeException("OOPS!!! The description of deadline cannot be empty.");
                }
                if (ss2Arr.length == 1) {
                    throw new DukeException("OOPS!!! The date/time of deadline cannot be empty.");
                }

                t = new Deadline(str.substring(9, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                arr.add(t);

            } else { //event

                if (strArr.length == 1) {
                    throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                }
                if (!str.contains(" /at ")) {
                    throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
                }

                int indexOfSlash = str.indexOf(47);
                String ss1 = str.substring(0, indexOfSlash);
                String[] ss1Arr = ss1.split(" ");
                String ss2 = str.substring(indexOfSlash);
                String[] ss2Arr = ss2.split(" ");

                if (ss1Arr.length == 1) {
                    throw new DukeException("OOPS!!! The description of event cannot be empty.");
                }
                if (ss2Arr.length == 1) {
                    throw new DukeException("OOPS!!! The date/time of event cannot be empty.");
                }

                t = new Event(str.substring(6, indexOfSlash - 1), str.substring(indexOfSlash + 4));
                arr.add(t);

            }

            System.out.println(lines);
            System.out.println("    Got it. I've added this task:");
            System.out.println("      " + t);
            System.out.println("    Now you have " + arr.size() + " tasks in the list.");
            System.out.println(lines);

        } else {
            throw new DukeException("OOPS!!! I,m sorry, but I don't know what that means :-(");
        }
    }
}
