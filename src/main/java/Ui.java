import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Deals with interactions with the user.
 */
public class Ui {
    String line = "____________________________________________________________";
    protected Scanner sc;

    public Ui() {
        this.sc = sc;
    }

    public String readCommand() {
        sc = new Scanner(System.in);
        return sc.nextLine();
    }

    public void printGreeting() {
        String logo = "  .-')   _   .-')      ('-.     _  .-')   .-') _         .-. .-')     ('-.    .-. .-')              \n" +
                " ( OO ).( '.( OO )_   ( OO ).-.( \\( -O ) (  OO) )        \\  ( OO )   ( OO ).-.\\  ( OO )             \n" +
                "(_)---\\_),--.   ,--.) / . --. / ,------. /     '._        ;-----.\\   / . --. / ;-----.\\  ,--.   ,--.\n" +
                "/    _ | |   `.'   |  | \\-.  \\  |   /`. '|'--...__)       | .-.  |   | \\-.  \\  | .-.  |   \\  `.'  / \n" +
                "\\  :` `. |         |.-'-'  |  | |  /  | |'--.  .--'       | '-' /_).-'-'  |  | | '-' /_).-')     /  \n" +
                " '..`''.)|  |'.'|  | \\| |_.'  | |  |_.' |   |  |          | .-. `.  \\| |_.'  | | .-. `.(OO  \\   /   \n" +
                ".-._)   \\|  |   |  |  |  .-.  | |  .  '.'   |  |          | |  \\  |  |  .-.  | | |  \\  ||   /  /\\_  \n" +
                "\\       /|  |   |  |  |  | |  | |  |\\  \\    |  |          | '--'  /  |  | |  | | '--'  /`-./  /.__) \n" +
                " `-----' `--'   `--'  `--' `--' `--' '--'   `--'          `------'   `--' `--' `------'   `--'      ";
        System.out.println(logo);
        String greet = line + "\n Hello! My name is Smart Baby~\n (๑★ᴗ★๑) What can I do for you?\n" + line;
        System.out.println(greet);
    }

    public void printBye() {
        System.out.println(line + "\n Zzz...sleeping time! ~u~\n" + line);
    }

    public void printOops() {
        System.out.println(line + "\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
    }

    public void printLine() {
        System.out.println(line);
    }

    public void printList(TaskList tasks) {
        System.out.println(line + "\nHere are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            assert task != null;
            System.out.println((i + 1) + "." + task.toString());
        }
        System.out.println(line);
    }

    public void printDone(Task task) {
        System.out.println(line + "\n Nice! I've marked this task as done:\n"
                + "[" + task.getStatusIcon() + "]" + task.getDescription() + "\n" + line);
    }

    public void printAddTask(Task task, int sizeOfTaskList) {
        System.out.println(line + "\n Got it. I've added this task:\n" + task.toString() + "\n"
                + "Now you have " + sizeOfTaskList + " tasks in the list.\n" + line);
    }

    public void printDelete(Task deletedTask, int sizeOfTaskList) {
        System.out.println(line + "\n Noted. I've removed this task: \n" + deletedTask.toString() + "\n"
                + "Now you have " + (sizeOfTaskList - 1) + " tasks in the list.\n" + line);
    }

    public void printFind(TaskList findList) {
        System.out.println(line + "\n Here are the matching tasks in your list:");
        for (int i = 0; i < findList.size(); i++) {
            Task task = findList.get(i);
            System.out.println((i+1) + "." + task.toString());
        }
        System.out.println(line);
    }

    public void printEventFormat() {
        System.out.println(line + "\nDoesn't match the event format. Please use /at dd/mm/yyyy 0000 (in 24hr).\n" + line);
    }

    public void printDeadlineFormat() {
        System.out.println(line + "\nDoesn't match the deadline format. Please use /by dd/mm/yyyy 0000 (in 24hr).\n" + line);
    }

    public void throwErrorMessage(String taskType) throws DukeException {
        if (Stream.of("delete", "done", "todo", "deadline", "event").anyMatch(s -> taskType.equals(s))) {
            throw new DukeException(line + "\n ☹ OOPS!!! The description of a " + taskType + " cannot be empty.\n" + line);
        } else {
            throw new DukeException(line + "\n ☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }
}
