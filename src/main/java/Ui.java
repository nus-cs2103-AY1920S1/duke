import java.util.ArrayList;

public class Ui {
    String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    String start = "Hello! I'm Duke\n    What can I do for you?";
    String end = "Bye. Hope to see you again soon!";

    //Function spacer takes input string and converts it to be
    // printed with the appropriate lines and indentation
    protected void spacer(String input) {
        String separator = "    ____________________________________________________________";
        String converted = "    " + input;
        System.out.println(separator);
        System.out.println(converted);
        System.out.println(separator + "\n");

    }

    protected void helloMessage() {
        //Print my welcome message
        spacer(start);
    }

    protected void byeMessage() {
        //Print my end message
        spacer(end);
    }

    //Print all tasks upon "list"
    protected void printTasks(ArrayList<Task> input) {
        String separator = "    ____________________________________________________________";
        System.out.println(separator);
        System.out.println("    Here are the tasks in your list:");
        for (int i = 0; i < input.size(); i++) {
            System.out.println("    " + (i + 1) + "." + input.get(i));
        }
        System.out.println(separator + "\n");

    }

    protected void printDone(String x) {
        spacer(x);
    }

    protected void printDelete(Task removed,TaskList main) {
        String separator = "    ____________________________________________________________";
        String removingTask = "    Noted. I've removed this task:";
        String converted = "      "+ removed;
        String taskTracking = "    Now you have " + main.getSize() + " tasks in the list.";
        String answer = separator + "\n" + removingTask + "\n"
                + converted + "\n" + taskTracking + "\n" + separator + "\n";

        System.out.printf(answer);
    }

    //Format for printing the "adding"
    public void printAdd(Task inputTask, TaskList main) {
        String separator = "    ____________________________________________________________";
        String addingTask = "    Got it. I've added this task:";
        String converted = "        "+ inputTask.toString();
        String taskTracking = "    Now you have " + main.getSize() + " tasks in the list.";
        System.out.println(separator);
        System.out.println(addingTask);
        System.out.println(converted);
        System.out.println(taskTracking);
        System.out.println(separator + "\n");
    }


}







