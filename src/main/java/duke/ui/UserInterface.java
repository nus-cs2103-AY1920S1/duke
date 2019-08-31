package duke.ui;

import duke.constant.Consts;
import duke.task.Task;
import duke.tasklist.MyList;

import java.util.List;
import java.util.Scanner;

public class UserInterface implements DukeUserInterface {
    private Scanner sc;

    public UserInterface() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String command = "";
        while (command.equals("")) {
            if (!sc.hasNextLine()) {
                continue;
            }
            command = sc.nextLine();
        }
        return command;
    }

    //closes scanner
    public void exit() {
        sc.close();
    }

    //prints the logo for the chat bot
    private void printLogo() {
        System.out.println(Consts.LOGO);
    }

    //prints the indentation used for the output
    private void printIndentation() {
        System.out.print("\t");
    }

    //prints line with indentation in front
    public void printLine() {
        printIndentation();
        System.out.println("____________________________________________________________");
    }

    //prints one section(bounded by lines)
    public void printSection(String msg) {
        printIndentation();
        System.out.println(" " + msg);
    }

    //prints one section(bounded by lines) with multiple lines of messages
    public void printSection(String[] msgArray) {
        for (String string : msgArray) {
            printIndentation();
            System.out.println(string);
        }
    }

    public void printIntro() {
        String[] array = new String[2];
        array[0] = " Hello! I'm Duke";
        array[1] = " What can I do for you?";
        printLogo();
        printSection(array);
        printLine();
        System.out.println();
    }

    public void printExitMsg() {
        printSection(" Bye. Hope to see you again soon!");
    }

    public void printAddTaskMsg(Task task, MyList taskList) {
        String[] array = new String[3];
        array[0] = " Got it. I've added this task: ";
        array[1] = String.format("   %s", task);
        array[2] = String.format(" Now you have %d %s in the list.", taskList.getNumTasks(), taskList.getNumTasks() == 1 ? "task" : "tasks");
        printSection(array);
    }

    public void printList(MyList myList) {
        List<Task> list = myList.getList();
        int listNum = 1;

        String[] array = new String[myList.getNumTasks() + 1];
        array[0] = " Here are the tasks in your list:";
        for (Task task : list) {
            array[listNum] = String.format(" %d.%s", listNum, task);
            listNum++;
        }
        printSection(array);
    }

    public void printFindList(MyList myList) {
        List<Task> list = myList.getList();
        int listNum = 1;

        String[] array = new String[myList.getNumTasks() + 1];
        array[0] = " Here are the matching tasks in your list:";
        for (Task task : list) {
            array[listNum] = String.format(" %d.%s", listNum, task);
            listNum++;
        }
        printSection(array);
    }

    public void printDoneMsg(Task task) {
        String[] array = new String[2];
        array[0] = " Nice! I've marked this task as done: ";
        array[1] = String.format("   %s", task);
        printSection(array);
    }

    public void printException(String msg) {
        printSection(msg);
    }

    public void printDeleteMsg(Task task, MyList taskList) {
        String[] array = new String[3];
        array[0] = " Noted. I've removed this task: ";
        array[1] = String.format("   %s", task);
        array[2] = String.format(" Now you have %d %s in the list.", taskList.getNumTasks(), taskList.getNumTasks() == 1 ? "task" : "tasks");
        printSection(array);
    }
}
