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


            switch (command) {

                case "list":
                    printList(taskList);
                    break;

                case "todo":
                    ToDo newTodo = new ToDo(sc.nextLine());
                    addTask(newTodo, taskList);
                    break;

                case "deadline":
                    String wholeTask = sc.nextLine();
                    int index = wholeTask.indexOf('/');
                    //what the task is
                    String description = wholeTask.substring(0, index);
                    //when it is due by
                    String date = wholeTask.substring(index + 4);
                    Deadline newDeadlineTask = new Deadline(description, date);
                    addTask(newDeadlineTask, taskList);
                    break;

                case "event":
                    String eventAndDate = sc.nextLine();
                    int index2 = eventAndDate.indexOf('/');
                    //what the task is
                    String eventDescr = eventAndDate.substring(0, index2);
                    //when it is due by
                    String date2 = eventAndDate.substring(index2 + 4);
                    Event newEventTask = new Event(eventDescr, date2);
                    addTask(newEventTask, taskList);
                    break;

                case "done":
                    int taskNumber = sc.nextInt()-1;
                    completeTask(taskNumber, taskList);
                    break;

                default:

                    break;

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
                System.out.println(n + ". " + item);
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
                    "Now you have " + list.size() + " tasks in the list ");
        } else {
            System.out.println("Got it. I've added this task: \n" + "   " + current.toString() + "\n" +
                    "Now you have " + list.size() + " task in the list ");
        }
    }
}
