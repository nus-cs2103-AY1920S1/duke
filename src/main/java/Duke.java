import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        printWelcomeMsg();

        List<Task> list = new LinkedList<>();
        String command = sc.nextLine();

        while (!command.equals("")) {
            printHorizontalLine();
            try {
                if (command.equals("bye")) {
                    System.out.println("     Bye. Hope to see you again soon!");
                } else if (command.equals("list")) {
                    printTasks(list);
                } else if (command.length() > 4 && command.substring(0, 4).equals("done")) {
                    doneTask(list, command);
                } else if (command.length() > 6 && command.substring(0, 6).equals("delete")) {
                    deleteTask(list, command);
                } else {  // add task or wrong command
                    String[] temp = command.split(" ");
                    if (!temp[0].equals("deadline") && !temp[0].equals("event") && !temp[0].equals("todo")) {
                        throw new DukeException("     ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    } else {
                        addTask(list, command);
                    }
                }
            } catch (DukeException e) {
                System.err.println(e);
            } finally {
                printHorizontalLine();
                if (command.equals("bye")) {
                    break;
                }
                command = sc.nextLine();
            }
        }

        sc.close();
    }

    private static void addTask(List<Task> list, String command) throws DukeException {
        String[] words = command.split(" ");
        String type = words[0];
        Task task;

        try {
            if (type.equals("todo")) {
                task = new Todo(command.substring(5));
            } else {
                int index = command.indexOf('/');
                if (type.equals("deadline")) {
                    task = new Deadline(command.substring(9, index - 1), command.substring(index + 4));
                } else {
                    task = new Event(command.substring(6, index - 1), command.substring(index + 4));
                }
            }
            list.add(task);
            System.out.println("     Got it. I've added this task: ");
            System.out.println("       " + task);
            System.out.println("     Now you have " + list.size() + " tasks in the list.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("     ☹ OOPS!!! The description of a " + type + " cannot be empty.");
        }
    }

    private static void doneTask(List<Task> list, String command) throws DukeException {
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

    private static void deleteTask(List<Task> list, String command) throws DukeException {
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

    private static void printTasks(List<Task> list) throws DukeException {
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
        System.out.println("     Hello! I'm Duke\n" + "     What can I do for you?");
        printHorizontalLine();
    }

    private static void printHorizontalLine() {
        System.out.println("    ____________________________________________________________");
    }
}
