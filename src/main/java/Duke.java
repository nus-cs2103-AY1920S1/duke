import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TaskList tasks = new TaskList();
        String filePath = "C:\\duke\\data\\tasklist.txt";

        printReply("Hello! I'm Duke\n\t What can I do for you?");
        String command = sc.nextLine();
        try {
            FileWriter fw = new FileWriter(filePath, true);
            while (!command.equals("bye")) {
                try {
                    String[] parts = command.split(" ", 2);
                    if (command.equals("list")) {
                        printList(tasks);
                    } else if (parts[0].equals("done")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        printReply(tasks.get(taskNum - 1).markAsDone());
                    } else if (parts[0].equals("delete")) {
                        int taskNum = Integer.parseInt(parts[1]);
                        removeFromList(tasks, taskNum);
                    } else if (parts[0].equals("todo")) {
                        addToList(new Todo(parts[1]), tasks, fw);
                    } else if (parts[0].equals("deadline")) {
                        String[] subparts = parts[1].split(" /by ");
                        addToList(new Deadline(subparts[0], new Date(subparts[1])), tasks, fw);
                    } else if (parts[0].equals("event")) {
                        String[] subparts = parts[1].split(" /at ");
                        addToList(new Event(subparts[0], new Date(subparts[1])), tasks, fw);
                    } else {
                        throw new DukeException("");
                    }
                } catch (DukeException e) {
                    printReply("OOPS!!! I'm sorry, but I don't know what that means :-(");
                } catch (ArrayIndexOutOfBoundsException e) {
                    printReply("OOPS!!! The description of a " + command.split(" ", 2)[0] + " cannot be empty");
                }
                command = sc.nextLine();
            }
            fw.close();
        }catch (IOException e) {
            System.out.println("File not found.");
        }
        printReply("Bye. Hope to see you again soon!");
    }

    private static void printReply(String reply){
        System.out.println("\t_______________________________________________________________\n\t " + reply
                       + "\n\t_______________________________________________________________");
    }
    public static void addToList(Task task, TaskList taskList, FileWriter fw){
        taskList.add(task);
        String reply = "Got it. I've added this task:\n\t  " + task + "\n\t Now you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        printReply(reply);
        String replyToFile = task.toFile();
        try{
            fw.write(replyToFile);
        }catch(IOException e){}

    }
    public static void printList(TaskList taskList){
        String reply = "Here are the tasks in your list:\n\t ";
        for(int i=0; i<taskList.size(); i++) {
            reply += (i + 1) + "." + taskList.get(i);
            if (i != taskList.size() - 1)
                reply += "\n\t ";
        }
        printReply(reply);
    }
    public static void removeFromList(TaskList taskList, int taskIndex) throws DukeException{
        if(taskIndex > taskList.size()) throw new DukeException("");
        String reply = "Noted. I've removed this task:\n\t  " + taskList.remove(taskIndex-1) + "\n\t Now you have " + taskList.size()
                + ((taskList.size() == 1)?" task":" tasks") + " in the list.";
        printReply(reply);
    }
}