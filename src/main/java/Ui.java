import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;

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
     * @return a String format welcome message to display
     */
    public String initiate() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        Parser.printLine();
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        Parser.printLine();
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

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

    public String printBye(){
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
