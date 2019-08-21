import java.util.*;

public class Duke {
    public static final String indent = "    ";
    private static List<Task> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String intro = "Hello! I'm Duke\n" +
                "What can I do for you?";

        System.out.println(indent(wrapWithHorizontalLines(intro)));
        String input;
        String output;
        while(!(input = sc.nextLine()).equals("bye")) {
            String[] splitInput = input.split(" ");
            String command = splitInput[0];
            switch(command) {
                case "list":
                    output = historyToString(history);
                    break;
                case "done":
                    int selectedIndex = Integer.parseInt(splitInput[1]) - 1;
                    Task task = history.get(selectedIndex);
                    task.markAsDone();
                    output = "Nice! I've marked this task as done: \n"
                            + indent(task.toString());
                    break;
                case "deadline":
                    int byIndex = input.indexOf(" /by ");
                    String deadlineDescription =  input.substring(9, byIndex);
                    String by = input.substring(byIndex + 5);
                    Deadline deadline = new Deadline(deadlineDescription, by);
                    history.add(deadline);
                    output = wrapWithAddTask(deadline);
                    break;
                case "event":
                    int atIndex = input.indexOf(" /at ");
                    String eventDescription =  input.substring(6, atIndex);
                    String at = input.substring(atIndex + 5);
                    Event event = new Event(eventDescription, at);
                    history.add(event);
                    output = wrapWithAddTask(event);
                    break;
                case "todo":
                    String todoDescription = input.substring(5);
                    Todo todo = new Todo(todoDescription);
                    history.add(todo);
                    output = wrapWithAddTask(todo);
                    break;
                default:
                    history.add(new Task(input));
                    output = "added: " + input;
            }
            System.out.println(indent(wrapWithHorizontalLines(output)));
        };

        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(indent(wrapWithHorizontalLines(endMessage)));
    }

    private static String historyToString(List<Task> history) {
        StringJoiner sj = new StringJoiner("\n");
        for(int i = 0; i < history.size(); i++) {
            sj.add((i + 1) + ". " + history.get(i));
        }
        return sj.toString();
    }

    private static String wrapWithHorizontalLines(String str) {
        return "____________________________________________________________\n"
                + str
                + "\n" + "____________________________________________________________";
    }

    private static String wrapWithAddTask(Task task) {
        return "Got it. I've added this task: \n"
                + indent(task.toString())
                + String.format("\nNow you have %d tasks in the list.", history.size());
    }

    private static String indent(String str) {
        String[] indentedStrings = Arrays.stream(str.split("\n")).map(s -> indent + s).toArray(String[]::new);
        return String.join("\n", indentedStrings);
    }
}
