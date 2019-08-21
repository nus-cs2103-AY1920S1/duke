import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */
    private ArrayList<Task> store = new ArrayList<>();

    public void greet() {
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
    }

    public void run() throws DukeIllegalDescriptionException, DukeIllegalInputException{
        boolean flag = true;
        Scanner sc = new Scanner(System.in);
        while(sc.hasNextLine()) {
            String cmd = sc.nextLine(); //Read the command
            String head[] = cmd.split(" ", 2); //Break the command into array
            switch(head[0]) { //Read the first key word
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    flag = false;
                    break;
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    for(int i = 0; i < store.size(); ++i) {
                        System.out.println(i + 1 + "." + store.get(i));
                    }
                    break;
                case "done":
                    int num = Integer.parseInt(head[1]);
                    Task newTask = store.get(num - 1);
                    newTask.markAsDone();
                    store.set(num - 1, newTask);
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(store.get(num - 1));
                    break;
                case "todo":
                    try {
                        Task todo = new Todo(head[1]);
                        store.add(todo);
                        printAddTask();
                        System.out.println(todo);
                        printCountTasks();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeIllegalDescriptionException(head[0]);
                    }
                    break;
                case "deadline":
                    try {
                        String[] dl = head[1].split(" /by ");
                        Task deadline = new Deadline(dl[0], dl[1]);
                        store.add(deadline);
                        printAddTask();
                        System.out.println(deadline);
                        printCountTasks();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeIllegalDescriptionException(head[0]);
                    }
                    break;
                case "event":
                    try {
                        String[] ev = head[1].split(" /at ");
                        Task event = new Event(ev[0], ev[1]);
                        store.add(event);
                        printAddTask();
                        System.out.println(event);
                        printCountTasks();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        throw new DukeIllegalDescriptionException(head[0]);
                    }
                    break;
                default:
                    throw new DukeIllegalInputException();
            }
            if (!flag) {
                break;
            }
        }
    }

    public void printAddTask() {
        System.out.println("Got it. I've added this task:");
    }

    public void printCountTasks() {
        System.out.println("Now you have " + store.size() + " tasks in the list.");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        try {
            duke.run();
        } catch (DukeIllegalInputException e) {
            System.out.println(e.getMessage());
        } catch (DukeIllegalDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }
}
