package duke;

public class SystemUi extends Ui {

    @Override
    protected void showError(String errorMessage) {
        System.out.println(prefix + " " + errorMessage);
    }

    @Override
    protected void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public void showLine() {
        System.out.println();
    }
}
