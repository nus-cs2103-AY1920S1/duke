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
            try{
                switch (commands[0]) {
                    case "done":
                        if( commands.length > 2){
                            throw new DukeException("☹ OOPS!!! Your command is in the wrong format.");
                        }
                        int completedTaskNum = Integer.parseInt(commands[1]);
                        if( completedTaskNum <= 0 || completedTaskNum > toDoList.size()){
                            throw new DukeException("Task Number is out of bounds");
                        }
                        toDoList.set(completedTaskNum - 1, toDoList.get(completedTaskNum - 1).markAsDone());
                        printLine();
                        printIndentedMsg("Nice! I've marked this task as done:");
                        printIndentedMsg("  " + toDoList.get(completedTaskNum - 1).getTaskStatus());
                        printLine();
                        break;
                    case "todo":
                        Todo newTodo = new Todo(msg.substring(4));
                        toDoList.add(newTodo);
                        printLine();
                        printIndentedMsg("Got it. I've added this task:"); 
                        printIndentedMsg(newTodo.getTaskStatus());
                        printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                        printLine();
                        break;
                    case "deadline":
                        Deadline newDeadline = new Deadline(msg.substring(8));
                        toDoList.add(newDeadline);
                        printLine();
                        printIndentedMsg("Got it. I've added this task:"); 
                        printIndentedMsg(newDeadline.getTaskStatus());
                        printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                        printLine();
                        break;
                    case "event":
                        Event newEvent = new Event(msg.substring(5));
                        toDoList.add(newEvent);
                        printLine();
                        printIndentedMsg("Got it. I've added this task:"); 
                        printIndentedMsg(newEvent.getTaskStatus());
                        printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                        printLine();
                        break;
                    case "delete":
                        if( commands.length > 2){
                            throw new DukeException("☹ OOPS!!! Your command is in the wrong format.");
                        }
                        int deleteTaskNum = Integer.parseInt(commands[1]);
                        if( deleteTaskNum <= 0 || deleteTaskNum > toDoList.size()){
                            throw new DukeException("Task Number is out of bounds");
                        }
                        Task removedTask = toDoList.remove(deleteTaskNum - 1);
                        printLine();
                        printIndentedMsg("Noted. I've removed this task:");
                        printIndentedMsg(removedTask.getTaskDescription());
                        printIndentedMsg("Now you have " + toDoList.size() + ((toDoList.size() <= 1) ? " task" : " tasks") + " in the list.");
                        printLine();
                        break;
                    case "list":
                        if( commands.length > 1){
                            throw new DukeException("☹ OOPS!!! Your command is in the wrong format.");
                        }
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
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
            catch(DukeException e){
                printLine();
                printIndentedMsg(e.getMessage());
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
