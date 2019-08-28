import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    private Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public String getNextLine() {
        return sc.nextLine();
    }

    public String getNext() {
        return sc.next();
    }

    public void printHello() {
        System.out.println("    _____________________________________");
        System.out.println("     Hello! I'm Duke\n     What can I do for you?");
        System.out.println("    _____________________________________\n");
    }

    public void printBye() {
        System.out.println("    _____________________________________");
        System.out.println("     Bye. Hope to see you again soon!");
        System.out.println("    _____________________________________");
    }

    public void printAddTask(TaskList taskList, Task newTask) {
        System.out.println("    _____________________________________");
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + newTask);
        System.out.println("     Now you have " + taskList.getListOfTasks().size() + " tasks in the list.");
        System.out.println("    _____________________________________\n");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println("    _____________________________________");
        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < taskList.getListOfTasks().size(); i++) {
            int number = i + 1;
            System.out.println("     " + number + "." + taskList.getListOfTasks().get(i));
        }
        System.out.println("    _____________________________________\n");
    }

    public void printDoneTask(TaskList taskList, int taskNumber) {
        System.out.println("    _____________________________________");
        System.out.println("     Nice! I've marked this task as done:");
        System.out.println("       " + taskList.getListOfTasks().get(taskNumber));
        System.out.println("    _____________________________________\n");
    }

    public void printDeleteTask(TaskList taskList, Task deletedTask) {
        System.out.println("    _____________________________________");
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + deletedTask);
        System.out.println("     Now you have " +  taskList.getListOfTasks().size() + " tasks in the list.");
        System.out.println("    _____________________________________\n");
    }
}