import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class Duke {

    private static void printStandard(LinkedList<String> toPrint) {
        String line = "    ____________________________________________________________";
        System.out.println(line);
        for (String string : toPrint) {
            System.out.println("     " + string);
        }
        System.out.println(line);
    }

    private static String getTasks(LinkedList<Task> list) {
        return String.format("Now you have %d tasks in the list.", list.size());
    }

    private static void generateTaskMessage(String message, String task, LinkedList<Task> list) {
        LinkedList<String> taskToAdd = new LinkedList<>();
        taskToAdd.addLast(message);
        taskToAdd.addLast(task);
        taskToAdd.addLast(getTasks(list));
        printStandard(taskToAdd);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String addString = "Got it. I've added this task:";
        String delString = "Noted. I've removed this task:";

        System.out.println("Hello from\n" + logo);

        LinkedList<Task> lst = new LinkedList<>();
        LinkedList<String> def = new LinkedList<>();
        LinkedList<String> error = new LinkedList<>();
        LinkedList<String> hello = new LinkedList<>();
        hello.addLast("Hello! I'm Duke");
        hello.addLast("What can I do for you?");
        printStandard(hello);

        def.addLast("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] arr = input.split(" ");
            String command = arr[0];
            String next = String.join(" ", arr).replace(command, "");
            switch (command) {
                case "bye":
                    LinkedList<String> bye = new LinkedList<>();
                    bye.addLast("Bye. Hope to see you again soon!");
                    printStandard(bye);
                    System.exit(0);
                    break;
                case "list":
                    LinkedList<String> list = new LinkedList<>();
                    list.addLast("Here are the tasks in your list:");
                    int i = 1;
                    for (Task task : lst) {
                        list.addLast(String.format("%d.%s", i, task));
                        ++i;
                    }
                    printStandard(list);
                    break;
                case "done":
                    try {
                        int val = Integer.parseInt(arr[1]);
                        if (arr.length > 2) throw new InputMismatchException();
                        Task temp = lst.get(val - 1);
                        temp.markAsDone();
                        LinkedList<String> done = new LinkedList<>();
                        done.addLast("Nice! I've marked this task as done:");
                        done.addLast(String.format("%s", temp));
                        printStandard(done);
                    } catch (IndexOutOfBoundsException e) {
                        error.addLast("☹ OOPS!!! The index to be completed is not within the task list size.");
                        printStandard(error);
                        error.removeFirst();
                    } catch (NumberFormatException e) {
                        error.addLast("☹ OOPS!!! Please enter a valid number.");
                        printStandard(error);
                        error.removeFirst();
                    } catch (InputMismatchException e) {
                        error.addLast("☹ OOPS!!! Please enter a single number.");
                        printStandard(error);
                        error.removeFirst();
                    }
                    break;
                case "todo":
                    try {
                        Todo todoTask = Todo.of(next);
                        lst.addLast(todoTask);
                        generateTaskMessage(addString, String.format("%s", todoTask), lst);
                    } catch (DukeException e) {
                        error.addLast(e.getMessage());
                        printStandard(error);
                        error.removeFirst();
                    }
                    break;
                case "deadline":
                    try {
                        String[] deadlineArr = next.split("/by");
                        Deadline deadlineTask = Deadline.of(deadlineArr[0], deadlineArr[1]);
                        lst.addLast(deadlineTask);
                        generateTaskMessage(addString, String.format("%s", deadlineTask), lst);
                    } catch (DukeException e) {
                        error.addLast(e.getMessage());
                        printStandard(error);
                        error.removeFirst();
                    }
                    break;
                case "event":
                    try {
                        String[] eventArr = next.split("/at");
                        Event eventTask = Event.of(eventArr[0], eventArr[1]);
                        lst.addLast(eventTask);
                        generateTaskMessage(addString, String.format("%s", eventTask), lst);
                    } catch (DukeException e) {
                        error.addLast(e.getMessage());
                        printStandard(error);
                        error.removeFirst();
                    }
                    break;
                case "delete":
                    try {
                        int del = Integer.parseInt(arr[1]);
                        if (arr.length > 2) throw new InputMismatchException();
                        Task removed = lst.remove(del - 1);
                        generateTaskMessage(delString, String.format("%s", removed), lst);
                    } catch (IndexOutOfBoundsException e) {
                        error.addLast("☹ OOPS!!! The index to delete is not within the task list size.");
                        printStandard(error);
                        error.removeFirst();
                    } catch (NumberFormatException e) {
                        error.addLast("☹ OOPS!!! Please enter a valid number.");
                        printStandard(error);
                        error.removeFirst();
                    } catch (InputMismatchException e) {
                        error.addLast("☹ OOPS!!! Please enter a single number.");
                        printStandard(error);
                        error.removeFirst();
                    }
                    break;
                default:
                    printStandard(def);
                    break;
            }
        }
    }
}
