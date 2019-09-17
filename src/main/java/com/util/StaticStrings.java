package com.util;

public class StaticStrings {

    public static final String INDENT = "    ";
    public static final String BORDER = INDENT + "___________________________________";
    public static final String STORAGE_SEPARATOR = " | ";

    // Responses to commands

    public static final String GREETING = "Hello! I'm Duke\n    What can I do for you?";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static final String ADD_TASK = "Noted. I've added this task:";
    public static final String REMOVE_TASK = "Noted. I've removed this task:";
    public static final String DONE_TASK = "Nice! I've marked this task as done:";

    public static final String LIST_TASK = "Here are the tasks in your list:";
    public static final String MATCHING_TASK = "Here are the matching tasks in your list:";

    public static final String TASK_ALREADY_DONE = "This task has already been marked done!";

    // Exception messages for Storage

    public static final String ERROR_FILE_NOT_FOUND = "File not found at given file path. Error in creating new file there. :-(";
    public static final String ERROR_READ_FROM_FILE = "Woah! File not written in the right format. :-( ";
    public static final String ERROR_SAVE_FROM_FILE = "Whoops! There was an error in saving to the file.";

    // Exception messages when executing Commands

    public static final String NO_TASKS_DELETE = "You have no tasks to delete!";
    public static final String NO_TASKS_MATCH = "No matching tasks!";
    public static final String NO_TASKS_DONE = "You have no tasks to mark done!";
    public static final String NO_TASKS = "You have no tasks yet";
    public static final String NOT_IN_RANGE = "Please give a number from 1 to ";
    public static final String NO_SUCH_COMMAND = "No such command with subcommand!";

    // Exception messages for Storage

    public static final String NO_DONE_IDX_PROVIDED = "Please provide index of task in list to be marked done.";
    public static final String TOO_MANY_DONE_INPUT = "Please just put the index of task in list after \"done\" and nothing else.";
    public static final String NON_INT_PROVIDED_DONE = "Please enter an integer after \"done\".";

    public static final String NO_DELETE_IDX_PROVIDED = "Please provide index of task in list to be deleted.";
    public static final String TOO_MANY_DELETE_INPUT = "Please just put the index of task in list after \"delete\" and nothing else.";
    public static final String NON_INT_PROVIDED_DELETE = "Please enter an integer after \"delete\".";

    // Messages for Statistics (Storage)

    public static final String ERROR_LOG_NOT_FOUND = "Error in reading in execution log file from given path, creating new file.";
    public static final String ERROR_SAVE_LOG = "Error in saving this execution to log, this will be missing.";

    // Message for Statistics (Execution)

    public static final String STATS_NUM_DONE = "No. of tasks marked done so far: ";
    public static final String STATS_LIST_DONE = "List of tasks done so far: ";
    public static final String STATS_NUM_DONE_PASTWEEK = "No. of tasks marked done in the past 7 days: ";
    public static final String STATS_NUM_DONE_TODO = "No. of ToDo tasks marked done so far: ";
    public static final String STATS_NUM_DONE_DEADLINE = "No. of Deadline tasks marked done so far: ";
    public static final String STATS_NUM_DONE_EVENT = "No. of Event tasks marked done so far: ";

    public static final String STATS_NUM_DELETED = "No. of tasks deleted so far: ";
    public static final String STATS_LIST_TASKS_DELETED = "List of tasks deleted so far: ";
    public static final String STATS_NUM_UNDONE_TASKS_DELETED = "No. of tasks deleted that was not done: ";

    public static final String STATS_SEARCHED_KEYWORDS = "List of words searched so far: ";
    public static final String STATS_SEARCHED_KEYWORDS_PASTWEKK = "List of words searched in the past 7 days: ";

}
