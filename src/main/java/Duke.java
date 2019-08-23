import java.util.*;
/**
 *  Week 2 iP Deliverable
 *
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Scanner sc = new Scanner(System.in);
        boolean hi = true;
        String input = "";
        int value = 0;
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(hi) {
            try{
                input = sc.nextLine();
                String arr[] = input.split(" ");
                if (arr[0].equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    hi = false;
                } else if (arr[0].equals("list")) {
                    if (tasks.size() == 0) {
                        System.out.println("List is empty!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i+1) + "." + tasks.get(i));
                        }
                    }
                } else if (arr[0].equals("done")) {
                    value = Integer.parseInt(input.substring(input.length() - 1));
                    tasks.get(value - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(value - 1));
                } else if (arr[0].equals("delete")) {
                    value = Integer.parseInt(input.substring(input.length() - 1));
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(value - 1));
                    tasks.remove(value - 1);
                    System.out.println("Now you have " + tasks.size()  + " tasks in the list.");
                } else {
                    input = input.replace(arr[0], "");
                    switch (arr[0]) {
                        case "todo":
                            tasks.add(new Todo(input, ""));
                            break;
                        case "deadline":
                            arr = process(input);
                            tasks.add(new Deadline(arr[0], arr[1]));
                            break;
                        case "event":
                            arr = process(input);
                            tasks.add(new Event(arr[0], arr[1]));
                            break;
                        default:
                            throw new UnsupportedOperationException();
                    }
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size()  + " tasks in the list.");
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a task cannot be empty! :(");
                continue;
            } catch (UnsupportedOperationException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means! :(");
                continue;
            }
        }
        sc.close();
    }

    private static String[] process(String input) throws ArrayIndexOutOfBoundsException {
        String desc[] = input.split("/");
        String temp[] = desc[1].split(" ");
        desc[1] = desc[1].replace(temp[0], "");
        desc[1] = " (" + temp[0] + ": " + desc[1] + ")";
        return desc;
    }
}
