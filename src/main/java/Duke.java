import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String welcomeMessage = "Hello! I'm Duke\n" + "What can I do for you?";
        System.out.println(welcomeMessage);
        Scanner reader = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            String currentLine = reader.nextLine();
            if (currentLine.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (currentLine.equals("list")) {
                int count = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task: list) {
                    System.out.println("" + count + "." + task);
                    count++;
                }
            } else {
                String[] strArr = currentLine.split(" ");
                String command = strArr[0];
                Task t;
                int index;
                try {
                    switch (command) {
                        case "todo":
                            String todo = toDoString(strArr);
                            t = new ToDo(todo);
                            list.add(t);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("  " + t);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            break;
                        case "deadline":
                            String[] deadline = deadlineEventString(strArr, true);
                            t = new Deadlines(deadline[0], deadline[1]);
                            list.add(t);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("  " + t);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            break;
                        case "event":
                            String[] event = deadlineEventString(strArr, false);
                            t = new Deadlines(event[0], event[1]);
                            list.add(t);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println("  " + t);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            break;
                        case "done":
                            index = Integer.parseInt(strArr[1]);
                            list.get(index - 1).setDone();
                            break;
                        case "delete":
                            index = Integer.parseInt(strArr[1]);
                            Task deleted = list.remove(index - 1);
                            System.out.println("Noted. I've removed this task:");
                            System.out.println("  " + deleted);
                            System.out.println("Now you have " + list.size() + " tasks in the list.");
                            break;
                        default:
                            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.println(e);
                }
            }
        }
    }

    private static String[] deadlineEventString(String[] arr, boolean deadline) throws DukeException {
        String[] res = new String[2];
        StringBuilder sb = new StringBuilder();
        int divide = 0;
        for (int i = 1; i < arr.length; i++) {
            if (deadline && arr[i].equals("/by") || !deadline && arr[i].equals("/at")) {
                divide = i;
                break;
            }
            sb.append(arr[i]);
            sb.append(" ");
        }
        res[0] = sb.toString().trim();
        if (res[0].isEmpty())
            throw new DukeException("☹ OOPS!!! The description of a " + (deadline ? "deadline" : "event") + " cannot be empty.");
        sb = new StringBuilder();
        for (int i = divide + 1; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        res[1] = sb.toString().trim();
        return res;
    }

    private static String toDoString(String[] arr) throws DukeException {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < arr.length; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        String res = sb.toString().trim();
        if (res.isEmpty())
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        return res;
    }
}