import java.io.IOException;

import duke.exception.DukeException;

public class Parser {

    private Duke parent;
    private TaskList taskList;

    public Parser(Duke parent, TaskList taskList) {
        this.parent = parent;
        this.taskList = taskList;
    }

    public void evaluateInput(String input) throws DukeException, IOException {
        if (input.equalsIgnoreCase("bye")) {
            parent.print("Bye. Have a nice day!");
        } else if (input.equalsIgnoreCase("list")) {
            parent.printTasks();
        } else if (input.toLowerCase().startsWith("done")) {
            evaluateDone(input);
        } else if (input.toLowerCase().startsWith("todo")
                || input.toLowerCase().startsWith("deadline")
                || input.toLowerCase().startsWith("event")) {
            taskList.addTask(input);
        } else if (input.toLowerCase().startsWith("delete")) {
            taskList.deleteTask(input);
        } else {
            throw new DukeException("OOPS!!! I don't know what this is :(");
        }
    }

    public void evaluateDone(String input) throws DukeException {
        String number = input.substring(4, input.length()).strip();
        if (number.isEmpty()) {
            parent.print("Invalid input! Mention a valid task number.");
        } else {
            try {
                int taskNumber = Integer.parseInt(number);
                if (taskNumber > taskList.size()) {
                    parent.print("Task doesn't exist.");
                } else {
                    taskList.get(taskNumber - 1).markAsDone();
                    String output = "Nice! I've marked this task as done:"
                            + "\n  "
                            + taskList.get(taskNumber - 1).toString();
                    parent.print(output);
                }
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new DukeException("OOPS!!! The Done command must be followed by a valid task ID.");
            }
        }
    }
}
