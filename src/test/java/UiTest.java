import duke.ui.Ui;

//@@author CarbonGrid
public class UiTest extends Ui {
    private String output;

    @Override
    public void show(String str) {
        if (!str.endsWith("\n")) {
            str += '\n';
        }
        output = str;
        super.show(str);
    }

    @Override
    public String toString() {
        String buf = output;
        output = "";
        return buf;
    }
}
//@@author