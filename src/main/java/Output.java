import java.util.List;

public class Output {
    public static String line = "    ____________________________________________________________";
    public static String indent = "     ";
    public static String hiMsg = indent + "Hello! I'm Duke" + "\n" + indent + "What can I do for you?";
    public static String byeMsg = indent + "Bye. Hope to see you again soon!";
    public static String addMsg = indent + "Got it. I've added this task:";
    public static String doneMsg = indent + "Nice! I've marked this task as done:";
    public static String delMsg = indent + "Noted. I've removed this task:";
    public static String emptyListMsg = indent + "You have no task in the list.";

    public static void line() {
        System.out.println(line);
    }
    public static void hiMsg() {
        System.out.println(hiMsg);
    }
    public static void byeMsg() {
        System.out.println(byeMsg);
    }
    public static void addMsg() {
        System.out.println(addMsg);
    }
    public static void doneMsg() {
        System.out.println(doneMsg);
    }
    public static void delMsg() {
        System.out.println(delMsg);
    }

    public static void emptyListMsg() {
        System.out.println(emptyListMsg);
    }

    public static void printIntro() {
        line();
        hiMsg();
        line();
        System.out.println();
    }
    public static <T> void printList(List<T> list) {
        if (!list.isEmpty()) {
            System.out.println(indent + "Here are the tasks in your list:");
            for (T t: list) {
                System.out.println(indent + (list.indexOf(t) + 1) + ". " + t);
            }
        } else {
            emptyListMsg();
        }
    }
    public static <T> void printListSize(List<T> list) {
        if (!list.isEmpty()) {
            System.out.println(indent + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            emptyListMsg();
        }
    }
    public static void printTask(Task task) {
        System.out.println(indent + "  " + task);
    }
}
