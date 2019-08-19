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

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        LinkedList<Task> lst = new LinkedList<>();
        LinkedList<String> def = new LinkedList<>();
        LinkedList<String> hello = new LinkedList<>();
        hello.addLast("Hello! I'm Duke");
        hello.addLast("What can I do for you?");
        printStandard(hello);

        String addTask = "Got it. I've added this task:";
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
                    int val = Integer.parseInt(arr[1]);
                    Task temp = lst.get(val - 1);
                    temp.markAsDone();
                    LinkedList<String> done = new LinkedList<>();
                    done.addLast("Nice! I've marked this task as done:");
                    done.addLast(String.format("%s", temp));
                    printStandard(done);
                    break;
                case "todo":
                    Todo todoTask = new Todo(next);
                    lst.addLast(todoTask);
                    LinkedList<String> todo = new LinkedList<>();
                    todo.addLast(addTask);
                    todo.addLast(String.format("%s", todoTask));
                    todo.addLast(getTasks(lst));
                    printStandard(todo);
                    break;
                case "deadline":
                    String[] deadlineArr = next.split("/by");
                    Deadline deadlineTask = new Deadline(deadlineArr[0], deadlineArr[1]);
                    lst.addLast(deadlineTask);
                    LinkedList<String> deadline = new LinkedList<>();
                    deadline.addLast(addTask);
                    deadline.addLast(String.format("%s", deadlineTask));
                    deadline.addLast(getTasks(lst));
                    printStandard(deadline);
                    break;
                case "event":
                    String[] eventArr = next.split("/at");
                    Event eventTask = new Event(eventArr[0], eventArr[1]);
                    lst.addLast(eventTask);
                    LinkedList<String> event = new LinkedList<>();
                    event.addLast(addTask);
                    event.addLast(String.format("%s", eventTask));
                    event.addLast(getTasks(lst));
                    printStandard(event);
                    break;
                default:
                    printStandard(def);
                    break;
            }
        }
    }
}
