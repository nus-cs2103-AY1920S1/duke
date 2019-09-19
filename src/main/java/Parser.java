import java.time.LocalDateTime;
import java.util.LinkedList;

public class Parser {
    /**
     * Converts a string into a LocalDateTime that is understood by the computer.
     *
     * @param rawTimestamp time and date in string format.
     * @return LocalDateTime with desired format.
     * @throws DukeException if there is logic error or bad input.
     */
    public static LocalDateTime convertDateAndTime(String rawTimestamp) throws DukeException {
        String[] dateTime = rawTimestamp.split(" ");
        String[] datePortion = dateTime[0].split("/");
        String timePortion = dateTime[1];

        if (dateTime.length == 2) {
            int date = Integer.parseInt(datePortion[0]);
            int month = Integer.parseInt(datePortion[1]);
            int year = Integer.parseInt(datePortion[2]);
            int hour = Integer.parseInt(timePortion.substring(0, 2));
            int minute = Integer.parseInt(timePortion) % 100;

            return LocalDateTime.of(year, month, date, hour, minute);
        } else {
            throw new DukeException("Date time format invalid");
        }
    }

    /**
     * To deals with making sense of the user command.
     *
     * @param tasks the TaskList from the txt file.
     * @param ui the message for interaction.
     * @param inputText the text that user input.
     * @param storage the location for retrieval and write the updated file.
     */
    public static String parse(TaskList tasks, Ui ui, String inputText, Storage storage) {
        String[] keyList = inputText.split(" ", 2);
        String actionKey = keyList[0];

        try {
            if (inputText.equals("list")) { // to print all the list of plans
                return ui.printList(tasks);
            } else if (actionKey.equals("done")) { // mark as done if the plan is finished
                LinkedList<Task> totalTasks = tasks.getListOfTasks();
                int index = Integer.parseInt(inputText.split(" ")[1]);
                checkForValidDone(totalTasks, index);

                Task selectedTask = totalTasks.get(index - 1);
                if (selectedTask.getIsDone()) {
                    throw new DukeException("Task " + index + " is already marked as done");
                } else {
                    selectedTask.markAsDone();
                }

                assert selectedTask.getStatusIcon().equals("X") : "Task should be marked as done";

                saveFile(storage, tasks);
                return ui.printTaskDone(selectedTask);
            } else if (actionKey.equals("delete")) { // delete a specific plan
                LinkedList<Task> totalTasks = tasks.getListOfTasks();
                int index = Integer.parseInt(keyList[1]);
                checkForValidDeletion(totalTasks, index);
                String outputText = ui.printTaskDelete(totalTasks, index);
                tasks.deleteTask(index - 1);
                saveFile(storage, tasks);
                return outputText;
            } else if (actionKey.equals("find")) {
                String textToSearch = keyList[1];
                return ui.searchTaskKeyword(tasks, textToSearch);
            } else { // to handle addition of a specific type of plan
                if (actionKey.equals("deadline")) {
                    String[] deadlineList = deadlineHandler(keyList);
                    LocalDateTime convertedTimeStamp = convertDateAndTime(deadlineList[1]);
                    Deadline newDeadline = new Deadline(deadlineList[0], convertedTimeStamp);

                    tasks.addTask(newDeadline);
                    saveFile(storage, tasks);
                    return ui.printAddTask(tasks, newDeadline);
                } else if (actionKey.equals("event")) {
                    String[] eventList = eventHandler(keyList);
                    LocalDateTime convertedTimeStamp = convertDateAndTime(eventList[1]);
                    Event newEvent = new Event(eventList[0], convertedTimeStamp);

                    tasks.addTask(newEvent);
                    saveFile(storage, tasks);
                    return ui.printAddTask(tasks, newEvent);
                } else if (actionKey.equals("todo")) {
                    if (keyList.length <= 1) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }

                    String description = inputText.split(" ", 2)[1];
                    Todo newTodo = new Todo(description);
                    tasks.addTask(newTodo);
                    saveFile(storage, tasks);
                    return ui.printAddTask(tasks, newTodo);
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means  :(");
                }
            }
        } catch (DukeException err) {
            return err.getMessage();
        } catch (NumberFormatException err) {
            return "Invalid string on the action, should be integer";
        } catch (Exception err) {
            return "[Exception]\n" + err;
        }
    }

    private static String[] deadlineHandler(String[] keyList) throws DukeException {
        if (keyList.length <= 1) {
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }

        String[] contentList = keyList[1].split(" /by ");
        if (contentList.length <= 1) {
            throw new DukeException("OOPS!!! Time need to be specified");
        }

        return contentList;
    }

    private static String[] eventHandler(String[] keyList) throws DukeException {
        if (keyList.length <= 1) {
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }

        String[] contentList = keyList[1].split(" /at ");
        if (contentList.length == 1) {
            throw new DukeException("OOPS!!! Time need to be specified");
        }

        return contentList;
    }

    private static void saveFile(Storage storage, TaskList tasks) {
        storage.writeFile(tasks.getListOfTasks());
    }

    private static void checkForValidDeletion(LinkedList<Task> totalTasks, int index) throws DukeException {
        if (totalTasks.size() == 0) {
            throw new DukeException("You cannot delete something from an empty list");
        } else if (index < 1) {
            throw new DukeException("Index cannot be negative or zero. Maximum index is " + totalTasks.size());
        } else if (index > totalTasks.size()) {
            throw new DukeException("Number is not in the list of tasks. Maximum index is " + totalTasks.size());
        }
    }

    private static void checkForValidDone(LinkedList<Task> totalTasks, int index) throws DukeException {
        if (totalTasks.size() == 0) {
            throw new DukeException("You cannot done something from an empty list");
        } else if (index < 1) {
            throw new DukeException("Index cannot be negative or zero. Maximum index is " + totalTasks.size());
        } else if (index > totalTasks.size()) {
            throw new DukeException("Number is not in the list of tasks. Maximum index is " + totalTasks.size());
        }
    }
}
