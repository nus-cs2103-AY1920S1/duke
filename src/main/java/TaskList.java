package seedu.duke;

import seedu.duke.Ui;
import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public ArrayList<Task> getArray() {
        return this.taskList;
    }

    public void markAsDone(String input) throws DukeException {

        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Ui.printBlock("Please input a number.");
            return;
        }

        // EXCEPTION: WHEN INDEX IS OUT OF RANGE
        if (index < 1 || index > taskList.size()) {

            String errorMessage;

            if (taskList.size() == 0) {
                throw new DukeDoneEmptyListException();
            } else {
                throw new DukeIndexOutOfBoundException(index, taskList.size());
            }
        }

        taskList.get(index - 1).markAsDone();

        String[] outputs = new String[2];
        outputs[0] = "Nice! I've marked this task as done: ";
        outputs[1] = "  " + taskList.get(index - 1).toString();

        Ui.printBlock(outputs);
    }

    public void list() {

        if (taskList.size() == 0) {
            Ui.printBlock("There is nothing to list - the todo list is empty.");
            return;
        }

        Ui.printBlock(toStringArray());
    }

    public void addTodo(String description) throws DukeException {
        Todo newTodo = new Todo(description);
        taskList.add(newTodo);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newTodo.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        PrettyPrint.printBlock(outputs);
    }

    public void addDeadline(String command) throws DukeException {

        String[] descriptions = command.split(" /by ", 2);
        if (descriptions.length == 1) {
            throw new DukeDeadlineMissingDateException();
        }

        Deadline newDeadline = new Deadline(descriptions[0], descriptions[1]);
        taskList.add(newDeadline);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newDeadline.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);

    }

    public void addEvent(String command) throws DukeException {

        String[] descriptions = command.split(" /at ", 2);
        if (descriptions.length == 1) {
            throw new DukeEventMissingDateException();
        }

        Event newEvent = new Event(descriptions[0], descriptions[1]);
        taskList.add(newEvent);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newEvent.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);

    }

    public void deleteTask(String input) throws DukeException {

        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            Ui.printBlock("Please input a number.");
            return;
        }

        // EXCEPTION: WHEN INDEX IS OUT OF RANGE
        if (index < 1 || index > taskList.size()) {

            String errorMessage;

            if (taskList.size() == 0) {
                throw new DukeDoneEmptyListException();
            } else {
                throw new DukeIndexOutOfBoundException(index, taskList.size());
            }
        }

        Task oldTask = taskList.get(index - 1);
        taskList.remove(index - 1);

        String[] outputs = new String[3];
        outputs[0] = "Noted. I've removed this task:";
        outputs[1] = "  " + oldTask.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", taskList.size());

        Ui.printBlock(outputs);

    }

    // Converts taskList into a String Array
    private String[] toStringArray() {
        String[] stringArray = new String[taskList.size()];
        for (int z = 0; z < taskList.size(); z++) {
            stringArray[z] = String.format("%3d." + taskList.get(z).toString(), z + 1);
        }
        return stringArray;
    }


}
