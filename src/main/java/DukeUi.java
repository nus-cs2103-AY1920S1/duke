public class DukeUi {
    //Greeting Messages
    public final static String GREET_HELLO = "Heya! It's friendly neighbourhood Duke at your service!\nWhat's the plan today?";
    public final static String GREET_BYE = "Alright, see you again!\nGood luck, and do your best!";
    
    //User Feedback Messages  
    public final static String FEEDBACK_EMPTY_LIST = "You've got no tasks on your list!";
    public final static String FEEDBACK_TASK_ADDED = "Gotcha! I've added a new task:\n  %s\nYou've got %d tasks in your list.";
    public final static String FEEDBACK_TASK_DONE = "Good job! I've marked this task as done:\n  %s";
    public final static String FEEDBACK_TASK_DELETE = "No problem! I've deleted the task:\n %s\nYou've got %d tasks in your list.";
    public final static String FEEDBACK_NUKE = "Boom! I've deleted all of your tasks!";
    public final static String FEEDBACK_FIND = "Scan complete! I've got your tasks right here: \n%s";
    public final static String FEEDBACK_FIND_NOTHING = "It's absolutely nothing!";
    
    //Error Messages
    public final static String ERROR_UNDECIPHERABLE_MESSAGE = "Hmm, yes. I totally get it!\n(Doesn't get it at all.)";
    public final static String ERROR_INCOMPLETE_COMMAND = "You're lacking something in your '%s' command, frendo.";
    public final static String ERROR_HOURS_OOB = "Umm, The hours don't go below 0, or above 23...";
    public final static String ERROR_MINUTES_OOB = "Umm, the minutes don't go below 0, or above 59...";
    public final static String ERROR_LIST_INDEX_SMALL = "I dunno if you write your lists starting from before 1,\nbut I sure don't.";
    public final static String ERROR_LIST_INDEX_BIG = "Oh! The fabled '%d' on the list!\n(It's fabled so it doesn't exist)...";
    public final static String ERROR_DAY_ZERO = "When I enter the mirror time dimension,\nI also count days starting from zero.";
    public final static String ERROR_DAY_BIG = "Wow, I never knew the month of %s had so many days!";
    public final static String ERROR_MONTH_ZERO = "When I enter the mirror time dimension,\nI also count months starting from zero.";
    public final static String ERROR_MONTH_BIG = "The months go November, December, Januember, Februember...";
    public final static String ERROR_NOT_NUMBER = "You need to give me a number to work with, not just '%s'.";
    public final static String ERROR_NO_DESCRIPTION = "Keep your secrets. I'll keep my list clean.\n(pout)";
}
