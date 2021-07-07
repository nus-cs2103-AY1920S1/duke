package duke.command;

/**
 * Container for user visible messages.
 */
class Messages {

    static final String MSG_MISSING_TASK = "> < Oh! Did you forget to add the task?";
    static final String MSG_MISSING_DEADLINE = "> < OOPS!! Did you forget to add the deadline?";
    static final String MSG_MISSING_EVENT_SPAN = "> < OOPS!! The span of event seems incomplete.\nPlease enter"
            + "in the format of /from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm.";
    static final String MSG_MISSING_KEYWORD = "> < Sorry, I did not catch your search keyword.";
    static final String MSG_MISSING_INDEX = "Oh! Did you forget to add the task index?";

    static final String MSG_EMPTY_LIST = "> < Oops! The list is empty.";
    static final String MSG_TASK_INDEX_EXCEEDED = "> < Oops! The list contains only %d task(s).";
    static final String MSG_NON_POSITIVE_INDEX = "> < Oops! We don't seem to have non-positive task index.";
    static final String MSG_ILLEGAL_ENTRY = "> < Oops! Nezuko cannot recognise that task index.";
    static final String MSG_ILLEGAL_COMMAND = "> < Sorry, Nezuko doesn't know what that means.";

}
