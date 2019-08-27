import java.util.Scanner;
import java.util.ArrayList;
/**
 *  CS2103 Week 2 iP Deliverable
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean hi = true;
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        while(hi) {
            try{
                String input = sc.nextLine();
                String task = input.split(" ")[0];
                int value;
                switch (task) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    hi = false;
                    break;
                case "list":
                    if (tasks.size() == 0) {
                        System.out.println("List is empty!");
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < tasks.size(); i++) {
                            System.out.println((i + 1) + "." + tasks.get(i));
                        }
                    }
                    break;
                case "done":
                    value = Integer.parseInt(input.substring(input.length() - 1));
                    tasks.get(value - 1).setDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(value - 1));
                    break;
                case "delete":
                    value = Integer.parseInt(input.substring(input.length() - 1));
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(value - 1));
                    tasks.remove(value - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                default:
                    tasks.add(create(task, input));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    break;
                }
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("OOPS!!! The details of an Event/Deadline cannot be empty! :(");
            } catch (UnsupportedOperationException ex) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means! :(");
            } catch (Exception ex) {
                System.out.println("OOPS!!! Something broke. Terminating now :(");
                hi = false;
            }
        }
        sc.close();
    }

    /**
     * Processes Input String to Description & Details
     * @param input Task String (without command)
     * @return Array. Index 0 = Description. Index 1  = Details
     * @throws ArrayIndexOutOfBoundsException Command missing details
     */
    private static String[] process(String input) throws ArrayIndexOutOfBoundsException {
        String[] desc = input.split("/");
        String[] temp = desc[1].split(" ");
        desc[1] = desc[1].replace(temp[0], "");
        desc[1] = " (" + temp[0] + ": " + desc[1] + ")";
        return desc;
    }

    /**
     * Creates Task from input command and description
     * @param task User input separated by spaces
     * @param input User input unprocessed
     * @return Task
     * @throws UnsupportedOperationException Command not understood
     */
    private static Task create(String task, String input) throws UnsupportedOperationException {
        input = input.replace(task, "");
        switch (task) {
        case "todo":
            return new Todo(input, "");
        case "deadline":
            String[] dead = process(input);
            return new Deadline(dead[0], dead[1]);
        case "event":
            String[] event = process(input);
            return new Event(event[0], event[1]);
        default:
            throw new UnsupportedOperationException();
        }
    }
}
