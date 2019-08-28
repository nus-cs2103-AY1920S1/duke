import java.util.Scanner;

public class UI {
    protected Scanner sc;

    public UI() {
        this.sc = sc;
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printGreeting() {
        String logo = "   _     _      _     _      _     _       _     _      _     _      _     _      _     _   \n" +
                "  (c).-.(c)    (c).-.(c)    (c).-.(c)     (c).-.(c)    (c).-.(c)    (c).-.(c)    (c).-.(c)  \n" +
                "   / ._. \\      / ._. \\      / ._. \\       / ._. \\      / ._. \\      / ._. \\      / ._. \\   \n" +
                " __\\( Y )/__  __\\( Y )/__  __\\( Y )/__   __\\( Y )/__  __\\( Y )/__  __\\( Y )/__  __\\( Y )/__ \n" +
                "(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._) (_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)(_.-/'-'\\-._)\n" +
                "   || T ||      || A ||      || I ||       || P ||      || I ||      || N ||      || G ||   \n" +
                " _.' `-' '._  _.' `-' '._  _.' `-' '._   _.' `-' '._  _.' `-' '._  _.' `-' '._  _.' `-' '._ \n" +
                "(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.) (.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)(.-./`-'\\.-.)\n" +
                " `-'     `-'  `-'     `-'  `-'     `-'   `-'     `-'  `-'     `-'  `-'     `-'  `-'     `-' ";
        System.out.println("Hello from\n" + logo);
        String greet = "____________________________________________________________\n" +
                "     Hello! I'm Tai Ping\n" +
                "     What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);
    }

    public void printBye() {
        String bye = "____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(bye);
    }

    public void printIDK() {
        System.out.println("____________________________________________________________\n" +
                "     ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
                "____________________________________________________________");
    }

    public void printLine() {
        System.out.println("____________________________________________________________\n");
    }

    public void printList(TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        int count = 1;
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            System.out.println(count + "." + t.toString());
            count++;
        }
        System.out.println("____________________________________________________________\n");
    }

    public void printDone(Task task) {
        System.out.println("____________________________________________________________\n" +
                "Congratulations! I've marked this task as done: ");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDescription());
        System.out.println("____________________________________________________________\n");
    }

    public void printAddTask(Task task, int taskListSize) {
        System.out.println("____________________________________________________________\n" +
                "Got it. I've added this task: ");
        System.out.println(task.toString());
        System.out.println("Now you have " +  taskListSize + " task(s) in the list.");
        System.out.println("____________________________________________________________\n");
    }

    public void printDelete(Task deletedTask, int taskListSize) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task: \n" +
                deletedTask.toString() + "\n" +
                "Now you have " + (taskListSize - 1) + " task(s) in the list.\n" +
                "____________________________________________________________");
    }

    public void throwInputError(String taskType) throws DukeException {
        switch (taskType) {
        case "todo" :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ Hey! Description of a todo cannot be empty :(\n" +
                    "____________________________________________________________");
        case "event" :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ Hey! Description of a event cannot be empty :(\n" +
                    "____________________________________________________________");
        case "deadline" :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ Hey! Description of a deadline cannot be empty :(\n" +
                    "____________________________________________________________");
        case "done" :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ Hey! Description of a done cannot be empty :(\n" +
                    "____________________________________________________________");
        case "delete" :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ Hey! Description of a delete cannot be empty :(\n" +
                    "____________________________________________________________");
        default :
            throw new DukeException("____________________________________________________________\n" +
                    "     ☹ OOPS!!! I'm sorry, but I don't know what that means :(\n" +
                    "____________________________________________________________");
        }
    }
}
