package duke.uitest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import duke.ui.Ui;

public class UiTest {

    @Test
    public void formatMessageCorrectly() {
        String formattedMessage = "    ____________________________________________________________\n" +
                "     Bye. Hope to see you again soon!\n" +
                "    ____________________________________________________________\n";
        Assertions.assertEquals(
                Ui.formatMessage("Bye. Hope to see you again soon!"),
                formattedMessage
        );
    }
}
