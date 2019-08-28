import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    static String format(String command) {
        return "    ____________________________________________________________\n"
                + indent(command)
                + "    ____________________________________________________________\n";
    }

    static String indent(String command) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(command);
        while(scanner.hasNext()) {
            String temp = scanner.nextLine();
            stringBuffer.append("     " + temp + "\n");
        }
        scanner.close();
        return stringBuffer.toString();
    }

    static void response(String command) {
        System.out.println(format(command));
    }

    static Todo createTodo(String input) throws DukeException {
        String description = input.length() == 0
                ? input
                : input.substring(1);
        if(description.equals("")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return new Todo(description);
    }

    static Deadline createDeadline(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        String temp = scanner.next();
        while(!temp.equals("/by")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }
        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);
        String date = scanner.nextLine();
        scanner.close();
        return new Deadline(description, date);
    }

    static Event createEvent(String input) {
        StringBuffer stringBuffer = new StringBuffer();
        Scanner scanner = new Scanner(input);
        String temp = scanner.next();
        while(!temp.equals("/at")) {
            stringBuffer.append(" " + temp);
            temp = scanner.next();
        }
        String description = stringBuffer.length() == 0
                ? stringBuffer.toString()
                : stringBuffer.toString().substring(1);
        String date = scanner.nextLine();
        scanner.close();
        return new Event(description, date);
    }

    static void addToList(Task task, ArrayList<Task> list) {
        list.add(task);
        response("Got it. I've added this task:\n  "
                + task.toString() + "\n"
                + "Now you have " + list.size() +" tasks in the list.");
    }

    public static void run() {
        response("Hello! I'm Duke\n"
                + "What can I do for you?");

        ArrayList<Task> list = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while(isRunning) {
            String cmd = scanner.next();
            switch (cmd) {
            case "bye":
                response("Bye. Hope to see you again soon!");
                isRunning = false;
                break;
            case "list":
                StringBuffer listBuffer = new StringBuffer();
                listBuffer.append("Here are the tasks in your list:\n");

                int len = list.size();
                for(int i = 0; i < len; i++) {
                    listBuffer.append((i + 1) + "."
                            + list.get(i).toString()
                            + "\n");
                }
                response(listBuffer.toString());
                break;
            case "done":
                int itemId = scanner.nextInt() - 1;
                list.get(itemId).markAsDone();
                response("Nice! I've marked this task as done: \n  "
                        + list.get(itemId).toString());
                break;
            case "delete":
                itemId = scanner.nextInt() - 1;
                Task item = list.remove(itemId);
                response("Noted. I've removed this task:  \n  "
                        + item.toString() + "\n"
                        + "Now you have " + list.size() +" tasks in the list.");
                break;
            case "todo":
                Task t = null;
                try {
                    t = createTodo(scanner.nextLine());
                    addToList(t, list);
                } catch(DukeException e) {
                    response("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;
            case "deadline":
                t = createDeadline(scanner.nextLine());
                addToList(t, list);
                break;
            case "event":
                t = createEvent(scanner.nextLine());
                addToList(t, list);
                break;
            default:
                scanner.nextLine();
                response("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
            }
        }
        scanner.close();
    }
}