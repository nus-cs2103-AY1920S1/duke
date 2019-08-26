import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    protected static String filepath = "data/duke.txt";
    static int idleCount;
    static ArrayList<Task> list;
    static String line = "____________________________________________________________";

    public static void main(String[] args) {
        intro();
        try {
            findFile();
        } catch (IOException e) {
            System.out.println(format("Something went wrong"));
        }

        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String full = sc.nextLine();
            String[] arr = full.split(" ", 2);
            String s = arr[0];

            try {
                validateInput(full);
            } catch (DukeException d) {
                System.out.println(format("☹ OOPS!!! " + d.response));
                continue;
            }

            //handles done commands using markAsDone method
            if (s.equals("done")) {
                markAsDone(Integer.valueOf(arr[1]) - 1);
                try {
                    update (false, "");
                } catch (IOException e) {
                    System.out.println("Something went wrong");
                }
            }

            else if (s.equals("delete")) {
                delete(Integer.valueOf(arr[1]) - 1);
            }

            //handles tasks using the process method
            else if (s.equals("todo") || s.equals("deadline") || s.equals("event")) {
                process(s, arr[1].trim());
            }

            //prints out list
            else if (s.toLowerCase().equals("list")) {
                System.out.println(line + "\n Here are the tasks in your list:");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(" " + i + ". " + list.get(i - 1).toString());
                }
                System.out.println(line);
            }

            else {
                if (s.equals("bye")) {
                    System.out.println(format("Bye. Hope to see you again soon!"));
                    break;
                }
            }
        }
    }

    public static void intro() {
        idleCount = 0;
        list = new ArrayList();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(format(logo + "\n Hello! I'm Duke\n What can I do for you?"));
    }

    public static void findFile() throws IOException {
        File f = new File(filepath);
        f.getParentFile().mkdir();
        f.createNewFile();
        load();
    }

    public static void update(Boolean append, String nextTask) throws IOException {
        FileWriter fw;
        if (append == false) {
            fw = new FileWriter(filepath);
            String tasks = "";
            for (Task t: list) {
                int done = t.isDone? 1 : 0;
                tasks += t.getType() + " . " + done + " . " + t.getFullDescription() + "\n";
            }
            fw.write(tasks);
            fw.close();
        } else {
            fw = new FileWriter(filepath, true);
            fw.append(nextTask);
            fw.close();
        }
    }

    public static void load() throws FileNotFoundException {
        File f = new File(filepath);
        Scanner sc = new Scanner(f);
        while (sc.hasNext()) {
            String full = sc.nextLine();
            String[] arr = full.split(" . ");
            Task t;
            String type = arr[0];
            int done = Integer.parseInt(arr[1]);
            if (type.equals("T")) {
                t = new ToDo(arr[2]);
            } else {
                if (type.equals("D")) {
                    t = new Deadline(arr[2], arr[3]);
                } else {
                    t = new Event(arr[2], arr[3]);
                }
            }
            if (done == 1) {
                t.done();
            }
            list.add(t);
        }
    }

    public static String format(String s) {
        return line + "\n " + s + "\n" + line;
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
        list.add(task);
        try {
            String text = task.getType() + " . 0 . " + task.getFullDescription() + "\n";
            update(true, text);
        } catch (IOException e) {
            System.out.println("Something went wrong");
        }
        printTask(task);
    }

    public static void printTask(Task t) {
        System.out.println(format("Got it. I've added this task: \n   " +
                t.toString() + "\n Now you have " + list.size() + " tasks in the list."));
    }

    public static void markAsDone(int i) {
        Task t = list.get(i);
        t.done();
        System.out.println(format("Nice! I've marked this task as done: \n   " + t.toString()));
    }

    public static void delete(int i) {
        Task t = list.get(i);
        list.remove(i);
        System.out.println(format("Noted. I've removed this task: \n   " + t.toString() + "\n Now you have " +
                list.size() + " tasks in the list."));
    }

    public static void exchangingMeaninglessPleasantries() {
        idleCount += 1;
        if (idleCount >= 5 && (idleCount % 2 == 1)) {
            System.out.println(format("You should be doing your work, really. Would you like me to list your tasks?"));
        } else if (idleCount >= 5 && (idleCount % 2 == 0)) {
            if (list.size() > 4) {
                System.out.println(format("You seem a little free for someone who has " + list.size() + " tasks."));
            } else {
                System.out.println(format("Aren't you a bit too free? Maybe you should take up a hobby."));
            }
        }
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

            //if done or delete are not followed by a number
            else if (first.equals("done") || first.equals("delete")) {
                throw new DukeException("Missing Task", "Please specify a task.");
            }

            //if it is not a single-worded valid input
            else if (!first.equals("bye") && !first.equals("list")) {
                throw new DukeException("Invalid Input", "I'm sorry, but I don't know what that means :-(");
            }
        } else {
            //if it is an invalid input containing multiple words
            if (!task && !first.equals("done") && !first.equals("delete")) {
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