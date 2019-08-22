import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static void printWithIndentation(String content) {
        System.out.println("    ____________________________________________________________\n" +
                "     " + content + "\n" +
                "    ____________________________________________________________\n    ");
    }

    private static void addTask(Task task, List<Task> list) {
        list.add(task);
        String output = "Got it. I've added this task: " + "\n" + "       "
                + task.toString() + "\n" + "     "
                + "Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list.");
        printWithIndentation(output);
    }

    private static String[] splitByKeyword(String input, String keyword) {
        StringBuilder before = new StringBuilder();
        boolean beforeFirst = true;
        StringBuilder after = new StringBuilder();
        boolean afterFirst = true;
        String[] words = input.split(" ");
        boolean isBefore = true;
        for (int i = 0; i < words.length; i++) {
            if (words[i].matches(keyword)) {
                isBefore = false;
            } else if (isBefore) {
                if (beforeFirst) {
                    beforeFirst = false;
                } else {
                    before.append(" ");
                }
                before.append(words[i]);
            } else {
                if (afterFirst) {
                    afterFirst = false;
                } else {
                    after.append(" ");
                }
                after.append(words[i]);
            }
        }
        String[] res = new String[2];
        res[0] = before.toString();
        res[1] = after.toString();
        return res;
    }

    public static void main(String[] args) {
        System.out.println("    ____________________________________________________________\n" +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                "    ____________________________________________________________");
        Scanner sc = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.matches("bye")) {
                printWithIndentation("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.matches("list")) {
                StringBuilder builder = new StringBuilder("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    builder.append("\n" + "     ");
                    builder.append(i + 1).append(".").append(tasks.get(i).toString());
                }
                printWithIndentation(builder.toString());
            } else if (userInput.startsWith("done")) {
                int doneNo = Integer.parseInt(userInput.split(" ")[1]) - 1;
                tasks.get(doneNo).markAsDone();
                String tempOut = "Nice! I've marked this task as done: " + "\n" + "       " +
                        tasks.get(doneNo).toString();
                printWithIndentation(tempOut);
            } else if (userInput.startsWith("todo")) {
                addTask(new ToDo(userInput.substring(5)), tasks);
            } else if (userInput.startsWith("deadline")) {
                String[] temp = splitByKeyword(userInput.substring(9), "/by");
                addTask(new Deadline(temp[0], temp[1]), tasks);
            } else if (userInput.startsWith("event")) {
                String[] temp = splitByKeyword(userInput.substring(6), "/at");
                addTask(new Event(temp[0], temp[1]), tasks);
            } else {
                addTask(new Task(userInput), tasks);
            }
        }
    }
}