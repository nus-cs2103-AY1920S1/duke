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

        while(!(input = sc.nextLine()).equals("bye")) {
            String output;
            try{
                output = handleInput(input);
            } catch(DukeException e) {
                output = e.getMessage();
            }
            System.out.println(output);
        };

        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(indent(wrapWithHorizontalLines(endMessage)));
    }

    private static String handleInput(String input) throws DukeException {
        String output;
        String[] splitInput = input.split(" ");
        String command = splitInput[0];
        switch(command) {
            case "list":
                output = historyToString(history);
                break;
            case "done":
                Task taskToMarkAsDone;
                int selectedIndex;
                try {
                    selectedIndex = Integer.parseInt(splitInput[1]) - 1;
                } catch(NumberFormatException e) {
                    throw new DukeException("Argument passed to done must be a valid integer");
                }
                try {
                    taskToMarkAsDone = history.get(selectedIndex);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Selected task number does not exist.");
                }
                taskToMarkAsDone.markAsDone();
                output = "Nice! I've marked this task as done: \n"
                        + indent(taskToMarkAsDone.toString());
                break;
            case "delete":
                Task taskToDelete;
                int deleteIndex;
                try {
                    deleteIndex = Integer.parseInt(splitInput[1]) - 1;
                } catch(NumberFormatException e) {
                    throw new DukeException("Argument passed to delete must be a valid integer");
                }
                try {
                    taskToDelete = history.get(deleteIndex);
                } catch(IndexOutOfBoundsException e) {
                    throw new DukeException("Selected task number does not exist.");
                }
                history.remove(deleteIndex);
                output = wrapWithDeleteTask(taskToDelete);
                break;
            case "deadline":
                int byIndex = input.indexOf(" /by ");
                if(byIndex < 0) {
                    throw new DukeException("Command deadline requires an argument /by, followed by deadline date");
                }
                String deadlineDescription =  input.substring(9, byIndex);
                String by = input.substring(byIndex + 5);
                Deadline deadline = new Deadline(deadlineDescription, by);
                history.add(deadline);
                output = wrapWithAddTask(deadline);
                break;
            case "event":
                int atIndex = input.indexOf(" /at ");
                if(atIndex < 0) {
                    throw new DukeException("Command event requires an argument /at, followed by event date");
                }
                String eventDescription =  input.substring(6, atIndex);
                String at = input.substring(atIndex + 5);
                Event event = new Event(eventDescription, at);
                history.add(event);
                output = wrapWithAddTask(event);
                break;
            case "todo":
                String todoDescription;
                try {
                    todoDescription = input.substring(5);
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                Todo todo = new Todo(todoDescription);
                history.add(todo);
                output = wrapWithAddTask(todo);
                break;
            default:
                output = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
        return indent(wrapWithHorizontalLines(output));
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

    private static String wrapWithDeleteTask(Task task) {
        return "Noted. I've removed this task: \n" +
                indent(task.toString()) +
                String.format("\nNow you have %d tasks in the list.", history.size());
    }

    private static String indent(String str) {
        String[] indentedStrings = Arrays.stream(str.split("\n")).map(s -> indent + s).toArray(String[]::new);
        return String.join("\n", indentedStrings);
    }
}
