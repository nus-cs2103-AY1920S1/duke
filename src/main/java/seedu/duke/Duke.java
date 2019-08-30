package seedu.duke;

import seedu.duke.parser.Parser;
import seedu.duke.storage.Storage;
import seedu.duke.task.Deadline;
import seedu.duke.task.Event;
import seedu.duke.task.Task;
import seedu.duke.task.Todo;
import seedu.duke.tasklist.TaskList;
import seedu.duke.ui.Ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Project Duke is a Personal Assistant Chatbot
 * that helps a person to keep track of various things.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Instantiates a Duke object.
     *
     * @param filePath absolute filepath of the where the text file is stored. Eg "C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt".
     */
    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    /**
     * Executes the Duke Command Line Interface
     */
    public void run(){
        // Variable initialization. description will hold the description of all tasks.
        // extraDescription will hold either the
        // dateTime attribute of Deadline class or location attribute of Event class
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

        ui.showWelcome();

        try {

            // Loads the data from txt file to the TaskList object, tasks
            tasks = new TaskList(this.storage.load());

        } catch (FileNotFoundException e){

            System.out.println(e.getMessage());

        }

        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine().trim();

        while ( true ){

            taskType = Parser.parseCommand(fullCommand);

            try {

                if (taskType.equals(possibleTasks.LIST.toString().toLowerCase())) {
                    // LIST command

                     ui.printList(tasks);

                } else if (taskType.equals(possibleTasks.DONE.toString().toLowerCase())) {
                    // DONE command

                    taskNum = Parser.getFinishedTaskNum(fullCommand);

                    // taskList index (starts from 0) differs from taskNum (starts from 1) by 1
                    taskNum--;

                    tasks.getTask(taskNum).setDone();

                    ui.printDoneSequence(tasks, taskNum);

                } else if (taskType.equals(possibleTasks.TODO.toString().toLowerCase())) {
                    // TODO command

                    if (fullCommand.length() < 5){
                        // fullCommand contains only the string "todo"
                        throw new DukeException ("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    description = Parser.getTodoDescription(fullCommand);

                    Todo newTodo = new Todo(description);

                    tasks.addTask(newTodo);

                    ui.printTodoSequence(tasks, newTodo);

                } else if (taskType.equals(possibleTasks.DEADLINE.toString().toLowerCase())) {
                    // DEADLINE command

                    if ( ( fullCommand.length() < 9 )){

                        // fullCommand contains only the string "deadline"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

                    } else if ( (fullCommand.lastIndexOf('/') < 1) || (  4+fullCommand.lastIndexOf('/') > fullCommand.length()   ) )  {

                        // fullCommand does not contain '/' chars or there are no char after "/by"
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

                    }
                    
                    description = Parser.getDeadlineDescription(fullCommand);
                    extraDescription = Parser.getDeadlineDateTime(fullCommand);

                    Deadline newDeadline = new Deadline(description, extraDescription);

                    tasks.addTask(newDeadline);

                    ui.printDeadlineSequence(tasks, newDeadline);

                } else if (taskType.equals(possibleTasks.EVENT.toString().toLowerCase())) {
                    // EVENT command

                    if ( ( fullCommand.length() < 6 )){ // Input is only "event"

                        // fullCommand contains only the string "event
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");

                    } else if ( (fullCommand.lastIndexOf('/') < 1) || (  4+fullCommand.lastIndexOf('/') > fullCommand.length()   ) )  {

                        // fullCommand does not contain '/' char or there are no chars after "/at"
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");

                    }

                    description = Parser.getEventDescription(fullCommand);
                    extraDescription = Parser.getEventLocation(fullCommand);

                    Event newEvent = new Event(description, extraDescription);

                    tasks.addTask(newEvent);

                    ui.printEventSequence(tasks, newEvent);

                } else if (taskType.equals(possibleTasks.DELETE.toString().toLowerCase())){
                    // DELETE command

                    taskNum = Parser.getDeletedTaskNum(fullCommand);

                    // taskList index (starts from 0) differs from taskNum (starts from 1) by 1
                    taskNum--;

                    if (taskNum >= tasks.getSize()){

                        // taskNum does not exist
                        throw new DukeException("Task no. " + (taskNum+1) + " does not exist");

                    }

                    Task taskToDelete = tasks.getTask(taskNum);

                    tasks.deleteTask(taskNum);

                    ui.printDeleteSequence(tasks, taskToDelete);
                    taskToDelete = null;

                } else if (taskType.equals(possibleTasks.BYE.toString().toLowerCase())){
                    // BYE command

                    ui.printByeSequence();

                    storage.clearFileBeforeSaving();
                    // Clear the txt file and adds headers

                    // Saves the task list to the file, following the pre-defined format
                    for (int i = 0; i < tasks.getSize(); i++){
                        storage.writeToFile(tasks.getTask(i).toSaveString());
                    }

                    break;
                    // Exits while loop once "bye" is entered

                } else {
                    // An unrecognizable command is detected
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e){

                ui.showLoadingError(e);

            } catch (StringIndexOutOfBoundsException e){

                System.out.println(e.getMessage());

            } catch (NumberFormatException e){

                System.out.println(e.getMessage());

            } catch (IOException e){

                System.out.println(e.getCause());

            }

            fullCommand = in.nextLine().trim();
            output = "";
        }

    }

    /**
     * Main methid to run Duke.
     * @param args
     */
    public static void main(String[] args) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }

    /**
     * possibleTasks is an enumeration of the constant, pre-defined, recognizable commands.
     */
    enum possibleTasks{
        BYE,
        LIST,
        DONE,
        DELETE,
        EVENT,
        TODO,
        DEADLINE
    }

}



