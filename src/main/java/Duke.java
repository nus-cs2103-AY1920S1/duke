import java.io.*;
import java.text.ParseException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<Task> alist = new ArrayList<>();
    private static String fileName = "duke.txt";

    private Duke() {}

    private void run() {
        loadData();
        ArrayList<Task> alist = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            try {
                String input = scanner.next();
                switch (input) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again soon!\n");
                        break;
                    case "list":
                        System.out.println("Here are the tasks in your list:\n");
                        int counter = 0;
                        for (Task t : alist) {
                            counter++;
                            System.out.println(counter + ". " + t);
                        }
                        break;
                    case "delete":
                        int index = scanner.nextInt();
                        if (index > alist.size()) {
                            throw new DukeException("OOPS!!! Task not found.");
                        }
                        Task task = alist.get(index - 1);
                        alist.remove(task);
                        writeToHardDisk();
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(task);
                        System.out.println("Now you have " + alist.size() + " tasks in the list.");
                        break;
                    case "done":
                        int indexD = scanner.nextInt();
                        if (indexD > alist.size()) {
                            throw new DukeException("OOPS!!! Task not found.");
                        }
                        Task taskDone = alist.get(indexD - 1);
                        taskDone.markDone();
                        writeToHardDisk();
                        System.out.println("Nice! I've marked this task as done: \n" +
                                "[" + taskDone.getStatusIcon() + "] " + taskDone.description);
                        break;
                    case "todo":
                        String what = scanner.nextLine();
                        if (what.isEmpty()) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        Todo todo = new Todo(what);
                        alist.add(todo);
                        writeToHardDisk();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(todo);
                        System.out.println("Now you have " + alist.size() + " tasks in the list.");
                        break;
                    case "deadline":
                        String when = scanner.nextLine();
                        if (when.isEmpty()) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String[] parts = when.split("/by");
                        if (parts.length == 1) {
                            throw new DukeException("OOPS!!! The time of a deadline cannot be empty.");
                        }
                        String desc = parts[0];
                        String time = parts[1];
                        Deadline deadline = new Deadline(desc);
                        deadline.parseTime(time);
                        alist.add(deadline);
                        writeToHardDisk();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(deadline);
                        System.out.println("Now you have " + alist.size() + " tasks in the list.");
                        break;
                    case "event":
                        String where = scanner.nextLine();
                        if (where.isEmpty()) {
                            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
                        }
                        String[] partsE = where.split("/at");
                        if (partsE.length == 1) {
                            throw new DukeException("OOPS!!! The time of an event cannot be empty.");
                        }
                        String descE = partsE[0];
                        String timeE = partsE[1];
                        Event event = new Event(descE);
                        event.parseTime(timeE);
                        alist.add(event);
                        writeToHardDisk();
                        System.out.println("Got it. I've added this task:");
                        System.out.println(event);
                        System.out.println("Now you have " + alist.size() + " tasks in the list.");
                        break;
                    default:     //invalid task name
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException | ParseException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    private static void loadData() {
        String line;
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                String description = line.substring(7);
                if (line.charAt(1) == 'T') {
                    Todo todo = new Todo(description);
                    if (line.charAt(4) == '\u2713') {
                        todo.markDone();
                    }
                    alist.add(todo);
                } else if (line.charAt(1) == 'D') {
                    Deadline deadline = new Deadline(description);
                    if (line.charAt(4) == '\u2713') {
                        deadline.markDone();
                    }
                    alist.add(deadline);
                } else {
                    Event event = new Event(description);
                    if (line.charAt(4) == '\u2713') {
                        event.markDone();
                    }
                    alist.add(event);
                }
            }
            br.close();
        } catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
    private static void writeToHardDisk() {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            for(Task t: alist) {
                fileWriter.write(t.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");
        Duke newDuke = new Duke();
        newDuke.run();
    }
}
