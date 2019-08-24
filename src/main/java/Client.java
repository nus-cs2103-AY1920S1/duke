public class Client {
    private static Client client = null;
    private Ui ui;
    private Parser parser;
    private TaskList taskList;
    private Storage storage;
    private ExceptionHandler exceptionHandler;


    private Client() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.exceptionHandler = new ExceptionHandler(this.ui);
    }

    static Client initialise() {
        client = new Client();
        return client;
    }

    boolean initialiseStorage(String pathName) {
        boolean shouldContinue = false;
        try {
            this.storage = new Storage(pathName, this.taskList);
            this.ui.greet();
            shouldContinue = true;
        } catch (JermiException e) {
            this.exceptionHandler.handleCheckedExceptions(e);
            this.ui.runFail();
        } catch (Exception e) {
            this.exceptionHandler.handleUncheckedExceptions(e);
            this.ui.runFail();
        }
        return shouldContinue;
    }

    void run(String pathName) {
        boolean shouldContinue = initialiseStorage(pathName);

        while (shouldContinue) {
            try {
                String inputCommand = this.ui.readCommand();
                String inputDetails = this.ui.readDetails();
                Command command = this.parser.parse(inputCommand, inputDetails);
                command.execute(this.taskList, this.ui, this.storage);
                shouldContinue = !command.shouldExit();
            } catch (JermiException e) {
                this.exceptionHandler.handleCheckedExceptions(e);
                shouldContinue = true;
            } catch (Exception e) {
                this.exceptionHandler.handleUncheckedExceptions(e);
                shouldContinue = false;
            }
        }

    }
}
