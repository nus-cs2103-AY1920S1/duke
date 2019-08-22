import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {


        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println("Hello I'm\n" + logo + "\nWhat can I do for you?");

        String command = promptEntry();



    while (!command.equals("bye")) {

        try {
        switch (command) {

            case "list":
                printList(taskList);
                break;

            case "todo":
                String task = sc.nextLine().trim();
                if(!task.isEmpty()) {
                    ToDo newTodo = new ToDo(task);
                    addTask(newTodo, taskList);
                } else {
                    throw new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
                }
                break;

            case "deadline":
                String wholeTask = sc.nextLine();
                int index = wholeTask.indexOf('/');
                if(index > 0) {
                    //what the task is
                    String description = wholeTask.substring(0, index).trim();
                    //when it is due by
                    String date = wholeTask.substring(index + 4).trim();
                    if(description.isEmpty() || date.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                                " It must be in the format <description> /by <date/time> ");
                    } else {
                        Deadline newDeadlineTask = new Deadline(description, date);
                        addTask(newDeadlineTask, taskList);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty." +
                            " It must be in the format <description> /by <date/time> ");
                }
                break;

            case "event":
                String eventAndDate = sc.nextLine();
                int index2 = eventAndDate.indexOf('/');
                if(index2 > 0) {
                    //what the task is
                    String eventDescr = eventAndDate.substring(0, index2).trim();
                    //when it is due by
                    String date2 = eventAndDate.substring(index2 + 4).trim();
                    if(eventDescr.isEmpty() || date2.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                                " It must be in the format <description> /at <start and end of specific time> ");
                    } else {
                        Event newEventTask = new Event(eventDescr, date2);
                        addTask(newEventTask, taskList);
                    }
                } else {
                    throw new DukeException("☹ OOPS!!! The description of an event cannot be empty." +
                            " It must be in the format <description> /at <start and end of specific time> ");
                }
                break;



            case "done":
                int taskNumber = sc.nextInt() - 1;
                completeTask(taskNumber, taskList);
                break;

            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");


        }


    } catch (DukeException e) {
            System.out.println(e);
        }

        command = promptEntry();


}
        printCommand("Bye. Hope to see you again soon!");

    }





    public static void printCommand(String command) {
        System.out.println(command);
    }

    public static String promptEntry() {
       return sc.next();
    }

    public static void printList(ArrayList<Task> toDoList) {
        int n = 1;

        if(toDoList.isEmpty()){

            System.out.println("List is empty");

        } else {

            for (Task item : toDoList) {
                System.out.println(n + "." + item);
                n++;
            }

        }
    }

    public static void completeTask(int taskNumber, ArrayList<Task> list) {
        Task completed = list.get(taskNumber);

            completed.markAsDone();
            System.out.println("Nice! I've marked this task as done: \n" + "   " + completed);

    }

    public static void addTask(Task current, ArrayList<Task> list) {
        list.add(current);
        if(list.size() >1) {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " tasks in the list. ");
        } else {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list. ");
        }
    }





}
