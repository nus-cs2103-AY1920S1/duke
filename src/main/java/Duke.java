import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    static List<Task> list;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        list = Database.retrieveData();

        printWelcomeMsg();

        String command = sc.nextLine();

        while (!command.equals("")) {
            printHorizontalLine();
            try {
                if (command.equalsIgnoreCase("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                } else if (command.equalsIgnoreCase("list")) {
                    printTasks();
                } else if (command.length() > 4 && command.substring(0, 4).equalsIgnoreCase("done")) {
                    doneTask(command);
                } else if (command.length() > 6 && command.substring(0, 6).equalsIgnoreCase("delete")) {
                    deleteTask(command);
                } else {  // add task or wrong command
                    String[] temp = command.split(" ");
                    if (!temp[0].equalsIgnoreCase("deadline")
                            && !temp[0].equalsIgnoreCase("event")
                            && !temp[0].equalsIgnoreCase("todo")) {
                        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } else {
                        addTask(command);
                    }
                }
            } catch (DukeException e) {
                System.err.println(e);
            } finally {
                printHorizontalLine();
                if (command.equals("bye")) {
                    Database.updateData(list);
                    break;
                }
                command = sc.nextLine();
            }
        }

        sc.close();
    }

    private static void addTask(String command) throws DukeException {
        String[] words = command.split(" ");
        String type = words[0];
        Task task;

        try {
            if (type.equalsIgnoreCase("todo")) {
                task = new Todo(command.substring(5));
            } else {
                int index = command.indexOf('/');
                if (type.equalsIgnoreCase("deadline")) {
                    task = new Deadline(command.substring(9, index - 1), command.substring(index + 4));
                } else if (type.equalsIgnoreCase("event")) {
                    task = new Event(command.substring(6, index - 1), command.substring(index + 4));
                } else {
                    throw new IllegalArgumentException("No such task type.");
                }
            }
            list.add(task);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + task);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The description of a " + type + " cannot be empty.");
            /*
            if (type.equalsIgnoreCase("todo")) {
                throw new DukeException("     ☹ OOPS!!! The description of a " + type + " cannot be empty.");
            } else if (type.equalsIgnoreCase("deadline")) {
                throw new DukeException("     ☹ OOPS!!! Format is wrong. Input format: \"deadline (description) /by DD/MM/YYYY 2359\".");
            } else {
                throw new DukeException("     ☹ OOPS!!! Format is wrong. Input format: \"event (description) /at DD/MM/YYYY 2359\".");
            }
            */
        } catch (IllegalArgumentException e) {
            throw new DukeException("     ☹ OOPS!!! The task type does not exist. Three types available: Todo, Deadline, Event.");
        }
    }

    private static void doneTask(String command) throws DukeException {
        try {
            String[] done = command.split(" ");
            int number = Integer.valueOf(done[1]);
            list.get(number - 1).markAsDone();
            System.out.println("     Nice! I've marked this task as done: ");
            System.out.println("       " + list.get(number - 1));
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number does not exist.");
        }
    }

    private static void deleteTask(String command) throws DukeException {
        try {
            String[] done = command.split(" ");
            int number = Integer.valueOf(done[1]);
            Task task = list.get(number - 1);
            list.remove(number - 1);
            System.out.println("     Noted. I've removed this task: ");
            System.out.println("       " + task);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number cannot be empty.");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The task number does not exist.");
        }
    }

    private static void printTasks() throws DukeException {
        if (list.isEmpty()) {
            throw new DukeException("     ☹ OOPS!!! The list is empty.");
        }
        System.out.println("     Here are the tasks in your list:");
        int count = 0;
        for (Task task:list) {
            count++;
            System.out.println("     " + count + "." + task);
        }
    }

    private static void printWelcomeMsg() {
        printHorizontalLine();
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?\n");

        if (!list.isEmpty()) {
            printTasks();
        } else {
            System.out.println("     There are no tasks in the list right now.");
        }


        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
