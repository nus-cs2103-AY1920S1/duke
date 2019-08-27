import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class Ui {
    public final String indent = "    ";
    Scanner sc = new Scanner(System.in);

    public void showWelcome() {
        String intro = "Hello! I'm Duke\n"
                + "What can I do for you?";
        System.out.println(indent(wrapWithHorizontalLines(intro)));
    }

    public void showBye() {
        String endMessage = "Bye. Hope to see you again soon!";
        System.out.println(indent(wrapWithHorizontalLines(endMessage)));
    }

    public void showTasks(List<Task> tasks) {
        StringJoiner sj = new StringJoiner("\n");
        for (int i = 0; i < tasks.size(); i++) {
            sj.add((i + 1) + ". " + tasks.get(i));
        }
        System.out.println(indent(wrapWithHorizontalLines(sj.toString())));
    }

    public void showMarkAsDone(Task task) {
        String output = "Nice! I've marked this task as done: \n"
                + indent(task.toString());
        System.out.println(indent(wrapWithHorizontalLines(output)));
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public String wrapWithHorizontalLines(String str) {
        return "____________________________________________________________\n"
                + str
                + "\n" + "____________________________________________________________";
    }

    public void showAddTask(Task task, int numberOfTasksLeft) {
        String output = "Got it. I've added this task: \n"
                + indent(task.toString())
                + String.format("\nNow you have %d tasks in the list.", numberOfTasksLeft);
        System.out.println(indent(wrapWithHorizontalLines(output)));
    }

    public void showDeleteTask(Task task, int numberOfTasksLeft) {
        String output = "Noted. I've removed this task: \n"
                + indent(task.toString())
                + String.format("\nNow you have %d tasks in the list.", numberOfTasksLeft);
        System.out.println(indent(wrapWithHorizontalLines(output)));
    }

    public void showGenericError() {
        String output = " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        System.out.println(indent(wrapWithHorizontalLines(output)));
    }

    public String indent(String str) {
        String[] indentedStrings = Arrays.stream(str.split("\n")).map(s -> indent + s).toArray(String[]::new);
        return String.join("\n", indentedStrings);
    }
}
