package seedu.duke.ui;

import seedu.duke.DukeException;
import seedu.duke.tasklist.TaskList;

public class Ui {
    private final String underscore = "    ____________________________________________________________" + "\n" ;

    public Ui(){

    }

    public void showLoadingError(DukeException e){
        System.out.println(e.getMessage());
    }

    public void showWelcome(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String underscore = "    ____________________________________________________________" + "\n" ;
        String intro = underscore +
                "      Hello! I'm seedu.duke.Duke " + "\n" +
                "      What can I do for you?" + "\n" +
                underscore ;
        System.out.println(logo + intro);
    }

    public void printList(TaskList tasks){
        String output = underscore + "     Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.getSize(); i++){
            output += "     " + (i + 1) + "." + tasks.getTask(i).toString() + "\n";
        }
        output += underscore;
        System.out.println(output);
    }


}
