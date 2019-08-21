import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        ArrayList<Task> taskList = new ArrayList<Task>();

        while (!command.equals("bye")) {
            //List tasks
            if (command.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                int numCommands = 0;
                for (Task tasks : taskList) {
                    numCommands += 1;
                    System.out.println(numCommands + "." + tasks);
                }
                //Mark tasks as done
            } else if (command.length() >= 4 && command.substring(0, 4).equals("done")) {
                try {
                    try {
                        //Error if user inputs spaces
                        if (command.substring(5).split(" ")[0].equals("")) {
                            throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        }
                        int taskNumber = Integer.parseInt(command.substring(5).split(" ")[0]);
                        //Check if task number is valid
                        if (taskNumber > 0 && taskNumber <= taskList.size()) {
                            taskList.get(taskNumber - 1).markAsDone();
                            System.out.println("Nice! I've marked this task as done: ");
                            System.out.println(taskList.get(taskNumber - 1));
                        } else {
                            throw new DukeException("☹ OOPS!!! Task number is invalid.");
                        }
                        //If user input for task number is empty
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        //If non-numeric input given for task number
                    } catch (NumberFormatException err) {
                        throw new DukeException("☹ OOPS!!! Task number is invalid.");
                    }
                    //Catch error so that program does not terminate
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For Todo tasks
            } else if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
                try {
                    try {
                        //Check if description is empty (does not check when user input
                        //multiple spaces as the description.
                        if (!command.substring(5).equals((""))) {
                            Todo newTodo = new Todo(command.substring(5));
                            taskList.add(newTodo);
                            System.out.println("Got it. I've added this task: ");
                            System.out.println(newTodo);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        } else {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For deadline tasks
            } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
                try {
                    try {
                        //Check if description is empty (does not check when user input
                        //multiple spaces as the description.
                        String[] commandLine = command.substring(9).split(" /by ");
                        Deadline newDeadline = new Deadline(commandLine[0], commandLine[1]);
                        taskList.add(newDeadline);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newDeadline);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
            } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
                try {
                    try {
                        String[] commandLine = command.substring(6).split(" /at ");
                        Event newEvent = new Event(commandLine[0], commandLine[1]);
                        taskList.add(newEvent);
                        System.out.println("Got it. I've added this task: ");
                        System.out.println(newEvent);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For delete
            } else if (command.length() >= 6 && command.substring(0, 6).equals("delete")) {
                //Similar method and check to "done"
                try {
                    try {
                        //Error if user inputs spaces
                        if (command.substring(7).split(" ")[0].equals("")) {
                            throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        }
                        int taskNumber = Integer.parseInt(command.substring(7).split(" ")[0]);
                        //Check if task number is valid
                        if (taskNumber > 0 && taskNumber <= taskList.size()) {
                            System.out.println("Noted. I've removed this task: ");
                            System.out.println(taskList.get(taskNumber - 1));
                            taskList.remove(taskNumber - 1);
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        } else {
                            throw new DukeException("☹ OOPS!!! Task number is invalid.");
                        }
                        //If user input for task number is empty
                    } catch (IndexOutOfBoundsException err) {
                        throw new DukeException("☹ OOPS!!! Task number cannot be empty.");
                        //If non-numeric input given for task number
                    } catch (NumberFormatException err) {
                        throw new DukeException("☹ OOPS!!! Task number is invalid.");
                    }
                    //Catch error so that program does not terminate
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
                //For other commands
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException err) {
                    System.out.println(err.getMessage());
                }
            }

            command = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
