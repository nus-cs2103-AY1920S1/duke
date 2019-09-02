package duke.lib.datahandling;

import duke.lib.TaskList;
import duke.lib.UI;
import duke.lib.common.DukeException;
import duke.lib.common.Time;
import duke.lib.task.Deadline;
import duke.lib.task.Event;
import duke.lib.task.Task;
import duke.lib.task.ToDo;

import java.util.ArrayList;

/**
 * Parser class handles all parsing of commands and execution.
 */
public class Parser {
    private DataStorage storage;
    private TaskList taskList;
    private UI ui;
    private boolean isExit;

    /**
     * Public constructor of Parser to be used to parse and execute.
     *
     * @param taskList Tasklist used by the duke program
     * @param storage Storage of where the save file is located
     * @param ui a ui to display the output from the commands
     */
    public Parser(TaskList taskList, DataStorage storage, UI ui) {
        this.storage = storage;
        this.taskList = taskList;
        this.ui = ui;
        this.isExit = false;
    }

    /**
     * Parse the command given which will then be executed.
     *
     * @param fullCommand the command input given by the user
     * @throws DukeException multiple cases of wrong input
     */
    public void parse(String fullCommand) throws DukeException {
        try {
            String[] words = fullCommand.split(" ", 2);
            String command;
            boolean moreThanOne;

            if (fullCommand.contains(" ")) {
                command = words[0];
                moreThanOne = true;
            } else {
                command = fullCommand;
                moreThanOne = false;
            }

            Task temp;

            switch (command) {
            case "find":
                if (!moreThanOne) {
                    throw new DukeException("Sorry, you need to input something to find what you're looking for.");
                }
                ui.displayList(findAllTaskWithName(words[1]), "Here are the matching tasks in your list:");
                break;
            case "bye":
                ui.display("Bye. Hope to see you again soon!");
                isExit = true;
                break;
            case "list":
                ArrayList<Task> list = taskList.getList();
                if (list.isEmpty()) {
                    throw new DukeException("Oh looks like there's nothing in your list so far.");
                }
                ui.displayList(list, "Here are the tasks in your list:");
                break;
            case "done":
                if (moreThanOne) {
                    String secondWord = words[1];
                    int index = Integer.parseInt(secondWord);
                    ui.display(taskList.markAsDone(index).toString(),
                            "Nice! I've marked this task as done:");
                } else {
                    throw new DukeException("I'm sorry, you didn't specify which index of the list you've done.");
                }
                break;
            case "delete":
                if (!moreThanOne) {
                    throw new DukeException("I'm sorry, "
                            + "you didn't specify which index of the list you want to delete.");
                }
                String secondWord = words[1];
                int index = Integer.parseInt(secondWord);
                ui.display(taskList.delete(index).toString(),
                        "Noted. I've removed this task:",
                        "Now you have " + taskList.getSize() + " tasks in the list.");
                break;
            case "todo":
                if (!moreThanOne) {
                    throw new DukeException("I'm sorry, the description of your ToDo cannot be empty.");
                }
                temp = new ToDo(words[1]);
                taskList.store(temp);
                ui.display(temp.toString(), "Got it. I've added this task:",
                        "Now you have " + taskList.getSize() + " tasks in the list.");
                break;
            case "deadline":
                if (moreThanOne) {
                    String[] spl = words[1].split(" /by ", 2);
                    if (spl.length <= 1) {
                        throw new DukeException("I'm sorry, you forgot to put a time for your deadline.");
                    }
                    String time = spl[1];
                    Time t = new Time(time);
                    temp = new Deadline(spl[0], t);
                    taskList.store(temp);
                    ui.display(temp.toString(), "Got it. I've added this task:",
                            "Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, the description of your DeadLine cannot be empty.");
                }
                break;
            case "event":
                if (moreThanOne) {
                    String[] split = words[1].split(" /at ", 2);
                    if (split.length <= 1) {
                        throw new DukeException("I'm sorry, you forgot to put a time for your event.");
                    }
                    String time = split[1];
                    Time t = new Time(time);
                    temp = new Event(split[0], t);
                    taskList.store(temp);
                    ui.display(temp.toString(), "Got it. I've added this task:",
                            "Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    throw new DukeException("I'm sorry, the description of your Event cannot be empty.");
                }
                break;
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("I'm sorry please give a number instead.");
        }
        updateStorage();
    }

    private ArrayList<Task> findAllTaskWithName(String name) throws DukeException {
        ArrayList<Task> tempTaskList = taskList.getList();
        ArrayList<Task> listWithMatchingName = new ArrayList<>();
        for (Task t : tempTaskList) {
            if (t.getName().contains(name)) {
                listWithMatchingName.add(t);
            }
        }
        if (listWithMatchingName.isEmpty()) {
            throw new DukeException("Oh looks like there's no tasks with that name in your list");
        }
        return listWithMatchingName;
    }

    private void updateStorage() throws DukeException {
        storage.write(taskList.getList());
    }

    public boolean isExit() {
        return isExit;
    }
}
