import java.util.Scanner;
import java.util.ArrayList;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class Duke {
    private static String helloMessage = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?";
    private static String goodbyeMessage = "Alright, see you again!\nGood luck, and do your best!";
    private static String taskAddedMessage = "Gotcha! I've added a new task:\n  %s\nYou've got %d tasks in your list.";
    private static String taskDoneMessage = "Good job! I've marked this task as done:\n  %s";
    private static String taskDeletedMessage = "No problem! I've deleted the task:\n %s\nYou've got %d tasks in your list.";

    //private TaskList tasks = new TaskList();
    private TaskList tasks;
    private DukeSaveLoad dukeSaveLoad = new DukeSaveLoad();
    
    //Constructor for Duke
    public Duke() {
        //If a pre-existing list exists, creates list data from it. Otherwise, an empty TaskList is initialised. 
        tasks = dukeSaveLoad.createTaskListByReadingFromSaveFile();
    }

    //Main method. Contains the interaction loop between Duke and the User.
    public static void main(String[] args) throws UnsupportedEncodingException {
        Duke duke = new Duke();
        Scanner sc = new Scanner(System.in);

        PrintStream printStream = new PrintStream(System.out, true, "UTF-8");
        printStream.print(duke.sayHi());

        while (true) { //Interaction loop with user. This will persist until shutdown.
            String userInput = sc.nextLine();
            
            try {
                DukeReply dukeReply = duke.processUserInput(userInput);
            
                printStream.print(dukeReply.dukeReplyString);
    
                if(dukeReply.shouldExitLoop) {
                    break;
                }
            } catch (DukeException e) {
                printStream.print(e.getMessage());
            }
        }

        sc.close();
    }

    //returns Duke's helloMessage
    public String sayHi() {
        return DukeTextFormatter.makeFormattedText(helloMessage);
    }

    //returns a DukeReply to be printed out, or a throws an error if input cannot be parsed by Duke
    public DukeReply processUserInput(String userInputString) throws DukeException {
        switch(identifyUserInputType(userInputString)){
        case Bye: //Duke will close down
            return new DukeReply(true, DukeTextFormatter.makeFormattedText(goodbyeMessage));
            //Fallthrough
        case List: //Duke will return the full list of Tasks
            if (tasks.isEmpty()) {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText("You've got no tasks on your list!"));
            } else {
                return new DukeReply(false, DukeTextFormatter.makeFormattedText(this.tasks.toString()));
            }
            //Fallthrough
        case Done: //Duke will mark one Task for completion
            DukeReply doneReply = processDoneCase(userInputString);
            dukeSaveLoad.writeTaskListToSaveFile(tasks); //Update the Save File
            return doneReply;
            //Fallthrough
        case Delete:
            DukeReply deleteReply = processDeleteCase(userInputString);
            dukeSaveLoad.writeTaskListToSaveFile(tasks); //Update the Save File
            return deleteReply;            
            //Fallthrough
        case ToDo: //Duke will record a Todo Task
            DukeReply ToDoReply = processTodoCase(userInputString);
            dukeSaveLoad.writeTaskListToSaveFile(tasks); //Update the Save File
            return ToDoReply;
            //Fallthrough
        case Deadline: //Duke will record a Deadline Task
            DukeReply deadlineReply = processDeadlineCase(userInputString);
            dukeSaveLoad.writeTaskListToSaveFile(tasks); //Update the Save File
            return deadlineReply;
            //Fallthrough
        case Event: //Duke will record an Event Task
            DukeReply eventReply = processEventCase(userInputString);
            dukeSaveLoad.writeTaskListToSaveFile(tasks); //Update the Save File
            return eventReply;
            //Fallthrough
        case Invalid: //Something went wrong
            throw new InvalidInputException("");
            //Fallthrough
        default: //Something went wrong
            throw new InvalidInputException("");
            //Fallthrough
        }
    }

    //Exists to make processUserInput a lot neater, by identifying the type of command the User issued.
    private userInputType identifyUserInputType(String userInputString) {
        if(userInputString.toLowerCase().startsWith("bye")) {
            return userInputType.Bye;
        } else if (userInputString.toLowerCase().startsWith("list")) {
            return userInputType.List;
        } else if (userInputString.toLowerCase().startsWith("done")) {
            return userInputType.Done;
        } else if (userInputString.toLowerCase().startsWith("delete")) {
            return userInputType.Delete;
        } else if (userInputString.toLowerCase().startsWith("todo")) {
            return userInputType.ToDo;
        } else if (userInputString.toLowerCase().startsWith("deadline")) {
            return userInputType.Deadline;
        } else if (userInputString.toLowerCase().startsWith("event")) {
            return userInputType.Event;
        } else {
            return userInputType.Invalid;
        }
    }

    //Processes the Done case. Returns a Reply stating that this has been successful, and throws an Exception otherwise.
    private DukeReply processDoneCase(String userInputString) throws IndexNotInRangeException, NoTaskDescriptionException, IndexNotANumberException {
        String [] splitString = userInputString.split(" ");
        boolean indexExists = splitString.length > 1;
        
        try {
            if(indexExists) {
                int userSpecifiedIndex = Integer.parseInt(splitString[1]);
                boolean indexIsInRange = userSpecifiedIndex > 0 && userSpecifiedIndex <= tasks.size();

                if(indexIsInRange) {
                    Task newlyFinishedTask = tasks.markAsDone(userSpecifiedIndex);
                    return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskDoneMessage, newlyFinishedTask.toString(), tasks.size()))); 
                } else {
                    throw new IndexNotInRangeException(splitString[1]);
                }
            } else {
                throw new NoTaskDescriptionException(splitString[0]);
            }
        } catch (NumberFormatException e) {
            throw new IndexNotANumberException(splitString[1]);
        }
    }

    //Processes the Delete case. Returns a Reply stating that this has been successful, and throws an Exception otherwise.
    private DukeReply processDeleteCase(String userInputString) throws IndexNotInRangeException, NoTaskDescriptionException, IndexNotANumberException {
        String [] splitString = userInputString.split(" ");
        boolean indexExists = splitString.length > 1;
        
        try {
            if(indexExists) {
                int userSpecifiedIndex = Integer.parseInt(splitString[1]);
                boolean indexIsInRange = userSpecifiedIndex > 0 && userSpecifiedIndex <= tasks.size();

                if(indexIsInRange) {
                    Task newlyDeletedTask = tasks.deleteAt(userSpecifiedIndex);
                    return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskDoneMessage, newlyDeletedTask.toString(), tasks.size()))); 
                } else {
                    throw new IndexNotInRangeException(splitString[1]);
                }
            } else {
                throw new NoTaskDescriptionException(splitString[0]);
            }
        } catch (NumberFormatException e) {
            throw new IndexNotANumberException(splitString[1]);
        }
    }

    //Processes the Todo case. Returns a Reply stating that this has been successful, and throws an Exception otherwise.
    private DukeReply processTodoCase(String userInputString) throws NoTaskDescriptionException {
        //Retrieve relevant data from user input
        String taskDescription = userInputString.substring(4).trim();
        boolean taskExists = taskDescription.length() > 0;

        if(taskExists) {
            //Intialise and record the new Task
            Task newTask = new ToDoTask(taskDescription);
            tasks.add(newTask);

            return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), tasks.size())));
        } else {
            throw new NoTaskDescriptionException(userInputString.trim());
        }
    }

    //Processes the Deadline case. Returns a Reply stating that this has been successful, and throws an Exception otherwise.
    private DukeReply processDeadlineCase(String userInputString) throws IncompleteInputException {
        //Retrieve relevant data from user input
        //Input is considered incomplete when there is no '/by'
        String [] splitString = userInputString.split("/by");
        boolean inputIsComplete = (splitString.length == 2);

        if(inputIsComplete) {
            String taskDescription = splitString[0].substring(8).trim();
            String doByString = splitString[1].trim();

            //Initialise and record the new Task
            Task newTask = new DeadlineTask(taskDescription, doByString);
            tasks.add(newTask);
    
            return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), tasks.size())));
        } else {
            throw new IncompleteInputException("deadline");
        }
    }

    //Processes the Event case. Returns a Reply stating that this has been successful, and throws an Exception otherwise.
    private DukeReply processEventCase(String userInputString) throws IncompleteInputException {
        //Retrieve relevant data from user input
        //Input is considered incomplete when there is no '/at'
        String [] splitString = userInputString.split("/at");
        boolean inputIsComplete = (splitString.length == 2);

        if(inputIsComplete) {
            String taskDescription = splitString[0].substring(5).trim();
            String timingString = splitString[1].trim();

            //Initialise and record the new Task
            Task newTask = new EventTask(taskDescription, timingString);
            tasks.add(newTask);
    
            return new DukeReply(false, DukeTextFormatter.makeFormattedText(String.format(taskAddedMessage, newTask.toString(), tasks.size())));
        } else {
            throw new IncompleteInputException("event");
        }
    }

    //Used to identify the type of command issued by the User
    private enum userInputType {
        Bye, List, Done, Delete, ToDo, Deadline, Event, Invalid
    };
}
