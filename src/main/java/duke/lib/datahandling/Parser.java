package duke.lib.datahandling;

import duke.lib.autocorrect.SpellCheck;
import duke.lib.ui.UI;
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
    private SpellCheck spellCheck;
    private boolean isExit;

    /**
     * Public constructor of Parser to be used to parse and execute.
     *
     * @param taskList Tasklist used by the duke program
     * @param storage Storage of where the save file is located
     */
    public Parser(TaskList taskList, DataStorage storage) throws DukeException{
        this.storage = storage;
        this.taskList = taskList;
        this.ui = new UI();
        this.spellCheck = new SpellCheck();
        this.isExit = false;
    }

    /**
     * Parses the command given which will then be executed.
     *
     * @param fullCommand the command input given by the user
     * @throws DukeException multiple cases of wrong input
     */
    public String parse(String fullCommand) throws DukeException {
        try {
            updateStorage();
            assert !fullCommand.isEmpty() : "input cannot be empty";
            String[] wordsInInput = fullCommand.split(" ", 2);
            String command;
            boolean moreThanOne;

            if (fullCommand.contains(" ")) {
                command = wordsInInput[0];
                moreThanOne = true;
            } else {
                command = fullCommand;
                moreThanOne = false;
            }

            return execute(command, wordsInInput, moreThanOne);
        } catch (NumberFormatException e) {
            throw new DukeException("I'm sorry please give a number instead.");
        }
    }

    private String execute(String command, String[] words, boolean moreThanOne) throws DukeException {
        Task temp;

        switch (command) {
        case "find": {
            if (!moreThanOne) {
                throw new DukeException("Sorry, you need to input something to find what you're looking for.");
            }
            try {
                ArrayList<String> taskWithName = findAllTaskWithName(words[1]);
                String[] tasksInArray = taskWithName.toArray(new String[taskWithName.size()]);
                return ui.format("Here are all the matching tasks with that name",
                        true, tasksInArray);
            } catch (IllegalArgumentException e) {
                try {
                    return "I couldn't find what any tasks with that name, " +
                            "perhaps you meant \"" + spellCheck.suggest(words[1]) + "\" instead?";
                } catch (DukeException e2) {
                    throw new DukeException(e.getMessage());
                }
            }
        }
        case "bye": {
            isExit = true;
            return ui.format("Bye. Hope to see you again soon!", false);
        }
        case "list": {
            ArrayList<Task> list = taskList.getList();
            if (list.isEmpty()) {
                throw new DukeException("Oh looks like there's nothing in your list so far.");
            }

            return ui.convertTaskListToString("Here are the tasks in your list:", list);
        }
        case "done": {
            if (!moreThanOne) {
                throw new DukeException("I'm sorry, you didn't specify which index of the list you've done.");
            }

            String secondWord = words[1];
            int index = Integer.parseInt(secondWord);
            return ui.format("Nice! I've marked this task as done:",
                    false, taskList.markAsDone(index).toString());
        }
        case "delete": {
            if (!moreThanOne) {
                throw new DukeException("I'm sorry, "
                        + "you didn't specify which index of the list you want to delete.");
            }

            String secondWord = words[1];
            int index = Integer.parseInt(secondWord);
            return ui.format("Noted. I've removed this task:", false,
                    taskList.delete(index).toString(),
                    "Now you have " + taskList.getSize() + " tasks in the list.");
        }
        case "todo": {
            if (!moreThanOne) {
                throw new DukeException("I'm sorry, the description of your ToDo cannot be empty.");
            }

            temp = new ToDo(words[1]);
            taskList.store(temp);
            return ui.format("Got it. I've added this task:", false, temp.toString(),
                    "Now you have " + taskList.getSize() + " tasks in the list.");
        }
        case "deadline": {
            if (!moreThanOne) {
                throw new DukeException("I'm sorry, the description of your DeadLine cannot be empty.");
            }

            String[] spl = words[1].split(" /by ", 2);

            if (spl.length <= 1) {
                throw new DukeException("I'm sorry, you forgot to put a time for your deadline.");
            }

            String time = spl[1];
            Time t = new Time(time);
            temp = new Deadline(spl[0], t);
            taskList.store(temp);
            return ui.format("Got it. I've added this task:", false, temp.toString(),
                    "Now you have " + taskList.getSize() + " tasks in the list.");
        }
        case "event": {
            if (!moreThanOne) {
                throw new DukeException("I'm sorry, the description of your Event cannot be empty.");
            }

            String[] split = words[1].split(" /at ", 2);

            if (split.length <= 1) {
                throw new DukeException("I'm sorry, you forgot to put a time for your event.");
            }

            String time = split[1];
            Time t = new Time(time);
            temp = new Event(split[0], t);
            taskList.store(temp);
            return ui.format("Got it. I've added this task:", false, temp.toString(),
                    "Now you have " + taskList.getSize() + " tasks in the list.");
        }
        default:
            try {
                return "Did you mean to say \"" + spellCheck.suggest(command) + "\" instead?";
            } catch (DukeException e) {
                throw new DukeException("I'm sorry, but I don't know what that means :(");
            }
        }
    }

    private ArrayList<String> findAllTaskWithName(String name) throws IllegalArgumentException {
        ArrayList<Task> tempTaskList = taskList.getList();
        ArrayList<String> listWithMatchingName = new ArrayList<>();
        for (Task t : tempTaskList) {
            if (t.getName().contains(name)) {
                listWithMatchingName.add(t.toString());
            }
        }
        if (listWithMatchingName.isEmpty()) {
            throw new IllegalArgumentException("Oh looks like there's no tasks with that name in your list");
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
