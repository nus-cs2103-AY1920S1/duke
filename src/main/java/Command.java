import java.io.IOException;

public class Command {

    /**
     * Runs the Instructions that have been entered by the user
     * It will process the String inputInstructions, inputCommand that the user
     * have entered and do the specific instructions using the storage and currentTaskList
     * object that have been created/passed down.
     *
     * @param inputInstruction a String that describes the task
     * @param currentUi input a Ui object to handle I/O
     * @param storage a storage object that will be used during the program
     * @param currentTaskList a taskList object that will be used to store the different task objects
     *
     * @return returns a String reply for Duke to display
     * @throws IOException throws exception if user input is invalid
     */
    public static String executeInstructions(String inputInstruction, Storage storage,
                                             TaskList currentTaskList, Ui currentUi) throws IOException {
        String inputCommand = Parser.getInputCommand(inputInstruction);
        if (inputCommand.equals("list")) {
            return currentUi.printList(currentTaskList);
        } else if (inputCommand.equals("done")) {
            int taskNum = Parser.getTaskNum(inputInstruction);
            try {
                if (currentTaskList.getNoOfTask() < taskNum || taskNum <= 0) {
                    throw new DukeException("index");
                }
                Task currentTask = currentTaskList.getTask(taskNum - 1);
                currentTask.markAsDone();
                storage.updateTaskToFile(currentTaskList.getEntireList());
                return currentUi.printDone(currentTask);
            } catch (Exception e) {
                return currentUi.printException(e);
            }
        } else if (inputCommand.equals("todo")) {
            try {
                if (inputInstruction.length() == 4 || inputInstruction.length() == 5) {
                    throw new DukeException("todo");
                }
                String subInput = inputInstruction.substring(5);
                Task newTask = new Todo(subInput);
                currentTaskList.addTask(newTask);
                storage.writeToFile(newTask + "\n");
                return currentUi.printTodo(newTask, currentTaskList);
            } catch (Exception e) {
                return currentUi.printException(e);
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
                storage.writeToFile(newTask + "\n");
                return currentUi.printDeadline(newTask, currentTaskList);
            } catch (Exception e) {
                return currentUi.printException(e);
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
                storage.writeToFile(newTask + "\n");
                return currentUi.printEvent(newTask, currentTaskList);
            } catch (Exception e) {
                return currentUi.printException(e);
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
                String outputString = currentUi.printDelete(taskNum, currentTaskList);
                storage.updateTaskToFile(currentTaskList.getEntireList());
                return outputString;
            } catch (Exception e) {
                return currentUi.printException(e);
            }
        } else if (inputCommand.equals("find")) {
            try {
                return currentUi.printFind(inputInstruction, currentTaskList);
            } catch (Exception e) {
                return currentUi.printException(e);
            }
        } else if (inputCommand.equals("bye")) {
            return currentUi.printBye();
        } else {
            return currentUi.printException(new DukeException("invalid"));
        }
    }
}
