import java.io.IOException;

/**
 * Represents an Ui class which handles all the display and I/O
 * that the user will enter. Ui class interacts with the user
 * and helps to call the other necessary classes to achieve the
 * user's input instructions.
 */

public class Ui {

    public void initiate() {
        System.out.println("___________________________________");
        System.out.println("Hello! I'm Duke\nWhat can i do for you?");
        System.out.println("___________________________________");
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
     * @throws IOException
     */
    public void executeInstructions(String inputInstruction, String inputCommand, Storage storage, TaskList currentTaskList) throws IOException {
        if (inputCommand.equals("list")) {
            System.out.println("__________________________________");
            System.out.println("Here are the tasks in your list:");
            currentTaskList.listTask();
            System.out.println("___________________________________");
        } else if (inputCommand.equals("done")) {
            int taskNum = Parser.getTaskNum(inputInstruction);
            Task currentTask = currentTaskList.getTask(taskNum - 1);
            currentTask.markAsDone();
            System.out.println("___________________________________");
            System.out.println("Nice! I've marked this task as done: ");
            System.out.println(currentTask);
            System.out.println("___________________________________");
            storage.updateTaskToFile(currentTaskList.getEntireList());
        } else if (inputCommand.equals("todo")) {
            try {
                if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                    throw new DukeException("todo");
                }
                String subInput = inputInstruction.substring(5);
                Task newTask = new Todo(subInput);
                currentTaskList.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("deadline")) {
            try {
                if (!inputInstruction.contains("/by") || inputInstruction.length() == 8 || inputInstruction.length() == 9) {
                    throw new DukeException("deadline");
                }
                String subInput1 = inputInstruction.substring(9, inputInstruction.lastIndexOf("/by") - 1);
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/by") + 4);
                Task newTask = new Deadline(subInput1, subInput2);
                currentTaskList.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("event")) {
            try {
                if (!inputInstruction.contains("/at") || inputInstruction.length() == 5 || inputInstruction.length() == 6) {
                    throw new DukeException("event");
                }
                String subInput1 = inputInstruction.substring(6, inputInstruction.lastIndexOf("/at"));
                String subInput2 = inputInstruction.substring(inputInstruction.lastIndexOf("/at") + 4);
                Task newTask = new Event(subInput1, subInput2);
                currentTaskList.addTask(newTask);
                System.out.println("___________________________________");
                System.out.println("Got it. I've added this task:\n" + newTask);
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.writeToFile(newTask + "\n");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else if (inputCommand.equals("delete")) {
            try {
                if (inputInstruction.length() == 6 || inputInstruction.length() == 7) {
                    throw new DukeException("delete");
                }
                int taskNum = Parser.getTaskNum(inputInstruction);
                if (taskNum > currentTaskList.noOfTask()) {
                    throw new DukeException("index");
                }
                Task currentTask = currentTaskList.getTask(taskNum - 1);
                currentTaskList.removeTask(taskNum - 1);
                System.out.println("___________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println(currentTask);
                Task.total--;
                System.out.println("Now you have " + Task.total + " tasks in the list.");
                System.out.println("___________________________________");
                storage.updateTaskToFile(currentTaskList.getEntireList());
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        } else {
            try {
                throw new DukeException("invalid");
            } catch (Exception e) {
                System.out.println("___________________________________");
                System.out.println(e);
                System.out.println("___________________________________");
            }
        }
    }
}
