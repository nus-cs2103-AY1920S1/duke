import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static Scanner sc = new Scanner(System.in);
    private static ArrayList<Task> tasksList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");


        while(sc.hasNextLine()) {
            String nextItem = sc.next();
            if (nextItem.equals("list")) {
                printList();
            } else if (nextItem.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (nextItem.equals("done")) {
                doneTask();
            } else if (nextItem.equals("todo")) {
                try {
                    addToDo();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("deadline")) {
                try {
                    addDeadline();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else if (nextItem.equals("event")) {
                try {
                    addEvent();
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            } else {
                try {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (DukeException ex) {
                    System.out.println(ex);
                }
            }
        }
    }

    private static void printList(){
        System.out.println("Here are the tasks in your list:");
        int length = tasksList.size();
        for (int i = 0; i < length; i++) {
            int toPrint = i + 1;
            System.out.println(toPrint + "." + tasksList.get(i));
        }
    }

    private static void doneTask(){
        int taskID = sc.nextInt();
        Task doneTask = tasksList.get(taskID - 1);
        doneTask.setDone(true);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + doneTask);
    }

    private static void addToDo() throws DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        Task newTask = new Task(currLine, false);
        tasksList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newTask);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
    }

    private static void addDeadline() throws  DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] stringSplit = currLine.split("/by");
        String deadlineName = stringSplit[0];
        if(stringSplit.length < 2){
            throw new DukeException("☹ OOPS!!! The deadline must be specified.");
        }
        String deadline = stringSplit[1];
        Deadline newDeadline = new Deadline(deadlineName, false, deadline);
        tasksList.add(newDeadline);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newDeadline);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
    }

    private static void addEvent() throws DukeException{
        String currLine = sc.nextLine();
        if(currLine.isEmpty()){
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String[] stringSplit = currLine.split("/at");
        String eventName = stringSplit[0];
        if(stringSplit.length < 2){
            throw new DukeException("☹ OOPS!!! The event timing must be specified.");
        }
        String duration = stringSplit[1];
        Event newEvent = new Event(eventName, false, duration);
        tasksList.add(newEvent);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + newEvent);
        System.out.println("Now you have " + tasksList.size() +
                " tasks in the list.");
    }
}