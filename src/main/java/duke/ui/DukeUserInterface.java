package duke.ui;

import duke.task.Task;
import duke.tasklist.MyList;

public interface DukeUserInterface {
    String readCommand();
    void exit();
    void printIntro();
    void printExitMsg();
    void printAddTaskMsg(Task task, MyList taskList);
    void printList(MyList myList);
    void printDoneMsg(Task task);
    void printException(String msg);
    void printDeleteMsg(Task task, MyList taskList);
}
