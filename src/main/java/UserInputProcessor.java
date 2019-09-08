/**
 * A static class that processes the user's inputs and returns Duke's reponses to the Duke main class.
 */
public class UserInputProcessor {
    /**
     * Takes in the user's input, identifies the type of command it is and calls upon the relevant "process" method in
     * order to generate a <code>DukeReply</code>.
     * 
     * @return A <code>DukeReply</code> to be processed by the Duke main class in its <code>run</code> method
     * @throws DukeException If the user's inputs are in the wrong format, or otherwise cannot be read by Duke
     */
    public static DukeReply processUserInput(String userInputString, 
                                             TaskList tasks,
                                             NoteList notes) throws DukeException {
        switch(identifyUserInputType(userInputString)){
        case Bye:
            return processByeCase();
            //Fallthrough
        case List:
            return processListCase(tasks);
            //Fallthrough
        case Done:
            return processDoneCase(userInputString, tasks);
            //Fallthrough
        case Delete:
            return processDeleteCase(userInputString, tasks);            
            //Fallthrough
        case Nuke:
            return processNukeCase(userInputString, tasks);
            //Fallthrough
        case Find:
            return processFindCase(userInputString, tasks);
            //Fallthrough
        case ToDo:
            return processToDoCase(userInputString, tasks);
            //Fallthrough
        case Deadline:
            return processDeadlineCase(userInputString, tasks);
            //Fallthrough
        case Event:
            return processEventCase(userInputString, tasks);
            //Fallthrough
        case NewNote:
            return processNoteCase(userInputString, notes);
            //Fallthrough
        case DeleteNote:
            return processDeleteNoteCase(userInputString, notes);
            //Fallthrough
        case NoteList:
            return processNoteListCase(userInputString, notes);
            //Fallthrough
        case NukeNote:
            return processNukeNoteCase(userInputString, notes);
            //Fallthrough
        case Invalid:
            throw new DukeException(DukeUi.ERROR_UNDECIPHERABLE_MESSAGE);
            //Fallthrough
        default:
            throw new DukeException(DukeUi.ERROR_UNDECIPHERABLE_MESSAGE);
            //Fallthrough
        }
    }

    //Exists to make processUserInput a lot neater, by identifying the type of command the User issued.
    private static userInputType identifyUserInputType(String userInputString) {
        if(userInputString.toLowerCase().startsWith("bye")) {
            return userInputType.Bye;
        } else if (userInputString.toLowerCase().startsWith("list")) {
            return userInputType.List;
        } else if (userInputString.toLowerCase().startsWith("done")) {
            return userInputType.Done;
        } else if (userInputString.toLowerCase().startsWith("deletenote")) {
            return userInputType.DeleteNote;
        } else if (userInputString.toLowerCase().startsWith("delete")) {
            return userInputType.Delete;
        } else if (userInputString.toLowerCase().startsWith("nukenote")) {
            return userInputType.NukeNote;
        } else if (userInputString.toLowerCase().startsWith("nuke")) {
            return userInputType.Nuke;
        } else if (userInputString.toLowerCase().startsWith("find")) {
            return userInputType.Find;
        } else if (userInputString.toLowerCase().startsWith("todo")) {
            return userInputType.ToDo;
        } else if (userInputString.toLowerCase().startsWith("deadline")) {
            return userInputType.Deadline;
        } else if (userInputString.toLowerCase().startsWith("event")) {
            return userInputType.Event;
        } else if (userInputString.toLowerCase().startsWith("notelist")) {
            return userInputType.NoteList;
        } else if(userInputString.toLowerCase().startsWith("note")) {
            return userInputType.NewNote;
        } else {
            return userInputType.Invalid;
        }
    }

    //Used to identify the type of command issued by the User
    private static enum userInputType {
        Bye, List, Done, Delete, Nuke, Find, ToDo, Deadline, Event, NewNote, DeleteNote, NoteList, NukeNote, Invalid
    };

    //Duke will shut down
    private static DukeReply processByeCase () {
        return new DukeReply(true, false, false, DukeUi.GREET_BYE);
    }

    //Duke will pull up the Tasklist
    private static DukeReply processListCase(TaskList tasks) {
        if (tasks.isEmpty()) {
            return new DukeReply(false, false, false, DukeUi.FEEDBACK_EMPTY_LIST);
        } else {
            return new DukeReply(false, false, false, tasks.toString());
        }
    }

    //Duke will try to mark a Task as done
    private static DukeReply processDoneCase(String userInputString, TaskList tasks) throws DukeException {
        String indexString = "";

        try{
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "done");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);
    
            Task newlyFinishedTask = tasks.markAsDone(userSpecifiedIndex);
            return new DukeReply(false, true, false, 
                String.format(DukeUi.FEEDBACK_TASK_DONE, newlyFinishedTask.toString(), tasks.size())); 
        } catch (NumberFormatException e) {
            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }
    }

    //Duke will try to delete one Task from the list
    private static DukeReply processDeleteCase(String userInputString, TaskList tasks) throws DukeException {
        String indexString = "";

        try{
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "delete");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);

            Task newlyDeletedTask = tasks.deleteAt(userSpecifiedIndex);
            return new DukeReply(false, true, false, 
                String.format(DukeUi.FEEDBACK_TASK_DELETE, newlyDeletedTask.toString(), tasks.size()));         
        } catch(NumberFormatException e) {
            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }
    }

    //Duke will delete all Tasks from the list
    private static DukeReply processNukeCase(String userInputString, TaskList tasks) {
        tasks.deleteAllTasks();
        
        return new DukeReply(false, true, false, DukeUi.FEEDBACK_NUKE);
    }

    //Duke will pull up all the Tasks that match the searchTerm
    private static DukeReply processFindCase(String userInputString, TaskList tasks) {
        String searchTerm = userInputString.substring(4).trim();
        String matchingTasksAsString = tasks.getMatchingTasksAsString(searchTerm);

        return new DukeReply(false, true, false, 
            String.format(DukeUi.FEEDBACK_FIND, matchingTasksAsString));
    }

    //Duke will create and add a new ToDoTask to the list
    private static DukeReply processToDoCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateToDoTask(userInputString);
        
        tasks.add(newlyAddedTask);
        
        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }

    //Duke will create and add a new DeadlineTask to the list
    private static DukeReply processDeadlineCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateDeadlineTask(userInputString);
        
        tasks.add(newlyAddedTask);

        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }

    //Duke will create and add a new EventTask to the list
    private static DukeReply processEventCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateEventTask(userInputString);
        
        tasks.add(newlyAddedTask);

        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }
    private static DukeReply processNoteCase(String userInputString, NoteList notes) {
        String newNote = userInputString.substring(4).trim();
        notes.add(newNote);

        return new DukeReply(false, false, true, 
            String.format(DukeUi.FEEDBACK_NOTE_ADDED, newNote, notes.size()));
    }

    private static DukeReply processDeleteNoteCase(String userInputString, NoteList notes) throws DukeException {
        String indexString = "";

        try{
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "delete note");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);

            String newlyDeletedNote = notes.deleteAt(userSpecifiedIndex);
            return new DukeReply(false, false, true,
                String.format(DukeUi.FEEDBACK_TASK_DELETE, newlyDeletedNote, notes.size()));         
        } catch(NumberFormatException e) {
            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }        
    }

    private static DukeReply processNoteListCase(String userInputString, NoteList notes) {
        if (notes.isEmpty()) {
            return new DukeReply(false, false, false, DukeUi.FEEDBACK_EMPTY_NOTE_LIST);
        } else {
            return new DukeReply(false, false, false, notes.toString());
        }       
    }

    private static DukeReply processNukeNoteCase(String userInputString, NoteList notes) {
        notes.deleteAllNotes();
        return new DukeReply(false, false, true, DukeUi.FEEDBACK_NUKE_NOTE);        
    }
    
    //Checks that the user input command included a index for the TaskList
    private static void checkCommandIncludesIndex(String [] splitString, String commandType) throws DukeException {
        if(splitString.length == 1) {
            throw new DukeException(
                String.format(DukeUi.ERROR_INCOMPLETE_COMMAND, commandType));
        }
    }
}
