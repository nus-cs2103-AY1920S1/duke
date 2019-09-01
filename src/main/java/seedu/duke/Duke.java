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

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
    }

    public void run(){
        // Variable initialization
        String output = ""; String taskType = ""; String description = ""; String extraDescription = ""; int taskNum = -1;

        ui.showWelcome();

        try {
            tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }

        // Creates scanner object to handle input
        Scanner in = new Scanner(System.in);
        String fullCommand = in.nextLine().trim();

        while ( true ){
            taskType = Parser.parseCommand(fullCommand); // taskType contains the first word of the command input string

            try {
                // LIST case
                if (taskType.equals(possibleTasks.LIST.toString().toLowerCase())) {

                     ui.printList(tasks);

                } else if (taskType.equals(possibleTasks.DONE.toString().toLowerCase())) {
                    // DONE case

                    taskNum = Parser.getFinishedTaskNum(fullCommand);
                    taskNum--; // ArrayList index == taskNum - 1

                    tasks.getTask(taskNum).setDone();

                    ui.printDoneSequence(tasks, taskNum);

                } else if (taskType.equals(possibleTasks.TODO.toString().toLowerCase())) {
                    // TODO case

                    if (fullCommand.length() < 5){
                        throw new DukeException ("☹ OOPS!!! The description of a todo cannot be empty.");
                    }

                    description = Parser.getTodoDescription(fullCommand);

                    Todo newTodo = new Todo(description);

                    tasks.addTask(newTodo);

                    ui.printTodoSequence(tasks, newTodo);

                    // DEADLINE case
                } else if (taskType.equals(possibleTasks.DEADLINE.toString().toLowerCase())) {

                    if ( ( fullCommand.length() < 9 )){ // Input is only "deadline"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (fullCommand.lastIndexOf('/') < 1) || (  4+fullCommand.lastIndexOf('/') > fullCommand.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = Parser.getDeadlineDescription(fullCommand);
                    extraDescription = Parser.getDeadlineDateTime(fullCommand);

                    Deadline newDeadline = new Deadline(description, extraDescription);

                    tasks.addTask(newDeadline);

                    ui.printDeadlineSequence(tasks, newDeadline);

                    // EVENT case
                } else if (taskType.equals(possibleTasks.EVENT.toString().toLowerCase())) {

                    if ( ( fullCommand.length() < 6 )){ // Input is only "event"
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    } else if ( (fullCommand.lastIndexOf('/') < 1) || (  4+fullCommand.lastIndexOf('/') > fullCommand.length()   ) )  {
                        throw new DukeException("☹ OOPS!!! The time period of an event cannot be empty.");
                    }

                    description = Parser.getEventDescription(fullCommand);
                    extraDescription = Parser.getEventLocation(fullCommand);

                    Event newEvent = new Event(description, extraDescription);

                    tasks.addTask(newEvent);

                    ui.printEventSequence(tasks, newEvent);

                    // DELETE case
                } else if (taskType.equals(possibleTasks.DELETE.toString().toLowerCase())){

                    taskNum = Parser.getDeletedTaskNum(fullCommand);
                    taskNum--; // ArrayList index == taskNum - 1

                    if (taskNum >= tasks.getSize()){
                        throw new DukeException("Task no. " + (taskNum+1) + " does not exist");
                    }

                    Task taskToDelete = tasks.getTask(taskNum);
                    tasks.deleteTask(taskNum);

                    ui.printDeleteSequence(tasks, taskToDelete);
                    taskToDelete = null;


                } else if (taskType.equals(possibleTasks.FIND.toString().toLowerCase())){

                    // Parser will parse the command and obtain the searchString.
                    // findSimilarTasks will return a TaskList containing only matching tasks.
                    TaskList similarTasks = tasks.findSimilarTasks( Parser.getFindTask(fullCommand) ) ;

                    ui.printFoundTasks(similarTasks);

                } else if (taskType.equals(possibleTasks.BYE.toString().toLowerCase())){
                    // BYE command
                    ui.printByeSequence();

                    // Clear the txt file and adds headers
                    storage.clearFileBeforeSaving();

                    // Saves the task list to the file, following the pre-defined format
                    for (int i = 0; i < tasks.getSize(); i++){
                        storage.writeToFile(tasks.getTask(i).toSaveString());
                    }

                    break;

                } else { // An invalid task command is given
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

    public static void main(String[] args) {
        new Duke("C:\\Users\\hatzi\\Documents\\Sourcetree\\duke\\data\\tasks.txt").run();
    }

    enum possibleTasks{
        BYE,
        LIST,
        DONE,
        DELETE,
        EVENT,
        TODO,
        DEADLINE,
        FIND
    }

}



