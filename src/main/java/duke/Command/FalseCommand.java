package duke.Command;

import duke.Ui;

public class FalseCommand {
    public FalseCommand(){

    }

    public String False(Ui ui){
        String output = ui.print_false();
        return output;
    }

}
