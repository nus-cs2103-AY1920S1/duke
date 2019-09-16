/**
 * A class that stores all of Duke's mesages as Strings. These are split into Greetings, Feedback and Error Messages.
 */
public class DukeUi {
    //Greeting Messages
    public static final String GREET_HELLO
        = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?";

    public static final String GREET_BYE
        = "Alright, see you again!\nGood luck, and do your best!";
    
    //User Feedback - Task Messages  
    public static final String FEEDBACK_EMPTY_LIST
        = "You've got no tasks on your list!";

    public static final String FEEDBACK_TASK_ADDED
        = "Gotcha! I've added a new task:\n  %s\nYou've got %d task(s) on your list.";

    public static final String FEEDBACK_TASK_DONE
        = "Good job! I've marked this task as done:\n  %s";

    public static final String FEEDBACK_TASK_DELETE
        = "No problem! I've deleted the task:\n %s\nYou've got %d task(s) on your list.";

    public static final String FEEDBACK_NUKE
        = "Boom! I've deleted all of your tasks!";

    public static final String FEEDBACK_FIND
        = "Scan complete! I've got your tasks right here: \n%s";

    public static final String FEEDBACK_FIND_NOTHING
        = "It's absolutely nothing!";

    //User Feedback - Note Messages
    public static final String FEEDBACK_NOTE_ADDED
        = "Gotcha! I've added a new note:\n %s\nYou've got %d note(s) on your list.";

    public static final String FEEDBACK_EMPTY_NOTE_LIST
        = "You've got no notes on your list!";

    public static final String FEEDBACK_NOTE_DELETE
        = "No problem! I've deleted the note:\n %s\nYou've got %d note(s) on your list.";

    public static final String FEEDBACK_NUKE_NOTE
        = "Boom! I've deleted all your notes!";
    
    //Error Messages
    public static final String ERROR_UNDECIPHERABLE_MESSAGE
        = "Hmm, yes. I totally get it!\n(Doesn't get it at all.)";
    
    public static final String ERROR_INCOMPLETE_COMMAND
        = "You're lacking something in your '%s' command, frendo.";
    
    public static final String ERROR_HOURS_OOB
        = "Umm, The hours don't go below 0, or above 23...";
    
    public static final String ERROR_MINUTES_OOB
        = "Umm, the minutes don't go below 0, or above 59...";
    
    public static final String ERROR_LIST_INDEX_SMALL
        = "I dunno if you write your lists starting from before 1,\nbut I sure don't.";
    
    public static final String ERROR_LIST_INDEX_BIG
        = "Oh! The fabled '%d' on the list!\n(It's fabled so it doesn't exist)...";
    
    public static final String ERROR_DAY_ZERO
        = "When I enter the mirror time dimension,\nI also count days starting from zero.";
    
    public static final String ERROR_DAY_BIG
        = "Wow, I never knew the month of %s had so many days!";
    
    public static final String ERROR_MONTH_ZERO
        = "When I enter the mirror time dimension,\nI also count months starting from zero.";
    
    public static final String ERROR_MONTH_BIG
        = "The months go November, December, Januember, Februember...";
    
    public static final String ERROR_NOT_NUMBER
        = "You need to give me a number to work with, not just '%s'.";
    
    public static final String ERROR_NO_DESCRIPTION
        = "Keep your secrets. I'll keep my list clean.";
}
