import java.util.Scanner;
import java.util.ArrayList;
//Test comment
public class Duke {
    private ArrayList<Task> tasks = new ArrayList<Task>();
    /*
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
    */
    public void introduction() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }
    public void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public void list() {
        System.out.println("Here are the tasks in your list:");
        for(int i = 1; i <= this.tasks.size(); i++) {
            Task curr_task = this.tasks.get(i-1);
            System.out.println(i + "." + curr_task);
        }
    }
    public void add(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println("  " + task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }
    public void done(int task_num) {
        Task curr_task = this.tasks.get(task_num - 1);
        curr_task.setDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println("  " + curr_task);
    }
    public void delete(int task_num) {
        Task curr_task = this.tasks.get(task_num - 1);
        System.out.println("Noted. I've removed this task: ");
        System.out.println("  " + curr_task);
        this.tasks.remove(task_num - 1);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }
    public static void main(String[] args) throws DukeException{
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();
        duke.introduction();
        try {
            while(sc.hasNextLine()) {
                String command = sc.nextLine();
                if (command.equals("bye")) {
                    duke.exit();
                    return;
                } else if (command.equals("list")) {
                    duke.list();
                } else if (command.startsWith("done ")) {
                    String[] splited = command.split(" ");
                    if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
                        duke.done(Integer.parseInt(splited[1]));
                    } else {
                        throw new DoneParameterException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else if (command.startsWith("todo")) {
                    if(command.equals("todo") || command.equals("todo ")) {
                        throw new EmptyToDoDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    ToDo curr_task = new ToDo(command.replaceFirst("todo ", ""));
                    duke.add(curr_task);
                } else if (command.startsWith("deadline ")) {
                    String[] splited = command.split(" /by ");
                    splited[0].replaceFirst("deadline ", "");
                    Deadline curr_task = new Deadline(splited[0], splited[1]);
                    duke.add(curr_task);
                } else if (command.startsWith("event ")) {
                    String[] splited = command.split(" /at ");
                    splited[0].replaceFirst("event ", "");
                    Event curr_task = new Event(splited[0], splited[1]);
                    duke.add(curr_task);
                } else if (command.startsWith("delete ")) {
                    String[] splited = command.split(" ");
                    if (splited[1].matches("^[0-9]*[1-9][0-9]*$") && splited.length == 2) {
                        duke.delete(Integer.parseInt(splited[1]));
                    } else {
                        throw new DeleteParameterException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            }
        } catch(InvalidInputException ex) {
            System.out.println(ex.getMessage());
        } catch(EmptyToDoDescriptionException ex) {
            System.out.println(ex.getMessage());
        } catch(DoneParameterException ex) {
            System.out.println(ex.getMessage());
        } catch(DeleteParameterException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
