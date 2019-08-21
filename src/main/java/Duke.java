import java.util.Scanner;

public class Duke {
    static Task[] array;
    static int count;
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String full = sc.nextLine();
            String[] arr = full.split(" ", 2);
            String s = arr[0];

            try {
                validateInput(full);
            } catch (DukeException d) {
                System.out.println(line + "\n ☹ OOPS!!! " + d.response + "\n" + line);
                continue;
            }

            //handles done commands using markAsDone method
            if (s.equals("done")) {
                markAsDone(Integer.valueOf(arr[1]) - 1);
            }

            //handles tasks using the process method
            else if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
                process(s, arr[1].trim());
            }

            //prints out list
            else if (s.toLowerCase().equals("list")) {
                System.out.println(line + "\n Here are the tasks in your list:");
                for (int i = 1; i <= count; i++) {
                    System.out.println(" " + i + ". " + array[i - 1].toString());
                }
                System.out.println(line);
            }

            else {
                if (s.equals("bye")) {
                    System.out.println(line + "\n Bye. Hope to see you again soon!\n" + line);
                    break;
                }
            }
        }
    }

    public static void intro() {
        array = new Task[100];
        count = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(line + "\n" + logo + "\n Hello! I'm Duke\n What can I do for you?\n" + line);
    }

    public static void process(String s, String des) {
        Task task = new Task("null");
        if (s.equals("todo")) {
            task = new ToDo(des);
        } else if (s.equals("deadline")) {
            String[] arr = des.split("/by");
            task = new Deadline(arr[0].trim(), arr[1].trim());
        } else if (s.equals("event")) {
            String[] arr = des.split("/at");
            task = new Event(arr[0].trim(), arr[1].trim());
        }
        array[count] = task;
        count += 1;
        printTask(task);
    }

    public static void printTask(Task t) {
        System.out.println(line + "\n Got it. I've added this task: \n  " +
                t.toString() + "\n Now you have " + count + " tasks in the list.\n" + line);
    }

    public static void markAsDone(int i) {
        array[i].done();
        Task t = array[i];
        System.out.println(line + "\n Nice! I've marked this task as done: \n " + t.toString() + "\n" + line);
    }

    public static void validateInput(String input) throws DukeException {
        String[] array = input.trim().split(" ");
        String first = array[0];
        Boolean task = false;

        if (first.equals("todo") || first.equals("deadline") || first.equals("event")) {
            task = true;
        }

        if (array.length == 1) {
            //if to do, event or deadline are missing a description
            if (task) {
                throw new DukeException("Empty Description", "The description of a " + first + " cannot be empty.");
            }

            //if done is not followed by a number
            else if (first.equals("done")) {
                throw new DukeException("Missing Task", "Please specify a task to mark as complete.");
            }

            //if it is not a single-worded valid input
            else if (!first.equals("bye") && !first.equals("list")) {
                throw new DukeException("Invalid Input", "I'm sorry, but I don't know what that means :-(");
            }
        } else {
            //if it is an invalid input containing multiple words
            if (!task && !first.equals("done")) {
                throw new DukeException("Invalid Input", "I'm sorry, but I don't know what that means :-(");
            }

            //if event or deadline do not have details
            else if ((first.equals("event") && !input.contains("/at")) ||
                    (first.equals("deadline") && !input.contains("/by"))) {
                throw new DukeException("Missing Details", "The details of a " + first + " cannot be missing.");
            }
        }
    }
}