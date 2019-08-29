import duke.Ui;

public class TestUi extends Ui {
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
