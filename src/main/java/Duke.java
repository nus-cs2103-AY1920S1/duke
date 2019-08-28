import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws IOException {
        Storage storage = new Storage("../../../data/duke.txt");
        Scanner sc = new Scanner(System.in);
<<<<<<< HEAD
        ArrayList<Task> tasks = new ArrayList<Task>();
        String line = "    ____________________________________________________________\n";
        String indent = "    ";
        String logo = line
                    + "     ____        _           \n"
                    + "    |  _ \\ _   _| | _____   \n"
                    + "    | | | | | | | |/ / _ \\  \n"
                    + "    | |_| | |_| |   <  __/   \n"
                    + "    |____/ \\__,_|_|\\_\\___|\n"
                    + "     Hello! I'm Duke          \n"
                    + "     What can I do for you?   \n"
                    + line;
=======
        ArrayList<Task> tasks = storage.readDuke();
        String line = "    ____________________________________________________________\n";
        String indent = "    ";
        String logo = line
                + "     ____        _           \n"
                + "    |  _ \\ _   _| | _____   \n"
                + "    | | | | | | | |/ / _ \\  \n"
                + "    | |_| | |_| |   <  __/   \n"
                + "    |____/ \\__,_|_|\\_\\___|\n"
                + "     Hello! I'm Duke          \n"
                + "     What can I do for you?   \n"
                + line;
>>>>>>> branch-Level-7
        System.out.println(logo);
        String command = sc.nextLine();
        String[] comd = command.split("\\s");
        while (!comd[0].equals("bye")) {
<<<<<<< HEAD
            if (comd[0].isEmpty()) {}
            else if (comd[0].equals("list")) {
=======
            if (comd[0].isEmpty()) {
            } else if (comd[0].equals("list")) {
>>>>>>> branch-Level-7
                System.out.print(line);
                System.out.println(indent + " Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println(indent + " " + (i + 1) + "." + tasks.get(i));
                }
                System.out.println(line);
            } else if (comd[0].equals("done")) {
                try {
                    if (comd.length <= 1) {
                        throw new DukeException(" OOPS!!! The task number cannot be empty.\n");
                    }
                    int n = Integer.parseInt(comd[1]) - 1;
                    if (n < 0 || n > tasks.size() - 1) {
                        throw new DukeException(" OOPS!!! The number inputted is not on the list.\n");
                    }
                    tasks.get(n).markAsDone();
<<<<<<< HEAD
=======
                    storage.writeDuke(tasks);
>>>>>>> branch-Level-7
                    System.out.print(line);
                    System.out.println(indent + " Nice! I've marked this task as done: \n" +
                            indent + "   " + tasks.get(n));
                    System.out.println(line);
                } catch (DukeException ex) {
                    System.out.print(line);
                    System.out.print(indent + ex.getMessage());
                    System.out.println(line);
                }
            } else if (comd[0].equals("delete")) {
                try {
                    if (comd.length <= 1) {
                        throw new DukeException(" OOPS!!! The task number cannot be empty.\n");
                    }
                    int n = Integer.parseInt(comd[1]) - 1;
                    if (n < 0 || n > tasks.size() - 1) {
                        throw new DukeException(" OOPS!!! The number inputted is not on the list.\n");
                    }
                    System.out.print(line);
                    System.out.println(indent + " Noted. I've removed this task: \n" +
                            indent + "   " + tasks.get(n) + "\n" +
                            indent + " Now you have " + (tasks.size() - 1) + " tasks in the list.");
                    System.out.println(line);
                    tasks.remove(n);
<<<<<<< HEAD
=======
                    storage.writeDuke(tasks);
>>>>>>> branch-Level-7
                } catch (DukeException ex) {
                    System.out.print(line);
                    System.out.print(indent + ex.getMessage());
                    System.out.println(line);
                }
            } else {
                try {
                    Task task = new Task();
                    if (comd[0].equals("todo")) {
                        if (comd.length <= 1) {
                            throw new DukeException(" OOPS!!! The description of a todo cannot be empty.\n");
                        } else {
                            String event = command.substring(5);
                            task = new ToDo(event);
                            tasks.add(task);
                        }
                    } else if (comd[0].equals("deadline")) {
                        if (comd.length <= 1) {
                            throw new DukeException(" OOPS!!! The description of a deadline cannot be empty.\n");
                        } else {
                            if (!command.contains("/by")) {
                                throw new DukeException(" OOPS!!! The time is missing a \"/\"\n");
                            }
<<<<<<< HEAD
                            String event = command.substring(9, command.indexOf('/') - 1);
                            String date = command.substring(command.indexOf("/by") + 4);
=======
                            String event = command.substring(9, command.lastIndexOf('/') - 1);
                            String date = command.substring(command.lastIndexOf('/') + 4);
>>>>>>> branch-Level-7
                            task = new Deadline(event, date);
                            tasks.add(task);
                        }
                    } else if (comd[0].equals("event")) {
                        if (comd.length <= 1) {
                            throw new DukeException(" OOPS!!! The description of a event cannot be empty.\n");
                        } else {
                            if (!command.contains("/at")) {
                                throw new DukeException(" OOPS!!! The time is missing a \"/\"\n");
                            }
<<<<<<< HEAD
                            String event = command.substring(6, command.indexOf('/') - 1);
                            String date = command.substring(command.indexOf("/at") + 4);
=======
                            String event = command.substring(6, command.lastIndexOf('/') - 1);
                            String date = command.substring(command.lastIndexOf('/') + 4);
>>>>>>> branch-Level-7
                            task = new Event(event, date);
                            tasks.add(task);
                        }
                    } else {
                        throw new DukeException(" OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                    }
<<<<<<< HEAD
=======
                    storage.writeDuke(tasks);
>>>>>>> branch-Level-7
                    System.out.print(line);
                    System.out.println(indent + " Got it. I've added this task: \n" +
                            indent + "   " + task + "\n" +
                            indent + " Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } catch (DukeException ex) {
                    System.out.print(line);
                    System.out.print(indent + ex.getMessage());
                    System.out.println(line);
                }
            }
            command = sc.nextLine();
            comd = command.split("\\s");
        }
        System.out.println(line + indent + " Bye. Hope to see you again soon!\n" + line);
    }
}
