import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    private static int numTasks = 0;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];

        printReply("Hello! I'm Duke\n\t What can I do for you?");
        String command = sc.nextLine();
            while (!command.equals("bye")) {
                try {
                    String[] parts = command.split(" ", 2);
                    if (command.equals("list"))
                        printList(taskList);
                    else if (parts[0].equals("done")) {
                        int taskNum = Integer.valueOf(parts[1]);
                        printReply(taskList[taskNum - 1].markAsDone());
                    } else if (parts[0].equals("todo")) {
                        addToList(new Todo(parts[1]), taskList);
                    } else if (parts[0].equals("deadline")) {
                        String[] subparts = parts[1].split(" /by ");
                        addToList(new Deadline(subparts[0], subparts[1]), taskList);
                    } else if (parts[0].equals("event")) {
                        String[] subparts = parts[1].split(" /at ");
                        addToList(new Event(subparts[0], subparts[1]), taskList);
                    } else
                        throw new DukeException("");
                }catch(DukeException e){
                    printReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }catch(ArrayIndexOutOfBoundsException e){
                    printReply("OOPS!!! The description of a " + command.split(" ", 2)[0] + " cannot be empty");
                }
                command = sc.nextLine();
            }
        printReply("Bye. Hope to see you again soon!");
    }
    public static void printReply(String reply){
        System.out.println("\t______________________________________________________________\n\t " + reply
                       + "\n\t______________________________________________________________");
    }
    public static void addToList(Task task, Task[] taskList){
        taskList[numTasks++] = task;
        String reply = "Got it. I've added this task: \n\t  " + task + "\n\t Now you have " + numTasks;
        if(numTasks == 1) reply += " task in the list.";
        else reply += " tasks in the list.";
        printReply(reply);
    }
    public static void printList(Task[] taskList){
        String listReply = "Here are the tasks in your list:\n\t ";
        for(int i=0; i<numTasks; i++) {
            listReply += (i + 1) + "." + taskList[i];
            if (i != numTasks - 1)
                listReply += "\n\t ";
        }
        printReply(listReply);
    }
}