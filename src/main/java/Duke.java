import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<Task>();

        printReply("Hello! I'm Duke\n\t What can I do for you?");
        String command = sc.nextLine();
            while (!command.equals("bye")) {
                try {
                    String[] parts = command.split(" ", 2);
                    if (command.equals("list")) {
                        printList(taskList);
                    } else if (parts[0].equals("done")) {
                        int taskNum = Integer.valueOf(parts[1]);
                        printReply(taskList.get(taskNum - 1).markAsDone());
                    } else if (parts[0].equals("delete")) {
                        int taskNum = Integer.valueOf(parts[1]);
                        removeFromList(taskList, taskNum);
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
        System.out.println("\t_______________________________________________________________\n\t " + reply
                       + "\n\t_______________________________________________________________");
    }
    public static void addToList(Task task, ArrayList<Task> taskList){
        taskList.add(task);
        String reply = "Got it. I've added this task:\n\t  " + task + "\n\t Now you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        printReply(reply);
    }
    public static void printList(ArrayList<Task> taskList){
        String reply = "Here are the tasks in your list:\n\t ";
        for(int i=0; i<taskList.size(); i++) {
            reply += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1)
                reply += "\n\t ";
        }
        printReply(reply);
    }
    public static void removeFromList(ArrayList<Task> taskList, int taskIndex) throws DukeException{
        if(taskIndex > taskList.size()) throw new DukeException("");
        String reply = "Noted. I've removed this task:\n\t  " + taskList.remove(taskIndex-1) + "\n\t Now you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        printReply(reply);
    }
}