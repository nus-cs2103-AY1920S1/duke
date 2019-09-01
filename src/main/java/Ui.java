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

    /**
     * Runs the start of the Ui, by introducing Duke.
     *
     * @return a String format welcome message to display
     */
    public String initiate() {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        String outputContent = buffer.toString();
        buffer.reset();
        return outputContent;
    }

    /**
     * Runs the Instructions that have been entered by the user
     * It will process the String inputInstructions, inputCommand that the user
     * have entered and do the specific instructions using the storage and currentTaskList
     * object that have been created/passed down.
     *
     * @param inputInstruction a String that describes the task
     * @param inputCommand a String command (Deadline, Event, List etc) that helps the program process the instruction
     * @param storage a storage object that will be used during the program
     * @param currentTaskList a taskList object that will be used to store the different task objects
     *
     * @return returns a String reply for Duke to display
     * @throws IOException throws exception if user input is invalid
     */
    public String executeInstructions(String inputInstruction, String inputCommand,
                                    Storage storage, TaskList currentTaskList) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        String outputContent;
        if (inputCommand.equals("list")) {
            System.setOut(new PrintStream(buffer));
            System.out.println("__________________________________");
            System.out.println("Here are the tasks in your list:");
            currentTaskList.listTask();
            System.out.println("___________________________________");
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            outputContent = buffer.toString();
            buffer.reset();
            return outputContent;
        } else if (inputCommand.equals("done")) {
            int taskNum = Parser.getTaskNum(inputInstruction);
            Task currentTask = currentTaskList.getTask(taskNum - 1);
            currentTask.markAsDone();
            System.setOut(new PrintStream(buffer));
            System.out.println("___________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(currentTask);
            System.out.println("___________________________________");
            storage.updateTaskToFile(currentTaskList.getEntireList());
            System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
            outputContent = buffer.toString();
            buffer.reset();
            return outputContent;
        } else if (inputCommand.equals("todo")) {
            try {
                if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                    throw new DukeException("todo");
                }
                String subInput = inputInstruction.substring(5);
                Task newTask = new Todo(subInput);
                currentTaskList.addTask(newTask);
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        } else if (inputCommand.equals("deadline")) {
            try {
                if (!inputInstruction.contains("/by") || inputInstruction.length() == 8
                        || inputInstruction.length() == 9) {
                    throw new DukeException("deadline");
                }
                String subInput1 = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/by") + 4);
                Task newTask = new Deadline(subInput1, subInput2);
                currentTaskList.addTask(newTask);
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        } else if (inputCommand.equals("event")) {
            try {
                if (!inputInstruction.contains("/at") || inputInstruction.length() == 5
                        || inputInstruction.length() == 6) {
                    throw new DukeException("event");
                }
                String subInput1 = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/at") + 4);
                Task newTask = new Event(subInput1, subInput2);
                currentTaskList.addTask(newTask);
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        } else if (inputCommand.equals("delete")) {
            try {
                if (inputInstruction.length() == 6 || inputInstruction.length() == 7) {
                    throw new DukeException("delete");
                }
                int taskNum = Parser.getTaskNum(inputInstruction);
                if (taskNum > currentTaskList.getNoOfTask()) {
                    throw new DukeException("index");
                }
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________\n" + "Noted. I've removed this task:");
                Task currentTask = currentTaskList.getTask(taskNum - 1);
                currentTaskList.removeTask(taskNum - 1);
                System.out.println(currentTask);
                System.out.println("Now you have " + currentTaskList.getNoOfTask() + " tasks in the list.");
                System.out.println("___________________________________");
                storage.updateTaskToFile(currentTaskList.getEntireList());
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        } else if (inputCommand.equals("find")) {
            try {
                System.setOut(new PrintStream(buffer));
                System.out.println("__________________________________");
                System.out.println("Here are the tasks in your list:");
                String subInput = inputInstruction.substring(5);
                currentTaskList.findTask(subInput);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        } else {
            try {
                throw new DukeException("invalid");
            } catch (Exception e) {
                System.setOut(new PrintStream(buffer));
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
                System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
                outputContent = buffer.toString();
                buffer.reset();
                return outputContent;
            }
        }
    }
}
