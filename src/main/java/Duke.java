import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
        /*String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);*/
        Greet();
        Detecting();
    }
    public static void Greet(){
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }
    public static void Detecting() throws DukeException {
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new LinkedList<>();
        while(true) {
            String cmd = sc.nextLine();
            Scanner cmdSc = new Scanner(cmd);
            if (cmd.toLowerCase().equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                String cmdWord = cmdSc.next().toLowerCase();
                switch (cmdWord) {
                    case "todo":
                        try {
                            if (cmdSc.hasNext()) {
                                String toDoTsk = cmdSc.nextLine();
                                tasks.add(new toDo(toDoTsk));
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //list
                    case "list":
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 1; i <= tasks.size(); i++) {
                            Task tsk = tasks.get(i-1);
                            System.out.println(i + ". " + tsk);
                        }
                        break;
                    //deadline
                    case "deadline":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskBy = cmdSc.nextLine();
                                Scanner ddlSc = new Scanner(tskBy).useDelimiter("\\s*/by\\s*");
                                tasks.add(new Deadline(ddlSc.next(), ddlSc.next()));
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of a deadline cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //event
                    case "event":
                        try {
                            if (cmdSc.hasNext()) {
                                String tskAt = cmdSc.nextLine();
                                Scanner evtSc = new Scanner(tskAt).useDelimiter("\\s*/at\\s*");
                                tasks.add(new Event(evtSc.next(), evtSc.next()));
                                System.out.println("Got it. I've added this task:\n  " + tasks.get(tasks.size() - 1));
                                System.out.println("Now you have " + tasks.size() + " tasks in the list");
                            } else {
                                throw new DukeException("The description of an event cannot be empty.");
                            }
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!! " + exp.getMessage());
                        }
                        break;
                    //done
                    case "done":
                        int numDone = Integer.parseInt(cmdSc.next()) - 1;
                        Task itemDone = tasks.get(numDone);
                        itemDone.mardAsDone();
                        System.out.println("Nice! I've marked this task as done:\n  " + itemDone);
                        break;
                    //delete
                    case "delete":
                        int numDelete = Integer.parseInt(cmdSc.next()) - 1;
                        Task itemDelete = tasks.remove(numDelete);
                        System.out.println("Noted. I've removed the task:\n  " + itemDelete);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        break;

                    default:
                        try {
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                        }
                        catch (DukeException exp) {
                            System.out.println("OOPS!!!" + exp.getMessage());
                        }
                }
            }
        }
    }
}

