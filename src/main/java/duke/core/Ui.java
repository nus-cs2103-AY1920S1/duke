package duke.core;

/**
 * Encapsulates a ui object to deal with interactions with the user.
 */

class Ui {

    /**
     * Returns a greeting message from duke.
     * @return String of greeting message.
     */
    String greet() {
        return "Hello! I'm Duke \nWhat can I do for you?";
    }

    /**
     * Returns the bye-bye message when the user enters "bye" command.
     * @return String of bye-bye message.
     */
    String sayBye() {
        return "Bye. Hope to see you again soon!";
    }

}