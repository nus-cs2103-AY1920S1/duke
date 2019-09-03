import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class TaskList {

    protected static ArrayList<Task> listOfTasks = new ArrayList<Task>();
    protected Task tasking = new Task("");
    protected Storage store = new Storage(Storage.file);

    /**
     * Constructor for TaskList.
     */
    public TaskList() {

    }

    /**
     * Overloaded Constructor for Task list in
     * the event that a array list is available
     * from the file.
     *
     * @param list Arraylist that contains all the tasks.
     */
    public TaskList(ArrayList<Task> list) {
        listOfTasks = list;
    }

    /**
     * Deletes the specified tasks off the task list.
     *
     * @param text Delete command with the which tasks to delete based of their numbering.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void deleteCommand(String text) throws IOException {
        int num = text.indexOf(" ");
        int taskNumber = Integer.parseInt(text.substring(num + 1, num + 2));
        tasking.printRemove();
        Ui.printDelete(taskNumber);
        listOfTasks.remove(taskNumber - 1);
        store.writeToFile(Storage.file, "");
        for (Task task : listOfTasks) {
            store.addToFile(Storage.file, task.toString());
        }
        tasking.printNumOfTasks();
    }

    /**
     * Deletes everything off the task list.
     *
     * @throws IOException If the named file exists
     * but is a directory rather than a regular file,
     * does not exist but cannot be created,
     * or cannot be opened for any other reason.
     */
    public void deleteAllCommand(String text) throws IOException {
        store.writeToFile(Storage.file, "");
        listOfTasks.clear();
        Ui.printLine();
        Ui.printIndent();
        System.out.println("Everything in your list has been removed! " +
                "Add more tasks to get started again!!!");
        Ui.printLine();
    }

    /**
     * Adds todo task into arraylist and into file.
     *
     * @param text Takes in todo command with task.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void toDoCommand(String text) throws IOException {
        tasking.printGI();
        Ui.printIndent();
        int num = text.indexOf(" ");
        Task task = new Todo(text.substring(num + 1));
        System.out.println("  " + task.toString());
        listOfTasks.add(task);
        store.addToFile(Storage.file, task.toString());
        tasking.printNumOfTasks();
    }

    /**
     * Event command with event and date at which it is and adds
     * it into the arraylist and file.
     *
     * @param text Takes in a event command with the task and date.
     * @throws DukeException If the user inputs more than the normal
     * number of days/months/time, this error will be thrown out instead.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void eventCommand(String text) throws DukeException, IOException {
        int num = text.indexOf("/");
        int num1 = text.indexOf(" ");
        int dayDate = Integer.parseInt(text.substring(num + 4, num + 6));
        int monthDate = Integer.parseInt(text.substring(num + 7, num + 9));
        int timeHour = Integer.parseInt(text.substring(num + 15, num + 17));
        int timeMin = Integer.parseInt(text.substring(num + 17));
        if (dayDate > 0 && dayDate <= 31 && monthDate > 0 && monthDate <= 12 &&
                timeHour > 0 && timeHour <= 24 && timeMin >= 0 && timeMin <= 59 ) {
            Task task = new Event(text.substring(num1, num - 1),
                    text.substring(num + 4));
            String taskers = task.toString();
            if (!taskers.equals("Invalid date format!")) {
                tasking.printGI();
                Ui.printIndent();
                System.out.println("  " + taskers);
                listOfTasks.add(task);
                store.addToFile(Storage.file, taskers);
                tasking.printNumOfTasks();
            } else {
                Ui.printIndent();
                throw new DukeException(taskers);
            }
        } else {
            Ui.printLine();
            Ui.printIndent();
            throw new DukeException("Hmmm?? I'm sorry but there are at most 31 days in a month! " +
                    "And remember, no negative dates allowed as well!!! :)");
        }
    }


    /**
     * Deadline command with deadline task and date at which it is and adds
     * it into the arraylist and file.
     *
     * @param text Takes in a deadline command with the task and date.
     * @throws DukeException If the user inputs more than the normal
     * number of days/months/time, this error will be thrown out instead.
     * @throws IOException If the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason.
     */
    public void deadlineCommand(String text) throws DukeException, IOException {
        int num = text.indexOf("/");
        int num1 = text.indexOf(" ");
        int dayDate = Integer.parseInt(text.substring(num + 4, num + 6));
        int monthDate = Integer.parseInt(text.substring(num + 7, num + 9));
        int timeHour = Integer.parseInt(text.substring(num + 15, num + 17));
        int timeMin = Integer.parseInt(text.substring(num + 17));
        if (dayDate > 0 && dayDate <= 31 && monthDate > 0 && monthDate <= 12 &&
                timeHour > 0 && timeHour <= 24 && timeMin >= 0 && timeMin <= 59 ) {
            Task task = new Deadline(text.substring(num1, num - 1),
                    text.substring(num + 4));
            String taskers = task.toString();
            if (!taskers.equals("Invalid date format!")) {
                tasking.printGI();
                Ui.printIndent();
                System.out.println("  " + taskers);
                listOfTasks.add(task);
                store.addToFile(Storage.file, taskers);
                tasking.printNumOfTasks();
            } else {
                Ui.printIndent();
                throw new DukeException(taskers);
            }
        } else {
            Ui.printLine();
            Ui.printIndent();
            throw new DukeException("Hmmm?? I'm sorry but there are at most 31 days in a month! " +
                    "And remember, no negative dates allowed as well!!! :)");
        }
    }
}
