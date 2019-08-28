package seedu.duke.ui;

import seedu.duke.DukeException;

public class Ui {

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
}
