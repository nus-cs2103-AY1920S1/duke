package task;

import java.util.Scanner;

public class Parser {

    /**
     * Parse the inputs from the user.
     * 
     * @param taskList Current TaskList that contains an ArrayList of tasks and
     *                 number of tasks.
     * @return New TaskList that contains an ArrayList of tasks and number of tasks.
     */
    public TaskList parse(TaskList taskList) {
        Scanner sc = new Scanner(System.in);

        String textInput;
        if (sc.hasNext()) {
            textInput = sc.nextLine();
        } else {
            textInput = "bye";
        }

        while (!textInput.equals("bye")) {
            try {
                if (textInput.equals("list")) {
                    Ui.printList(taskList);
                } else if (textInput.startsWith("done")) {
                    if (textInput.equals("done") || textInput.equals("done ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }

                    int completedIndex = Integer.parseInt(textInput.replaceFirst("done ", "")) - 1;
                    if (completedIndex < 0 || completedIndex >= taskList.getCounter()) {
                        throw new DukeException("OOPS!!! Index not found.");
                    }

                    taskList = taskList.doneTask(completedIndex);
                } else if (textInput.startsWith("todo")) {
                    if (textInput.equals("todo") || textInput.equals("todo ")) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String description = textInput.replaceFirst("todo ", "");
                    taskList = taskList.addTask(new Todo(description));
                } else if (textInput.startsWith("deadline")) {
                    if (textInput.equals("deadline") || textInput.equals("deadline ")) {
                        throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String removeTaskWord = textInput.replaceFirst("deadline ", "");
                    String[] taskSplit = removeTaskWord.split(" /by ");
                    taskList = taskList.addTask(new Deadline(taskSplit[0], taskSplit[1]));
                } else if (textInput.startsWith("event")) {
                    if (textInput.equals("event") || textInput.equals("event ")) {
                        throw new DukeException("OOPS!!! The description of a event cannot be empty.");
                    }

                    String removeTaskWord = textInput.replaceFirst("event ", "");
                    String[] taskSplit = removeTaskWord.split(" /at ");
                    taskList = taskList.addTask(new Event(taskSplit[0], taskSplit[1]));
                } else if (textInput.startsWith("delete")) {
                    if (textInput.equals("delete") || textInput.equals("delete ")) {
                        throw new DukeException("OOPS!!! Index required.");
                    }

                    int deletedIndex = Integer.parseInt(textInput.replaceFirst("delete ", "")) - 1;
                    if (deletedIndex < 0 || deletedIndex >= taskList.getCounter()) {
                        throw new DukeException("OOPS!!! Index not found.");
                    }

                    taskList = taskList.deleteTask(deletedIndex);
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                Ui.printException(e);
            } finally {
                textInput = sc.nextLine();
            }
        }

        // Close the scanner
        sc.close();

        return taskList;
    }
}