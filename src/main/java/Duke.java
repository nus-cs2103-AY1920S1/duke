import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static void printIndentedMsg(String msg){
        String indent= "     ";
        System.out.println(indent + msg);
    }

    private static void printLine(){
        String line= "    ____________________________________________________________";
        System.out.println(line);
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        printLine();
        printIndentedMsg("Hello! I'm Duke");
        printIndentedMsg("What can I do for you?");
        printLine();
        String msg = s.nextLine();
        String[] commands = msg.split(" ");
        ArrayList<Task> toDoList = new ArrayList<>();
        while(!(msg.equals("bye") && commands.length == 1)) {
            switch (commands[0]) {
                case "done":
                    try{
                        int completedTaskNum = Integer.parseInt(commands[1]);
                        if( completedTaskNum <= 0 || completedTaskNum > toDoList.size()){
                            throw new IllegalArgumentException("Task Number is out of bounds");
                        }
                        toDoList.set(completedTaskNum - 1, toDoList.get(completedTaskNum - 1).markAsDone());
                        printLine();
                        printIndentedMsg("Nice! I've marked this task as done:");
                        printIndentedMsg("  " + toDoList.get(completedTaskNum - 1).getTaskStatus());
                        printLine();
                    }catch (IllegalArgumentException e){
                        printLine();
                        printIndentedMsg("whoops, inavlid command");
                        printLine();
                    }
                    break;
                case "todo":
                    Todo newTodo = new Todo(msg.substring(5));
                    toDoList.add(newTodo);
                    printLine();
                    printIndentedMsg("Got it. I've added this task:"); 
                    printIndentedMsg(newTodo.getTaskStatus());
                    printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                    printLine();
                    break;
                case "deadline":
                    Deadline newDeadline = new Deadline(msg.substring(9));
                    toDoList.add(newDeadline);
                    printLine();
                    printIndentedMsg("Got it. I've added this task:"); 
                    printIndentedMsg(newDeadline.getTaskStatus());
                    printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                    printLine();
                    break;
                case "event":
                    Event newEvent = new Event(msg.substring(6));
                    toDoList.add(newEvent);
                    printLine();
                    printIndentedMsg("Got it. I've added this task:"); 
                    printIndentedMsg(newEvent.getTaskStatus());
                    printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                    printLine();
                    break;
                case "list":
                    printLine();
                    int startNumber = 1;
                    printIndentedMsg("Here are the tasks in your list:");
                    for(Task t : toDoList){
                        printIndentedMsg("" + startNumber + "." + t.getTaskStatus());
                        startNumber++;
                    }
                    printLine();
                    break;
                default:
                    printLine();
                    printIndentedMsg("whoops, invalid command");
                    printLine();
            }
            msg = s.nextLine();
            commands = msg.split(" ");
        }
        printLine();
        printIndentedMsg("Bye. Hope to see you again soon!");
        printLine();
    }
}
