import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    static ArrayList<Task> memory = new ArrayList<>();
    static int index;
    static DateTime DT = new DateTime();

    public static void main(String[] args) {
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"; */
        System.out.println("Hello! I'm Duke");
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
            try {
                if (input.equals("list")) {
                    printList();
                } else if (input.equals("done")) {
                    int taskNum = sc.nextInt();
                    doneTask(taskNum);
                } else if (input.equals("todo")) {
                    String TodoTask = sc.nextLine();
                    if (TodoTask.equals("")) {
                        throw new DukeException("Oops! The description of a todo cannot be empty.");
                    } else {
                        toDoTask(TodoTask);
                    }
                } else if (input.equals("event")) {
                    String EventTask = sc.nextLine();
                    if (EventTask.equals("")) {
                        throw new DukeException("Oops! The description of an event cannot be empty.");
                    } else {
                        eventTask(EventTask);
                    }
                } else if (input.equals("deadline")) {
                    String DeadlineTask = sc.nextLine();
                    if (DeadlineTask.equals("")) {
                        throw new DukeException("Oops! The description of a deadline cannot be empty.");
                    } else {
                        deadlineTask(DeadlineTask);
                        System.out.println("deadline created");
                    }
                } else if (input.equals("delete")) {
                    int deleteNum = sc.nextInt();
                    deleteTask(deleteNum);
                } else {
                    throw new DukeException("Oops! I'm sorry, but I don't know what that means :(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                input = sc.next();
            }
        }
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        }
    }

    /*
    This method marks the targeted task as done and provides confirmation to the user through output.
     */
    public static void doneTask(int taskNum) throws DukeException {
        index = memory.size();
        if (taskNum > index) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task target = memory.get(taskNum - 1);
            target.markAsDone();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(target);
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
    public static void eventTask(String b) throws DukeException {
        String[] details = b.split("/at");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the event timing and resubmit that command.");
        } else {
            String[] time = details[1].trim().split(" ");
            if (time.length < 2) {
                throw new DukeException("Oops! Please write the event timing in: ddmmyy time(military)");
            } else {
                try {
                    String formattedTime = DT.getDate(time[0]) + DT.getTime(time[1]);
                    Event newEvent = new Event(details[0], formattedTime);
                    memory.add(newEvent);
                    printTask(newEvent);
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the event timing in: ddmmyy time(military)");
                }
            }
        }
    }

    /*
    This method creates a deadlineTask and adds it into memory.
    If command does not include a deadline, then user is prompted to enter the command again.
     */
    public static void deadlineTask(String c) throws DukeException {
        String[] details = c.split("/by");
        if (details.length < 2) {
            throw new DukeException("Oops! Please include the deadline and resubmit that command.");
        } else {
            String[] time = details[1].trim().split(" ");
            if (time.length < 2) {
                throw new DukeException("Oops! Please write the deadline in: ddmmyy time(military)");
            } else {
                try {
                    String formattedTime = DT.getDate(time[0]) + DT.getTime(time[1]);
                    Deadline newDeadline = new Deadline(details[0], formattedTime);
                    memory.add(newDeadline);
                    printTask(newDeadline);
                } catch (DateException e) {
                    throw new DukeException("Oops! " + e.getMessage() + " Please write the event timing in: ddmmyy time(military)");
                }
            }
        }
    }

    /*
    This method removes the target task from the list and prompts the user the number of remaining tasks saved.
    @param int deleteNum the index of the task to be deleted
     */
    public static void deleteTask(int deleteNum) throws DukeException {
        index = memory.size();
        if (deleteNum > index) {
            throw new DukeException("Oops! This task number does not exist.");
        } else {
            Task removed = memory.remove(deleteNum - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(removed);
            index = memory.size();
            System.out.println("Now you have " + index + " tasks in the list.");
        }
    }

    public static void printTask(Task task) {
        index = memory.size();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + index + " tasks in the list.");
    }
}

