import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static boolean isBye = false;
    private static ArrayList<Task> tasks = new ArrayList<Task>();

    /**
     * prints details for all available task when the list command is invoked.
     */
    public static void printList(){
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++){
            Task task = tasks.get(i);
            int count = i + 1;
            System.out.println(count + "." + task.toString());
        }
    }

    /**
     * marks a task as done and prints message that a task is marked as done.
     *
     * @param taskIndex position of a task in the tasks Arraylist
     *
     */

    public static void done(int taskIndex){
        try{
            Task t = tasks.get(taskIndex);
            t.setCompleted();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("   [✓] " + t.getTask());
        } catch (IndexOutOfBoundsException ex){
            if (tasks.size() == 0){
                System.out.println("No tasks to be done.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }
        }
    }

    /**
     * deletes a task from tasks Arraylist and prints message that a task is deleted.
     *
     * @param taskIndex position of a task in the tasks Arraylist
     *
     */

    public static void delete(int taskIndex){
        try{
            Task deleted_task = tasks.remove(taskIndex);
            System.out.println("Noted. I've removed this task:");
            System.out.println(" " + deleted_task.toString());

            if (tasks.size() <= 1){
                System.out.println("Now you have " + tasks.size() + " task in the list.");
            } else {
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            }
        } catch (IndexOutOfBoundsException ex){
            if (tasks.size() == 0) {
                System.out.println("Nothing to delete.");
            } else {
                taskIndex += 1;
                System.out.println("Task " + taskIndex + " does not exist.");
            }
        }
    }

    /**
     * creates a deadline object and prints a message related to its creation.
     *
     * @param input information in the form of: return book /by 2/12/2019 1800 (an example)
     *
     */

    public static void deadline(String input){
        String[] words = input.split(" /by ");
        Deadline dl = new Deadline(words[0], words[1]);
        tasks.add(dl);
        dl.printAddedDeadline(tasks.size());
    }

    /**
     * creates an event object and prints a message related to its creation.
     *
     * @param input information in the form of: proj-meeting /at 13/10/2019 6-8pm (an example)
     *
     */
    public static void event(String input){
        String[] words = input.split(" /at ");
        Events event = new Events(words[0], words[1]);
        tasks.add(event);
        event.printAddedEvent(tasks.size());
    }

    /**
     * creates a todo object and prints a message related to its creation.
     *
     * @param input information in the form of: proj-meeting (an example)
     *
     */

    public static void todo(String input){
        Todo td = new Todo(input);
        tasks.add(td);
        td.printAddedTodo(tasks.size());
    }

    /**
     * responds a command
     *
     * @param input a command for Duke
     *
     */
    public static void checkCommand(String input) throws NoValidCommandException{
        String[] words = input.split(" ", 2);
        String command = words[0];

            switch (command) {
            case "bye":
                isBye = true;
                System.out.println("Bye. Hope to " +
                    "see you again soon!");
                break;

            case "list":
                printList();
                break;

            case "done":
                int doneIndex = Integer.parseInt(words[1]) - 1;
                done(doneIndex);
                break;

            case "delete":
                int delIndex = Integer.parseInt(words[1]) - 1;
                delete(delIndex);
                break;

            case "deadline":
                deadline(words[1]);
                break;

            case "event":
                try{
                event(words[1]);
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("Either event description or event specific timing is missing.");
                }
                break;

            case "todo":
                try {
                    todo(words[1]);
                } catch (IndexOutOfBoundsException ex){
                    System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;

            default:
                throw new NoValidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

    }

    /**
     * runs the commandline interface for an user to interact with Duke.
     *
     */

    public static void runInterface(){
        Scanner scan = new Scanner(System.in);
        while(!isBye){
            System.out.print("input command here: ");
            String input = scan.nextLine();
            try{
                checkCommand(input);
            } catch (NoValidCommandException ex){
                ex.printErrorMessage();
            }
        }
    }

    public static void main(String[] args)  {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        runInterface();

    }

}
