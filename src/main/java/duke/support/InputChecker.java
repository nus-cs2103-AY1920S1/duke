package duke.support;

/**
 * A supporting class for CommandGenerator. It checks the validity of user's input.
 */
public class InputChecker {
    /**
     * Returns true if the topic is not empty.
     *
     * @param topic the topic of the to do.
     * @return true if the topic is not empty.
     */
    public boolean isValidToDo(String topic) {
        return "".equals(topic);
    }

    /**
     * Returns true if all details of a Deadline task is given.
     *
     * @param details details of a Deadline task.
     * @return true if all details of a Deadline task is given.
     */
    public boolean isValidDeadline(String[] details) {
        return isValidInput(details);
    }

    /**
     * Returns true if all details of an Event task is given.
     *
     * @param details details of a Event task.
     * @return true if all details of an Event task is given.
     */
    public boolean isValidEvent(String[] details) {
        return isValidInput(details);
    }

    /**
     * Returns true if all details of a Deadline or Event task is given.
     *
     * @param details details of a Deadline or Event task.
     * @return true if all details of a Deadline or Event task is given.
     */
    private boolean isValidInput(String[] details) {
        boolean isNotEmptyTopic = !(details.length == 0 || "".equals(details[0].trim()));
        boolean isNotEmptyDate = !(details.length <= 1 || "".equals(details[1].trim()));

        return isNotEmptyTopic && isNotEmptyDate;
    }
}
