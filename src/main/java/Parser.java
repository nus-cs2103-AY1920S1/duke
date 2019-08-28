import java.time.LocalDateTime;

public class Parser {
    /**
     * Parses user input based on different type of command.
     * For each case, different parse requirement is needed
     *
     * @param taskList TaskList of the current file.
     * @param ui Ui of the project.
     * @param storage Storage of the project.
     * @param inputType InputType of the user.
     * @param userInput The rest of user input after the inputType.
     * @throws DukeException If user input is not in the format
     */
    public static void parse(TaskList taskList, Ui ui, Storage storage, String inputType, String userInput) throws DukeException {
        if (inputType.equals("todo")) {
            try {
                String description = userInput.trim();
                if (description.isBlank()) {
                    throw new IllegalArgumentException();
                }
                Task newTodo = new Todo(description, false);
                taskList.addTask(newTodo);
                ui.printAddTask(taskList, newTodo);
            } catch (IllegalArgumentException e) {
                throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: todo [task description]");
            }
        } else if (inputType.equals("list")) {
            try {
                if (!userInput.isBlank()) {
                    throw new Exception();
                }
                ui.printTaskList(taskList);
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: list");
            }
        } else if (inputType.equals("deadline")) {
            try {
                String[] statement = userInput.split("/by");
                String taskDescription = statement[0].trim();
                String taskBy = statement[1].trim();

                if (taskDescription.isBlank() || taskBy.isBlank()) {
                    throw new IllegalArgumentException();
                }

                Task newDeadline = new Deadline(taskDescription, false, dateTimeConverter(taskBy));
                taskList.addTask(newDeadline);
                ui.printAddTask(taskList, newDeadline);

            } catch (IllegalArgumentException e) {
                throw new DukeException("OOPS!!! Task description/Task by can not be empty");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: deadline [task description] /by [dd/mm/yyyy HHmm]");
            }
        } else if (inputType.equals("event")) {
            try {
                String[] statement = userInput.split("/at");
                String taskDescription = statement[0].trim();
                String taskAt = statement[1].trim();

                if (taskDescription.isBlank() || taskAt.isBlank()) {
                    throw new IllegalArgumentException();
                }


                Task newEvent = new Event(taskDescription, false, dateTimeConverter(taskAt));
                taskList.addTask(newEvent);
                ui.printAddTask(taskList, newEvent);

            } catch (IllegalArgumentException e) {
                throw new DukeException("OOPS!!! Task description/Task at can not be empty");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: event [task description] /at [dd/mm/yyyy HHmm]");
            }
        } else if (inputType.equals("done")) {
            try {
                int taskNumber = Integer.parseInt(userInput.trim()) - 1;
                taskList.getListOfTasks().get(taskNumber).markAsDone();
                ui.printDoneTask(taskList, taskNumber);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The task number you specified is not in the list.");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: done [task number]");
            }
        } else if (inputType.equals("delete")) {
            try {
                int taskNumber = Integer.parseInt(userInput.trim()) - 1;
                Task deletedTask = taskList.getListOfTasks().get(taskNumber);
                taskList.getListOfTasks().remove(taskNumber);
                ui.printDeleteTask(taskList, deletedTask);
            } catch (IndexOutOfBoundsException e) {
                throw new DukeException("OOPS!!! The task number you specified is not in the list.");
            } catch (Exception e) {
                throw new DukeException("OOPS!!! Your input format is wrong. Use: delete [task number]");
            }
        } else {
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Converts string to dateTime object in java.
     *
     * @param str String that will be converted.
     * @return Date Time object if str can be converted.
     * @throws ArrayIndexOutOfBoundsException If string can not be converted
     */
    public static LocalDateTime dateTimeConverter(String str) throws ArrayIndexOutOfBoundsException{
        String[] dateTime = str.split(" ");
        String[] date = dateTime[0].split("/");
        String time = dateTime[1];
        return LocalDateTime.of(Integer.parseInt(date[2]),
                Integer.parseInt(date[1]), Integer.parseInt(date[0]),
                        Integer.parseInt(time.substring(0, 2)), Integer.parseInt(time.substring(2)));

    }
}