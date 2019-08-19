import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasksList = new ArrayList<>();
        while(sc.hasNextLine()) {
            String nextItem = sc.next();
            if (nextItem.equals("list")) {
                printList(tasksList);
            } else if (nextItem.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                return;
            } else if (nextItem.equals("done")) {
                int taskID = sc.nextInt();
                Task doneTask = tasksList.get(taskID - 1);
                doneTask.setDone(true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " + doneTask);
            } else if (nextItem.equals("todo")) {
                String currLine = sc.nextLine();
                Task newTask = new Task(currLine, false);
                tasksList.add(newTask);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newTask);
                System.out.println("Now you have " + tasksList.size() +
                        " tasks in the list.");
            } else if (nextItem.equals("deadline")) {
                String nextSegment = sc.next();
                String deadlineName = "";
                while (!nextSegment.equals("/by")) {
                    deadlineName = deadlineName + " " + nextSegment;
                    nextSegment = sc.next();
                }
                String deadline = sc.nextLine();
                Deadline newDeadline = new Deadline(deadlineName, false, deadline);
                tasksList.add(newDeadline);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newDeadline);
                System.out.println("Now you have " + tasksList.size() +
                        " tasks in the list.");
            } else if (nextItem.equals("event")) {
                String nextSegment = sc.next();
                String eventName = "";
                while (!nextSegment.equals("/at")) {
                    eventName = eventName + " " + nextSegment;
                    nextSegment = sc.next();
                }
                String duration = sc.nextLine();
                Event newEvent = new Event(eventName, false, duration);
                tasksList.add(newEvent);
                System.out.println("Got it. I've added this task:");
                System.out.println("  " + newEvent);
                System.out.println("Now you have " + tasksList.size() +
                        " tasks in the list.");
            }
        }
    }

    static void printList(ArrayList<Task> tasksList){
        System.out.println("Here are the tasks in your list:");
        int length = tasksList.size();
        for (int i = 0; i < length; i++) {
            int toPrint = i + 1;
            System.out.println(toPrint + "." + tasksList.get(i));
        }
    }
}