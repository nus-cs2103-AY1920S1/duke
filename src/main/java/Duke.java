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
        boolean bool = false;
        LinkedList<Task> lst = new LinkedList<>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        LinkedList<String> hello = new LinkedList<>();
        hello.addLast("Hello! I'm Duke");
        hello.addLast("What can I do for you?");
        printStandard(hello);

        String addTask = "Got it. I've added this task:";

        while (!bool) {
            String command = sc.next();
            switch (command) {
                case "bye":
                    bool = true;
                    LinkedList<String> bye = new LinkedList<>();
                    bye.addLast("Bye. Hope to see you again soon!");
                    printStandard(bye);
                    break;
                case "list":
                    int size = lst.size();
                    LinkedList<String> list = new LinkedList<>();
                    list.addLast("Here are the tasks in your list:");
                    for (int i = 1; i <= size; i++) {
                        Task temp = lst.removeFirst();
                        lst.addLast(temp);
                        list.addLast(String.format("%d.%s", i, temp));
                    }
                    printStandard(list);
                    break;
                case "done":
                    int val = Integer.parseInt(sc.next());
                    Task temp = lst.get(val - 1);
                    temp.markAsDone();
                    LinkedList<String> done = new LinkedList<>();
                    done.addLast("Nice! I've marked this task as done:");
                    done.addLast(String.format("%s", temp));
                    printStandard(done);
                    break;
                case "todo":
                    String todoString = sc.nextLine();
                    Todo todoTask = new Todo(todoString);
                    lst.addLast(todoTask);
                    LinkedList<String> todo = new LinkedList<>();
                    todo.addLast(addTask);
                    todo.addLast(String.format("%s", todoTask));
                    todo.addLast(getTasks(lst));
                    printStandard(todo);
                    break;
                case "deadline":
                    String deadlineString = sc.nextLine();
                    String[] deadlineArr = deadlineString.split("/by");
                    Deadline deadlineTask = new Deadline(deadlineArr[0], deadlineArr[1]);
                    lst.addLast(deadlineTask);
                    LinkedList<String> deadline = new LinkedList<>();
                    deadline.addLast(addTask);
                    deadline.addLast(String.format("%s", deadlineTask));
                    deadline.addLast(getTasks(lst));
                    printStandard(deadline);
                    break;
                case "event":
                    String eventString = sc.nextLine();
                    String[] eventArr =eventString.split("/at");
                    Event eventTask = new Event(eventArr[0],  eventArr[1]);
                    lst.addLast(eventTask);
                    LinkedList<String> event = new LinkedList<>();
                    event.addLast(addTask);
                    event.addLast(String.format("%s", eventTask));
                    event.addLast(getTasks(lst));
                    printStandard(event);
                    break;
                default:
                    System.out.println(command);
                    break;
            }
        }
    }
}
