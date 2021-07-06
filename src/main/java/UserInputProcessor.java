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
        switch (identifyUserInputType(userInputString)) {
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
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            throw new DukeException(DukeUi.ERROR_UNDECIPHERABLE_MESSAGE);
            //Fallthrough
        default:
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            throw new DukeException(DukeUi.ERROR_UNDECIPHERABLE_MESSAGE);
            //Fallthrough
        }
    }

    /**
     * Returns as an enum the type of command issued by the user.
     * 
     * @param userInputString The user's input command
     * @return The type of command issued by the user
     */
    private static UserInputType identifyUserInputType(String userInputString) {
        if (userInputString.toLowerCase().startsWith("bye")) {
            return UserInputType.Bye;
        } else if (userInputString.toLowerCase().startsWith("list")) {
            return UserInputType.List;
        } else if (userInputString.toLowerCase().startsWith("done")) {
            return UserInputType.Done;
        } else if (userInputString.toLowerCase().startsWith("deletenote")) {
            return UserInputType.DeleteNote;
        } else if (userInputString.toLowerCase().startsWith("delete")) {
            return UserInputType.Delete;
        } else if (userInputString.toLowerCase().startsWith("nukenote")) {
            return UserInputType.NukeNote;
        } else if (userInputString.toLowerCase().startsWith("nuke")) {
            return UserInputType.Nuke;
        } else if (userInputString.toLowerCase().startsWith("find")) {
            return UserInputType.Find;
        } else if (userInputString.toLowerCase().startsWith("todo")) {
            return UserInputType.ToDo;
        } else if (userInputString.toLowerCase().startsWith("deadline")) {
            return UserInputType.Deadline;
        } else if (userInputString.toLowerCase().startsWith("event")) {
            return UserInputType.Event;
        } else if (userInputString.toLowerCase().startsWith("notelist")) {
            return UserInputType.NoteList;
        } else if (userInputString.toLowerCase().startsWith("note")) {
            return UserInputType.NewNote;
        } else {
            return UserInputType.Invalid;
        }
    }

    //Used to identify the type of command issued by the User
    private static enum UserInputType {
        Bye, List, Done, Delete, Nuke, Find, ToDo, Deadline, Event, NewNote, DeleteNote, NoteList, NukeNote, Invalid
    }

    /**
     * Causes the program to shutdown. 
     * 
     * @return A <code>DukeReply</code> containing Duke's response and instructions to shutdown the program
     */
    private static DukeReply processByeCase() {
        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);
        return new DukeReply(true, false, false, DukeUi.GREET_BYE);
    }

    /**
     * Displays the entire <code>TaskList</code>.
     * 
     * @param tasks The <code>TaskList</code> to be displayed
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processListCase(TaskList tasks) {
        if (tasks.isEmpty()) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            return new DukeReply(false, false, false, DukeUi.FEEDBACK_EMPTY_LIST);
        } else {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            return new DukeReply(false, false, false, tasks.toString());
        }
    }

    /**
     * Marks a <code>Task</code> as done at the specified index.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be deleted from
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException If the user's input lacks an index,
     *     or if the index does not exist in the <code>TaskList</code>
     */
    private static DukeReply processDoneCase(String userInputString, TaskList tasks) throws DukeException {
        String indexString = "";

        try {
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "done");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);
    
            Task newlyFinishedTask = tasks.markAsDone(userSpecifiedIndex);

            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

            return new DukeReply(false, true, false, 
                String.format(DukeUi.FEEDBACK_TASK_DONE, newlyFinishedTask.toString(), tasks.size())); 
        } catch (NumberFormatException e) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Sweat);
        
            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }
    }

    /**
     * Deletes a <code>Task</code> from the <code>TaskList</code> at the specified index.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be deleted from
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException If the user's input lacks an index,
     *     or if the index does not exist in the <code>TaskList</code>
     */
    private static DukeReply processDeleteCase(String userInputString, TaskList tasks) throws DukeException {
        String indexString = "";

        try {
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "delete");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);

            Task newlyDeletedTask = tasks.deleteAt(userSpecifiedIndex);

            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

            return new DukeReply(false, true, false, 
                String.format(DukeUi.FEEDBACK_TASK_DELETE, newlyDeletedTask.toString(), tasks.size()));         
        } catch (NumberFormatException e) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Sweat);

            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }
    }

    /**
     * Deletes all <code>Task</code>s from the <code>TaskList</code>.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be deleted from
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processNukeCase(String userInputString, TaskList tasks) {
        tasks.deleteAllTasks();
        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
        return new DukeReply(false, true, false, DukeUi.FEEDBACK_NUKE);
    }

    /**
     * Searches for all the <code>Tasks</code> containing the specified searchTerm.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be searched through
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processFindCase(String userInputString, TaskList tasks) {
        String searchTerm = userInputString.substring(4).trim();
        String matchingTasksAsString = tasks.getMatchingTasksAsString(searchTerm);

        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);

        return new DukeReply(false, true, false, 
            String.format(DukeUi.FEEDBACK_FIND, matchingTasksAsString));
    }

    /**
     * Creates and adds a new <code>ToDoTask</code> to the <code>TaskList</code>.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be added to
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException If the user's command lacks a description
     */
    private static DukeReply processToDoCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateToDoTask(userInputString);
        
        tasks.add(newlyAddedTask);

        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);
        
        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }

    /**
     * Creates and adds a new <code>DeadlineTask</code> to the <code>TaskList</code>.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be added to
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException If the user's command is incomplete, lacks a description or contains an invalid date
     */
    private static DukeReply processDeadlineCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateDeadlineTask(userInputString);
        
        tasks.add(newlyAddedTask);

        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }

    /**
     * Creates and adds a new <code>EventTask</code> to the <code>TaskList</code>.
     * 
     * @param userInputString The user's input command
     * @param tasks The <code>TaskList</code> to be added to
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException When the user's input is incomplete, lacks a description or includes an invalid date
     */
    private static DukeReply processEventCase(String userInputString, TaskList tasks) throws DukeException {
        Task newlyAddedTask = TextToTaskTranslator.translateEventTask(userInputString);
        
        tasks.add(newlyAddedTask);

        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

        return new DukeReply(false, true, false,
            String.format(DukeUi.FEEDBACK_TASK_ADDED, newlyAddedTask.toString(), tasks.size()));
    }

    /**
     * Creates and adds a new note to the <code>NoteList</code>.
     * 
     * @param userInputString The user's input command
     * @param notes The <code>NoteList</code> to be added to
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processNoteCase(String userInputString, NoteList notes) {
        String newNote = userInputString.substring(4).trim();
        notes.add(newNote);

        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

        return new DukeReply(false, false, true, 
            String.format(DukeUi.FEEDBACK_NOTE_ADDED, newNote, notes.size()));
    }

    /**
     * Deletes a note from the <code>NoteList</code>.
     * 
     * @param userInputString The user's input command
     * @param notes The <code>NoteList</code> to be deleted from
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     * @throws DukeException If the chosen index does not exist in the <code>NoteList</code>
     */
    private static DukeReply processDeleteNoteCase(String userInputString, NoteList notes) throws DukeException {
        String indexString = "";

        try {
            String [] splitString = userInputString.split(" ");

            checkCommandIncludesIndex(splitString, "delete note");
            indexString = splitString[1];
            int userSpecifiedIndex = Integer.parseInt(splitString[1]);

            String newlyDeletedNote = notes.deleteAt(userSpecifiedIndex);

            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);

            return new DukeReply(false, false, true,
                String.format(DukeUi.FEEDBACK_NOTE_DELETE, newlyDeletedNote, notes.size()));         
        } catch (NumberFormatException e) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Sweat);
            throw new DukeException(
                String.format(DukeUi.ERROR_NOT_NUMBER, indexString));
        }        
    }

    /**
     * Displays the entire <code>NoteList</code>.
     * 
     * @param userInputString The user's input command
     * @param notes The <code>NoteList</code> to be displayed
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processNoteListCase(String userInputString, NoteList notes) {
        if (notes.isEmpty()) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            return new DukeReply(false, false, false, DukeUi.FEEDBACK_EMPTY_NOTE_LIST);
        } else {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Smile);
            return new DukeReply(false, false, false, notes.toString());
        }       
    }

    /**
     * Deletes all notes from the <code>NoteList</code>.
     * 
     * @param userInputString The user's input command
     * @param notes The <code>NoteList</code> to be deleted from.
     * @return A <code>DukeReply</code> containing Duke's response and instructions for changing state
     */
    private static DukeReply processNukeNoteCase(String userInputString, NoteList notes) {
        notes.deleteAllNotes();
        GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Wink);
        return new DukeReply(false, false, true, DukeUi.FEEDBACK_NUKE_NOTE);        
    }
    
    /**
     * Checks if the user command includes an index and throws an exception is not. 
     * 
     * @param splitString The <code>String</code> array
     * @param commandType The type of user command causing this check
     * @throws DukeException If the command does not include an index
     */
    private static void checkCommandIncludesIndex(String [] splitString, String commandType) throws DukeException {
        if (splitString.length == 1) {
            GlobalDukeImageChoiceBuffer.setDukeImageChoice(DukeImageChoice.Sweat);
            throw new DukeException(
                String.format(DukeUi.ERROR_INCOMPLETE_COMMAND, commandType));
        }
    }
}
