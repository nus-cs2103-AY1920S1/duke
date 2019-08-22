import java.util.Scanner;
import java.util.LinkedList;
import java.util.Iterator;

public class Duke {
    public static void main(String[] args) {
        String dividerLine = new String("\u2501").repeat(80).concat("\n");
        String startMessage = "  Hello! I'm Duke\n"
                + "  What can I do for you?\n";
        String logo = "   ____        _        \n"
                + "  |  _ \\ _   _| | _____ \n"
                + "  | | | | | | | |/ / _ \\\n"
                + "  | |_| | |_| |   <  __/\n"
                + "  |____/ \\__,_|_|\\_\\___|\n"
                + startMessage
                + dividerLine;

        LinkedList<Task> tasks = new LinkedList<>();

        System.out.print(dividerLine + "  Hello from\n" + logo);

        //Take in input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        //Check if bye
        while (!input.equals("bye")) {
            //Split input incase it has a command and argument
            String[] inputArray = input.split(" ", 2);

            String[] cmdArgs = new String[0];
            Task newTask = new Task("");

            //If list print all tasks
            try {
                switch (inputArray[0]) {
                    case "list":
                        System.out.print(dividerLine);
    
                        //Print each task
                        Iterator<Task> tasksIterator = tasks.iterator();
                        Task currTask = new Task("");
    
                        for (int i = 1; i <= tasks.size(); i++) {
                            currTask = tasksIterator.next();
    
                            System.out.print("  "
                                    + i
                                    + ". "
                                    + currTask
                                    + "\n");
                        }
    
                        System.out.print(dividerLine);
                        break;
                    case "done":
                        if (inputArray.length < 2) {
                            throw new DukeException("OOPS!!! You need to specify the number of the task " 
                                    + "you want to mark as done!");
                        }
                        int taskNum = Integer.parseInt(inputArray[1]);
                        int taskIndex = taskNum - 1;
    
                        Task modifiedTask = tasks.get(taskIndex);
                        modifiedTask.markAsDone();
    
                        //Printing
                        System.out.print(dividerLine);
    
                        System.out.print("  Nice! I've marked this task as done:\n");
                        System.out.print("    "
                                + modifiedTask
                                + "\n");
    
                        System.out.print(dividerLine);
                        break;
                    case "todo":
                        if (inputArray.length < 2) {
                            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                        }
                        newTask = new Todo(inputArray[1]);
                        tasks.add(newTask);
                        System.out.print(dividerLine
                                + "  Got it. I've added this task:\n"
                                + "    "
                                + newTask
                                + "\n"
                                + "  Now you have "
                                + tasks.size()
                                + " task(s) in the list."
                                + "\n"
                                + dividerLine);
                        break;
                    case "event":
                        if (inputArray.length < 2) {
                            throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                        }
                        cmdArgs = inputArray[1].split(" /at ", 2);
                        newTask = new Event(cmdArgs[0], cmdArgs[1]);
                        tasks.add(newTask);
                        System.out.print(dividerLine
                                + "  Got it. I've added this task:\n"
                                + "    "
                                + newTask
                                + "\n"
                                + "  Now you have "
                                + tasks.size()
                                + " task(s) in the list."
                                + "\n"
                                + dividerLine);
                        break;
                    case "deadline":
                        if (inputArray.length < 2) {
                            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                        }
                        cmdArgs = inputArray[1].split(" /by ", 2);
                        newTask = new Deadline(cmdArgs[0], cmdArgs[1]);
                        tasks.add(newTask);
                        System.out.print(dividerLine
                                + "  Got it. I've added this task:\n"
                                + "    "
                                + newTask
                                + "\n"
                                + "  Now you have "
                                + tasks.size()
                                + " task(s) in the list."
                                + "\n"
                                + dividerLine);
                        break;
                    default:
                        throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.print(dividerLine
                        + "  ☹ "
                        + e.getMessage()
                        + "\n"
                        + dividerLine);
            } finally {
                input = sc.nextLine();
            }
        }
        // Exit if bye
        System.out.print(dividerLine
                + "  Bye. Hope to see you again soon!\n"
                + dividerLine);

    }
}
