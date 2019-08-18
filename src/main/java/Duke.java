import java.security.spec.RSAOtherPrimeInfo;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        String line = "\t____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        String greeting = line
                + "\t Hello! I'm Duke\n"
                + "\t What can I do for you?\n"
                + line;
        System.out.println(greeting);
        while (!sc.hasNext("bye")) {
            String s = sc.nextLine();
            String arr[] = s.split(" ", 2);
            String cmd = arr[0];
            switch (cmd) {
                case "todo":
                    String todo_content = arr[1];
                    Task todo = new Todo(todo_content);
                    list.add(todo);
                    System.out.println(String.format(line
                            + "\t Got it. I've added this task:\n"
                            + String.format("\t   %s\n", todo)
                            + String.format("\t Now you have %d tasks in the list.\n", list.size())
                            + line
                    ));
                    break;
                case "deadline":
                    String deadline_content[] = arr[1].split(" /by ", 2);
                    Task deadline = new Deadline(deadline_content[0], deadline_content[1]);
                    list.add(deadline);
                    System.out.println(String.format(line
                            + "\t Got it. I've added this task:\n"
                            + String.format("\t   %s\n", deadline)
                            + String.format("\t Now you have %d tasks in the list.\n", list.size())
                            + line
                    ));
                    break;
                case "event":
                    String event_content[] = arr[1].split(" /at ", 2);
                    Task event = new Event(event_content[0], event_content[1]);
                    list.add(event);
                    System.out.println(String.format(line
                            + "\t Got it. I've added this task:\n"
                            + String.format("\t   %s\n", event)
                            + String.format("\t Now you have %d tasks in the list.\n", list.size())
                            + line
                    ));
                    break;
                case "done":
                    int index = Integer.valueOf(arr[1]);
                    Task task = list.get(index - 1);
                    task.markAsDone();
                    System.out.println(String.format(line
                            + "\t Nice! I've marked this task as done:\n"
                            + String.format("\t   %s\n", task.toString())
                            + line
                    ));
                    break;
                case "list":
                    String stringlist = line
                            + "\t Here are the tasks in your list:\n"
                            + IntStream
                            .range(0, list.size())
                            .mapToObj(i -> {
                                Task t = list.get(i);
                                return "\t " + String.format("%d.%s", i + 1, t.toString()) + "\n";
                            })
                            .reduce((x,y) -> x + y)
                            .orElse("")
                            + line;
                    System.out.println(stringlist);
                    break;
                default:
                    System.err.println("Invalid Command");

            }
        }
        System.out.println(line + "\t Bye. Hope to see you again soon!\n" + line);
    }
}
