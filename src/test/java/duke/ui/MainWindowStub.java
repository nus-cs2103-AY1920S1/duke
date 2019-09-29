package duke.ui;

//Custom MainWindow stub class for comparing output messages where MainWindow is used.
public class MainWindowStub extends MainWindow {
    private StringBuilder messageShown;

    public MainWindowStub() {
        this.messageShown = new StringBuilder();
    }

    @Override
    public void showMessage(String message) {
        messageShown.append(message);
    }

    public String getMessages() {
        return messageShown.toString();
    }
}
