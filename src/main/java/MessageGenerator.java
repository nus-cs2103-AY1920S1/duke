import java.util.List;

public class MessageGenerator {

    Formatter formatter = new Formatter();

    public MessageGenerator() {}

    public String listTasks() {
        return "Here are the tasks in your list: ";
    }

    public String removeTask() {
        return "Noted. I've removed this task: ";
    }

    public String addTask() {
        return "Got it. I've added this task: ";
    }

    public String markDone() {
        return "Nice! I've marked this task as done:";
    }

    public String numTask(int n) {
        return "Now you have " + n + " task(s) in the list.";
    }

    public String greeting() {
        return "Hello I'm Duke\n" + "What can I do for you?";
    }

    public String byeString() {
        return "Bye. Hope to see you again soon!";
    }

    public String formatTask(Task task) {
        return  "  " + task.toString();
    }

    public void printRemove(Task task, int n) {
        formatter.printFormat(removeTask(), formatTask(task), numTask(n));
    }

    public void printAdd(Task task, int n) {
        formatter.printFormat(addTask(), formatTask(task), numTask(n));
    }

    public void printDone(Task task) {
        formatter.printFormat(markDone(), formatTask(task));
    }

    public void printList(List<String> list) {
        formatter.printFormat(listTasks(), list);
    }

    public void greet() {
        formatter.printFormat(greeting());
    }

    public void bye() {
        formatter.printFormat(byeString());
    }

}
