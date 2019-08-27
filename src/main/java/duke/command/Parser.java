package duke.command;

import duke.exception.DukeIllegalActionException;
import duke.exception.DukeIllegalDescriptionException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.LinkedList;

/**
 * Makes sense of user's input and react in accordance.
 */
class Parser {

    /**
     * @param act     keyword indicating the intended operation from user input
     * @param storage storage object to interact with
     * @throws FileNotFoundException
     * @throws DukeIllegalDescriptionException
     * @throws DukeIllegalActionException
     */
    static void parse(String act, Storage storage) throws FileNotFoundException,
            DukeIllegalDescriptionException, DukeIllegalActionException {
        try {
            switch (Action.valueOf(act)) {
            case list:
                System.out.println("Here are the tasks in your list:\n");
                for (int i = 0; i < TaskList.taskList.size(); i++) {
                    System.out.println(1 + i + "." + TaskList.taskList.get(i).toString());
                }
                break;
            case bye:
                System.out.println("Bye. Hope to see you again soon!");
                Ui.sc.close();
                break;
            case done:
                int n = Ui.sc.nextInt();
                TaskList.taskList.get(n - 1).setDone();
                System.out.println("Nice! I've marked this task as done:\n" + TaskList.taskList.get(n - 1).toString());
                storage.saveData();
                break;
            case todo:
                String tdDescription = Ui.sc.nextLine();
                if (tdDescription.isBlank()) {
                    throw new DukeIllegalDescriptionException(act);
                } else {
                    Task todo = new ToDo(tdDescription);
                    TaskList.taskList.add(todo);
                    System.out.println("Got it. I've added this task: \n" + todo.toString()
                            + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                    storage.saveData();
                }
                break;
            case deadline:
                String dlDetail = Ui.sc.nextLine();
                int dlDivision = dlDetail.indexOf("/");
                try {
                    String dlDescription = dlDetail.substring(0, dlDivision - 1);

                    String by = dlDetail.substring(dlDivision + 3);
                    Task dl = new Deadline(dlDescription, by);
                    TaskList.taskList.add(dl);
                    System.out.println("Got it. I've added this task: \n" + dl.toString()
                            + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                    storage.saveData();
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeIllegalDescriptionException(act);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case event:
                String eventDetail = Ui.sc.nextLine();
                int eventDivision = eventDetail.indexOf("/");
                try {
                    String eventDescription = eventDetail.substring(0, eventDivision - 1);
                    String at = eventDetail.substring(eventDivision + 3);

                    Task event = new Event(eventDescription, at);
                    TaskList.taskList.add(event);
                    System.out.println("Got it. I've added this task: \n" + event.toString()
                            + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                    storage.saveData();
                } catch (StringIndexOutOfBoundsException e) {
                    throw new DukeIllegalDescriptionException(act);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case delete:
                int d = Ui.sc.nextInt() - 1;
                Task temp = TaskList.taskList.get(d);
                TaskList.taskList.remove(d);
                System.out.println("Noted. I've removed this task: \n" + temp.toString()
                        + "\nNow you have " + (TaskList.taskList.size()) + " tasks in the list.");
                storage.saveData();
                break;
            case find:
                String keyword = Ui.sc.nextLine();
                boolean isFound = false;
                LinkedList<Task> foundList = new LinkedList<>();
                for (Task task : TaskList.taskList) {
                    if (task.toString().contains(keyword)) {
                        foundList.add(task);
                        isFound = true;
                    }
                }
                if (isFound) {
                    System.out.println("Here are the matching tasks in your list:\n");
                    for (int i = 0; i < foundList.size(); i++) {
                        System.out.println(1 + i + "." + foundList.get(i).toString());
                    }
                } else {
                    System.out.println("Keyword not found ;_;");
                }
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeIllegalActionException();
        }
    }
}

/**
 * Predefined commands.
 */
enum Action {
    list, bye, done, todo, deadline, event, delete, find
}
