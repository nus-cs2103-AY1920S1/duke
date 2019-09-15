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


}
