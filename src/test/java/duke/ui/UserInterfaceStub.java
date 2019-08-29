package duke.ui;

import duke.constant.Consts;
import duke.task.Task;
import duke.tasklist.MyList;

import java.util.List;
import java.util.Scanner;

public class UserInterfaceStub implements DukeUserInterface {
    private Scanner sc;

    public UserInterfaceStub() {
    }

    public String readCommand() {
        return "Testing readCommand";
    }

    //closes scanner
    public void exit() {}

    //prints the logo for the chat bot
    private void printLogo() {}

    //prints the indentation used for the output
    private void printIndentation() {}

    //prints line with indentation in front
    public void printLine() {
    }

    //prints one section(bounded by lines)
    public void printSection(String msg) {}

    //prints one section(bounded by lines) with multiple lines of messages
    public void printSection(String[] msgArray) {}

    public void printIntro() {}

    public void printExitMsg() {}

    public void printAddTaskMsg(Task task, MyList taskList) {}

    public void printList(MyList myList) {}

    public void printDoneMsg(Task task) {}

    public void printException(String msg) {}

    public void printDeleteMsg(Task task, MyList taskList) {}
}
