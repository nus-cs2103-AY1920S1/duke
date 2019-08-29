package duke.ui;

import duke.task.Task;
import duke.tasklist.MyList;

public interface DukeUserInterface {
    public String readCommand();
    public void exit();
    public void printIntro();
    public void printExitMsg();
    public void printAddTaskMsg(Task task, MyList taskList);
    public void printList(MyList myList);
    public void printDoneMsg(Task task);
    public void printException(String msg);
    public void printDeleteMsg(Task task, MyList taskList);
}
