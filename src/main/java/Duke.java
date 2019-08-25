import exceptionpackage.DukeException;
import exceptionpackage.DoneException;
import exceptionpackage.ToDoException;
import exceptionpackage.DeadlineException;
import exceptionpackage.EventException;
import exceptionpackage.DeleteException;
import exceptionpackage.InvalidInstructionException;
import taskpackage.Deadline;
import taskpackage.Event;
import taskpackage.Task;
import taskpackage.ToDo;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String horizontalLine = "    ____________________________________________________________";

        //Greetings message
        System.out.println(horizontalLine);
        System.out.println("     Hello from! I'm Duke\n" + "     What can I do for you?");
        System.out.println(horizontalLine + "\n");

        //Setup Scanner and List
        Scanner sc = new Scanner(System.in);
        List<Task> store = new ArrayList<Task>();

        //Scanner reading
        String userInput = sc.next();
        while (!userInput.equals("bye")) {

            if (userInput.equals("list")) { //list out
                System.out.println(horizontalLine);
                System.out.println("     Here are the tasks in your list:");
                int i = 1;
                for (Task task : store) {
                    System.out.println("     " + i + ". " + task);
                    i++;
                }
                System.out.println(horizontalLine + "\n");
            } else if (userInput.equals("done")) { //done
                String argument = sc.nextLine().strip();
                if (argument.isEmpty()) {
                    System.out.println(horizontalLine);
                    System.err.println("     " + new DoneException());
                    System.out.println(horizontalLine + "\n");
                } else {
                    int taskToBeDone = Integer.parseInt(argument);
                    if (store.size() < taskToBeDone) {
                        System.out.println(horizontalLine);
                        System.err.println("     " + new DoneException("OOPS!!! There is no such task in your list!"));
                        System.out.println("     " + "Current number of tasks = " + store.size());
                        System.out.println(horizontalLine + "\n");
                    } else {
                        Task task = store.get(taskToBeDone - 1);
                        if (task.isDone()) {
                            System.out.println(horizontalLine);
                            System.out.println("     Hey! I've already marked this task as done :)");
                            System.out.println("       " + task);
                            System.out.println(horizontalLine + "\n");
                        } else {
                            task.markAsDone();
                            System.out.println(horizontalLine);
                            System.out.println("     Nice! I've marked this task as done:");
                            System.out.println("       " + task);
                            System.out.println(horizontalLine + "\n");
                        }
                    }
                }
            } else if (userInput.equals("todo")) { //todo task
                String argument = sc.nextLine().strip();
                if (argument.isEmpty()) {
                    System.out.println(horizontalLine);
                    System.err.println("      " + new ToDoException());
                    System.out.println(horizontalLine + "\n");
                } else {
                    Task newToDoTask = new ToDo(argument);
                    store.add(newToDoTask);
                    System.out.println(horizontalLine);
                    System.out.println("     Got it. I've added this task:");
                    System.out.println("       " + newToDoTask);
                    if (store.size() > 1) {
                        System.out.println("     Now you have " + store.size() + " tasks in the list.");
                    } else {
                        System.out.println("     Now you have " + store.size() + " task in the list.");
                    }
                    System.out.println(horizontalLine + "\n");
                }
            } else if (userInput.equals("deadline")) { //deadline task
                String argument = sc.nextLine().strip();
                if (argument.isEmpty()) {
                    System.out.println(horizontalLine);
                    System.err.println("     " + new DeadlineException());
                    System.out.println(horizontalLine + "\n");
                } else {
                    String[] splitResult = argument.split("/by");
                    if (splitResult.length != 2) { //there might be a typo
                        System.out.println(horizontalLine);
                        System.err.println("     " + new DeadlineException("OOPS!!! Did you type anything wrongly?"));
                        System.out.println("     " + "Make sure to use '/' to indicate timing!");
                        System.out.println(horizontalLine + "\n");
                    } else {
                        try {
                            Task newDeadlineTask = new Deadline(splitResult[0].strip(), splitResult[1].strip());
                            store.add(newDeadlineTask);
                            System.out.println(horizontalLine);
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + newDeadlineTask);
                            if (store.size() > 1) {
                                System.out.println("     Now you have " + store.size() + " tasks in the list.");
                            } else {
                                System.out.println("     Now you have " + store.size() + " task in the list.");
                            }
                            System.out.println(horizontalLine + "\n");
                        } catch (ParseException e) {
                            System.out.println(horizontalLine);
                            System.out.println("     " + e);
                            System.out.println("     OOPS!!! Please key in date and time in the format of <dd/M/yyyy hh:mm>");
                            System.out.println(horizontalLine + "\n");
                        }
                    }
                }
            } else if (userInput.equals("event")) { //event task
                String argument = sc.nextLine().strip();
                if (argument.isEmpty()) {
                    System.out.println(horizontalLine);
                    System.err.println("     " + new EventException());
                    System.out.println(horizontalLine + "\n");
                } else {
                    String[] splitResult = argument.split("/at");
                    if (splitResult.length != 2) { //there might be a typo
                        System.out.println(horizontalLine);
                        System.err.println("     " + new EventException("OOPS!!! Did you type anything wrongly?"));
                        System.out.println("     " + "Make sure to use '/' to indicate timing!");
                        System.out.println(horizontalLine + "\n");
                    } else {
                        try {
                            Task newEventTask = new Event(splitResult[0].strip(), splitResult[1].strip());
                            store.add(newEventTask);
                            System.out.println(horizontalLine);
                            System.out.println("     Got it. I've added this task:");
                            System.out.println("       " + newEventTask);
                            if (store.size() > 1) {
                                System.out.println("     Now you have " + store.size() + " tasks in the list.");
                            } else {
                                System.out.println("     Now you have " + store.size() + " task in the list.");
                            }
                            System.out.println(horizontalLine + "\n");
                        } catch (ParseException e) {
                                System.out.println(horizontalLine);
                                System.out.println("     " + e);
                                System.out.println("     OOPS!!! Please key in date and time in the format of <dd/M/yyyy hh:mm>");
                                System.out.println(horizontalLine + "\n");
                        }
                    }
                }
            } else if (userInput.equals("delete")) {
                String argument = sc.nextLine().strip();
                if (argument.isEmpty()) {
                    System.out.println(horizontalLine);
                    System.err.println("     " + new DeleteException());
                    System.out.println(horizontalLine + "\n");
                } else {
                    int taskToDelete = Integer.parseInt(argument);
                    if (store.size() < taskToDelete) { //check if it's a valid delete
                        System.out.println(horizontalLine);
                        System.err.println("     " + new DeleteException("OOPS!!! There is no such task in your list!"));
                        System.out.println("     " + "Current number of tasks = " + store.size());
                        System.out.println(horizontalLine + "\n");
                    } else {
                        Task task = store.get(taskToDelete - 1);
                        store.remove(taskToDelete - 1);
                        System.out.println(horizontalLine);
                        System.out.println("     Noted I've removed this task:");
                        System.out.println("       " + task);
                        if (store.size() > 1) {
                            System.out.println("     Now you have " + store.size() + " tasks in the list.");
                        } else {
                            System.out.println("     Now you have " + store.size() + " task in the list.");
                        }
                        System.out.println(horizontalLine + "\n");
                    }
                }
            } else {
                System.out.println(horizontalLine);
                System.err.println("     " + new InvalidInstructionException());
                System.out.println(horizontalLine + "\n");
                sc.nextLine();
            }
            userInput = sc.next();
        }

        //Bye message
        System.out.println(horizontalLine);
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);

    }
}
