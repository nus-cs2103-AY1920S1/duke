package duke;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import duke.extension.expense.Expense;
import duke.extension.expense.ExpenseList;
import duke.logic.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;


/**
 * The main class in this programme.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private ExpenseList listOfAllExpenses;
    private Ui ui;

    public Duke() {
        this("data/tasks.txt");
    }

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
            listOfAllExpenses = storage.getTempExpenseList(); //Since storage has been loaded, the tempExpenseList is stored

        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
            listOfAllExpenses = new ExpenseList();
        }
    }

    public String run(String input) {
        ui.showWelcome(); //can take this out and put it at the start of the initial programme
        Parser inputParser = new Parser(input);
        inputParser.parse();
        String command = inputParser.getCommand();
        String taskDetails = inputParser.getTaskDetails();
        try {
            if (command.equals("bye")) {
                return ui.showBye();
            } else if (command.equals("list")) {
                StringBuilder listBuilder = new StringBuilder();
                listBuilder.append(ui.showSeparationLine());
                if (tasks.getListOfTasks().isEmpty()) {
                    listBuilder.append("Sorry, there are no tasks in the list");
                    listBuilder.append(ui.showSeparationLine());
                } else {
                    listBuilder.append("     Here are the tasks in your list:\n");
                    listBuilder.append(tasks.printList());
                    listBuilder.append(ui.showSeparationLine());
                }
                return listBuilder.toString();
            } else if (command.equals("done")) {
                int taskNumber = Integer.parseInt(taskDetails);
                Task newlyDoneTask = tasks.getTask(taskNumber - 1);
                tasks.setTaskAsDone(taskNumber - 1);
                storage.write(tasks.getListOfTasks(), listOfAllExpenses.getExpenseCategories());
                return ui.showDone(newlyDoneTask);
            } else if (command.equals("delete")) {
                int taskNumber = Integer.parseInt(taskDetails);
                Task taskToBeDeleted = tasks.getTask(taskNumber - 1);
                tasks.deleteTask(taskNumber - 1);
                int newSizeOfList = tasks.getListOfTasks().size();
                storage.write(tasks.getListOfTasks(), listOfAllExpenses.getExpenseCategories());
                return ui.showDelete(taskToBeDeleted, newSizeOfList);
            } else if (command.equals("find")) {
                String keyword = taskDetails;
                return ui.showMatchingTasks(tasks.find(keyword));
            } else if (command.equals("todo") || command.equals("deadline") || command.equals("event")) {
                Task newTask;
                if (taskDetails.equals("")) {
                    throw new DukeException("      :( OOPS!!! The description of a " +
                            command + " cannot be empty.");
                }
                switch (command) {
                case "todo":
                    newTask = new Todo(taskDetails);
                    break;
                case "deadline":
                case "event":
                    //replace the first / so that the dates will not be split up
                    taskDetails = taskDetails.replaceFirst("/", ":");  //need to assign this to tempString so it is re-recorded
                    String[] tempStringArr = taskDetails.split(":");
                    String description = ((String) Array.get(tempStringArr, 0)).trim();  //to remove ending whitespace
                    String secondString = ((String) Array.get(tempStringArr, 1)).substring(3);
                    if (command.equals("deadline")) {
                        newTask = new Deadline(description, secondString);
                    } else {
                        newTask = new Event(description, secondString);
                    }
                    break;
                default:
                    throw new IllegalArgumentException("OOPS, no such event type!");  //should take this redundancy out and make the entire thing try catch
                }
                tasks.addTask(newTask);
                int newSizeOfList = tasks.getListOfTasks().size();
                storage.write(tasks.getListOfTasks(), listOfAllExpenses.getExpenseCategories());
                return ui.showAdd(newTask, newSizeOfList);
            } else if (command.equals("spending")) {
                //Format: Finance {category} {amount} {description}
                taskDetails = taskDetails.replaceFirst(" ", ":");
                taskDetails = taskDetails.replaceFirst(" ", ":");
                String[] tempStringArr = taskDetails.split(":");
                String category = ((String) Array.get(tempStringArr, 0));
                String amountInString = ((String) Array.get(tempStringArr, 1));
                String description = ((String) Array.get(tempStringArr, 2));
                Expense newExpense = new Expense(Double.parseDouble(amountInString), description);
                listOfAllExpenses.addExpense(category, newExpense);
                storage.write(tasks.getListOfTasks(), listOfAllExpenses.getExpenseCategories());
                return ui.showAddedExpense(newExpense);
            } else if (command.equals("expenses")) {
                return listOfAllExpenses.printList();
            } else if (command.equals("delexp")) {
                String[] tempStringArr = taskDetails.split(" ");
                String category = ((String) Array.get(tempStringArr, 0));
                int number = Integer.parseInt((String) Array.get(tempStringArr, 1));
                Expense deletedExpense = listOfAllExpenses.deleteExpense(category, number - 1);
                storage.write(tasks.getListOfTasks(), listOfAllExpenses.getExpenseCategories());
                return ui.showDeletedExpense(deletedExpense);
            } else if (command.equals("glossary")) {
                return ui.showGlossary();
            } else {//all other keywords not part of Duke's task handling schedule
                throw new DukeException("      OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (DukeException | IllegalArgumentException | ArrayIndexOutOfBoundsException e) {
            return ui.showSeparationLine() + e.getMessage() + ui.showSeparationLine() + ui.showBlankLine();
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        //pass the string input to duke, duke should then run this and return a string that can be passed into the label.
        return this.run(input);
    }
}



