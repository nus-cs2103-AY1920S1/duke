import java.util.*;
/**
 *  Week 2 iP Deliverable
 *  @author Ahmed Bahajjaj
 */
public class Duke {
    public static void main(String[] args) {
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
                String[] cmd = input.split(" ");
                switch (cmd[0]) {
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
                        tasks.add(create(cmd, input));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("OOPS!!! The description of a task cannot be empty! :(");
            } catch (UnsupportedOperationException e) {
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means! :(");
            } catch (Exception e) {
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
     * @param cmd User input separated by spaces
     * @param input User input unprocessed
     * @return Task
     * @throws UnsupportedOperationException Command not understood
     */
    private static Task create(String[] cmd, String input) throws UnsupportedOperationException {
        input = input.replace(cmd[0], "");
        switch (cmd[0]) {
            case "todo":
                return new Todo(input, "");
            case "deadline":
                cmd = process(input);
                return new Deadline(cmd[0], cmd[1]);
            case "event":
                cmd = process(input);
                return new Event(cmd[0], cmd[1]);
            default:
                throw new UnsupportedOperationException();
        }
    }
}
