
public class Duke {
    private static Ui uiManager = new Ui();

    public void run(){
        uiManager.takeUserCommand();
    }

    public static void main(String[] args)  {
        Duke duke = new Duke();
        duke.run();

    }

}
