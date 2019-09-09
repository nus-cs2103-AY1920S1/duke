package duke.ui;

public interface Ui {
    void showMessage(final String message);

    void showWarning(final String warning);

    void showError(final String error);

    void showWelcome();
}
