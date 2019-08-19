import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> memory = new ArrayList<>();
    static int index;

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        activeChat();
    }

    /*
    This method queries for user input and returns the desired result.
     */
    public static void activeChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                printList();
            } else if (input.equals("done")) {
                int taskNum = sc.nextInt();
                doneTask(taskNum);
            } else if (input.equals("todo")) {
                String TodoTask = sc.nextLine();
                try {
                    if (TodoTask.equals("")) {
                        throw new DukeException("Oops! The description of a todo cannot be empty.");
                    } else {
                        toDoTask(TodoTask);
                    }
                } catch (Exception e) {
                    System.out.println("Oops! The description of a todo cannot be empty.");
                }
            } else if (input.equals("event")) {
                String EventTask = sc.nextLine();
                try {
                    if (EventTask.equals("")) {
                        throw new DukeException("Oops! The description of an event cannot be empty.");
                    } else {
                        eventTask(EventTask);
                    }
                } catch (Exception e) {
                    System.out.println("Oops! The description of an event cannot be empty.");
                }
            } else if (input.equals("deadline")) {
                String DeadlineTask = sc.nextLine();
                try {
                    if (DeadlineTask.equals("")) {
                        throw new DukeException("Oops! The description of a deadline cannot be empty.");
                    } else {
                        deadlineTask(DeadlineTask);
                    }
                } catch (Exception e) {
                    System.out.println("Oops! The description of a deadline cannot be empty.");
                }
            } else if (input.equals("delete")) {
                int deleteNum = sc.nextInt();
                deleteTask(deleteNum);
            } else {
                System.out.println("Oops! I'm sorry, but I don't know what that means :(");
            }
            input = sc.next();
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    /*
    This method marks the targeted task as done and provides confirmation to the user through output.
     */
    public static void doneTask(int taskNum) {
        index = memory.size();
        try {
            if (taskNum > index) {
                throw new DukeException("Oops! This task number does not exist.");
            } else {
                Task target = memory.get(taskNum - 1);
                target.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(target);
            }
        } catch (Exception e) {
            System.out.println("Oops! This task number does not exist.");
        }
    }

    /*
    This method iterates through the data collected and prints them out in the desired format.
     */
    public static void printList() {
        System.out.println("Here are the tasks in your list:");
        int id = 1;
        for (Task current : memory) {
            System.out.println(id + "." + current.toString());
            id++;
        }
    }

    /*
    This method creates a ToDo object and adds it into memory.
     */
    public static void toDoTask(String a) {
        ToDo newTodo = new ToDo(a);
        memory.add(newTodo);
        printTask(newTodo);
    }

    /*
    This method creates an Event object and adds it into memory.
    If command does not include a timing, then user is prompted to enter the command again.
     */
    public static void eventTask(String b) {
        String[] details = b.split("/at");
        try {
            if (details.length < 2) {
                throw new DukeException("Oops! Please include the event timing and resubmit that command.");
            } else {
                Event newEvent = new Event(details[0], details[1]);
                memory.add(newEvent);
                printTask(newEvent);
            }
        } catch (Exception e) {
            System.out.println("Oops! Please include the event timing and resubmit that command.");
        }
    }

    /*
    This method creates a deadlineTask and adds it into memory.
     */
    public static void deadlineTask(String c) {
        String[] details = c.split("/by");
        try {
            if (details.length < 2) {
                throw new DukeException("Oops! Please include the deadline and resubmit that command.");
            } else {
                Deadline newDeadline = new Deadline(details[0], details[1]);
                memory.add(newDeadline);
                printTask(newDeadline);
            }
        } catch (Exception e) {
            System.out.println("Oops! Please include the deadline and resubmit that command.");
        }
    }

    public static void deleteTask(int deleteNum) {
        index = memory.size();
        try {
            if (deleteNum > index) {
                throw new DukeException("Oops! This task number does not exist.");
            } else {
                Task removed = memory.remove(deleteNum - 1);
                System.out.println("Noted. I've removed this task:");
                System.out.println(removed);
                index = memory.size();
                System.out.println("Now you have " + index + " tasks in the list.");
            }
        } catch (Exception e) {
            System.out.println("Oops! This task number does not exist.");
        }
    }

    public static void printTask(Task task) {
        index = memory.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + index + " tasks in the list.");
    }
}

