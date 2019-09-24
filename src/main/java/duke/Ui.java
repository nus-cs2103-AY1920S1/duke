package duke;

import duke.tasks.Task;
import duke.exceptions.DukeException;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * Represents an Ui class which handles all the display and I/O
 * that the user will enter. Ui class interacts with the user
 * and helps to call the other necessary classes to achieve the
 * user's input instructions.
 */

public class Ui {

    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
    String outputContent;

    /**
     * Runs the start of the Ui, by introducing Duke.
     *
     * @param status takes in a String information about Ui, Status(new or loaded)
     * @return a String format welcome message to display
     */
    public String initiate(String status) {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        if (status.equals("loaded")) {
            System.setOut(new PrintStream(buffer));
            Parser.printLine();
            System.out.println("Hello! I'm Duke\nWhat can i do for you?");
            Parser.printLine();
            System.out.println("Previous Existing - File Loaded");
            Parser.printLine();
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            String outputContent = buffer.toString();
            buffer.reset();
            return outputContent;
        } else {
            System.setOut(new PrintStream(buffer));
            Parser.printLine();
            System.out.println("Hello! I'm Duke\nWhat can i do for you?");
            Parser.printLine();
            System.out.println("No Previous Existing File Found");
            Parser.printLine();
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            String outputContent = buffer.toString();
            buffer.reset();
            return outputContent;
        }
    }

    /**
     * prints the the ouput of the entire Tasklist.
     *
     * @param currentTaskList takes in the entire TaskList
     * @return String output of the command
     */
    public String printList(TaskList currentTaskList) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Here are the tasks in your list:");
        currentTaskList.listTask();
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Done".
     *
     * @param currentTask takes in a task which is marked as done
     * @return String output of the command
     */
    public String printDone(Task currentTask) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(currentTask);
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of Exceptions.
     *
     * @param e which is the custom Exception from DukeException
     * @return String output of the command
     */
    public String printException(Exception e) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println(e);
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "To-do".
     *
     * @param newTask take in the new To-do task object
     * @param currentTaskList takes in the entire list of Task
     * @return String output of the command
     */
    public String printTodo(Task newTask, TaskList currentTaskList) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Got it. I've added this task:\n" + newTask);
        System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Deadline".
     *
     * @param newTask take in the new Deadline task object
     * @param currentTaskList takes in the entire list of Task
     * @return String output of the command
     */
    public String printDeadline(Task newTask, TaskList currentTaskList) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Got it. I've added this task:\n" + newTask);
        System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Event".
     *
     * @param newTask take in the new Event task object
     * @param currentTaskList takes in the entire list of Task
     * @return String output of the command
     */
    public String printEvent(Task newTask, TaskList currentTaskList) {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Got it. I've added this task:\n" + newTask);
        System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Delete".
     *
     * @param taskNum take in the index of the task
     * @param currentTaskList takes in the entire list of Task
     * @return String output of the command
     */
    public String printDelete(int taskNum, TaskList currentTaskList) {
        System.setOut(new PrintStream(buffer));
        System.out.println("___________________________________\n" + "Noted. I've removed this task:");
        Task currentTask = currentTaskList.getTask(taskNum - 1);
        currentTaskList.removeTask(taskNum - 1);
        System.out.println(currentTask);
        System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Find".
     *
     * @param inputInstruction string input of the user's instruction
     * @param currentTaskList takes in the entire TaskList
     * @return String output of the command
     * @throws DukeException if user inputs an invalid input
     */
    public String printFind(String inputInstruction, TaskList currentTaskList) throws DukeException {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Here are the tasks in your list:");
        String subInput = inputInstruction.substring(5);
        currentTaskList.findTask(subInput);
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * prints the output of command "Bye".
     *
     * @return String output of the command
     */
    public String printBye() {
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Bye. Hope to see you again soon!!");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

}
