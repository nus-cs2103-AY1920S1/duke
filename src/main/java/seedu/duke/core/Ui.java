package seedu.duke.core;

import javafx.scene.control.Label;
import seedu.duke.model.dto.Task;
import seedu.duke.model.dto.Todo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Ui {
    private Properties prop;
    private static String LOGO =
                      " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";

    private static String GREETINGMSG =
            "________________________________________________________________________________________________ \n"
            + "Hello! I'm Duke\n"
            + "What can I  do for you?\n"
            + "________________________________________________________________________________________________";

    /**
     * Constructor for Ui which handles all user interface and loads properties by default.
     */
    public Ui() {
        prop = new Properties();
        try {
            InputStream inputStream = new FileInputStream("data/duke.properties");
            prop.load(inputStream);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public Label showWelcome() {
        return new Label(GREETINGMSG);
    }

    public void printLine() {
        System.out.println("____________________________________________________________");
    }

    public Label printByeMessage() {
        return new Label(prop.getProperty("msg.bye"));
    }


    /**
     * Prints all the tasks inside task list.
     * @param list Task list (ArrayList) where all tasks are stored.
     */
    public String displayList(String output, List<Task> list) {
        if (list.isEmpty()) {
            output += prop.getProperty("cmd.list.empty");
        } else {
            output += prop.getProperty("cmd.list");
            output += IntStream.rangeClosed(1, list.size())
                    .mapToObj(index -> (String)(index + "." + list.get(index - 1)))
                    .collect(Collectors.joining("\n"));
        }
        return output;
    }

    /**
     * Prints only the specified task from task list.
     * @param list Task list (ArrayList) where all tasks are stored
     * @param index index of which task user want to print out.
     */
    public String displayTask(String output, List<Task> list, int index) {
        boolean isValidIndex = index >= 0
                && index + 1 <= list.size();
        if (isValidIndex) {
            output = new StringBuilder(output)
                    .append(list.get(index) + "\n").toString();
        }
        return output;
    }
}
