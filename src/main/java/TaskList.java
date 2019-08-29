import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {

    static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void readList(Scanner sc) {
        while(sc.hasNext()) {
            try {
                String command = sc.nextLine();
                String[] words = command.split(" ", 2);
                String action = words[0];

                if (command.equals("bye")) {
                    printBye();
                    return;
                } else if (action.equals("done")) {
                    doneTask(words);
                } else if (action.equals("delete")) {
                    deleteTask(words);
                } else if (action.equals("todo")) {
                    addTodo(words);
                } else if (action.equals("deadline")) {
                    addDeadline(words);
                } else if (action.equals("event")) {
                    addEvent(words);
                } else if (command.equals("list")) {
                    printList();
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException ex) {
                System.out.println("    ____________________________________________________________");
                System.err.println("     " + ex);
                System.out.println("    ____________________________________________________________");
            }

        }
    }

    public void doneTask(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a done task cannot be empty.");
        }

        int numoftasks = list.size();
        int doneIndex = Integer.parseInt(words[1]);
        if (doneIndex > numoftasks || doneIndex <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }

        Task currentTask = list.get(doneIndex - 1);
        currentTask.markAsDone();
        System.out.println("    ____________________________________________________________");
        System.out.println("     Nice! I've marked this task as done: ");
        System.out.println("       " + currentTask);
        System.out.println("    ____________________________________________________________");
    }

    public void deleteTask(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The index of a delete task cannot be empty.");
        }
        int numoftasks = list.size();
        int doneIndex = Integer.parseInt(words[1]);
        if (doneIndex > numoftasks || doneIndex <= 0) {
            throw new DukeException("☹ OOPS!!! The task is not found.");
        }

        Task currentTask = list.get(doneIndex - 1);
        list.remove(doneIndex - 1);
        System.out.println("    ____________________________________________________________");
        System.out.println("     Noted. I've removed this task: ");
        System.out.println("       " + currentTask);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public void addEvent(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
        }
        String description = words[1];
        String[] actionAndTime = description.split("/at");
        Event event = new Event(actionAndTime[0], actionAndTime[1]);
        list.add(event);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + event);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public void addDeadline(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }

        String description = words[1];
        String[] actionAndTime = description.split("/by");

        Deadline deadline = new Deadline(actionAndTime[0], actionAndTime[1]);
        list.add(deadline);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + deadline);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public void addTodo(String[] words) throws DukeException {
        if (words.length < 2) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        String description = words[1];
        ToDo todo = new ToDo(description);
        list.add(todo);

        System.out.println("    ____________________________________________________________");
        System.out.println("     Got it. I've added this task: ");
        System.out.println("       " + todo);
        printNumber();
        System.out.println("    ____________________________________________________________");
    }

    public void printList() {
        System.out.println("    ____________________________________________________________");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println("     " + index + ". " + list.get(i));
        }
        System.out.println("    ____________________________________________________________");
    }

    public void printBye() {
        System.out.println("    ____________________________________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    ____________________________________________________________");
    }

    public void printNumber() {
        String string = String.format("     Now you have %d tasks in the list.", list.size());
        System.out.println(string);
    }

}
