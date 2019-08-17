public class DukeException extends Exception {
    static final DukeException UNKNOWN_COMMAND = new DukeException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");

    static final DukeException TODO_EMPTY_DESC = new DukeException(" ☹ OOPS!!! The description of a todo cannot be empty.");
    static final DukeException EVENT_EMPTY_DESC = new DukeException(" ☹ OOPS!!! The description of an event cannot be empty.");
    static final DukeException DEADLINE_EMPTY_DESC = new DukeException(" ☹ OOPS!!! The description of a deadline cannot be empty.");

    static final DukeException EVENT_EMPTY_ARG = new DukeException(" ☹ OOPS!!! The time of an event cannot be empty, e.g. event play tennis /at Monday");
    static final DukeException DEADLINE_EMPTY_ARG = new DukeException(" ☹ OOPS!!! The time of an deadline cannot be empty, e.g. deadline do homework /by August 9");

    static final DukeException TASK_NOT_EXIST = new DukeException(" ☹ OOPS!!! Task does not exist.");
    static final DukeException ARG_MUST_BE_NUM = new DukeException(" ☹ OOPS!!! Argument must be a number.");
    static final DukeException TASK_NOT_SPECIFIED = new DukeException(" ☹ OOPS!!! Which task have you done?");

    private String desc;

    private DukeException(String desc) {
        this.desc = desc;
    }

    String getDesc() {
        return desc;
    }
}
