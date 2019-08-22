package seedu.duke;

import seedu.duke.PrettyPrint;
import java.util.ArrayList;
import java.util.Scanner;

public class EventHandler {

    private ArrayList<Task> todoList;
    Scanner sc = new Scanner(System.in);

    public EventHandler() {
        this.todoList = new ArrayList<Task>();
    }

    public void run(String command) throws DukeException {
        String[] inputs = command.split(" ", 2);
        switch (inputs[0]) {
        case "bye":
            return;
        case "list":
            list();
            break;
        case "done":
            markAsDone(inputs[1]);
            break;
        case "todo":
            addTodo(inputs[1]);
            break;
        case "deadline":
            addDeadline(inputs[1]);
            break;
        case "event":
            addEvent(inputs[1]);
            break;
        default:
            throw new DukeNoSuchCommandException();
        }
    }

    private void markAsDone(String input) throws DukeException {

        int index;

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            PrettyPrint.printBlock("Please input a number.");
            return;
        }

        // EXCEPTION: WHEN INDEX IS OUT OF RANGE
        if (index < 1 || index > todoList.size()) {

            String errorMessage;

            if (todoList.size() == 0) {
                throw new DukeDoneEmptyListException();
            } else {
                throw new DukeIndexOutOfBoundException(index, todoList.size());
            }
        }

        todoList.get(index - 1).markAsDone();

        String[] outputs = new String[2];
        outputs[0] = "Nice! I've marked this task as done: ";
        outputs[1] = "  " + todoList.get(index - 1).toString();

        PrettyPrint.printBlock(outputs);
    }

    private void addTodo(String description) throws DukeException {
        Todo newTodo = new Todo(description);
        todoList.add(newTodo);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newTodo.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", todoList.size());

        PrettyPrint.printBlock(outputs);
    }

    private void list() {

        if (todoList.size() == 0) {
            PrettyPrint.printBlock("There is nothing to list - the todo list is empty.");
            return;
        }

        PrettyPrint.printBlock(toStringArray());
    }

    private void addDeadline(String command) throws DukeException {

        String[] descriptions = command.split(" /by ", 2);
        if (descriptions.length == 1) {
            throw new DukeDeadlineMissingDateException();
        }

        Deadline newDeadline = new Deadline(descriptions[0], descriptions[1]);
        todoList.add(newDeadline);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newDeadline.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", todoList.size());

        PrettyPrint.printBlock(outputs);

    }

    private void addEvent(String command) throws DukeException {

        String[] descriptions = command.split(" /at ", 2);
        if (descriptions.length == 1) {
            throw new DukeEventMissingDateException();
        }

        Event newEvent = new Event(descriptions[0], descriptions[1]);
        todoList.add(newEvent);

        String[] outputs = new String[3];
        outputs[0] = "Got it. I've added this task:";
        outputs[1] = "  " + newEvent.toString();
        outputs[2] = String.format("Now you have %d tasks in the list.", todoList.size());

        PrettyPrint.printBlock(outputs);

    }

    // Converts todoList into a String Array
    private String[] toStringArray() {
        String[] stringArray = new String[todoList.size()];
        for (int z = 0; z < todoList.size(); z++) {
            stringArray[z] = String.format("%3d." + todoList.get(z).toString(), z + 1);
        }
        return stringArray;
    }

}
